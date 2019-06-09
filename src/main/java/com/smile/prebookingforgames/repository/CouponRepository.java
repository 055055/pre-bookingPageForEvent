package com.smile.prebookingforgames.repository;

import com.smile.prebookingforgames.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select couponNumber from Coupon e where e.couponNumber=?1")

    Optional<String> findByCouponNumber(String couponNumber);

   @Query("select phoneNumber from Coupon e where e.phoneNumber=?1")
   Optional<String> findByPhoneNumber(String phoneNumber);

}