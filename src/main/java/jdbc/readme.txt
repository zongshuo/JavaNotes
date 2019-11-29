jdbc连接：
    1、加载驱动
        Class.forName("oracle.jdbc.driver.oracleDriver");
    2、创建连接对象
        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521/orcl", "name", "pwd");
    3、执行sql
        PreparedStatement ps = con.preparedStatement(sql);
        ResultSet rs = ps.executeQuery();
    4、声明事务
        con.setAutoCommit(false);
        ps.executeUpdate();
        con.commit();
        com.rollback();
