package com.kko.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kko.coupon.model.Coupon;

//@Repository 생략가능
public interface CouponRepository extends JpaRepository<Coupon, Long> { 

}
