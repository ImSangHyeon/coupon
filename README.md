# KAKAOPAY ��������

**����ȯ��**
  - JAVA8
  - Spring Boot 2.2.11
  - JPA
  - MYSQL
  - LOMBOK

**ROOT Page**
	URL : http://localhost:8080/coupon/

**�����ذ� ����**
### ������ȣ ���� ���
 �� ������ȣ ��� ������ ĳ���� ���� (String[] psblChtrs)
 �� java.Util.Random Class ����Ͽ� psblChtrs ������ ��� ������ ���ڿ� ����
 �� ����� ������ȣ�� �ߺ��� �����ϴ��� üũ (HashMap ���)

**���� ���**
#### ������� ���� �ʿ���.

#### 0. �ӽ� ����� ��� (id�� "kkoUser1" �� ����� ("Lion")�� User ���̺� ����Ѵ�.
> URL : test/userRegst
>
> Method : GET
>

#### 1. ������ �ڵ��� ������ N�� �����Ͽ� �����ͺ��̽��� �����ϴ� API�� �����ϼ���.

> URL : cp/crtn/100

#### 2. ������ ������ �ϳ��� ����ڿ��� �����ϴ� API�� �����ϼ���. (������ �����Ͽ� �������� ������ ���� ����)

> URL : cp/issue/kkoUser1

#### 3. ����ڿ��� ���޵� ������ ��ȸ�ϴ� API�� �����ϼ���.

> URL : cp/getIssueInf/kkoUser1

#### 4. ���޵� ������ �ϳ��� ����ϴ� API�� �����ϼ���. (���� ������ �Ұ�)

> URL : cp/useCoupon/{couponNo}

#### 5. ���޵� ������ �ϳ��� ��� ����ϴ� API�� �����ϼ���. (��ҵ� ���� ���� ����)
> URL : cp/cancleUsedCoupon/{couponNo}

#### 6. �߱޵� ������ ���� ����� ��ü ���� ����� ��ȸ�ϴ� API�� �����ϼ���. vrEdDt : ��ȸ�������
> URL : cp/getExprdCouponLst/{vrEdDt}

