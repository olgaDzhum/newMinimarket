package com.geekbrains.util;

import com.geekbrains.db.dao.ProductsMapper;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
@UtilityClass
public class DbUtils {
    private static String resource = "myBatisConfig.xml";

    @SneakyThrows
    public static ProductsMapper getProductsMapper(){
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream(resource));
        SqlSession session = sessionFactory.openSession(true);
        return session.getMapper(ProductsMapper.class);


    }
}
