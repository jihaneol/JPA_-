package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "dellivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY) // 연관관계의 주인이 아니다.
    private Order order;

    @Embedded
    private Address address;

    // 기본 EnumType.ORDINAL 이고 순서로 저장 이것의 문제는 새로 만들어 질때 문제가 발생
    // 절대 사용 하지말자
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //READY, COMP

}
