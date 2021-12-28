package com.smile.prebookingforgames.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "COUPON")
public class CouponEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponSeq;
    private String phoneNumber;
    private boolean privateYn;
    private String couponNumber;
    private boolean deleted;
    @CreationTimestamp
    private LocalDateTime regDate;

    @Builder
    public CouponEntity(String phoneNumber, boolean privateYn, String couponNumber) {
        this.phoneNumber = phoneNumber;
        this.privateYn = privateYn;
        this.couponNumber = couponNumber;
    }
}