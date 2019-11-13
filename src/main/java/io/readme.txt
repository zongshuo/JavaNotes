流：是一种有序的数据集合，有数据源和目的地。数据源可以是文件、内存、或是网络连接
    是对数据传输的总称或抽象，即数据在设备间的传输称为流。
    本质是数据传输，根据数据传输的特性将流抽象为各种类，方便更直观的进行数据操作。
流的分类：
    按方向（站在内存的角度）：输入流、输出流
    按功能：节点流（低级流）--用于直接操作目标设备、过滤流（高级流）--对一个已存在的流的连接和封装，通过对数据的处理为程序提供功能强大灵活的读写功能。
    操作单元：字节流--可以处理任何类型的数据、字符流--只能处理字符类型的数据。字节流和字符流可以转换。

Console：控制台输入输出类
ObjectStreamClass：类的序列化描述符
ObjectStreamField：该类是描述串行化类中的串行化字段的类，一般会用一个ObjectStreamField数组来来声明一个类中的串行化字段。
ObjectInputStream.GetField：
ObjectInputStream.PutField：
RandomAccessFile：随机访问文件的读写
SerializablePermission：
StreamTokenizer：
//TODO 都是干啥用的？