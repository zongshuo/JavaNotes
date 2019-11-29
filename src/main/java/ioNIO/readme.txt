NIO：同步非阻塞IO（面向缓冲的java.nio包的Channel、Selector、Buffer等），多个进程的io可以注册到一个复用器（select）上
    然后用一个进程调用该select，select会监听所有注册进来的io。当任意io在内核缓冲区中有数据可读时，select返回。
    NIO主要用于网络通信编程。
    特点：一个进程解决多个进程io的阻塞问题，性能好；
          Reactor（反应器）模式；
          实现、开发应用难度较大；
          监听的io最大连接数不能多于FD_SIZE;
          使用高并发服务器应用开发：一个线程响应多个请求。
          //TODO 代码实现？
NIO之所以是同步的，是应为它的accept、read、write方法的内核io操作都会阻塞当前线程。

NIO中有分散（scatter）和聚集（gather）的概念，这种方式要求Buffer大小不固定的数据会有误差，需要注意。
scatter：分散读取，将多个buffer保存到一个buffer数据组中，将数组作为参数传递给read方法。
        读取时会按数组顺序填满里面的各个buffer。直到数据被读完或者数组被填满。
gather：集中写，与写一样将多个buffer放入Buffer的数据组中，将数组作为参数传递给write方法。
        写入时会按顺序将数组中的buffer写入通道。

Reactor模式（反应器模式）：是一种为了处理并发服务请求并将请求提交到一个或多个服务处理程序的事件设计模式。
Proactor模式（主动器模式）：在异步操作完成后触发服务请求的分配和分发。

Channel：通道，channel是一个对象，可以通过它读取和写入数据。可以看做是bio中的流。
        操作系统的内核缓冲区，读缓冲或网络缓冲。
        channel是双向的，既可以度又可以写。
        channel可以异步的读写。
        channel是面向缓存的，读写必须通过buffer对象，讲数据读、写到buffer中，再将buffer中的数据读、写到channel中。
    实现:
    1、FileChannel：是一个连接到文件的通道。该通道总是阻塞的，所以不能注册到selector中。
        属性：
            A、FileChannel.MapMode：内存映像文件访问的三种方式：
                a、MapMode.READ_ONLY：只读，试图修改得到的缓冲区将导致抛出异常。
                b、MapMode.READ_WRITE：读/写，对得到的缓冲区的更改最终将写入文件；但该更改对映射到同一文件的其他程序不一定是可见的。内容已经读到缓存。
                c、MapMode.PRIVATE：私用，可读可写，但是修改的内容不会写入文件，只是buffer自身的改变，这种能力称为“copy on write”。
            B、position：文件映射时的起始位置
        方法：
            A、map：把文件映射到虚拟内存，并返回逻辑地址address
                    将内核缓冲区的数据地址映射到用户缓冲区。
    2、DatagramChannel：是一个能够收发UDP数据报的通道。
    3、SocketChannel：是一个连接到TCP网络套接字的通道。
    4、ServerSocketChannel：是一个可以监听新连接的TCP连接的通道。
Buffer：缓冲区，buffer是一个对象，包含一些要写入或者读到stream对象的数据。
        应用程序不能直接对channel进行读写，必须通过buffer进行，即channel是通过buffer来读写数据的。
        用户缓冲区。

        使用Buffer一般遵循以下四个步骤：
        1、写入数据到Buffer
        2、调用flip方法，切换buffer的读写模式
        3、从buffer中读取数据
        4、调用clear（会清空整个缓冲区）方法或者compact（只清空已经读过的数据，未读过数据被移入缓冲区的起始处，新写入的防盗缓冲区未读数据的后面）方法
    实现：
        1、ByteBuffer：其他缓存都是基于ByteBuffer
        2、CharBuffer：增加字节转换过程
        3、ShortBuffer：
        4、IntBuffer：
        5、LongBuffer：
        6、FloatBuffer：
        7、DoubleBuffer：
        8、MappedByteBuffer：零拷贝缓存，用于大文件的操作，读写性能极高。
            该类继承自ByteBuffer，内部维护了一个逻辑地址address。
            FileChannel fileChannel = new RandomAccessFile(new File(""), "r").getChannel();//getChannel方法线程安全
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, 1024);
            mappedByteBuffer.get();
Selector：选择器，selector是一个对象，可以注册到很多个channel上，监听各个channel上发生的事件，并且能够根据事件情况决定channel读写。
           通过一个线程管理多个channel，就可以处理大量网络连接了。
           线程之间的切换对操作系统来说代价是很高的，并且每个线程会占用一定的系统资源。对于操作系统来说线程越少越好。
    方法：
        Set<SelectionKey> keys = selector.selectedKeys();
    实现：
        Selector selector = Selector.open();
        Channel a = SocketChannel.open();
        Channel b = SocketChannel.open();
        //使用选择器，channel必须是非阻塞的
        a.configureBlocking(false);
        b.configureBlocking(false);

        SelectionKey keyA = a.register(selector, SelectionKey.OP_READ);
        SelectionKey keyB = b.register(selector, SelectionKey.OP_READ);
SelectionKey：选择键，是一个抽象类。讲channel与selector之间建立了关系，并维护channel事件。channel注册到selector后返回一个selectionKey。
        可以通过cancel方法取消键，取消的键不会立即从selector中移除，而是添加到cancelledKeys中，
        在下一次select操作时移除它，所以在调用某个key时，需要使用isValid进行校验。
    属性：
        1、OP_ACCEPT：连接就绪
        2、OP_CONNECT：接收就绪
        3、OP_READ：读就绪
        4、OP_WRITE：写就绪
    方法：
        1、selectionKey.isAcceptable()
        2、selectionKey.isConnectable()
        3、selectionKey.isReadable()
        4、selectionKey.isWritable()
        5、selectionKey.channel()：获取注册到selector的channel
        6、selectionKey.selector()：获取注册的selector
NIO多路复用：
    1、通过selector.open()创建一个selector，作为类似调度员的角色。
    2、创建一个ServerSocketChannel，并且向selector注册，通过指定SelectionKey.OP_ACCEPT,告诉调度员，他关注的是新的连接请求。
    3、配置ServerSocketChannel的阻塞模式为非阻塞，否则会不予许注册，会抛出IllegalBlockingModeException。
    4、selector会阻塞在select操作，当有channel发生接入请求，就会被唤醒。
    5、在具体方法中，通过SocketChannel和buffer进行数据操作。
//TODO 实现原理？
NIO零拷贝：使用堆外内存