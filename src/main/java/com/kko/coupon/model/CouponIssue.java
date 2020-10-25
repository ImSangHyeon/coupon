package com.kko.coupon.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

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
public class CouponIssue {
	@Id	//PK
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	private Long couponIssueId;		//쿠폰발행ID
	
	@Column(nullable = true, length = 10)
	private String issueDate;	//발급일자

	@JoinColumn(name="couponId")
	@ManyToOne 
	private Coupon coupon;	//쿠폰
	
	@JoinColumn(name="userId")
	@ManyToOne
	private User user;	//유저
	
	@CreationTimestamp
	@Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private Timestamp inputDate;	//입력일자
}
