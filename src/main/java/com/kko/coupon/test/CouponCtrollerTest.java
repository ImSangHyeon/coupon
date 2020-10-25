package com.kko.coupon.test;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kko.coupon.model.Coupon;
import com.kko.coupon.model.User;
import com.kko.coupon.repository.UserRepository;



@RestController
public class CouponCtrollerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("test/hello")
	public String hello() {
		return "<h1>Hello Yimga !!</h1>";
	}
	
	@GetMapping("test/userRegst")
	public String userRegstTest() {
		User user = new User();
		user.setUserId("kkoUser1");
		user.setPassWord("kkopw");
		user.setUserNm("Lion");
		return this.userRegist(user);
		
	}
	
	@PostMapping("user/regist")
	public String userRegist(User user) {
		
		return user.getUserNm() + "닙이 가입완료되었습니다.( " + user.getUserId() + ")";
	}
	
	@GetMapping("user/login")
	public String userLogin(User user) {
		return user.getUserNm() + "닙이 로그인되었습니다.( " + user.getUserId() + ")";
	}
	
	@GetMapping("cp/crtn")
	public String crtnCoupon(int cnt) {
		List<Coupon> couponLst = new ArrayList<Coupon>();
		
		return couponLst.size() + " 건의 쿠폰이 발급되었습니다." ;
	}
}

