package com.example.mybatistest;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.List;

@SpringBootTest
class MybatisTestApplicationTests {

    @Test
    void contextLoads() throws Exception{
        // mybatis工作原理

        // 系统启动时，会加载mybatis全局配置文件和映射文件，并将解析的相关信息存储在Configuration对象中
        InputStream in = Resources.getResourceAsStream("mybatis-config.xml");

        // 每一个MyBatis的应用程序都以一个SqlSessionFactory对象的实例为核心.同时SqlSessionFactory也是线程安全的,SqlSessionFactory一旦被创建,应该在应用执行期间都存在.在应用运行期间不要重复创建多次,建议使用单例模式
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

        // SqlSession是一次会话，SqlSession是应用程序与持久层之间执行交互操作的一个单线程对象,也是MyBatis执行持久化操作的关键对象.SqlSession对象完全包含以数据库为背景的所有执行SQL操作的方法,它的底层封装了JDBC连接,可以用SqlSession实例来直接执行被映射的SQL语句.
        // 所以，一个应用程序同一时间多个查询请求，产生多个SqlSession对象（会话对象），查询结束后关闭会话
        SqlSession sqlSession = factory.openSession();

        // sqlSession相关api执行sql
        List<Object> list = sqlSession.selectList("");

        // 关闭会话
        sqlSession.close();
    }

}
