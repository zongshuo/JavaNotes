outputStream：输出字节流类，是一个抽象类，所有输出字节流的父类。
子类：
    1、FileOutputStream：向本地文件系统的文件中输出字节
    2、FilterOutputStream：装饰者，重要的是子类的扩展和方法。
        子类：
            a、BufferedOutputStream：为输出流提供缓冲。
            b、DataOutputStream：数据输出流，允许应用程序以与机器无关方式将Java基本数据类型写到底层输出流。
            c、PrintStream：打印输出流，用来装饰其他输出流。为其他输出流添加了功能，使他们能方便的打印各种数据值的表示形式。
    3、ObjectOutputStream：装饰流，用于对象的输出（序列化）。
    4、PipedOutputStream：向与其他线程共用的管道中写入字节。
    5、ByteArrayOutputStream：向byte数组中写入字节。
缓冲流：
    1、BufferedOutputStream：缓冲字节流，可指定缓冲区大小，默认8K。