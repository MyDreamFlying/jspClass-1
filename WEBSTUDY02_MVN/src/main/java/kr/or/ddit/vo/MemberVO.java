package kr.or.ddit.vo;
import java.io.Serializable;
import java.util.Set;
/**
 * 회원 관리를 위한 Domain Layer (Java Bean 규약)
 *
 * Data Mapper(SQL Mapper)를 이용한 다중 테이블 조인 방법
 * 
 * ex) 한명의 회원과 그동안 구매한 상품 목록을 함께 조회.
 * 1. 메인 데이터를 가진 메인테이블을 식별(Member)
 * 2. 조인의 대상이 되는 테이블로부터 조회된 데이터를 바인딩 할 VO 설계.
 * 		(MemberVO, ProdVO)
 * 3. 테이블 사이의 관계를 VO 사이의 관계로 구조화.
 * 	1:N - has many (MemberVO has many ProdVO)
 * 	1:1 - has a (ProdVO has a BuyerVO)
 * 
 * 4. resultType을 대신하여 resultMap 으로 수동 바인딩 설정
 * 		1:N - collection 엘리먼트 
 * 		1:1 - association
 * 		(Member.xml -> memberMap)
 * 
 */
public class MemberVO implements Serializable {
	public MemberVO() {
		super();
	}
	public MemberVO(String mem_id, String mem_pass) {
		super();
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
	}
	private String mem_id;
	private String mem_pass;
	private String mem_name;
	private String mem_regno1;
	private String mem_regno2;
	private String mem_bir;
	private String mem_zip;
	private String mem_add1;
	private String mem_add2;
	private String mem_hometel;
	private String mem_comtel;
	private String mem_hp;
	private String mem_mail;
	private String mem_job;
	private String mem_like;
	private String mem_memorial;
	private String mem_memorialday;
	private Integer mem_mileage;
	private String mem_delete;
	
	private Set<ProdVO> prodList;	// has many(1:N) 관계 생성
	
	public Set<ProdVO> getProdList() {
		return prodList;
	}
	public void setProdList(Set<ProdVO> prodList) {
		this.prodList = prodList;
	}
	
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_pass() {
		return mem_pass;
	}
	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public String getMem_regno1() {
		return mem_regno1;
	}
	public void setMem_regno1(String mem_regno1) {
		this.mem_regno1 = mem_regno1;
	}
	public String getMem_regno2() {
		return mem_regno2;
	}
	public void setMem_regno2(String mem_regno2) {
		this.mem_regno2 = mem_regno2;
	}
	public String getMem_bir() {
		return mem_bir;
	}
	public void setMem_bir(String mem_bir) {
		this.mem_bir = mem_bir;
	}
	public String getMem_zip() {
		return mem_zip;
	}
	public void setMem_zip(String mem_zip) {
		this.mem_zip = mem_zip;
	}
	public String getMem_add1() {
		return mem_add1;
	}
	public void setMem_add1(String mem_add1) {
		this.mem_add1 = mem_add1;
	}
	public String getMem_add2() {
		return mem_add2;
	}
	public void setMem_add2(String mem_add2) {
		this.mem_add2 = mem_add2;
	}
	public String getMem_hometel() {
		return mem_hometel;
	}
	public void setMem_hometel(String mem_hometel) {
		this.mem_hometel = mem_hometel;
	}
	public String getMem_comtel() {
		return mem_comtel;
	}
	public void setMem_comtel(String mem_comtel) {
		this.mem_comtel = mem_comtel;
	}
	public String getMem_hp() {
		return mem_hp;
	}
	public void setMem_hp(String mem_hp) {
		this.mem_hp = mem_hp;
	}
	public String getMem_mail() {
		return mem_mail;
	}
	public void setMem_mail(String mem_mail) {
		this.mem_mail = mem_mail;
	}
	public String getMem_job() {
		return mem_job;
	}
	public void setMem_job(String mem_job) {
		this.mem_job = mem_job;
	}
	public String getMem_like() {
		return mem_like;
	}
	public void setMem_like(String mem_like) {
		this.mem_like = mem_like;
	}
	public String getMem_memorial() {
		return mem_memorial;
	}
	public void setMem_memorial(String mem_memorial) {
		this.mem_memorial = mem_memorial;
	}
	public String getMem_memorialday() {
		return mem_memorialday;
	}
	public void setMem_memorialday(String mem_memorialday) {
		this.mem_memorialday = mem_memorialday;
	}
	public Integer getMem_mileage() {
		return mem_mileage;
	}
	public void setMem_mileage(Integer mem_mileage) {
		this.mem_mileage = mem_mileage;
	}
	public String getMem_delete() {
		return mem_delete;
	}
	public void setMem_delete(String mem_delete) {
		this.mem_delete = mem_delete;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mem_id == null) ? 0 : mem_id.hashCode());
		result = prime * result + ((mem_regno1 == null) ? 0 : mem_regno1.hashCode());
		result = prime * result + ((mem_regno2 == null) ? 0 : mem_regno2.hashCode());
		return result;
	}
	@Override	// 상태 비교
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MemberVO other = (MemberVO) obj;
		if (mem_id == null) {
			if (other.mem_id != null)
				return false;
		} else if (!mem_id.equals(other.mem_id))
			return false;
		if (mem_regno1 == null) {
			if (other.mem_regno1 != null)
				return false;
		} else if (!mem_regno1.equals(other.mem_regno1))
			return false;
		if (mem_regno2 == null) {
			if (other.mem_regno2 != null)
				return false;
		} else if (!mem_regno2.equals(other.mem_regno2))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MemberVO [mem_id=" + mem_id + ", mem_name=" + mem_name + ", mem_bir=" + mem_bir + ", mem_zip=" + mem_zip
				+ ", mem_add1=" + mem_add1 + ", mem_add2=" + mem_add2 + ", mem_hometel=" + mem_hometel + ", mem_comtel="
				+ mem_comtel + ", mem_hp=" + mem_hp + ", mem_mail=" + mem_mail + ", mem_job=" + mem_job + ", mem_like="
				+ mem_like + ", mem_memorial=" + mem_memorial + ", mem_memorialday=" + mem_memorialday
				+ ", mem_mileage=" + mem_mileage + ", mem_delete=" + mem_delete + "]";
	}
	
}