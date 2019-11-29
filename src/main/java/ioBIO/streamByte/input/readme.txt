inputStream：输入字节流，是一个抽象类，所有输入字节流的父类。
子类：
    1、FileInputStream：从文件系统的文件中获取字节
    2、FilterInputStream：装饰者，重要的是子类的扩展和方法。
        子类：
            a、BufferedInputStream：为流提供缓冲功能
            b、DataInputStream：数据输入流，允许应用程序以与机器无关方式从底层输入流中读取基本 Java 数据类型。
            c、PushbackInputStream：允许读取字节，然后再将它们返回流中。
    3、ObjectInputStream：用于对象的读取（反序列化），是装饰流
    4、PipedInputStream：管道输入流，用于线程的通信
    5、SequenceInputStream：将集合中的所有流按序读取。
        构造：
            a、SequenceInputStream(InputStream inputStream1, InputStream inputStream2)
            b、SequenceInputStream(Enumeration<? extend InputStream> enum)
                Enumeration：枚举类，用于枚举集合中的所有元素。
                    只支持遍历Vector和HashTable，不支持元素的移除。
                Vector<InputStream> vector = new Vector<InputStream>();
                vector.add(new FileInputStream(new File("")));
                vector.add(new FileInputStream(new File("")));
                SequenceInputStream sequence = new SequenceInputStream(vector.elements());
                sequence.read();
    6、StringBufferedInputStream：从StringBuffer中读取数据
    7、ByteArrayInputStream：从Byte数组中读取数据
    //TODO 其他类怎么用？
缓冲流：
    1、BufferedInputStream：输入字节缓冲流