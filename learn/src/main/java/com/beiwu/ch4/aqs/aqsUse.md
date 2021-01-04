# AQS使用
AQS(AbstractQueuedSynchronizer 同步器),是用来构建锁或者其他同步组件的基础框架，它使用了一个int成员变量表示同步状态，通过内置的FIFO队列来完成资源获取线程的排队工作。


锁是面向使用者的，它定义了使用者与锁交互的接口（比如可以允许两个线程并行访问），隐藏了实现细节；
同步器是面向锁的实现者，它简化了锁得到实现方式，屏蔽了同步状态管理、线程的排队、等待与唤醒等底层操作。
