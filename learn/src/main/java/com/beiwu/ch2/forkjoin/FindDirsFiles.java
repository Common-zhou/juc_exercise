package com.beiwu.ch2.forkjoin;

import com.beiwu.ch1.tools.SleepTools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * @author zhoubing
 * @date 2020-12-24 23:39
 * 需求: 递归找到所有文件 输出以txt结尾的文件名
 */
public class FindDirsFiles {
    private static class FindFilesRecursive extends RecursiveAction {
        private final String path;

        public FindFilesRecursive(String path) {
            this.path = path;
        }

        @Override
        protected void compute() {
            File file = new File(path);

            List<FindFilesRecursive> tasks = new ArrayList<>();

            if (file.isFile()) {
                if (file.getAbsolutePath().endsWith("txt")) {
                    System.out.println("文件:" + file.getAbsolutePath());
                }
            } else {
                // 如果是文件夹
                File[] files = file.listFiles();
                if (files != null){
                    for (File sonFile : files) {
                        FindFilesRecursive sonFind = new FindFilesRecursive(sonFile.getAbsolutePath());
                        tasks.add(sonFind);
                        sonFind.invoke();
//                    sonFind.join();
                    }

                    if (!tasks.isEmpty()){
//                        for (FindFilesRecursive task : invokeAll(tasks)) {
//                            task.join();
//                        }
                        for (FindFilesRecursive task : tasks) {
                            task.join();
                        }
                    }
                }
            }


        }
    }

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        FindFilesRecursive task = new FindFilesRecursive("D:");

        pool.execute(task);

        System.out.println("Task is Running......");
        SleepTools.ms(1);
        int otherWork = 0;
        for (int i = 0; i < 100; i++) {
            otherWork += i;
        }
        System.out.println("Main Thread done sth......, otherWork=" + otherWork);

        // 这里是一个阻塞方法  只有拿到结果了 才会往下走
        task.join();

        System.out.println("Task end");
    }
}
