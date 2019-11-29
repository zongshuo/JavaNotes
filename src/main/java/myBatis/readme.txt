$符号用的是Statement，是以字符串的形式拼接sql语句，有sql注入的风险。
#符号用的是PreparedStatement，是使用占位符预编译sql语句，可以防止sql注入。

hibernate和ibatis的区别
    1、前者自动生成sql，后者手动写sql
    2、hibernate可以自动建表，ibatis不可以，移植性差
    3、ibatis对sql的效率有更好的可控性和表现

DataSource分类：
    1、unpooled：不适用连接池的数据源
    2、pooled：使用连接池的数据源
    3、JNDI：使用jndi实现的数据源
DataSource的创建发生在MyBatis初始化的过程中。根据xml配置文件中DataSource的type属性来确认创建何种格式的DataSource。
mybatis是根据工厂模式来创建数据源的。
DataSource何时创建Connection对象？
    需要执行sql并创建SqlSession对象时才会创建connection，执行方法被调用后才创建。

连接池：将connection封装到PooledConnection中，该对象有两种状态：空闲、活动。两种状态的对象分别存入PoolState容器中的idleConnection和activeConnection两个List中。