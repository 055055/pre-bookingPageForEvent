package com.smile.prebookingforgames.repository;

import com.smile.prebookingforgames.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
    Optional<CouponEntity> findByCouponNumber(String couponNumber);
    Optional<CouponEntity> findByPhoneNumber(String phoneNumber);
}