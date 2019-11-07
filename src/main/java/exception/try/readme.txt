Java异常监控结构
    1、正常结构
      try{

      }catch(Exception a){
        a.toString();
      }catch(Exception b){
        b.toString();
      }finally{
        //关闭资源
      }
    2、自动关闭资源结构：try-with-resource
    try(FileInputStream input = new ileInputStream(new ile("test"))){
        .....
    }catch(IOException e){
        .....
    }
    该结构在出现异常时会自动关闭try中实例化的资源，但要求需要自动关闭的资源实现:
        1、Closeable接口
        2、AutoCloseable接口
    只有实现了以上接口中的一个，才可以自动调用close方法去自动关闭资源。

    第二种结构只是一种语法糖，底层仍然使用第一种结构实现。不过在catch块中增加了一个方法:addSuppressed()。
    异常抑制方法，如果业务处理和关闭连接都出现了异常，业务处理的异常会抑制关闭连接的异常
    只抛出业务处理中的异常，但仍然可以通过getSuppressed()方法获得关闭链接的异常。

注意事项：
    1、try中和finally中同时return，只执行finally中的return。
    2、catch中和finally中同时return，只执行finally中的return。
    3、