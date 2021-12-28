package com.smile.prebookingforgames.repository;

import com.smile.prebookingforgames.entity.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
    Optional<CouponEntity> findByCouponNumberAndDeleted(String couponNumber, Boolean deleted);

    Optional<CouponEntity> findByPhoneNumberAndDeleted(String phoneNumber, Boolean deleted);
}