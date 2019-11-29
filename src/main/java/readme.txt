java 反射加载类的两种方式：
    1、Class.forName()：会将类初始化，即静态代码块和静态变量的赋值会被执行
        Class.forName("类名").newInstance();
    2、ClassLoader：只是将类加载到JVM中。spring中的IOC既是使用ClassLoader方式，使用时再初始化。
        ClassLoader.getSystemClassLoader.loadClass("类名").newInstance();

java7特性中，switch中添加对String类型的支持。

String、StringBuilder、StringBuffer区别？
    1、String的内容不可变，后两者内容可变。
    2、StringBuilder非线程安全，另外两个安全。
    3、单线程下StringBuilder效率高，如果变化少应该用String。