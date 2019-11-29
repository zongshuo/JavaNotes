HashMap与Hashtable的区别：
    1、都实现了Map、Cloneable、Serializable接口。
    2、继承的父类不同
        Hashtable-->Dictionary
        HashMap-->AbstractMap
    3、线程安全性不同
       Hashtable是线程安全的
       HashMap非线程安全，可以通过Collections.synchronizedMap包装为线程安全的。
    4、Hashtable比HashMap多一个contains（Object object）方法。
    5、HashMap的key和value都可以为null，Hashtable都不可以。
    6、遍历的内部实现方式上不同，都使用了iterator，但是Hashtable还使用了Enumeration。
    7、hash值不同
        Hashtable直接使用对象的hashCode。
        HashMap重新计算hash值。
    8、内部数组的初始化和扩容方式不同，导致相同的值在hashtable和hashMap的数组中的位置不同。
        Hashtable初始为11，扩容为start*2+1
        HashMap初始为16，扩容为start*2


解决哈希冲突的方法：
    1、拉链法
    2、开放定址法
HashMap的实现原理：
    以哈希表的形式存储key/value对象。
    哈希表的主干是一个数组（Entry）。
    该数组存储key的hash值：key->哈希函数->存储地址->数组。
    因为存放存储地址的数组不是无限大的，所以要求哈希函数要计算简单和散列地址结算均匀。
    通过hash函数计算key的存储地址时，有可能存在不同key值但是计算出的存储地址相同的情况，这种情况叫做“哈希冲突/哈希碰撞”。
    HashMap采用了数组+链表（链地址法）解决哈希冲突。

    没有同址元素时增、查很快。
    有同址元素，首先遍历链表，存在则覆盖原值，否则新增。
    对于查找操作，有同址元素时会进行遍历并通过equals方法查找。
实现方式：
    数组：元素1、元素2、元素3、元素4
            |     |      |      |
          同址    null  同址
          元素1         元素1
            |
          同址
          元素2