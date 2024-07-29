package com.example.bitcoin.dto;



import lombok.Data;

@Data
public class MemberVO {

	public String getRsiAuto() {
		return rsiAuto;
	}

	public void setRsiAuto(String rsiAuto) {
		this.rsiAuto = rsiAuto;
	}

	public String getRsiCoin() {
		return rsiCoin;
	}

	public void setRsiCoin(String rsiCoin) {
		this.rsiCoin = rsiCoin;
	}

	public String getRsiAutoPrice() {
		return rsiAutoPrice;
	}

	public void setRsiAutoPrice(String rsiAutoPrice) {
		this.rsiAutoPrice = rsiAutoPrice;
	}

	public String getRsiOrderPrice() {
		return rsiOrderPrice;
	}

	public void setRsiOrderPrice(String rsiOrderPrice) {
		this.rsiOrderPrice = rsiOrderPrice;
	}

	public String getRsiSellPrice() {
		return rsiSellPrice;
	}

	public void setRsiSellPrice(String rsiSellPrice) {
		this.rsiSellPrice = rsiSellPrice;
	}

	String id; // 아이디
	String password; // 비밀번호
	String accessCode; // 엑세스코드
	String secretCode; // 시크릿코드
	String role; // 권한
	String auto; // 자동매매 y/n

	public String getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(String sellPrice) {
		this.sellPrice = sellPrice;
	}

	String orderPrice; // 살떄 가격
	String autoPrice; // 자동매매 돌리는 돈
	String sellPrice; // 팔때 가격
	/*  public static MemberVO createUser(String id, String pw, PasswordEncoder  passwordEncoder){

		  MemberVO member = new MemberVO();
		  member.id = id;
		  member.password = passwordEncoder.encode(pw);
		  member.role = "ROLE_USER";
		  return member;
	  }*/

	String rsiAuto;
	String rsiCoin;
	String rsiAutoPrice;
	String rsiOrderPrice;
	String rsiSellPrice;

	public String getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(String orderPrice) {
		this.orderPrice = orderPrice;
	}

	public String getAutoPrice() {
		return autoPrice;
	}

	public void setAutoPrice(String autoPrice) {
		this.autoPrice = autoPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccessCode() {
		return accessCode;
	}

	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}

	public String getAuto() {
		return auto;
	}

	public void setAuto(String auto) {
		this.auto = auto;
	}

	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
