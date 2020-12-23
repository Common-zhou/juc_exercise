# 1.多线程1
## 1.1创建线程的两种方式
```text
继承Thread 
实现Runnable(Callable)
```

## 1.2start和run的区别
重写的是run方法
启动的是start方法

start是将线程标记为可执行状态，等待cpu时间片，如果有时间片，会开始执行。
如果是调用run方法，则会直接当成一个普通方法使用。

## 1.3sleep不会释放锁

## 1.4关闭线程 不要使用stop等方法 过于粗暴。
