异常的结构
    Throwable:
        1、Error(错误)：由jvm产生和抛出，如：OutOfMemoryError
        2、Exception(异常)：
            A、运行时异常：NullPointerException
            B、非运行时异常：IOException
非运行时异常如果不处理就不能编译通过。运行时异常可以不处理，一般由程序逻辑错误引起。
