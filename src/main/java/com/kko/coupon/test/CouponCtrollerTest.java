package com.kko.coupon.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CouponCtrollerTest {
	@GetMapping("test/hello")
	public String hello() {
		return "<h1>Hello Yimga !!</h1>";
	}
}
