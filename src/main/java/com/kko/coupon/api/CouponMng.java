package com.kko.coupon.api;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kko.coupon.model.Coupon;


public interface CouponMng {
	public List<Coupon> crtnCouPon(String cnt);
}
