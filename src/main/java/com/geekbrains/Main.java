package com.geekbrains;

import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.util.List;

@Slf4j
public class Main {
    @SneakyThrows
    public static void main(String[] args) {
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("myBatisConfig.xml"));

        SqlSession session = sessionFactory.openSession();

        ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
        Products product = productsMapper.selectByPrimaryKey(3L);
        log.info("product: {}", product);

        ProductsExample filter = new ProductsExample();

        filter.createCriteria()
                .andTitleGreaterThan("a")
                .andTitleLessThan("h");

        List<Products> products = productsMapper.selectByExample(filter);
        products.forEach(p -> log.info("product: {}", p));

        Products newProduct = new Products();
        newProduct.setPrice(300);
        newProduct.setTitle("Яблочко");
        newProduct.setCategoryId(1L);

        productsMapper.insertSelective(newProduct);

        filter.clear();
        filter.createCriteria()
                .andTitleEqualTo(newProduct.getTitle());

        List<Products> newProducts = productsMapper.selectByExample(filter);
        newProducts.forEach(products1 -> log.info("product new: {}",products1));
session.commit();

    }
}
