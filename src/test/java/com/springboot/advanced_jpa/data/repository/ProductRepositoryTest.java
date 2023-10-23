package com.springboot.advanced_jpa.data.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.advanced_jpa.data.entity.Product;
import com.springboot.advanced_jpa.data.entity.QProduct;

@SpringBootTest
public class ProductRepositoryTest {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;
    //QueryDSL Configuration 클래스에서 JPAQueryFactory객체를 @Bean 객체로 등록해두면 스프링 컨테이너에서 가져다 쓸 수 있다
    //@Autowired
    //JPAQueryFactory jpaQueryFactory;

    @Test
    void sortingAndPagingTest() {
        Product product1 = new Product();
        product1.setName("펜");
        product1.setPrice(1000);
        product1.setStock(100);
        product1.setCreatedAt(LocalDateTime.now());
        product1.setUpdatedAt(LocalDateTime.now());


        Product product2 = new Product();
        product2.setName("펜");
        product2.setPrice(5000);
        product2.setStock(300);
        product2.setCreatedAt(LocalDateTime.now());
        product2.setUpdatedAt(LocalDateTime.now());

        Product product3 = new Product();
        product3.setName("펜");
        product3.setPrice(500);
        product3.setStock(50);
        product3.setCreatedAt(LocalDateTime.now());
        product3.setUpdatedAt(LocalDateTime.now());

        Product savedProduct1 = productRepository.save(product1);
        Product savedProduct2 = productRepository.save(product2);
        Product savedProduct3 = productRepository.save(product3);

        
        

        System.out.println(productRepository.findByName("펜",getSort() ));

        Page<Product> productPage = productRepository.findByName("펜", PageRequest.of(0, 2));
        System.out.println(productPage.getContent());


        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
            .from(qProduct)
            .where(qProduct.name.eq("펜"))
            .orderBy(qProduct.price.asc())
            .fetch();

        System.out.println("dsl 테스트");
        for (Product product : productList) {
                System.out.println("---------------------------");
                System.out.println();
                System.out.println("Product Number: " + product.getNumber());
                System.out.println("Product Name : " + product.getName());
                System.out.println("Product Price : " + product.getPrice());
                System.out.println("Product Stock: " + product.getStock());
                System.out.println();
                System.out.println("-----------------------------");
        }


        System.out.println("JPAQueryFactory를 활용한 QueryDSL 테스트");
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        List<Product> productList2 = jpaQueryFactory.selectFrom(qProduct)
            .where(qProduct.name.eq("펜"))
            .orderBy(qProduct.price.asc())
            .fetch();


        for (Product product : productList2) {
            System.out.println("-------------------");
            System.out.println();
            System.out.println("Product Number: " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock: " + product.getStock());
            System.out.println();
            System.out.println("-----------------------------");
        }
        System.out.println("JPAQueryFactory의 select() 메소드 활용");
        List<String> productList3 = jpaQueryFactory
            .select(qProduct.name)
            .from(qProduct)
            .where(qProduct.name.eq("펜"))
            .orderBy(qProduct.price.asc())
            .fetch();

        for (String product : productList3) {
            System.out.println("-------------------");
            System.out.println("Product Name : " + product);
            System.out.println("----------------------");
        }



        List<Tuple> tupleList = jpaQueryFactory
            .select(qProduct.name, qProduct.price)
            .from(qProduct)
            .where(qProduct.name.eq("펜"))
            .orderBy(qProduct.price.asc())
            .fetch();
        
        for (Tuple product : tupleList) {
            System.out.println("----------------------");
            System.out.println("Product Name: " +product.get(qProduct.name));
            System.out.println("Product Price: "+ product.get(qProduct.price));
            System.out.println("---------------------------------");
        }
            
    }

    private Sort getSort() {
        return Sort.by(
            Order.asc("price"),
            Order.desc("stock")
        );
    }

    // JPAQuery를 활용한 QueryDSL 테스트 코드
    @Test
    void queryDslTest() {
        JPAQuery<Product> query = new JPAQuery(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = query
            .from(qProduct)
            .where(qProduct.name.eq("펜"))
            .orderBy(qProduct.price.asc())
            .fetch();

        System.out.println("dsl 테스트");
        for (Product product : productList) {
                System.out.println("---------------------------");
                System.out.println();
                System.out.println("Product Number: " + product.getNumber());
                System.out.println("Product Name : " + product.getName());
                System.out.println("Product Price : " + product.getPrice());
                System.out.println("Product Stock: " + product.getStock());
                System.out.println();
                System.out.println("-----------------------------");
        }
    }
    //JPAQueryFactory를 활용한 QueryDSL 테스트 코드
    @Test
    void queryDSLTest2(){
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        QProduct qProduct = QProduct.product;

        List<Product> productList = jpaQueryFactory.selectFrom(qProduct)
            .where(qProduct.name.eq("펜"))
            .orderBy(qProduct.price.asc())
            .fetch();

        for (Product product : productList) {
            System.out.println("-----------------------");
            System.out.println();
             System.out.println("Product Number: " + product.getNumber());
            System.out.println("Product Name : " + product.getName());
            System.out.println("Product Price : " + product.getPrice());
            System.out.println("Product Stock: " + product.getStock());
            System.out.println();
            System.out.println("-----------------------------");
        }
        
    }
}
