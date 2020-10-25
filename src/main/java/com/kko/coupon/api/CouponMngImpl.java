package com.kko.coupon.api;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


import com.kko.coupon.model.Coupon;
import com.kko.coupon.model.CouponStatCd;


public class CouponMngImpl implements CouponMng  {
	final char[] psblChtrs =
	    {'1','2','3','4','5','6','7','8','9','0'
	    ,'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
	    };
	final int psblChtrsCnt = psblChtrs.length;
	
	/**
	 * (신규) 쿠폰 객체를 cnt 만큼 생성하여 리턴함
	 * @param cnt
	 * @return List<Coupon>
	 */
	public List<Coupon> crtnCouPon(String cnt){
		int totCnt = Integer.parseInt(cnt); 
		List<Coupon> cpLst = new ArrayList<Coupon>();
		String[] couponNoLst = this.crtnCouponNo(totCnt);
		
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		String curDate = sdf.format(new Date());
		Calendar cd = Calendar.getInstance();
		cd.add(Calendar.DATE, 100);
		
		String vrStDt = curDate;
		String vrEdDt = sdf.format(cd.getTime());
		
		for (String couponNo : couponNoLst) {
			Coupon coupon = new Coupon();
			coupon.setCouponNo(couponNo);
			coupon.setCouponStatCd(CouponStatCd.CRTN_CPT);
			coupon.setVrEdDt(vrStDt);
			coupon.setVrEdDt(vrEdDt);
		}
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
			for (i= 8; i > 0; i--) {
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
