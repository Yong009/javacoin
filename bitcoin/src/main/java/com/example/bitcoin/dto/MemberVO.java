package com.example.bitcoin.dto;




import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data

public class MemberVO {

	String id;				//아이디
    String password;		//비밀번호
    String accessCode;		//엑세스코드
    String secretCode;		//시크릿코드
    String role;			//권한

	public static MemberVO createUser(String id, String pw, PasswordEncoder passwordEncoder){

		MemberVO member = new MemberVO();
		member.id = id;
		member.password = passwordEncoder.encode(pw);
		member.role = "N";
		return member;
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
	public String getSecretCode() {
		return secretCode;
	}
	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}



}
