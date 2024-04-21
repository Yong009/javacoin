package com.example.bitcoin.dto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;


@Entity
@Table(name ="member")
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;				//아이디
    private String password;		//비밀번호
    private String accessCode;		//엑세스코드
    private String secretCode;		//시크릿코드
    private String role;			//권한


    public static  Member createMember(MemberVO vo, PasswordEncoder passwordEncoder){

        Member member = new Member();
        member.setId(vo.getId());
        String password = passwordEncoder.encode(vo.getPassword());
        member.setPassword(password);
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
