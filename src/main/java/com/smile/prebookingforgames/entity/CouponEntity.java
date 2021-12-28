package com.smile.prebookingforgames.entity;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@ToString
@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "COUPON")
@Where(clause="deleted=false")
public class CouponEntity {

    @Id @GeneratedValue
    @Column(name = "COUPON_SEQ",updatable = false)
    private Long couponSeq;

    @Column(name = "PHONE_NUMBER",updatable = false)
    private String phoneNumber;

    @Column(name = "PERSONAL_INFORMATION_AGREEMENT_YN",updatable = false)
    private boolean privateYn;

    @Column(name = "COUPON_NUMBER",updatable = false)
    private String couponNumber;

    @Column(name = "DELETED",updatable = true)
    private boolean deleted;

    @Column(name = "REG_DATE",updatable = false)
    @CreationTimestamp
    private LocalDateTime regDate;

    @Builder
    public CouponEntity(String phoneNumber, boolean privateYn, String couponNumber) {
        this.phoneNumber = phoneNumber;
        this.privateYn = privateYn;
        this.couponNumber = couponNumber;
    }
}
