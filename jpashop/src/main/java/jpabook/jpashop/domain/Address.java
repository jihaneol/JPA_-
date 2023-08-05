package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

/**
 * 값 타입은 변경 불가능하게 만들자 !!
 * JPA 스펙상 엔티티나 임베디드 타입은 기본 생성자를 protect나 public으로 해야한다.
 * protect가 안전하다.
 */
@Embeddable // 내장 타입 이다
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected  Address(){
    }
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
