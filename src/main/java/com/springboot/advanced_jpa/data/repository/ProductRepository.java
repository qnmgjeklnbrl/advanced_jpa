package com.springboot.advanced_jpa.data.repository;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.advanced_jpa.data.entity.Product;
import java.util.List;




public interface ProductRepository extends JpaRepository<Product, Long> {
    //쿼리 메소드의 주제 키워드

    // find...By 조회 키워드
    Optional<Product> findByNumber(Long number);
    List<Product> findByName(String name);
    Product queryByNumber(Long number);


    // exists...By     특정 데이터가 존재하는지 확인하는 키워드, 리턴 타입으로 boolean 타입 사용
    boolean existsByNumber(Long number);

    // count...By 조회 쿼리를 수행한 후 쿼리 결과로 나온 레코드의 개수를 리턴

    long countByName(String name);

    // delete...By 삭제쿼리
    void deleteByNumber(Long number);
    long removeByName(String name);

    // ...First<number>..., Top<number>... 쿼리를 통해 조회된 결괏값의 개수를 제한하는 키워드 단건으로 조회 하려면 <number>생략
    List<Product> findFirst5ByName(String name);
    List<Product> findTop10ByName(String name);

    //쿼리 메소드의 조건자 키워드

    // Is 값의 일치를 조건으로
    // findByNumber 메소드와 동일하게 동작
    Product findByNumberIs (Long number);
    Product findByNumberEquals (Long number);

    // (Is)Not 값의 불일치를 조건으로 사용하는 조건자 키워드
    Product findByNumberIsNot(Long number);
    Product findByNumberNot(Long number);

    // (Is)Null, (Is)NotNull 값이 null인지 검사하는 조건자 키워드
    List<Product> findByUpdatedAtNull();
    List<Product> findByUpdatedAtIsNull();
    List<Product> findByUpdatedAtNotNull();
    List<Product> findByUpdatedAtIsNotNull();

    //(Is)True, (Is)False boolean 타입으로 지정된 칼럼값을 확인하는 키워드

    //And, Or 여러 조건을 묶을 때 사용
    Product findByNumberAndName(Long number, String name);
    Product findByNumberOrName(Long number, String name);

    //(Is)GreaterThan, (Is)LessThan, (Is)Between 숫자나 datetime 칼럼을 대상으로 한 비교 연산에 사용
    //GreaterThan, LessThan 키워드는 비교 대상에 대한 초과/미만의 개념으로 비교 연산을 수행하고
    //경곗값을 포함하려면 Equal 키워드를 추가하면 된다.

    List<Product> findByPriceIsGreaterThan(Long price);
    List<Product> findByPriceGreaterThan(Long price);
    List<Product> findByPriceGreaterThanEqual(Long price);

    List<Product> findByPriceIsLessThan(Long price);
    List<Product> findByPriceLessThan(Long price);
    List<Product> findByPriceLessThanEqual(Long price);

    List<Product> findByPriceIsBetween(Long lowPrice, Long highPrice);
    List<Product> findByPriceBetween(Long lowPrice, Long highPrice);
    
    // (Is)StartingWith(==StartsWith), (Is)EndingWith(==EndsWith), 
    // (Is)ContainingWith(==Contains), (Is)Like
    // 칼럼값에서 일부 일치 여부를 확인하는 조건자 키워드, SQL 쿼리문에서 값의
    // 일부를 포함하는 값을 추출할 때 사용하는 '%' 키워드와 동일한 역할을 하는 키워드이다.
    
    List<Product> findByNameContains(String name);
    List<Product> findByNameContaining(String name);
    List<Product> findByNameIsContaining(String name);

    List<Product> findByNameStartsWith(String name);
    List<Product> findByNameStartingWith(String name);
    List<Product> findByNameIsStartingWith(String name);

    List<Product> findByNameEndsWith(String name);
    List<Product> findByNameEndingWith(String name);
    List<Product> findByNameIsEndingWith(String name);

    //정렬 처리하기
    // Asc: 오름차순, Dsec: 내림차순
    List<Product> findByNameOrderByNumberAsc(String name);
    List<Product> findByNameOrderByNumberDesc(String name);

    // 쿼리 메소드에서 여러 정렬 기준 사용
    //And 를 붙이지 않음
    List<Product> findByNameOrderByPriceAscStockDesc(String name);

    // 매개변수를 활용한 쿼리 정렬
    List<Product> findByName(String name, Sort sort);
    
}