package com.kko.coupon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kko.coupon.model.CouponIssue;

//@Repository 생략가능
public interface CouponIssueRepository extends JpaRepository<CouponIssue, Long> { 

}
