package com.kko.coupon.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Coupon {
	@Id	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int couponId;		//쿠폰ID
	
	@Column(unique = true, nullable = false, length = 30) 
	private String couponNo;	//쿠폰번호
	
	@Column(nullable = false, length = 10)
	private String vrStDt;		//유효시작일자
	
	@Column(nullable = false, length = 10)
	private String vrEdDt;		//유효종료일자
	
	@Column(nullable = true, length = 10)
	private String issueDate;	//발급일자
	
	@Enumerated(EnumType.STRING)
	private CouponStatCd couponStatCd;  
	
	@CreationTimestamp
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp InputDate;	//입력일자
	
}
