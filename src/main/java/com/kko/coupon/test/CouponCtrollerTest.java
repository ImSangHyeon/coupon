package com.kko.coupon.test;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kko.coupon.model.Coupon;
import com.kko.coupon.model.CouponIssue;
import com.kko.coupon.model.CouponStatCd;
import com.kko.coupon.model.User;
import com.kko.coupon.repository.CouponIssueRepository;
import com.kko.coupon.repository.CouponRepository;
import com.kko.coupon.repository.UserRepository;



@RestController
public class CouponCtrollerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CouponRepository couponRepository;
	
	@Autowired
	private CouponIssueRepository couponIssueRepository;
	
	
	final char[] psblChtrs =
	    {'1','2','3','4','5','6','7','8','9','0'
	    ,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
	    };
	final int psblChtrsCnt = psblChtrs.length;
	
	
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
		
		user = this.userRegist(user);
//		return user;
		return user.getUserNm() + "닙이 가입완료되었습니다.( " + user.getUserId() + ")";
	}
	
	@PostMapping("user/regist")
	public User userRegist(User user) {
		user = userRepository.save(user);
		return user;
	}
	
	@GetMapping("user/login")
	public String userLogin(User user) {
		User cfmUser = userRepository.findById(user.getUserId()).get();
		return user.getUserNm() + "닙이 로그인되었습니다.( " + user.getUserId() + ")";
	}
	
	@GetMapping("cp/crtn/{cnt}")
	public String crtnCoupon(@PathVariable String cnt) {
		List<Coupon> couponLst = new ArrayList<Coupon>(); 
		couponLst = this.crtnCouPon(cnt);
		if(couponLst != null) {
			int inx = 0;
			inx ++; 
			for (Coupon coupon : couponLst) {
				couponRepository.save(coupon);
			}
		}
		return couponLst.size() + " 건의 쿠폰이 발급되었습니다." ;
	}
	
	/**
	 * 발급된쿠폰정보조회
	 * @param userId
	 * @return
	 */
	@GetMapping("cp/getIssueInf/{userId}")
	public String getCouponIssueInf(@PathVariable String userId) {
		String rsltStr = "";
		List<CouponIssue> couponIssueLst = couponIssueRepository.findAll();
		if(couponIssueLst == null) {
			return "발급된 쿠폰이 없습니다.";
		}
		if(userId == null || userId.isEmpty()) {
			return "유저정보가 없습니다.";
		}
		
		CouponIssue couponIssue = null;
		int totCnt = couponIssueLst.size(); 
		for (int inx =0; inx < totCnt; inx ++ ) {
			couponIssue = couponIssueLst.get(inx);
			if(!userId.equals(couponIssue.getUser().getUserId())) {
				couponIssueLst.remove(inx);
				totCnt --;
				inx --;
			}else {
				if(rsltStr.isEmpty()) {
					rsltStr = rsltStr + couponIssue.getCoupon().getCouponNo() + "/" + couponIssue.getCoupon().getCouponStatCd()+ "/" + couponIssue.getCoupon().getVrEdDt();    
				}else {
					rsltStr = rsltStr + "<br>" + couponIssue.getCoupon().getCouponNo() + "/" + couponIssue.getCoupon().getCouponStatCd()+ "/" + couponIssue.getCoupon().getVrEdDt();
				}
			}
		}
		
		if(rsltStr.isEmpty()) {
			return "발급된 쿠폰이 없습니다.";
		}else {
			return "지급된 쿠폰정보(" + userId +")<br>" + rsltStr;
		}	
	}
	
	@GetMapping("cp/useCoupon/{couponNo}")
	public String useCoupon(@PathVariable String couponNo) {
		
		List<Coupon> couponLst = couponRepository.findAll();
		
		if(couponNo == null || couponNo.isEmpty()) {
			return "쿠폰 번호를 확인해 주세요.";
		}
		
		if(couponLst == null) {
			return "사용이 불가능한 쿠폰입니다.";
		}
		
		Coupon useObjCoupon = null;
		
		for (Coupon coupon : couponLst) {
			if(couponNo.equals(coupon.getCouponNo())) {
				useObjCoupon = coupon;
				break;
			}
		}
		
		if(useObjCoupon == null ) {
			return "사용이 불가능한 쿠폰입니다.";
		}else if(!CouponStatCd.USE_PSBL.equals(useObjCoupon.getCouponStatCd())) {
			return "사용이 불가능한 상태의 쿠폰입니다.";
		}else {
			useObjCoupon.setCouponStatCd(CouponStatCd.USE_CMPT);		//쿠폰사용 완료처리
			couponRepository.save(useObjCoupon);
			return couponNo + " 쿠폰이 사용되었습니다.";
		}
	}
	
	@GetMapping("cp/cancleUsedCoupon/{couponNo}")
	public String cancleUsedCoupon(@PathVariable String couponNo) {
		
		List<Coupon> couponLst = couponRepository.findAll();
		
		if(couponNo == null || couponNo.isEmpty()) {
			return "쿠폰 번호를 확인해 주세요.";
		}
		
		if(couponLst == null) {
			return "취소가 불가능한 쿠폰입니다.";
		}
		
		Coupon useObjCoupon = null;
		
		for (Coupon coupon : couponLst) {
			if(couponNo.equals(coupon.getCouponNo())) {
				useObjCoupon = coupon;
				break;
			}
		}
		
		if(useObjCoupon == null ) {
			return "취소가 불가능한 쿠폰입니다.";
		}else if(!CouponStatCd.USE_CMPT.equals(useObjCoupon.getCouponStatCd())) {
			return "취소가 불가능한 상태의 쿠폰입니다.";
		}else {
			useObjCoupon.setCouponStatCd(CouponStatCd.USE_PSBL);		//쿠폰사용 사용가능처리
			couponRepository.save(useObjCoupon);
			return couponNo + " 쿠폰이 사용취소되었습니다.";
		}
	}
	
	/**
	 * 유저에게 사용 가능한 쿠폰을 발행
	 * @param userId
	 * @return
	 */
	@GetMapping("cp/issue/{userId}")
	public String regstCouponIssue(@PathVariable String userId) {
		
		User user = new User();
		user.setUserId(userId);
		
		CouponIssue couponIssue = new CouponIssue();
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd");
		String curDate = sdf.format(cd.getTime());
		
		couponIssue.setUser(user);
		couponIssue.setIssueDate(curDate);
		Coupon validcoupon = getValidCoupon();
		if(validcoupon == null) {
			return "발급가능한 쿠폰이 없습니다";
		}
		couponIssue.setCoupon(validcoupon);
		couponIssue = couponIssueRepository.save(couponIssue);	//쿠폰발행정보생성
		
		couponIssue.getCoupon().setCouponStatCd(CouponStatCd.USE_PSBL);
		couponRepository.save(couponIssue.getCoupon());		//쿠폰상태 변경 (발행완료 --> 사용가능)
		
		return userId + "에게 쿠폰이 지급되었습니다.(" + validcoupon.getCouponNo() + ")" ;
	}
	
	/**
	 * 발금 간능한 유효 쿠폰 조회
	 * @return
	 */
	@PostMapping("cp/retv/")
	public Coupon getValidCoupon() {
		Coupon validcoupon = null;
		List<Coupon> couponLst = couponRepository.findAll();
		for (Coupon coupon : couponLst) {
			if(CouponStatCd.CRTN_CPT.equals(coupon.getCouponStatCd())){
				validcoupon = coupon;
				break;
			}
		}
		return validcoupon;
	}
	
	
	/**
	 * (신규) 쿠폰 객체를 cnt 만큼 생성하여 리턴함
	 * @param cnt
	 * @return List<Coupon>
	 */
	public List<Coupon> crtnCouPon(String cnt){
		int totCnt = Integer.parseInt(cnt); 
		List<Coupon> cpLst = new ArrayList<Coupon>();
		String[] couponNoLst = this.crtnCouponNo(totCnt);
		
		Calendar cd = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat ( "yyyy-MM-dd");
		String curDate = sdf.format(cd.getTime());
		
		System.out.println("curDate : " + curDate);
		
		cd.add(Calendar.DATE, 100);
		
		String vrStDt = curDate;
		String vrEdDt = sdf.format(cd.getTime());
		
		for (String couponNo : couponNoLst) {
			Coupon coupon = new Coupon();
			coupon.setCouponNo(couponNo);
			coupon.setCouponStatCd(CouponStatCd.CRTN_CPT);
			coupon.setVrStDt(vrStDt);
			coupon.setVrEdDt(vrEdDt);
			cpLst.add(coupon);
		}
		
		System.out.println(cpLst.get(0));
		
		return cpLst;
	}
	
	/**
	 * 쿠폰번호 생성
	 * @param cnt
	 * @return 쿠폰번호 목록
	 */
	private String[] crtnCouponNo(int cnt) {
		// TODO Auto-generated method stub
		
		String[] couponNoarr = new String[cnt];
		Random rnd = new Random();
		HashMap hashmap = new HashMap<String, String>();

		int inx = 0;
		int i = 0;
		while (inx < cnt) {
			StringBuffer buf = new StringBuffer(16);
			for (i= 19; i > 0; i--) {
				buf.append(psblChtrs[rnd.nextInt(psblChtrsCnt)]);
			}
			String couponnum = buf.toString();
			System.out.println("couponnum==>"+couponnum);   
			   
			if(!hashmap.containsKey(couponnum)) {
			   couponNoarr[inx] = couponnum;
			   inx++;
			}
		}

		return couponNoarr;
	}
}

;