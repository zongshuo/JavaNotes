NIO：同步非阻塞IO（面向缓冲的java.nio包的Channel、Selector、Buffer等），多个进程的io可以注册到一个复用器（select）上
    然后用一个进程调用该select，select会监听所有注册进来的io。当任意io在内核缓冲区中有数据可读时，select返回。
    特点：一个进程解决多个进程io的阻塞问题，性能好；
          Reactor（反应器）模式；
          实现、开发应用难度较大；
          监听的io最大连接数不能多于FD_SIZE;
          使用高并发服务器应用开发：一个线程响应多个请求。
          //TODO 代码实现？