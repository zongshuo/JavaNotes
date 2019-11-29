AIO：异步非阻塞IO（java.nio.channels包下的异步channel），当进程发起一个io操作，进程返回（不阻塞），但也不能返回结果；
     内核把这个io处理完后，会通知（回调）进程结果。如果io操作成功则进程直接获取到数据。
     相较于NIO来说，AIO更近一步，它不是io准备好时再通知线程，而是在io操作已经完成后，再给线程发出通知。
     因此AIO是不会阻塞的，此时我们的业务逻辑将变成一个回调函数，等待io操作完成后，由系统自动触发。
     当进行读写操作时，只需调用read或write方法即可。这两个方法是异步的，完成后会主动调用回调函数。

     特点：不阻塞，数据一步到位；
          Proactor(主动器)模式；
          需要操作系统底层支持，Linux2.5版本首现，Linux2.6版本标准特性；
          实现、开发应用难度大；
          非常适合高性能高并发应用。
          //TODO 代码实现？
     实现：
        1、AsynchronousSocketChannel
        2、AsynchronousServerSocketChannel
        3、AsynchronousFileChannel
        4、AsynchronousDatagramChannel
CompletionHandler<V,A>：接口，AIO中发出一个事件（accept、read、write）后要指定事件处理类（回调函数）。
        事件处理类需要实现CompletionHandler接口。
        方法：
            1、void completed（V result，A attachment）
            2、void failed（Throwable exc，A attachment）
