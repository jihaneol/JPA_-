package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    @Embedded // 내장 타입을 사용했다.
    private Address address;

    @OneToMany(mappedBy = "member") // order 테이블에있는 member 필드에 의해서 맵핑되었다.
    // 나는 주인이 아니다
    private List<Order> orders = new ArrayList<>();







}
