package com.example.bitcoin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.Body;
import com.example.bitcoin.dto.CommentVO;
import com.example.bitcoin.dto.IndexData;
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.dto.OrderVO;
import com.example.bitcoin.dto.PagingVO;
import com.example.bitcoin.dto.PriceVO;
import com.example.bitcoin.dto.ProfitVO;
import com.example.bitcoin.dto.QuestionVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;
import com.example.bitcoin.service.serviceimpl.coinserviceimpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Controller
public class GetAccounts {

	@Autowired
	coinservice coinservice2;

	@Autowired
	coinmapper coinmapper4;

	@Autowired
	coinserviceimpl coinserviceimpl2;

	/*public GetAccounts(coinserviceimpl coinserviceimpl2) {
		this.coinserviceimpl2 = coinserviceimpl2;
	}*/

	private static final Logger LOG = LoggerFactory.getLogger(GetAccounts.class);

	// 시작 시 첫 페이지
	@GetMapping("/")
	public String loginPage2() {
		return "chart2";
	}

	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}

//	// 로그인 페이지
//	@GetMapping("/jsp")
//	public String loginPage3() {
//
//			return "login";
//		}
//
//
//	// 로그인 후 첫 페이지 ( 로그인 정보 가져옴 )
//	@GetMapping("/mainPage5")
//	public String getUserInfo5(@AuthenticationPrincipal UserDetails userDetails, Model model) {
//		model.addAttribute("user", userDetails.getUsername());
//		return "mainPage5.jsp";
//	}

	@GetMapping("/error")
	public String error() {

		return "error";

	}

	// 회원가입 페이지
	@GetMapping("/memberJoin")
	public String joinPage() {
		return "memberJoin";
	}

	// 차트
	@GetMapping("/chart")
	public String chart() {
		return "chart";

	}

	// 차트2
	@GetMapping("/chart2")
	public String chart2() {
		return "chart2";

	}

	@ResponseBody
	@PostMapping("/memberAll")
	public List<MemberVO> memberAll() {

		List<MemberVO> vo = coinservice2.getMemberAll();
		return vo;
	}

	// 회원탈퇴
	@ResponseBody
	@PostMapping("/deleteMember")
	public void deleteMember(@RequestBody MemberVO vo) {

		coinservice2.deleteMember(vo.getId());

	}

	// 회원가입

	@GetMapping("/join")
	public void registerMember(MemberVO member) {

		coinservice2.memberJoin(member);

	}

	// 아이디 중복확인
	@GetMapping("/check")
	public int checkId(MemberVO member) {
		int a = coinservice2.checkId(member);
		return a;
	}

	// 이미지
	@GetMapping("/bit")
	public String image() {
		return "bitcoin.jpg";
	}

	// 이미지2
	@GetMapping("/bit2")
	public String image2() {
		return "Bitcoin_Cash.png";
	}

	@GetMapping("/home")
	public String logout() {
		return "home";
	}

	// 주문하기 페이지
	@GetMapping("/orderPage")
	public String orderPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "order";
	}

	// 자동매매 페이지
	@GetMapping("/changeAuto")
	public String chagePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "changeAuto";
	}

	// 자동매매 모니터링 페이지
	@GetMapping("/monitoring")
	public String monitoring(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "monitoring";
	}

	// 마이 페이지
	@GetMapping("/myPage")
	public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "myContents";
	}

	// 잔고 조회
	@ResponseBody
	@PostMapping("/account7")
	public String accountTest(@RequestBody MemberVO vo) {

		String result = coinservice2.account7(vo);

		return result;
	}

	// 로그인 후 첫 페이지 ( 로그인 정보 가져옴 )
	@GetMapping("/mainPage")
	public String getUserInfo(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "mainPage";
	}

	// 로그인 후 코드 가져오기
	@ResponseBody
	@PostMapping("/getCode")
	public List<MemberVO> getCode(@RequestBody MemberVO vo) {

		List<MemberVO> vo2 = new ArrayList<>();

		vo2 = coinservice2.getCode(vo.getId());

		return vo2;

	}

	// 코드 저장
	@ResponseBody
	@PostMapping("/saveCode")
	public boolean saveCode(@RequestBody MemberVO vo) {

		List<MemberVO> vo2 = new ArrayList<>();

		boolean a = coinservice2.saveCode(vo);

		return a;
	}

	// 회원 정보 수정
	@ResponseBody
	@PostMapping("/updateMember")
	public void updateMember(@RequestBody MemberVO vo) {

		coinservice2.updateMember(vo);
	}

	// 게시판 페이지 이동
	@GetMapping("/board")
	public String board(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "board";
	}

	// 게시판 페이지 이동(비회원용)
	@GetMapping("/board2")
	public String board2() {

		return "board2";
	}

	// 게시판 상세 페이지 이동 ( 비회원용 )
	@GetMapping("/boardDetail2")
	public String boardDetail2() {

		return "boardDetail2";
	}

	// 게시판 글쓰기 페이지
	@GetMapping("/boardwrite")
	public String write(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "write";
	}

	@ResponseBody
	// 게시판 글쓰기 등록
	@PostMapping("/insertBoard")
	public void insertBoard(@RequestBody BoardVO vo) {

		coinservice2.insertBoard(vo);
	}

	// 게시판 전체 리스트
	@ResponseBody
	@GetMapping("/boardListAjax")
	public List<BoardVO> boardListAjax() {

		List<BoardVO> list = coinservice2.getList();
		return list;
	}

	// 게시판 전체 개수

	@ResponseBody
	@GetMapping("/boardMax")
	public int boardMax() {

		return coinservice2.getMax();
	}


	  // 회원 총 수

	  @ResponseBody
	  @GetMapping("/memberMax")
	  public int memberMax() {

		  return  coinservice2.getMemberMax();
	  }


	// 회원 페이징
	@ResponseBody
	@PostMapping("/memberListAjax2")
	public List<MemberVO> memberListAjax2(@RequestBody PagingVO vo) {

		List<MemberVO> list = coinservice2.getMemberLists(vo);

		return list;
	}

	// 게시판 페이징 적용
	@ResponseBody
	@PostMapping("/boardListAjax2")
	public List<BoardVO> boardListAjax2(@RequestBody PagingVO vo) {

		List<BoardVO> list = coinservice2.getLists(vo);

		return list;
	}

	// 게시판 상세 페이지
	@GetMapping("/boardDetail")
	public String boardDetail(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "boardDetail";
	}

	// 게시판 상세 내용
	@ResponseBody
	@PostMapping("/boardDetailAjax")
	public List<BoardVO> boardDetailAjax(@RequestBody BoardVO vo) {

		List<BoardVO> list = coinservice2.getListDetail(vo.getSeq());

		return list;
	}

	// 게시판 수정하기
	@ResponseBody
	@PostMapping("/updateBoard")
	public void updateBoard(@RequestBody BoardVO vo) {

		coinservice2.updateBoard(vo);
	}

	// 조회수 증가
	@ResponseBody
	@PostMapping("/updateView")
	public void updateView(@RequestBody BoardVO vo) {

		coinservice2.updateView(vo);
	}

	// 게시판 글 삭제
	@ResponseBody
	@PostMapping("/deleteBoard")
	public void deleteBoard(@RequestBody BoardVO vo) {

		coinservice2.deleteBoard(vo);
	}

	// 댓글 불러오기
	@ResponseBody
	@PostMapping("/comment")
	public List<CommentVO> comment(@RequestBody CommentVO vo) {

		List<CommentVO> list = coinservice2.getComment(vo);

		return list;
	}

	// 댓글 등록
	@ResponseBody
	@PostMapping("/insertComment")
	public void insertComment(@RequestBody CommentVO vo) {

		coinservice2.insertComment(vo);
	}

	// 댓글 삭제
	@ResponseBody
	@PostMapping("/deleteComment")
	public void deleteComment(@RequestBody CommentVO vo) {

		coinservice2.deleteComment(vo);
	}

	// 건의 사항 페에지
	@GetMapping("/question")
	public String questionPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "question";
	}

	// 건의 사항 불러오기(전체)
	@ResponseBody
	@GetMapping("/questionAjax")
	public List<QuestionVO> getQuestion() {

		List<QuestionVO> list = coinservice2.getQuestion();

		return list;
	}

	// 건의 사항 불러오기 ( 그 사람만 )
	@ResponseBody
	@GetMapping("/questionAjax2")
	public List<QuestionVO> getQuestion2(@RequestBody QuestionVO vo) {

		List<QuestionVO> list = coinservice2.getQuestion2(vo.getWriter());

		return list;
	}

	// 건의 사항 상세
	@ResponseBody
	@PostMapping("/questionDetailAjax")
	public List<QuestionVO> getQuestionDetail(@RequestBody QuestionVO vo) {

		List<QuestionVO> list = coinservice2.getQuestionDetail(vo.getSeq());

		return list;
	}

	// 건의 사항 상세 페이지
	@ResponseBody
	@GetMapping("/questionDetail")
	public String questionDetailPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "questionDetail";
	}

	// 헤더 호출
	@GetMapping("/header.html")
	public String header(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "header";
	}

	// 헤더2 호출
	@GetMapping("/header2.html")
	public String header2() {

		return "header2";
	}

	// 사이드바 호출
	@GetMapping("/sidebar.html")
	public String sidebar(@AuthenticationPrincipal UserDetails userDetails, Model model){
		model.addAttribute("user", userDetails.getUsername());
		return "sidebar";
	}

	// 푸터 호출
	@GetMapping("/footer.html")
	public String footer() {
		return "footer";
	}

	// 회원 관리
	@GetMapping("/memberManage")
	public String memberManage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "memberManage";
	}

	// 마켓 정보
	@ResponseBody
	@GetMapping("/market7")
	public String marketTest() {

		return coinservice2.market7();
	}

	// 거래 내역 페이지
	@GetMapping("/buySellPage")
	public String buySellPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		model.addAttribute("user", userDetails.getUsername());
		return "buySell";
	}

	// 주문 리스트
	@ResponseBody
	@PostMapping("/orderList")
	public String orderList(@RequestBody MemberVO vo) {

		List<MemberVO> list = coinservice2.getCode(vo.getId());

		System.out.println(list.get(0).getSecretCode());
		String accessKey = list.get(0).getAccessCode();
		String secretKey = list.get(0).getSecretCode();
		String serverUrl = "https://api.upbit.com";

		HashMap<String, String> params = new HashMap<>();
		params.put("state", "done");

		String[] uuids = {
				/* "9ca023a5-851b-4fec-9f0a-48cd83c2eaae" */
		};

		ArrayList<String> queryElements = new ArrayList<>();
		for (Map.Entry<String, String> entity : params.entrySet()) {
			queryElements.add(entity.getKey() + "=" + entity.getValue());
		}
		for (String uuid : uuids) {
			queryElements.add("uuids[]=" + uuid);
		}

		String queryString = String.join("&", queryElements.toArray(new String[0]));

		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		try {
			md.update(queryString.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

		Algorithm algorithm = Algorithm.HMAC256(secretKey);
		String jwtToken = JWT.create().withClaim("access_key", accessKey)
				.withClaim("nonce", UUID.randomUUID().toString()).withClaim("query_hash", queryHash)
				.withClaim("query_hash_alg", "SHA512").sign(algorithm);

		String authenticationToken = "Bearer " + jwtToken;
		String result = null;
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(serverUrl + "/v1/orders?" + queryString);
			request.setHeader("Content-Type", "application/json");
			request.addHeader("Authorization", authenticationToken);

			HttpResponse response = client.execute(request);
			HttpEntity entity = response.getEntity();
			// System.out.println(EntityUtils.toString(entity, "UTF-8"));
			result = EntityUtils.toString(entity, "UTF-8");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	// 자동매매 on
	@ResponseBody
	@PostMapping("/autoTrade2")
	public void auto2(@RequestBody MemberVO member) {
		coinservice2.autoOn(member.getId());
	}

	// 변동성 돌파 전략 자동 끄기
	@ResponseBody
	@PostMapping("/autoStop")
	public void autoStop(@RequestBody MemberVO vo) {

		coinservice2.autoStop7(vo.getId());
	}

	// 변동성 돌파 전략 스케줄러 ( 5초마다 계속 )
	@Scheduled(fixedRate = 5000) // 5초마다 실행
	public void autoScuedule() {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		List<MemberVO> list = coinservice2.autoCheck();

		if (list == null || list.isEmpty()) {
			logger.info("자동매매 실행한 유저가 존재하지 않습니다.");
			return;
		}

		int i, j, k;

		String balance, currency, market, market2, nowPrice2;
		Long balances;
		BigDecimal lowPrice, highPrice, prevClosePrice, targetPrice, minus, multi, nowPrice, lowPrice5, highPrice5,
				lowPrice2, highPrice2;
		BigDecimal dotFive = new BigDecimal(0.5);

		LocalTime now3 = LocalTime.now();
		DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm");
		String formatedNow3 = now3.format(formatter3);

		if (formatedNow3.equals("08:59")) {
			String mk = null;

			try {
				mk = coinservice2.currentPrice7();
			} catch (Exception e) {
				logger.error("전일 고가, 저가의 API 호출에 실패했습니다: {}", e.getMessage());

			}

			JSONArray jsonArray5 = new JSONArray(mk);

			for (int p = 0; p < jsonArray5.length(); p++) {
				JSONObject jsonObject5 = jsonArray5.getJSONObject(p);
				currency = jsonObject5.getString("market");
				highPrice5 = jsonObject5.getBigDecimal("high_price");
				lowPrice5 = jsonObject5.getBigDecimal("low_price");

				PriceVO po = new PriceVO();
				po.setHighPrice(highPrice5);
				po.setLowPrice(lowPrice5);
				coinservice2.updatePrice(po);
				break;
			}

		}

		for (i = 0; i < list.size(); i++) {

			String accc = list.get(i).getAccessCode();
			String sece = list.get(i).getSecretCode();
			String userId = list.get(i).getId();
			String autoPrice = list.get(i).getAutoPrice();

			if ((accc == null || accc.equals(' ')) || (sece == null || sece.equals(' '))
					|| (autoPrice == null || autoPrice.equals(' '))) {

				logger.info(
						"사용자 {}의 secret코드 또는 access코드 저장하지 않거나 가격을 입력하지 않았습니다. 저장 및 입력 되어야 자동매매 프로그램을 작동시킬 수 있습니다.!!",
						userId);
				continue;
			}

			MemberVO member = new MemberVO();
			member.setAccessCode(accc);
			member.setSecretCode(sece);
			member.setAutoPrice(autoPrice);

			// String mk = null;
			String cp = null;
			String account = null;

			try {
				// mk = coinservice2.market7(); //마켓
				cp = coinservice2.currentPrice7(); // 현재가
				account = coinservice2.account7(member); // 잔고
			} catch (Exception e) {
				logger.error("사용자 {}의 API 호출에 실패했습니다: {}", userId, e.getMessage());
				continue;
			}

			// JSONArray jsonArray = new JSONArray(mk);
			JSONArray jsonArray2 = new JSONArray(cp); // 현재가
			JSONArray jsonArray3 = new JSONArray(account); // 잔고

			boolean hasKrwBtc = false;




//			String market10;
//			BigDecimal nowPrice10;
//
//				for (int pm = 0; pm < jsonArray2.length(); pm++) {
//
//					JSONObject jsonObject10 = jsonArray2.getJSONObject(pm);
//					market10 = jsonObject10.getString("market");
//					if(market10.equals("KRW-BTC")) {
//						nowPrice10 = jsonObject10.getBigDecimal("trade_price");
//						List<MemberVO> list10 = coinservice2.getCode(userId);
//						list10.get(0).getOrderPrice();
//						list10.get(0).getSellPrice();
//					}
//
//				}

			for (j = 0; j < jsonArray3.length(); j++) {
				JSONObject jsonObject3 = jsonArray3.getJSONObject(j);
				currency = jsonObject3.getString("currency");

				if (currency.equals("BTC")) {
					hasKrwBtc = true;
					LocalTime now = LocalTime.now();
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
					String formatedNow = now.format(formatter);

					if (formatedNow.equals("09:00")) {
						balance = jsonObject3.getString("balance");
						OrderVO vo3 = new OrderVO();
						vo3.setCoin("KRW-BTC");
						vo3.setAccessCode(member.getAccessCode());
						vo3.setSecretCode(member.getSecretCode());
						vo3.setOrderType("ask");
						vo3.setVolume(balance);

//						String market10;
//						BigDecimal nowPrice10;

						try {
							coinservice2.sell7(vo3);
							ProfitVO vo11 = new ProfitVO();
							vo11.setId(userId);
							coinservice2.saveProfit(vo11);
//							for (int pm = 0; pm < jsonArray2.length(); pm++) {
//
//								JSONObject jsonObject10 = jsonArray2.getJSONObject(pm);
//								market10 = jsonObject10.getString("market");
//								if(market10.equals("KRW-BTC")) {
//									nowPrice10 = jsonObject10.getBigDecimal("trade_price");
//								}
//							}

						} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
							logger.error("사용자 {}의 매도 주문 실행 중 오류 발생: {}", userId, e.getMessage());
						}
						logger.info("사용자 {}의 매도 주문 실행: 코인 = KRW-BTC, 잔고 = {}", userId, balance);
						account = coinservice2.account7(member);
						jsonArray3 = new JSONArray(account);
					} else {
						logger.info("사용자 {}의 9시가 되지 않아 매도 주문이 체결되지 않았습니다.", userId);
					}
					break; // 더 이상 순회할 필요가 없으므로 루프 종료
				}
			}

			LocalTime now = LocalTime.now();
			DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("HH:mm");
			String formatedNow2 = now.format(formatter2);

			if (hasKrwBtc == false && !formatedNow2.equals("09:00") && !formatedNow2.equals("08:59")) {

				for (j = 0; j < jsonArray3.length(); j++) {
					JSONObject jsonObject5 = jsonArray3.getJSONObject(j);
					currency = jsonObject5.getString("currency");

					if (currency.equals("KRW")) {
						balance = jsonObject5.getString("balance");
						BigDecimal big = new BigDecimal(balance);
						BigDecimal Dec = big.setScale(0, BigDecimal.ROUND_DOWN); // 숫자를 정수화
						balances = Dec.longValue();
						String balances2 = balances.toString();

						if (balances <= 5000) {
							break;
						}

						for (k = 0; k < jsonArray3.length(); k++) {
							JSONObject jsonObject2 = jsonArray2.getJSONObject(k);
							market = jsonObject2.getString("market");

							if (market.contains("KRW-BTC")) {

								PriceVO pv2 = new PriceVO();
								pv2.setSeq(1);

								List<PriceVO> pv =coinservice2.getPriceList();

								highPrice2 = pv.get(0).getHighPrice();
								lowPrice2 = pv.get(0).getLowPrice();
								prevClosePrice = jsonObject2.getBigDecimal("prev_closing_price");
								nowPrice = jsonObject2.getBigDecimal("trade_price");
								minus = highPrice2.subtract(lowPrice2);
								// minus = highPrice.subtract(lowPrice);
								multi = minus.multiply(dotFive);
								targetPrice = prevClosePrice.add(multi);
								nowPrice2 = nowPrice.toString();

								if (targetPrice.compareTo(nowPrice) <= 0) {
									logger.info("사용자 {}의 목표 타겟 도달: 현재 가격 = {}, 목표 가격 = {}, 매수 금액 = {}", userId,
											nowPrice, targetPrice, autoPrice);
									OrderVO vo2 = new OrderVO();
									vo2.setCoin("KRW-BTC");
									vo2.setAccessCode(member.getAccessCode());
									vo2.setSecretCode(member.getSecretCode());
									vo2.setOrderType("bid");
									vo2.setPrice(member.getAutoPrice());
									// vo2.setPrice(balances2);

									try {
										coinservice2.order7(vo2);
										MemberVO price2 = new MemberVO();
										price2.setId(userId);
										price2.setOrderPrice(nowPrice2);
										coinservice2.saveOrderPrice(price2);
									} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {

										logger.error("사용자 {}의 매수 주문 실행 중 오류 발생: {}", userId, e.getMessage());
									}
									logger.info("사용자 {}의  매수 주문 실행: 코인 = KRW-BTC, 가격 = {}", userId, balances);
									account = coinservice2.account7(member);
									jsonArray3 = new JSONArray(account);
								} else {
									logger.info("사용자 {}의  목표가에 오지 않아 매수 주문 실행 안함: 코인 = KRW-BTC, 매수 금액 = {}, 목표 가격 = {}",
											userId, autoPrice, targetPrice);
								}
							}
						}
					}

				}
			}

		}
	}

	// 이건 rsi 9랑 값이 같다!!! ( 업비트 rsi 방식 )
	 	@ResponseBody
	    @GetMapping("/upbitRsi")
	    public double getCurrentRsi() {
	        String bitcoinSymbol = "bitcoin";
	        int period = 14;
	        List<Double> closes = new ArrayList<>();

	        try {
	            // CoinGecko API를 통해 비트코인의 최근 14일 가격 데이터 가져오기
	            String apiUrl = "https://api.coingecko.com/api/v3/coins/" + bitcoinSymbol + "/market_chart";
	            String params = "?vs_currency=usd&days=14&interval=daily";
	            URL url = new URL(apiUrl + params);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");

	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder response = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            reader.close();

	            // JSON 데이터 파싱
	            JSONArray pricesArray = new JSONObject(response.toString()).getJSONArray("prices");
	            for (int i = 0; i < pricesArray.length(); i++) {
	                JSONArray priceData = pricesArray.getJSONArray(i);
	                double closePrice = priceData.getDouble(1); // 종가는 배열의 두 번째 요소입니다.
	                closes.add(closePrice);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return -1; // 오류 발생 시 -1 반환
	        }

	        // 데이터가 부족한 경우 처리
	        if (closes.size() < period + 1) {
	            return -1; // 데이터가 부족한 경우 -1 반환
	        }

	        // RSI 계산
	        double sumGain = 0.0;
	        double sumLoss = 0.0;

	        // 초기 SMA (Simple Moving Average) 계산
	        for (int i = 1; i <= period; i++) {
	            double change = closes.get(i) - closes.get(i - 1);
	            if (change >= 0) {
	                sumGain += change;
	            } else {
	                sumLoss -= change;
	            }
	        }
	        double avgGain = sumGain / period;
	        double avgLoss = sumLoss / period;

	        // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산
	        for (int i = period; i < closes.size(); i++) {
	            double change = closes.get(i) - closes.get(i - 1);
	            double gain = (change >= 0) ? change : 0;
	            double loss = (change < 0) ? -change : 0;

	            avgGain = (avgGain * (period - 1) + gain) / period;
	            avgLoss = (avgLoss * (period - 1) + loss) / period;
	        }

	        double rs = avgGain / avgLoss;
	        double rsi = 100 - (100 / (1 + rs));

	        return rsi;
	    }




	 	//일반 rsi ( 업비트 rsi 14랑 흡사 )
	 	@ResponseBody
	    @GetMapping("/rsi")
	    public double getCurrentRsi2() {
	        String bitcoinSymbol = "bitcoin";
	        int period = 14;
	        List<Double> closes = new ArrayList<>();

	        try {
	            // CoinGecko API를 통해 비트코인의 최근 15일 가격 데이터 가져오기
	            String apiUrl = "https://api.coingecko.com/api/v3/coins/" + bitcoinSymbol + "/market_chart";
	            String params = "?vs_currency=usd&days=15&interval=daily"; // 15일을 가져와서 14일 간의 변화를 계산
	            URL url = new URL(apiUrl + params);
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            conn.setRequestMethod("GET");

	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	            StringBuilder response = new StringBuilder();
	            String line;
	            while ((line = reader.readLine()) != null) {
	                response.append(line);
	            }
	            reader.close();

	            // JSON 데이터 파싱
	            JSONArray pricesArray = new JSONObject(response.toString()).getJSONArray("prices");
	            for (int i = 0; i < pricesArray.length(); i++) {
	                JSONArray priceData = pricesArray.getJSONArray(i);
	                double closePrice = priceData.getDouble(1); // 종가는 배열의 두 번째 요소입니다.
	                closes.add(closePrice);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return -1; // 오류 발생 시 -1 반환
	        }

	        // 데이터가 부족한 경우 처리
	        if (closes.size() < period + 1) {
	            return -1; // 데이터가 부족한 경우 -1 반환
	        }

	        // RSI 계산
	        double[] rsiValues = calculateRsi(closes, period);

	        return rsiValues[rsiValues.length - 1]; // 최신 RSI 값 반환
	    }

	    private double[] calculateRsi(List<Double> closes, int period) {
	        double[] rsiValues = new double[closes.size()];
	        double sumGain = 0.0;
	        double sumLoss = 0.0;

	        // 초기 SMA (Simple Moving Average) 계산
	        for (int i = 1; i <= period; i++) {
	            double change = closes.get(i) - closes.get(i - 1);
	            if (change >= 0) {
	                sumGain += change;
	            } else {
	                sumLoss -= change;
	            }
	        }
	        double avgGain = sumGain / period;
	        double avgLoss = sumLoss / period;

	        // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산
	        for (int i = period; i < closes.size(); i++) {
	            double change = closes.get(i) - closes.get(i - 1);
	            double gain = (change >= 0) ? change : 0;
	            double loss = (change < 0) ? -change : 0;

	            avgGain = (avgGain * (period - 1) + gain) / period;
	            avgLoss = (avgLoss * (period - 1) + loss) / period;

	            double rs = avgGain / avgLoss;
	            double rsi = 100 - (100 / (1 + rs));
	            rsiValues[i] = rsi;
	        }

	        return rsiValues;
	    }

	    //업비트 rsi14 적용
	    @ResponseBody
	    @GetMapping("/rsi14")
	    public double getCurrentRsi14() {
	    	  String market = "KRW-BTC";
	          int period = 14;
	          List<BigDecimal> closes = new ArrayList<>();

	          OkHttpClient client = new OkHttpClient();

	          // Upbit API를 통해 비트코인의 최근 100개의 3분봉 가격 데이터 가져오기
	          String apiUrl = "https://api.upbit.com/v1/candles/minutes/3?market=" + market + "&count=100";
	          Request request = new Request.Builder()
	                  .url(apiUrl)
	                  .get()
	                  .build();

	          try (Response response = client.newCall(request).execute()) {
	              if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

	              String responseBody = response.body().string();

	              // JSON 데이터 파싱
	              JSONArray candlesArray = new JSONArray(responseBody);
	              for (int i = 0; i < candlesArray.length(); i++) {
	                  JSONObject candle = candlesArray.getJSONObject(i);
	                  BigDecimal closePrice = candle.getBigDecimal("trade_price");
	                  closes.add(closePrice);
	              }
	          } catch (IOException e) {
	              e.printStackTrace();
	              return -1; // 오류 발생 시 -1 반환
	          }

	          // 데이터가 부족한 경우 처리
	          if (closes.size() < period + 1) {
	              return -1; // 데이터가 부족한 경우 -1 반환
	          }

	          // RSI 계산
	          double rsi = calculateRsi14(closes, period);

	          return rsi; // 최신 RSI 값 반환
	      }

	      private double calculateRsi14(List<BigDecimal> closes, int period) {
	          BigDecimal sumGain = BigDecimal.ZERO;
	          BigDecimal sumLoss = BigDecimal.ZERO;

	          // 초기 SMA (Simple Moving Average) 계산
	          for (int i = 1; i <= period; i++) {
	              BigDecimal change = closes.get(i).subtract(closes.get(i - 1));
	              if (change.compareTo(BigDecimal.ZERO) >= 0) {
	                  sumGain = sumGain.add(change);
	              } else {
	                  sumLoss = sumLoss.add(change.abs());
	              }
	          }
	          BigDecimal avgGain = sumGain.divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
	          BigDecimal avgLoss = sumLoss.divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);

	          // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산
	          for (int i = period; i < closes.size(); i++) {
	              BigDecimal change = closes.get(i).subtract(closes.get(i - 1));
	              BigDecimal gain = (change.compareTo(BigDecimal.ZERO) >= 0) ? change : BigDecimal.ZERO;
	              BigDecimal loss = (change.compareTo(BigDecimal.ZERO) < 0) ? change.abs() : BigDecimal.ZERO;

	              avgGain = (avgGain.multiply(BigDecimal.valueOf(period - 1)).add(gain)).divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
	              avgLoss = (avgLoss.multiply(BigDecimal.valueOf(period - 1)).add(loss)).divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);

	              double rs = avgGain.divide(avgLoss, 4, RoundingMode.HALF_UP).doubleValue();
	              double rsi = 100 - (100 / (1 + rs));
	              return rsi;
	          }

	          return -1; // 데이터가 부족한 경우 -1 반환
	      }




	    //거래대금 50개 상위
	    @ResponseBody
	    @GetMapping("/rsiSearch")
	    public String rsiSearch() {

	    	String a = coinservice2.currentPrice7();

	    	JSONArray jsonArray = new JSONArray(a);
	    	List<JSONObject> jsonObjects = new ArrayList<>();
	    	for(int i=0; i< jsonArray.length(); i++) {
	    		JSONObject jsonobject =jsonArray.getJSONObject(i);
	    		jsonObjects.add(jsonobject);
	    	}

	    	jsonObjects.sort(Comparator.comparing(obj ->obj.getBigDecimal("acc_trade_price_24h"),Comparator.reverseOrder()));

	    	List<JSONObject> top50List = jsonObjects.subList(0, Math.min(jsonObjects.size(), 50));

	    	System.out.println("내림차순");

	    	for(JSONObject obj : top50List) {
	    		System.out.println(obj.getString("market")+ ": " + obj.getBigDecimal("acc_trade_price_24h"));
	    	}


	    	//jsonArray.sort(Comparator.comparing(e -> new BigDecimal(e.getAsJsonObject().get(""))))
//	    	for(int i = 0; i<jsonArray.length(); i++) {
//	    		JSONObject jsonObject = jsonArray.getJSONObject(i);
//	    		BigDecimal price = jsonObject.getBigDecimal("acc_trade_price_24h");
//	    		System.out.println(price);
//	    	}





	    	return "test";

	    }




//	    //업비트 api 이용 rsi
//	    @ResponseBody
//	    @GetMapping("/rsi14")
//	    public double getRsi14() throws IOException {
//
//	    	String MARKET_URL = "https://api.upbit.com/v1/candles/minutes/3";
//	        String MARKET_PARAM = "?market=KRW-BTC&count=100"; // 최근 100개의 3분봉 데이터를 가져옴
//
//	    	String url = MARKET_URL + MARKET_PARAM;
//
//	    	okhttp3.Request request = new Request.Builder()
//	                .url(url)
//	                .get()
//	                .build();
//
//	    	 OkHttpClient client = new OkHttpClient();
//	    	 okhttp3.Response response = client.newCall(request).execute();
//
//	    	 String responseBody = response.body().string();
//
//
//	        //String responseBody = okhttp3.Request.Get(url).execute().returnContent().asString();
//
//	        JSONArray jsonArray = new JSONArray(responseBody);
//	        List<BigDecimal> closePrices = new ArrayList<>();
//
//	        for (int i = 0; i < jsonArray.length(); i++) {
//	            JSONObject jsonObject = jsonArray.getJSONObject(i);
//	            BigDecimal closePrice = jsonObject.getBigDecimal("trade_price");
//	            closePrices.add(closePrice);
//	        }
//
//	        int period = 14; // RSI 기간 설정 (14일 기간)
//	        double rsi = calculateRSI4(closePrices, period);
//
//	        return rsi;
//
//	    }
//
//	    private double calculateRSI4(List<BigDecimal> closePrices, int period) {
//	        if (closePrices.size() < period + 1) {
//	            throw new IllegalArgumentException("Insufficient data points for RSI calculation");
//	        }
//
//	        List<BigDecimal> priceChanges = new ArrayList<>();
//	        for (int i = 1; i < closePrices.size(); i++) {
//	            BigDecimal priceChange = closePrices.get(i).subtract(closePrices.get(i - 1));
//	            priceChanges.add(priceChange);
//	        }
//
//	        List<BigDecimal> gainList = new ArrayList<>();
//	        List<BigDecimal> lossList = new ArrayList<>();
//
//	        for (int i = 0; i < period; i++) {
//	            BigDecimal priceChange = priceChanges.get(i);
//	            if (priceChange.compareTo(BigDecimal.ZERO) > 0) {
//	                gainList.add(priceChange);
//	                lossList.add(BigDecimal.ZERO);
//	            } else {
//	                gainList.add(BigDecimal.ZERO);
//	                lossList.add(priceChange.abs());
//	            }
//	        }
//
//	        BigDecimal avgGain = calculateAverage(gainList);
//	        BigDecimal avgLoss = calculateAverage(lossList);
//
//	        double rs = avgGain.divide(avgLoss, 4, RoundingMode.HALF_UP).doubleValue();
//	        double rsi = 100.0 - (100.0 / (1.0 + rs));
//
//	        return rsi;
//	    }
//
//	    private BigDecimal calculateAverage(List<BigDecimal> values) {
//	        BigDecimal sum = BigDecimal.ZERO;
//	        for (BigDecimal value : values) {
//	            sum = sum.add(value);
//	        }
//	        System.out.println(sum.divide(BigDecimal.valueOf(values.size()), 4, RoundingMode.HALF_UP));
//	        return sum.divide(BigDecimal.valueOf(values.size()), 4, RoundingMode.HALF_UP);
//
//	    }
//	    @ResponseBody
//	    @GetMapping("/rsi14")
//	    public double getCurrentRsi14() {
//	        String market = "KRW-BTC";
//	        int period = 14;
//	        List<BigDecimal> closes = new ArrayList<>();
//
//	        OkHttpClient client = new OkHttpClient();
//
//	        // Upbit API를 통해 비트코인의 최근 100개의 3분봉 가격 데이터 가져오기
//	        String apiUrl = "https://api.upbit.com/v1/candles/minutes/3?market=" + market + "&count=100";
//	        Request request = new Request.Builder()
//	                .url(apiUrl)
//	                .get()
//	                .build();
//
//	        try (Response response = client.newCall(request).execute()) {
//	            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//	            String responseBody = response.body().string();
//
//	            // JSON 데이터 파싱
//	            JSONArray candlesArray = new JSONArray(responseBody);
//	            for (int i = 0; i < candlesArray.length(); i++) {
//	                JSONObject candle = candlesArray.getJSONObject(i);
//	                BigDecimal closePrice = candle.getBigDecimal("trade_price");
//	                closes.add(closePrice);
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return -1; // 오류 발생 시 -1 반환
//	        }
//
//	        // 데이터가 부족한 경우 처리
//	        if (closes.size() < period + 1) {
//	            return -1; // 데이터가 부족한 경우 -1 반환
//	        }
//
//	        // RSI 계산
//	        double[] rsiValues = calculateRsi14(closes, period);
//
//	        return rsiValues[rsiValues.length - 1]; // 최신 RSI 값 반환
//	    }
//
//	    private double[] calculateRsi14(List<BigDecimal> closes, int period) {
//	        double[] rsiValues = new double[closes.size()];
//	        BigDecimal sumGain = BigDecimal.ZERO;
//	        BigDecimal sumLoss = BigDecimal.ZERO;
//
//	        // 초기 SMA (Simple Moving Average) 계산
//	        for (int i = 1; i <= period; i++) {
//	            BigDecimal change = closes.get(i).subtract(closes.get(i - 1));
//	            if (change.compareTo(BigDecimal.ZERO) >= 0) {
//	                sumGain = sumGain.add(change);
//	            } else {
//	                sumLoss = sumLoss.add(change.abs());
//	            }
//	        }
//	        BigDecimal avgGain = sumGain.divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
//	        BigDecimal avgLoss = sumLoss.divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
//
//	        // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산
//	        for (int i = period; i < closes.size(); i++) {
//	            BigDecimal change = closes.get(i).subtract(closes.get(i - 1));
//	            BigDecimal gain = (change.compareTo(BigDecimal.ZERO) >= 0) ? change : BigDecimal.ZERO;
//	            BigDecimal loss = (change.compareTo(BigDecimal.ZERO) < 0) ? change.abs() : BigDecimal.ZERO;
//
//	            avgGain = (avgGain.multiply(BigDecimal.valueOf(period - 1)).add(gain)).divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
//	            avgLoss = (avgLoss.multiply(BigDecimal.valueOf(period - 1)).add(loss)).divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
//
//	            double rs = avgGain.divide(avgLoss, 4, RoundingMode.HALF_UP).doubleValue();
//	            double rsi = 100 - (100 / (1 + rs));
//	            rsiValues[i] = rsi;
//	        }
//
//	        return rsiValues;
//	    }

	    //

//	    @ResponseBody
//	    @GetMapping("/rsiSearch")
//	    public String rsiSearch() {
//
//	    	 String MARKET_URL = "https://api.upbit.com/v1/market/all";
//	    	 String TICKER_URL = "https://api.upbit.com/v1/ticker?markets=";
//
//	    	 CloseableHttpClient httpClient = HttpClients.createDefault();
//
//	    	 try {
//	    		  HttpGet request = new HttpGet(MARKET_URL);
//	              CloseableHttpResponse response = httpClient.execute(request);
//	              ObjectMapper mapper = new ObjectMapper();
//
//	              if (response.getCode() == 200) {
//	                  List<Market> markets = mapper.readValue(response.getEntity().getContent(),
//	                          new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Market.class));
//
//	                  // Filter KRW markets
//	                  List<String> krwMarkets = markets.stream()
//	                          .filter(market -> market.getMarket().startsWith("KRW"))
//	                          .map(Market::getMarket)
//	                          .collect(Collectors.toList());
//
//	                  String marketsParam = String.join(",", krwMarkets);
//	                  HttpGet tickerRequest = new HttpGet(TICKER_URL + marketsParam);
//	                  CloseableHttpResponse tickerResponse = httpClient.execute(tickerRequest);
//
//	                  if (tickerResponse.getCode() == 200) {
//	                      List<Ticker> tickers = mapper.readValue(tickerResponse.getEntity().getContent(),
//	                              new ObjectMapper().getTypeFactory().constructCollectionType(List.class, Ticker.class));
//
//	                      // Step 3: Sort tickers by 24h trade price in descending order
//	                      tickers.sort((t1, t2) -> Double.compare(t2.getAcc_trade_price_24h(), t1.getAcc_trade_price_24h()));
//
//	                      // Step 4: Print top 50 KRW markets by 24h trade price
//	                      System.out.println("Top 50 KRW Markets by 24h Trade Price:");
//	                      for (int i = 0; i < 50 && i < tickers.size(); i++) {
//	                          System.out.println((i + 1) + ". " + tickers.get(i).getMarket() + ": " + tickers.get(i).getAcc_trade_price_24h());
//	                      }
//	                  } else {
//	                      System.err.println("Failed to fetch ticker data: " + tickerResponse.getCode());
//	                  }
//	              } else {
//	                  System.err.println("Failed to fetch market data: " + response.getCode());
//	              }
//
//	          } catch (IOException e) {
//	              e.printStackTrace();
//	          } finally {
//	              try {
//	                  httpClient.close();
//	              } catch (IOException e) {
//	                  e.printStackTrace();
//	              }
//	          }
//	      }
//	    	 }
//
//		class Market {
//		    private String market;
//
//		    public String getMarket() {
//		        return market;
//		    }
//
//		    public void setMarket(String market) {
//		        this.market = market;
//		    }
//		}
//
//		class Ticker {
//		    private String market;
//		    private double acc_trade_price_24h;
//
//		    public String getMarket() {
//		        return market;
//		    }
//
//		    public void setMarket(String market) {
//		        this.market = market;
//		    }
//
//		    public double getAcc_trade_price_24h() {
//		        return acc_trade_price_24h;
//		    }
//
//		    public void setAcc_trade_price_24h(double acc_trade_price_24h) {
//		        this.acc_trade_price_24h = acc_trade_price_24h;
//		    }
//		}

		//}

//	    RestTemplate restTemplate;
// 	    ObjectMapper objectMapper;
//
// 	    public GetAccounts(RestTemplate restTemplate, ObjectMapper objectMapper) {
// 	        this.restTemplate = restTemplate;
// 	        this.objectMapper = objectMapper;
// 	    }

//	    @ResponseBody
//	    @GetMapping("/rsiSearch")
//	    public String rsiSearch() throws IOException {
//
//	    	String MARKET_URL = "https://api.upbit.com/v1/market/all";
//	    	String TICKER_URL = "https://api.upbit.com/v1/ticker?markets=";
//
//	    	String marketResponse = restTemplate.getForObject(MARKET_URL, String.class);
//	        List<Market> markets = objectMapper.readValue(marketResponse, new TypeReference<List<Market>>() {});
//
//
//
//	    	List<String> krwMarkets = markets.stream()
//	    				.filter(market -> market.getMarket().startsWith("KRW"))
//	    				.map(Market::getMarket)
//	    				.collect(Collectors.toList());
//
//	    	String marketsParam = String.join(",", krwMarkets);
//	        String tickerResponse = restTemplate.getForObject(TICKER_URL + marketsParam, String.class);
//	        List<Ticker> tickers = objectMapper.readValue(tickerResponse, new TypeReference<List<Ticker>>() {});
//
//	    	tickers.sort((ticker1, ticker2) -> Double.compare(ticker2.getAcc_trade_price_24h(), ticker1.getAcc_trade_price_24h()));
//
//	    	StringBuilder result = new StringBuilder();
//	        result.append("Top 50 KRW Markets by 24h Trade Price:\n");
//	        for (int i = 0; i < 50 && i < tickers.size(); i++) {
//	            Ticker ticker = tickers.get(i);
//	            result.append((i + 1)).append(". ").append(ticker.getMarket()).append(": ").append(ticker.getAcc_trade_price_24h()).append("\n");
//	         }
//
//	         return result.toString();
//	    }
//
//	    static class Market {
//            private String market;
//            private String korean_name;
//            private String english_name;
//
//            public String getMarket() {
//                return market;
//            }
//
//            public void setMarket(String market) {
//                this.market = market;
//            }
//
//            public String getKorean_name() {
//                return korean_name;
//            }
//
//            public void setKorean_name(String korean_name) {
//                this.korean_name = korean_name;
//            }
//
//            public String getEnglish_name() {
//                return english_name;
//            }
//
//            public void setEnglish_name(String english_name) {
//                this.english_name = english_name;
//            }
//        }
//
//        static class Ticker {
//            private String market;
//            private double acc_trade_price_24h;
//
//            public String getMarket() {
//                return market;
//            }
//
//            public void setMarket(String market) {
//                this.market = market;
//            }
//
//            public double getAcc_trade_price_24h() {
//                return acc_trade_price_24h;
//            }
//
//            public void setAcc_trade_price_24h(double acc_trade_price_24h) {
//                this.acc_trade_price_24h = acc_trade_price_24h;
//            }
//        }

//		  @ResponseBody
//	    @GetMapping("/rsi")
//	    public double getCurrentRsi() {
//	        String bitcoinSymbol = "bitcoin";
//	        int period = 14;
//	        List<Double> closes = new ArrayList<>();
//
//	        try {
//	            // CoinGecko API를 통해 비트코인의 최근 14일 가격 데이터 가져오기
//	            String apiUrl = "https://api.coingecko.com/api/v3/coins/" + bitcoinSymbol + "/market_chart";
//	            String params = "?vs_currency=usd&days=15&interval=daily"; // 15일을 가져와서 14일 간의 변화를 계산
//	            URL url = new URL(apiUrl + params);
//	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//	            conn.setRequestMethod("GET");
//
//	            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	            StringBuilder response = new StringBuilder();
//	            String line;
//	            while ((line = reader.readLine()) != null) {
//	                response.append(line);
//	            }
//	            reader.close();
//
//	            // JSON 데이터 파싱
//	            JSONArray pricesArray = new JSONObject(response.toString()).getJSONArray("prices");
//	            for (int i = 0; i < pricesArray.length(); i++) {
//	                JSONArray priceData = pricesArray.getJSONArray(i);
//	                double closePrice = priceData.getDouble(1); // 종가는 배열의 두 번째 요소입니다.
//	                closes.add(closePrice);
//	            }
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	            return -1; // 오류 발생 시 -1 반환
//	        }
//
//	        // 데이터가 부족한 경우 처리
//	        if (closes.size() < period + 1) {
//	            return -1; // 데이터가 부족한 경우 -1 반환
//	        }
//
//	        // RSI 계산
//	        double[] rsiValues = new double[closes.size()];
//	        double sumGain = 0.0;
//	        double sumLoss = 0.0;
//
//	        // 초기 SMA (Simple Moving Average) 계산
//	        for (int i = 1; i <= period; i++) {
//	            double change = closes.get(i) - closes.get(i - 1);
//	            if (change >= 0) {
//	                sumGain += change;
//	            } else {
//	                sumLoss -= change;
//	            }
//	        }
//	        double avgGain = sumGain / period;
//	        double avgLoss = sumLoss / period;
//
//	        // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산
//	        for (int i = period; i < closes.size(); i++) {
//	            double change = closes.get(i) - closes.get(i - 1);
//	            double gain = (change >= 0) ? change : 0;
//	            double loss = (change < 0) ? -change : 0;
//
//	            avgGain = (avgGain * (period - 1) + gain) / period;
//	            avgLoss = (avgLoss * (period - 1) + loss) / period;
//
//	            double rs = avgGain / avgLoss;
//	            double rsi = 100 - (100 / (1 + rs));
//	            rsiValues[i] = rsi;
//	        }
//
//	        return rsiValues[rsiValues.length - 1]; // 최신 RSI 값 반환
//	    }


//	 	@ResponseBody
//	 	public Double getRsiByMinutes() {
//	 	    final int minutes = 30;
//	 	    final String market = "KRW-BTC";
//	 	    final int maxCount = 200;
//	 	    // 업비트 캔들 API 호출 (Docs: https://docs.upbit.com/reference/%EB%B6%84minute-%EC%BA%94%EB%93%A4-1)
//	 	    List<MinuteCandleRes> candleResList = marketPriceReaderService.getCandleMinutes(minutes, market, maxCount);
//	 	    if (CollectionUtils.isEmpty(candleResList)) {
//	 	        return null;
//	 	    }
//
//	 	    // 지수 이동 평균은 과거 데이터부터 구해주어야 합니다.
//	 	    candleResList = candleResList.stream()
//	 	            .sorted(Comparator.comparing(CandleRes::getTimestamp))  // 오름차순 (과거 순)
//	 	            .collect(Collectors.toList());  // Sort
//
//	 	    double zero = 0;
//	 	    List<Double> upList = new ArrayList<>();  // 상승 리스트
//	 	    List<Double> downList = new ArrayList<>();  // 하락 리스트
//	 	    for (int i = 0; i < candleResList.size() - 1; i++) {
//	 	        // 최근 종가 - 전일 종가 = gap 값이 양수일 경우 상승했다는 뜻 / 음수일 경우 하락이라는 뜻
//	 	        double gapByTradePrice = candleResList.get(i + 1).getTradePrice().doubleValue() - candleResList.get(i).getTradePrice().doubleValue();
//	 	        if (gapByTradePrice > 0) {  // 종가가 전일 종가보다 상승일 경우
//	 	            upList.add(gapByTradePrice);
//	 	            downList.add(zero);
//	 	        } else if (gapByTradePrice < 0) {  // 종가가 전일 종가보다 하락일 경우
//	 	            downList.add(gapByTradePrice * -1);  // 음수를 양수로 변환해준다.
//	 	            upList.add(zero);
//	 	        } else {  // 상승, 하락이 없을 경우 종가 - 전일 종가 = gap은 0이므로 0값을 넣어줍니다.
//	 	            upList.add(zero);
//	 	            downList.add(zero);
//	 	        }
//	 	    }
//
//	 	    double day = 14;  // 가중치를 위한 기준 일자 (보통 14일 기준)
//	 	    double a = (double) 1 / (1 + (day - 1));  // 지수 이동 평균의 정식 공식은 a = 2 / 1 + day 이지만 업비트에서 사용하는 수식은 a = 1 / (1 + (day - 1))
//
//	 	    // AU값 구하기
//	 	    double upEma = 0;  // 상승 값의 지수이동평균
//	 	    if (CollectionUtils.isNotEmpty(upList)) {
//	 	        upEma = upList.get(0).doubleValue();
//	 	        if (upList.size() > 1) {
//	 	            for (int i = 1 ; i < upList.size(); i++) {
//	 	                upEma = (upList.get(i).doubleValue() * a) + (upEma * (1 - a));
//	 	            }
//	 	        }
//	 	    }
//
//	 	    // AD값 구하기
//	 	    double downEma = 0;  // 하락 값의 지수이동평균
//	 	    if (CollectionUtils.isNotEmpty(downList)) {
//	 	        downEma = downList.get(0).doubleValue();
//	 	        if (downList.size() > 1) {
//	 	            for (int i = 1; i < downList.size(); i++) {
//	 	                downEma = (downList.get(i).doubleValue() * a) + (downEma * (1 - a));
//	 	            }
//	 	        }
//	 	    }
//
//	 	    // rsi 계산
//	 	    double au = upEma;
//	 	    double ad = downEma;
//	 	    double rs = au / ad;
//	 	    double rsi = 100 - (100 / (1 + rs));
//
//	 	    return rsi;
//	 	}


	// 수익률 저장
	@ResponseBody
	@PostMapping("/saveProfit")
	public void saveProfit(@RequestBody ProfitVO vo) {


		coinservice2.saveProfit(vo);
	}

	// 수익률 불러오기
	@ResponseBody
	@PostMapping
	public List<ProfitVO> getProfitList(@RequestBody ProfitVO vo){

		List<ProfitVO> list = coinservice2.getProfitList(vo);

		return list;
	}

	// 팔때 가격 저장
	@ResponseBody
	@PostMapping("/buySellPrice")
	public void buySellPrice(@RequestBody MemberVO vo) {

		coinservice2.updateSellPrice(vo);

	}


	// 자동 매매 전일 저가, 고가 저장
	@ResponseBody
	@PostMapping("/highLowPrice")
	public void highLowPrice(@RequestBody PriceVO vo) {

		coinservice2.updatePrice(vo);

	}

	// 저가 , 고가 불러오기
	@ResponseBody
	@GetMapping("/yesterdayPrice")
	public List<PriceVO> yesterdayPrice() {

		List<PriceVO> list = coinservice2.getPriceList();

		return list;
	}

	// 자동 매매 금액 저장
	@ResponseBody
	@PostMapping("/saveAutoPrice")
	public void saveAutoPrice(@RequestBody MemberVO vo) {

		coinservice2.saveAutoPrice(vo);
	}

	// 자동매매 시 매수 금액 저장
	@ResponseBody
	@PostMapping("/saveOrderPrice")
	public void saveOrderPrice(@RequestBody MemberVO vo) {

		coinservice2.saveOrderPrice(vo);
	}

	// 수익률 계산 사용X
	@ResponseBody
	@PostMapping("/profit")
	public List<MemberVO> myAutoProfit(@RequestBody MemberVO vo) {

		List<MemberVO> list = coinservice2.getCode(vo.getId());
		list.get(0).getAutoPrice();
		list.get(0).getOrderPrice();

		return list;

	}

	// 관리자가 자동매매 끄기
	@ResponseBody
	@PostMapping("/manageAuto")
	public void manageAuto(@RequestBody MemberVO vo) {
		coinservice2.manageAuto(vo.getId());
	}

	// 현재가 정보
	@ResponseBody
	@GetMapping("/currentPrice7")
	public String currentPriceTest() {

		return coinservice2.currentPrice7();
	}

	// 매수 주문 test
	@ResponseBody
	@PostMapping("/order7")
	public String orderTest(@RequestBody OrderVO vo) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		String a = coinservice2.order7(vo);
		return a;
	}

	// 매도 하기 test
	@ResponseBody
	@PostMapping("/sell7")
	public String sellTest(@RequestBody OrderVO vo) throws UnsupportedEncodingException, NoSuchAlgorithmException {

		String a = coinservice2.sell7(vo);
		return a;
	}

	// 모니터링
	@ResponseBody
	@PostMapping("/memberAuto")
	public List<MemberVO> memberAuto(@RequestBody MemberVO vo) {

		List<MemberVO> member = coinservice2.getMemberAuto(vo.getAuto());
		return member;
	}



	// 한국투자증권 test 국내 주식 주문
	@GetMapping("/koreaStock")
	public void koreaStock() throws UnsupportedEncodingException {
		String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/trading/order-cash";
		String tr_id = "TTTC0802U";
		String data = "{\n" + "    \"CANO\": \"종합계좌번호\",\n" + "    \"ACNT_PRDT_CD\": \"계좌상품코드\",\n"
				+ "    \"ACNT_PWD\": \"계좌비밀번호\",\n" + "    \"PDNO\": \"상품번호\",\n" + "    \"ORD_DVSN\": \"주문구분\",\n"
				+ "    \"ORD_QTY\": \"주문수량\",\n" + "    \"ORD_UNPR\": \"주문단가\",\n" + "    \"CTAC_TLNO\": \"연락전화번호\"\n"
				+ "}";
		// httpPostBodyConnection(url,data,tr_id);

		// public static void httpPostBodyConnection(String UrlData, String
		// ParamData,String TrId) throws IOException {
		String totalUrl = "";
		totalUrl = url.trim().toString();

		URL url2 = null;
		HttpURLConnection conn = null;

		String responseData = "";
		BufferedReader br = null;

		StringBuffer sb = new StringBuffer();
		String returnData = "";

		try {
			url2 = new URL(totalUrl);
			conn = (HttpURLConnection) url2.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("authorization", "Bearer {TOKEN}");
			conn.setRequestProperty("appKey", "{Client_ID}");
			conn.setRequestProperty("appSecret", "{Client_Secret}");
			conn.setRequestProperty("personalSeckey", "{personalSeckey}");
			conn.setRequestProperty("tr_id", tr_id);
			conn.setRequestProperty("tr_cont", " ");
			conn.setRequestProperty("custtype", "법인(B), 개인(P)");
			conn.setRequestProperty("seq_no", "법인(01), 개인( )");
			conn.setRequestProperty("mac_address", "{Mac_address}");
			conn.setRequestProperty("phone_num", "P01011112222");
			conn.setRequestProperty("ip_addr", "{IP_addr}");
			conn.setRequestProperty("hashkey", "{Hash값}");
			conn.setRequestProperty("gt_uid", "{Global UID}");
			conn.setDoOutput(true);

			try (OutputStream os = conn.getOutputStream()) {
				byte request_data[] = data.getBytes("utf-8");
				os.write(request_data);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			conn.connect();
			System.out.println("http 요청 방식" + "POST BODY JSON");
			System.out.println("http 요청 타입" + "application/json");
			System.out.println("http 요청 주소" + url);
			System.out.println("");

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} catch (IOException e) {

			br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		} finally {
			try {
				sb = new StringBuffer();
				while ((responseData = br.readLine()) != null) {
					sb.append(responseData);
				}
				returnData = sb.toString();
				String responseCode = String.valueOf(conn.getResponseCode());
				System.out.println("http 응답 코드 : " + responseCode);
				System.out.println("http 응답 데이터 : " + returnData);
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
			}
		}
	}

	// 한국투자증권 test 매수 가능 조회
	@GetMapping("/koreaOrder")
	public void koreaOrder() throws UnsupportedEncodingException {

		String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/trading/inquire-psbl-order";
		String tr_id = "TTTC8908R";
		String data = "{\n" + "    \"CANO\": \"종합계좌번호\",\n" + "    \"ACNT_PRDT_CD\": \"계좌상품코드\",\n"
				+ "    \"ACNT_PWD\": \"계좌비밀번호\",\n" + "    \"PDNO\": \"상품번호\",\n" + "    \"ORD_UNPR\": \"주문단가\",\n"
				+ "    \"ORD_DVSN\": \"주문구분\",\n" + "    \"CMA_EVLU_AMT_ICLD_YN\": \"CMA평가금액포함여부\",\n"
				+ "    \"OVRS_ICLD_YN\": \"해외포함여부\"\n" + "}";

		String totalUrl = "";
		totalUrl = url.trim().toString();

		URL url2 = null;
		HttpURLConnection conn = null;

		String responseData = "";
		BufferedReader br = null;

		StringBuffer sb = new StringBuffer();
		String returnData = "";

		try {
			url2 = new URL(totalUrl);
			conn = (HttpURLConnection) url2.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("authorization", "Bearer {TOKEN}");
			conn.setRequestProperty("appKey", "{Client_ID}");
			conn.setRequestProperty("appSecret", "{Client_Secret}");
			conn.setRequestProperty("personalSeckey", "{personalSeckey}");
			conn.setRequestProperty("tr_id", tr_id);
			conn.setRequestProperty("tr_cont", " ");
			conn.setRequestProperty("custtype", "법인(B), 개인(P)");
			conn.setRequestProperty("seq_no", "법인(01), 개인( )");
			conn.setRequestProperty("mac_address", "{Mac_address}");
			conn.setRequestProperty("phone_num", "P01011112222");
			conn.setRequestProperty("ip_addr", "{IP_addr}");
			conn.setRequestProperty("hashkey", "{Hash값}");
			conn.setRequestProperty("gt_uid", "{Global UID}");
			conn.setDoOutput(true);

			try (OutputStream os = conn.getOutputStream()) {
				byte request_data[] = data.getBytes("utf-8");
				os.write(request_data);
				os.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			conn.connect();
			System.out.println("http 요청 방식" + "POST BODY JSON");
			System.out.println("http 요청 타입" + "application/json");
			System.out.println("http 요청 주소" + url);
			System.out.println("");

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		} finally {
			try {
				sb = new StringBuffer();
				while ((responseData = br.readLine()) != null) {
					sb.append(responseData);
				}
				returnData = sb.toString();
				String responseCode = String.valueOf(conn.getResponseCode());
				System.out.println("http 응답 코드 : " + responseCode);
				System.out.println("http 응답 데이터 : " + returnData);
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
			}
		}
	}


//		}


//	// 한국투자증권 국내 주식 검색기
	@ResponseBody
	@GetMapping("/koreaPrice")
	public void koreaPrice() throws UnsupportedEncodingException {
		// 국내 주식 시세 조회
		String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price";
		String tr_id = "FHKST01010100";
		String data = "?fid_cond_mrkt_div_code=J" + // FID 조건 시장 분류 코드
				"&fid_input_iscd=000660"; // FID 입력 종목코드

		String totalUrl = "";
		totalUrl = url.trim().toString();

		URL url2 = null;
		HttpURLConnection conn = null;

		String responseData = "";
		BufferedReader br = null;

		StringBuffer sb = new StringBuffer();
		String returnData = "";


		try {
			url2 = new URL(totalUrl + data);
			conn = (HttpURLConnection) url2.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("authorization", "Bearer {TOKEN}");
			conn.setRequestProperty("appKey", "PSKs0RRJqLoIX17saBUAGHuEMB637QGV571U");
			conn.setRequestProperty("appSecret", "Q9Ul35SBhS0lITcIhctv5nX9o0LjhfrZz9Lb0F+FHIh95ICyDnepsFpEa3qQbJEjwxeaAhL//Kj9d+cs3ZsZJj9h3nwRdTxTWhuKnNc/cp2TLSOmgSh2IGsIUUrOluguzEpZ+tgn9AKDzjBrW6kUiMg5DoBKCI65G30HW3+eUFbzKZpTcFs=");
			conn.setRequestProperty("personalSeckey", "{personalSeckey}");
			conn.setRequestProperty("tr_id", tr_id);
			conn.setRequestProperty("tr_cont", " ");
			conn.setRequestProperty("custtype", "P");   //법인(B), 개인(P)
			conn.setRequestProperty("seq_no", "");  // 법인(01), 개인( )
			conn.setRequestProperty("mac_address", "00-09-0F-AA-00-01");
			conn.setRequestProperty("phone_num", "P01066251557");
			conn.setRequestProperty("ip_addr", "118.45.185.68");
			conn.setRequestProperty("hashkey", "{Hash값}");
			conn.setRequestProperty("gt_uid", "{Global UID}");
			conn.setDoOutput(true);

			conn.connect();

			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		} catch (IOException e) {
			br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
		} finally {
			try {
				sb = new StringBuffer();
				while ((responseData = br.readLine()) != null) {
					sb.append(responseData);
				}
				returnData = sb.toString();
				String responseCode = String.valueOf(conn.getResponseCode());
				System.out.println("http 응답 코드 : " + responseCode);
				System.out.println("http 응답 데이터 : " + returnData);
				if (br != null) {
					br.close();
				}
			} catch (IOException e) {
				throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
			}
		}
	}


		@Autowired
	    private AccessTokenManager accessTokenManager;

	    private WebClient webClient;
	    private String path;
	    private String tr_id;

	    public GetAccounts(WebClient.Builder webClientBuilder) {
	        this.webClient = webClientBuilder.baseUrl(KisConfig.REST_BASE_URL).build();
	    }

	    @GetMapping("/index")
	    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
	    	model.addAttribute("user", userDetails.getUsername());
	    	return "index";
	    }

	    @GetMapping("/index2")
	    public String index2() {

	    	return "index2";
	    }

	    @GetMapping("/indices")
	    public String majorIndices(Model model) {

	    	List<Tuple2<String, String>> iscdsAndOtherVariable1 = Arrays.asList(
	    	        Tuples.of("0001", "U"),
	    	        Tuples.of("2001", "U"),
	    	        Tuples.of("1001", "U")
	    	    );

	        Flux<IndexData> indicesFlux = Flux.fromIterable(iscdsAndOtherVariable1)
	                .concatMap(tuple -> getMajorIndex(tuple.getT1(), tuple.getT2()))
	                .map(jsonData -> {
	                    ObjectMapper objectMapper = new ObjectMapper();
	                    try {
	                        return objectMapper.readValue(jsonData, IndexData.class);
	                    } catch (JsonProcessingException e) {
	                        throw new RuntimeException(e);
	                    }
	                });

	        List<IndexData> indicesList = indicesFlux.collectList().block();
	        model.addAttribute("indicesKor", indicesList);

	    	model.addAttribute("jobDate", getJobDateTime());

	    	return "indices";
	    }

	    public String getStringToday() {
	    	LocalDate localDate = LocalDate.now();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	    	return localDate.format(formatter);
	    }

	    public String getJobDateTime() {
	    	LocalDateTime now = LocalDateTime.now();
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    	return now.format(formatter);
	    }

	    public Mono<String> getMajorIndex(String iscd, String fid_cond_mrkt_div_code) {

	    	if (fid_cond_mrkt_div_code.equals("U")) {
	    		path = KisConfig.FHKUP03500100_PATH;
	    		tr_id = "FHKUP03500100";
	    	} else {
	    		path = KisConfig.FHKST03030100_PATH;
	    		tr_id = "FHKST03030100";
	    	}

	        return webClient.get()
	        		.uri(uriBuilder -> uriBuilder
	        			    .path(path)
	        			    .queryParam("fid_cond_mrkt_div_code", fid_cond_mrkt_div_code)
	        			    .queryParam("fid_input_iscd", iscd)
	        			    .queryParam("fid_input_date_1", getStringToday())
	        			    .queryParam("fid_input_date_2", getStringToday())
	        			    .queryParam("fid_period_div_code", "D")
	        			    .build())
	                .header("content-type","application/json")
	                .header("authorization","Bearer " + accessTokenManager.getAccessToken())
	                .header("appkey",KisConfig.APPKEY)
	                .header("appsecret",KisConfig.APPSECRET)
	                .header("tr_id",tr_id)
	                .retrieve()
	                .bodyToMono(String.class);

	    }

	    @GetMapping("/equities/{id}")
	    public Mono<String> CurrentPrice(@PathVariable("id") String id, Model model) {
	    	String url = KisConfig.REST_BASE_URL + "/uapi/domestic-stock/v1/quotations/inquire-price?fid_cond_mrkt_div_code=J&fid_input_iscd=" + id;

	        return webClient.get()
	                .uri(url)
	                .header("content-type","application/json")
	                .header("authorization","Bearer " + accessTokenManager.getAccessToken())
	                .header("appkey",KisConfig.APPKEY)
	                .header("appsecret",KisConfig.APPSECRET)
	                .header("tr_id","FHKST01010100")
	                .retrieve()
	                .bodyToMono(Body.class)
	                .doOnSuccess(body -> {
	                	model.addAttribute("equity", body.getOutput());
	                	model.addAttribute("jobDate", getJobDateTime());
	                })
	                .doOnError(result -> System.out.println("*** error: " + result))
	                .thenReturn("equities");
	    }
	    // 여기까지 주식 검색하는 데 쓰이는 영역
}



//	//바이낸스
//	@ResponseBody
//	@GetMapping("/binance")
//	public static String getStringFormUrl(String url) throws Throwable{
//
//		HttpURLConnection huc = (HttpURLConnection) new URL(url).openConnection();
//		 huc.setRequestMethod("GET");
//	        huc.addRequestProperty("User-Agent",
//	                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
//	        huc.setRequestProperty("Accept", "application/json, text/javascript, */*; q=0.01");
//	        huc.connect();
//	        InputStream in = null;
//	        if( huc.getResponseCode() != 200 ){
//	            in = huc.getErrorStream();
//	        }else{
//	            in = huc.getInputStream();
//	        }
//	        BufferedReader br = new BufferedReader(new InputStreamReader(in , "UTF-8"));
//	        String line = null;
//	        StringBuilder sb = new StringBuilder();
//	        while ((line = br.readLine()) != null) {
//	            sb.append(line);
//	        }
//	        br.close();
//	        System.out.println(sb.toString());
//
//
//
//	        return sb.toString();
//
//	}
//
//	//바이낸스
//	@ResponseBody
//	@GetMapping("/binance2")
//	public static void printTicker() throws Throwable{
//
//
//		 String json_str = URLUtil.getStringFromUrl("https://api.binance.com/api/v1/ticker/24hr");
//	     Gson gson = new Gson();
//	     JsonArray ja = gson.fromJson(json_str, JsonElement.class).getAsJsonArray();
//	     for(int i = 0; i < ja.size();i++){
//	         JsonObject market = ja.get(i).getAsJsonObject();
//	         System.out.println(market.get("symbol") + " : " + market);
//	     }
//
//
//	}
/*
 * public static void main2(String[] args) {
 *
 * String accessKey = "EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8";
 * System.getenv("EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8"); // access 코드
 * String secretKey = "1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG";
 * System.getenv("1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG"); // secret 코드
 * String serverUrl = "https://api.upbit.com";
 *
 * Algorithm algorithm = Algorithm.HMAC256(secretKey); String jwtToken =
 * JWT.create().withClaim("access_key", accessKey) .withClaim("nonce",
 * UUID.randomUUID().toString()).sign(algorithm);
 *
 * String authenticationToken = "Bearer " + jwtToken;
 *
 *
 *
 *
 * //최근 체결 내역
 *
 * OkHttpClient client3 = new OkHttpClient();
 *
 * okhttp3.Request request3 = new okhttp3.Request.Builder()
 * .url("https://api.upbit.com/v1/trades/ticks?market=KRW-BTC&count=1") .get()
 * .addHeader("accept", "application/json") .build();
 *
 * try { Response response3 = client3.newCall(request3).execute();
 * if(response3.body() != null) { String responseBody3 =
 * response3.body().string(); System.out.println(responseBody3); }
 *
 *
 * } catch (IOException e) { e.printStackTrace(); }
 *
 *
 * //시세 캔들 조회
 *
 * OkHttpClient client4 = new OkHttpClient();
 *
 * okhttp3.Request request4 = new okhttp3.Request.Builder()
 * .url("https://api.upbit.com/v1/candles/minutes/1?market=KRW-BTC&count=1")
 * //minutes(분봉), days(일봉), weeks(주봉), months(월봉) .get() .addHeader("accept",
 * "application/json") .build();
 *
 * try {
 *
 * Response response4 = client4.newCall(request4).execute(); }catch(IOException
 * e) { e.printStackTrace(); }
 *
 *
 *
 *
 *
 *
 *
 *
 * }
 */
////변동성 돌파 전략
//@ResponseBody
//@PostMapping("/autoTrade")
//// public void auto(@RequestBody MemberVO member) throws NoSuchAlgorithmException, UnsupportedEncodingException {
//
//
//  CompletableFuture.runAsync(() -> {
//      autoTrade(member);
//  });
//}

//@Async
//public void autoTrade(MemberVO member) {
//  Logger logger = LoggerFactory.getLogger(this.getClass());
//  coinservice2.autoOn(member.getId());
//
//  String ac = member.getAccessCode();
//
//  /*String ac = "EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8";*/
//  String sc = member.getSecretCode();
//  /*String sc = "1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG";*/
//  String userId = member.getId();
//
//  MemberVO vo = new MemberVO();
//  boolean b1 = true;
//
//  while (b1) {
//
//      List<MemberVO> autoCheck = coinservice2.getCode(userId);
//      //System.out.println(autoCheck.get(0).getAuto());
//      String check = autoCheck.get(0).getAuto();
//      if (check.equals("N")) {
//          b1 = false;
//          logger.info("자동 거래 중지: 사용자 ID = {}", userId);
//          break;
//      } else {
//          boolean b2 = true;
//          //System.out.println(vo);
//          vo.setAccessCode(ac);
//          vo.setSecretCode(sc);
//          String a = coinservice2.market7();
//          String b = coinservice2.currentPrice7();
//          String c = coinservice2.account7(vo);
//
//          // 예외 처리 추가
//          if (a == null || b == null || c == null) {
//              throw new RuntimeException("API 호출에 실패했습니다.");
//          }
//
//          String market;
//          String market2;
//          String koreanName;
//          String currency;
//          String balance;
//          String coin2;
//
//          BigDecimal lowPrice;
//          BigDecimal highPrice;
//          BigDecimal prevClosePrice;
//          BigDecimal targetPrice;
//          BigDecimal minus;
//          BigDecimal multi;
//          BigDecimal dotFive = new BigDecimal(0.5);
//          BigDecimal nowPrice;
//
//          JSONArray jsonArray = new JSONArray(a);   //마켓
//          JSONArray jsonArray2 = new JSONArray(b);  //현재가
//          JSONArray jsonArray3 = new JSONArray(c);  //잔고
//
//          //for(int i=0; i<jsonArray.length(); i++){
//          //JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//          //market = jsonObject.getString("market");
//          //koreanName = jsonObject.getString("korean_name");
//
//          for (int j = 0; j < jsonArray2.length(); j++) {
//
//              JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
//              market2 = jsonObject2.getString("market");
//
//              if (market2.contains("KRW-BTC")) {
//
//
//                  highPrice = jsonObject2.getBigDecimal("high_price");
//                  lowPrice = jsonObject2.getBigDecimal("low_price");
//                  prevClosePrice = jsonObject2.getBigDecimal("prev_closing_price");
//                  nowPrice = jsonObject2.getBigDecimal("trade_price");
//                  minus = highPrice.subtract(lowPrice);
//                  multi = minus.multiply(dotFive);
//                  targetPrice = prevClosePrice.add(multi);
//                  //System.out.println(dotFive);
//
//                  // add 덧셈 subtract 뺄셈, multiply 곱셈, divide 나눗셈
//
//
//                  //-1 작은 경우,  0 같은 경우, 1 큰경우
//                  if (targetPrice.compareTo(nowPrice) <= 0) {
//                      logger.info("사용자 = {} 목표 타겟 도달: 현재 가격 = {}, 목표 가격 = {}", userId, nowPrice, targetPrice);
//
//                      //System.out.println("목표 타겟 도달!!!");
//
//                      for (int i = 0; i < jsonArray3.length(); i++) {
//
//                          JSONObject jsonObject3 = jsonArray3.getJSONObject(i);
//
//                          currency = jsonObject3.getString("currency");
//                          //balance  = jsonObject3.getString("balance");
//
//                          if (currency.equals("KRW")) {
//
//                              OrderVO vo2 = new OrderVO();
//                              vo2.setCoin("KRW-BTC");
//                              vo2.setAccessCode(vo.getAccessCode());
//                              vo2.setSecretCode(vo.getSecretCode());
//                              vo2.setOrderType("bid");
//                              vo2.setPrice("6000");
//                              try {
//                                  coinservice2.order7(vo2);
//                              } catch (NoSuchAlgorithmException e) {
//                                  // TODO Auto-generated catch block
//                                  e.printStackTrace();
//                              } catch (UnsupportedEncodingException e) {
//                                  // TODO Auto-generated catch block
//                                  e.printStackTrace();
//                              }
//                              logger.info("매수 주문 실행: 코인 = KRW-BTC, 가격 = 6000");
//
//                              while (b2) {
//
//                                  String d = coinservice2.account7(vo);
//                                  JSONArray jsonArray4 = new JSONArray(d);
//
//                                  for (int idx = 0; idx < jsonArray4.length(); idx++) {
//
//
//                                      JSONObject jsonObject4 = jsonArray4.getJSONObject(idx);
//                                      coin2 = jsonObject4.getString("currency");
//
//                                      if (coin2.equals("BTC")) {
//
//                                          LocalTime now = LocalTime.now();
//                                          DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
//
//                                          String formatedNow = now.format(formatter);
//                                          //System.out.println(formatedNow);
//                                          if (formatedNow.equals("09:00")) {
//
//                                              balance = jsonObject4.getString("balance");
//                                              //System.out.println(balance);
//                                              OrderVO vo3 = new OrderVO();
//                                              vo3.setCoin("KRW-BTC");
//                                              vo3.setAccessCode(vo.getAccessCode());
//                                              vo3.setSecretCode(vo.getSecretCode());
//                                              vo3.setOrderType("ask");
//                                              vo3.setVolume(balance);
//
//                                              try {
//                                                  coinservice2.sell7(vo3);
//                                              } catch (NoSuchAlgorithmException e) {
//                                                  // TODO Auto-generated catch block
//                                                  e.printStackTrace();
//                                              } catch (UnsupportedEncodingException e) {
//                                                  // TODO Auto-generated catch block
//                                                  e.printStackTrace();
//                                              }
//
//                                              logger.info("매도 주문 실행: 코인 = KRW-BTC, 잔고 = {}", balance);
//
//
//                                              try {
//                                                  Thread.sleep(30000);
//                                                  b2 = false;
//                                                  break;
//                                              } catch (InterruptedException e) {
//                                                  e.printStackTrace();
//                                              }
//
//                                          }
//                                      }
//
//                                  }
//
//                                  try {
//                                      Thread.sleep(1000);
//                                  } catch (InterruptedException e) {
//                                      logger.error("거래 중 오류 발생", e);
//                                      e.printStackTrace();
//                                  }
//
//                              }
//                          }
//                      }
//
//
//                  } else {
//                      logger.info("사용자" + userId + "목표 타겟 미 도달!!");
//                      try {
//                          Thread.sleep(1000);
//                      } catch (InterruptedException e) {
//                          // TODO Auto-generated catch block
//                          e.printStackTrace();
//                      }
//                      //System.out.println("목표 타겟 미 도달!!");
//
//                  }
//
//
//              }
//          }
//      }
//
//
//  }
//
//}
