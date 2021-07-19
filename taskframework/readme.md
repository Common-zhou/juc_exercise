# 并发任务执行框架

这是并发执行框架的总结文档

## 文档目的

介绍该framework以及介绍它的设计思路。方便后续类似设计的总结和再使用。

## 代码位置

项目的代码在framework里。

## 框架功能

首先介绍功能，使用该包可以实现对多线程并不了解的情况下，编写代码使用多线程完成工作。

## 细节

## 1.简单介绍

```
将任务(Job) 分为若干个Task，即Job里可以包含多个Task，Task即为我们需要实现的最小子任务。
比如有一个试卷任务，那么我们 试卷任务就是Job。处理多份试卷即为多个Task。

JobInfo即为Job的信息，里面包含：
    需要处理的Task长度
    成功的、已经处理的个数
    需要执行的任务动作(即接口、需要完成的功能)，也即后面的接口，ITaskProcessor
    Task的执行结果集合。(其实结果可以存放于此，这样方便后续对任务进度的检查)

接下来介绍接口ITaskProcessor<T,R>
    它是你需要完成任务的接口，使用泛型，T代表输入参数、R代表输出参数。只有一个方法，即Task的执行任务流程。
    返回值是TaskResult。因为任务可能成功 可能失败 可能异常。所以使用该类来完成这些标识工作。
        Type标识任务的状态，data存储的是执行的结果，message存储的是一些附加信息。
        TaskResultType是任务状态的枚举类。包含成功、失败、异常。

```


## 2.**最重要的一个类**是`PendingJobPool`

该类提供了`Job的注册`、 `JobInfo的获取`功能。

该类内部有一个内部类，`PendingTask`,里面封装了JobInfo和入参。

```Java
//JobInfo中有任务执行的接口，又有Task入参
//所以可以执行Task 获取到结果，最后通过JobInfo将执行结果放回去
private static class PendingTask<T, R> implements Runnable {

        private JobInfo<R> jobInfo;
        private T processData;

        public PendingTask(JobInfo<R> jobInfo, T processData) {
            this.jobInfo = jobInfo;
            this.processData = processData;
        }

        @Override
        public void run() {
            ITaskProcessor<T, R> iTaskProcessor = (ITaskProcessor<T, R>) jobInfo.getiTaskProcessor();
            TaskResult<R> taskResult = null;
            R data = null;
            try {
                taskResult = iTaskProcessor.execute(processData);

                // 这代表 里面的操作根本没有向 TaskResult赋值
                if (taskResult == null) {
                    // 代表错误 没有返回给我Result  即认为任务执行失败
                    taskResult = new TaskResult<>(TaskResultType.Failure, data, "taskResult is null.Please check process.");
                }
                // 这代表没有设置状态 那我认为它是执行失败的
                if (taskResult.getResultType() == null) {
                    if (taskResult.getMessage() != null) {
                        taskResult = new TaskResult<>(TaskResultType.Failure, data, taskResult.getMessage());
                    } else {
                        taskResult = new TaskResult<>(TaskResultType.Failure, data, "taskResult restType is null,and message is null.");
                    }
                }

            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println(String.format("ERROR with message: %s", e.getMessage()));
                taskResult = new TaskResult<>(TaskResultType.Exception, data, e.getMessage());
            }
            jobInfo.addTaskResult(taskResult);
        }
    }
```


需要注意的是，让`PendingJobPool`单例，是为了不让外部new多余的任务执行框架。

## 3.任务的过期清除

当任务结果过期时(比如30min)，将任务结果从Map中清除。

采用的方法是，使用一个延时队列，当addTaskResult(TaskResult taskResult)时

检查长度，是否已经到达需要处理的长度(jobLength)。如果到了，则将该jobName加入延时队列，并设置过期时间。

启动一个线程，专门用于从延时队列中获取数据，如果获取到了，则说明需要将该job从内存中清除。直接将map中的键删掉即可。


