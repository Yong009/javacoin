package com.example.bitcoin;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;


import com.example.bitcoin.dto.MemberVO;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.bitcoin.service.coinservice;

import jakarta.servlet.http.HttpSession;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Controller
public class GetAccounts {


	@Autowired
	coinservice coinservice2;


	@GetMapping("/test")
	public String test() {
		return "/test.html";
	}

	/*@GetMapping("/mainPage")
	public String mainPage() {
		return "/mainPage.html";
	}*/

	@GetMapping("/login")
	public String loginPage() {
		return "/login.html";
	}

	@GetMapping("/home")
	public String logout() {
		return "/home.html";
	}

	@GetMapping("/login2")
	public String login2() {
		return "/login2.html";
	}


	@ResponseBody
	@PostMapping("/account")
	public String account(@RequestBody MemberVO vo) {

		String accessKey = vo.getAccessCode();
		/*System.getenv("EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8");  */// access 코드
		String secretKey = vo.getSecretCode();
		/*System.getenv("1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG");  */// secret 코드
		String serverUrl = "https://api.upbit.com";

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String jwtToken = JWT.create().withClaim("access_key", accessKey)
				.withClaim("nonce", UUID.randomUUID().toString()).sign(algorithm);

		String authenticationToken = "Bearer " + jwtToken;

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
		request.setHeader("Content-Type", "application/json");
		request.addHeader("Authorization", authenticationToken);

		HttpResponse response = null;
		try {
			response = client.execute(request);


		} catch (IOException e) {
			e.printStackTrace();
		}

		HttpEntity entity = response.getEntity();
		String result = null;
		try {

			result = EntityUtils.toString(entity, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}


	@GetMapping("/mainPage")
	public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user",userDetails.getUsername());
		return "/mainPage.html";
	}


	@PostMapping("/code")
	@ResponseBody
	public MemberVO codeSave(@RequestBody MemberVO vo){

		MemberVO vo2 = new MemberVO();
		vo2 = coinservice2.codeSave(vo);
		return vo2;





	}

	/*public static void main2(String[] args) {

		String accessKey = "EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8";
		System.getenv("EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8");  // access 코드
		String secretKey = "1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG";
		System.getenv("1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG");  // secret 코드
		String serverUrl = "https://api.upbit.com";

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String jwtToken = JWT.create().withClaim("access_key", accessKey)
				.withClaim("nonce", UUID.randomUUID().toString()).sign(algorithm);

		String authenticationToken = "Bearer " + jwtToken;






		// 내 자산 정보

		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(serverUrl + "/v1/accounts");
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", authenticationToken);

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();

			System.out.println(EntityUtils.toString(entity, "UTF-8"));

			// 마켓 정보

			OkHttpClient clients = new OkHttpClient();

			okhttp3.Request requests = new okhttp3.Request.Builder()
					.url("https://api.upbit.com/v1/market/all?isDetails=true").get()         //true = 상세한 정보, false = 간단한 정보
					.addHeader("accept", "application/json").build();

			try {
				Response responses = clients.newCall(requests).execute();
				// 응답처리
				if (responses.body() != null) {
					String responseBody = responses.body().string();
					System.out.println(responseBody);
				} else {
					// 응답 본문이 null인 경우의 처리
				}
			} catch (IOException e) {
				e.printStackTrace();
				//IOException 처리
			}

			// 현재가 정보

			OkHttpClient client2 = new OkHttpClient();

			okhttp3.Request request2 = new okhttp3.Request.Builder()
					.url("https://api.upbit.com/v1/ticker?markets=KRW-BTC").get()               //?markets=KRW-BTC" 비트코인
					.addHeader("accept", "application/json").build();

			// Response response2 = client.newCall(request).execute();
			try {
				Response response2 = client2.newCall(request2).execute();
				// 응답 처리
				if (response2.body() != null) {
					String responseBody2 = response2.body().string();
					System.out.println(responseBody2);
				} else {
					// 응답 본문이 null인 경우의 처리
				}
			} catch (IOException e) {
				e.printStackTrace();
				// IOException 처리
			}


			//최근 체결 내역

			OkHttpClient client3 = new OkHttpClient();

			okhttp3.Request request3 = new okhttp3.Request.Builder()
			  .url("https://api.upbit.com/v1/trades/ticks?market=KRW-BTC&count=1")
			  .get()
			  .addHeader("accept", "application/json")
			  .build();

			try {
				Response response3 = client3.newCall(request3).execute();
				if(response3.body() != null) {
					String responseBody3 = response3.body().string();
					System.out.println(responseBody3);
				}


			} catch (IOException e) {
				e.printStackTrace();
			}


			//시세 캔들 조회

			OkHttpClient client4 = new OkHttpClient();

			okhttp3.Request request4 = new okhttp3.Request.Builder()
			  .url("https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&count=1")   //minutes(분봉), days(일봉),  weeks(주봉), months(월봉)
			  .get()
			  .addHeader("accept", "application/json")
			  .build();

			try {

				Response response4 = client4.newCall(request4).execute();
			}catch(IOException e) {
				e.printStackTrace();
			}








	}*/
}
