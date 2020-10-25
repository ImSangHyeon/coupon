# KAKAOPAY 사전과제

**개발환경**
  - JAVA8
  - Spring Boot 2.2.11
  - JPA
  - MYSQL
  - LOMBOK

**ROOT Page**
	URL : http://localhost:8080/coupon/

##문제해결 전략
### 쿠폰번호 생성 방법
 ① 쿠폰번호 사용 가능한 캐릭터 정의 (String[] psblChtrs)
 ② java.Util.Random Class 사용하여 psblChtrs 내에서 사용 가능한 문자열 생성
 ③ 추출된 쿠폰번호중 중복이 존재하는지 체크 (HashMap 사용)

##실행 방법
### 순서대로 수행 필요함.

### 0. 임시 사용자 등록 (id가 "kkoUser1" 인 사용자 ("Lion")을 User 테이블에 등록한다.
> URL : test/userRegst
>
> Method : GET
>

### 1. 랜덤한 코드의 쿠폰을 N개 생성하여 데이터베이스에 보관하는 API를 구현하세요.

> URL : cp/crtn/100

### 2. 생성된 쿠폰중 하나를 사용자에게 지급하는 API를 구현하세요. (여러번 수행하여 유저에게 여러번 지급 가능)

> URL : cp/issue/kkoUser1

### 3. 사용자에게 지급된 쿠폰을 조회하는 API를 구현하세요.

> URL : cp/getIssueInf/kkoUser1

### 4. 지급된 쿠폰중 하나를 사용하는 API를 구현하세요. (쿠폰 재사용은 불가)

> URL : cp/useCoupon/{couponNo}

### 5. 지급된 쿠폰중 하나를 사용 취소하는 API를 구현하세요. (취소된 쿠폰 재사용 가능)
> URL : cp/cancleUsedCoupon/{couponNo}

### 6. 발급된 쿠폰중 당일 만료된 전체 쿠폰 목록을 조회하는 API를 구현하세요. vrEdDt : 조회대상일자
> URL : cp/getExprdCouponLst/{vrEdDt}

