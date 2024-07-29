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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
import com.example.bitcoin.dto.NoticeVO;
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
		return "login";
	}

	// 로그인 페이지
	@GetMapping("/login")
	public String loginPage() {

		return "login";
	}

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

	//대시보드
	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model model) {

		List<NoticeVO> vo = coinservice2.getNotice();
		model.addAttribute("vo",vo);
		model.addAttribute("user", userDetails.getUsername());
		return "dashboard";
	}

	//대시보드 리스트
	@ResponseBody
	@PostMapping("/notice")
	public List<NoticeVO> getNotice(@AuthenticationPrincipal UserDetails userDetails,  Model model, Model model2){

		List<NoticeVO> vo = coinservice2.getNotice();
		model.addAttribute("vo", vo);
		model2.addAttribute("user", userDetails.getUsername());
		return vo;

	}

	// 공지사항 게시판 수
	@ResponseBody
	@GetMapping("/noticeMax")
	public int noticeMax() {

		return coinservice2.noticeMax();
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


	// 자동매매 돌리는 총 수

	@ResponseBody
	@GetMapping("/autoOnMax")
	public int autoOnMax() {

		return coinservice2.autoOnMax();
	}



	// 자동매매 안돌리는 총 수

	@ResponseBody
	@GetMapping("/autoOffMax")
	public int autoOffMax() {

		return coinservice2.autoOffMax();
	}

	// 모니터링 페이징1
	@ResponseBody
	@PostMapping("/moniterAjax")
	public List<MemberVO> moniterAjax(@RequestBody PagingVO vo) {

		List<MemberVO> list = coinservice2.getMoniterOn(vo);

		return list;
	}

	// 모니터링 페이징2
	@ResponseBody
	@PostMapping("/moniterAjax2")
	public List<MemberVO> moniterAjax2(@RequestBody PagingVO vo) {

		List<MemberVO> list = coinservice2.getMoniterOff(vo);

		return list;
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


	// 건의 게시판 전체 개수
	@ResponseBody
	@GetMapping("/questionMax")
	public int questionMax() {

		return coinservice2.getQuestionMax();
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
		System.out.println(list);
		return list;
	}

	// 건의사항 전체 페이징
	@ResponseBody
	@PostMapping("/questionListAjax2")
	public List<QuestionVO> questionListAjax2(@RequestBody PagingVO vo) {
		System.out.println(vo.getPage());

		List<QuestionVO> list = coinservice2.questionList2(vo);

		return list;
	}

	// 건의사항 그 사람만 페이징
	@ResponseBody
	@GetMapping("/questionListAjax3")
	public List<QuestionVO> questionListAjax3(@RequestBody PagingVO vo) {
		System.out.println(vo.getPage());
		System.out.println(vo.getWriter());
		List<QuestionVO> list = coinservice2.questionList3(vo);

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

	// 건의 사항 페이지
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
	@GetMapping("/header")
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
							/*
							 * ProfitVO vo11 = new ProfitVO(); vo11.setId(userId);
							 * coinservice2.saveProfit(vo11);
							 */


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


	// rsi 자동매매 on인 사람
	public List<MemberVO> rsiAutoMember() {

		return coinservice2.getRsiMember();

	}

	@ResponseBody
	@PostMapping("/insertRsi")
	// rsi 살때 가격, 팔때 가격, 코인 저장, 투입되는 돈
	public void insertRsi(@RequestBody MemberVO vo) {

		coinservice2.insertRsi(vo);

	}

	//rsi 자동매매
	@Scheduled(fixedRate = 6000)
	public void rsiAuto2() {

		Logger logger = LoggerFactory.getLogger(this.getClass());
		List<MemberVO> list = coinservice2.getRsiMember();

		if (list == null || list.isEmpty()) {
			logger.info("자동매매 실행한 유저가 존재하지 않습니다.");
			return;
		}

		for (int i = 0; i < list.size(); i++) {

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

			String ma = coinservice2.account7(member);
			String hc = coinservice2.rsiSearch();

			 // Parse account response to get a set of currencies
	        JSONArray accountArray = new JSONArray(ma);
	        Set<String> accountCurrencies = new HashSet<>();
	        for (int j = 0; j < accountArray.length(); j++) {
	            JSONObject jsonObject = accountArray.getJSONObject(j);
	            String currencyWithPrefix = "KRW-" + jsonObject.getString("currency");
	            accountCurrencies.add(currencyWithPrefix);
	        }

	        JSONArray rsiSearchArray = new JSONArray(hc);
	        List<JSONObject> filteredMarkets = new ArrayList<>();
	        for (int m = 0; m < rsiSearchArray.length(); m++) {
	            JSONObject marketObject = rsiSearchArray.getJSONObject(m);
	            String market = marketObject.getString("market");

	            if (!accountCurrencies.contains(market)) {
	                filteredMarkets.add(marketObject);
	            }
	        }

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
	        
	        String API_URL = "https://api.upbit.com/v1/candles/minutes/1";
	  	  	String market2;
	  	  	int COUNT = 200;
	  	  	int PERIOD = 14;

	  	  	List<Double> rsiForMarket = new ArrayList<>();
	        for(JSONObject obj : filteredMarkets) {

	        	market2 = obj.getString("market");

	        	 try {
			            String jsonResponse = getApiResponse(market2);
			            ArrayList<Double> closes = parseCloses(jsonResponse);
			            ArrayList<Double> rsiValues = calculateRSI(closes, PERIOD);

			            if(rsiValues.get(rsiValues.size() - 1) <= 30) {
			            	System.out.println(market2+":"+rsiValues.get(rsiValues.size() - 1));
			            }


	        	 }catch(Exception e) {
	        		  e.printStackTrace();
	        	 }

	        }
		}




//			String API_URL = "https://api.upbit.com/v1/candles/minutes/1";
//			String MARKET;
//			int COUNT = 200;
//			int PERIOD = 14;
//			String a = filteredMarkets;
//
//			  JSONArray jsonArray = new JSONArray(a);
//		        List<JSONObject> jsonObjects = new ArrayList<>();
//		        for (int j = 0; j < jsonArray.length(); j++) {
//		            JSONObject jsonobject = jsonArray.getJSONObject(j);
//		            jsonObjects.add(jsonobject);
//		        }
//
//		        List<JSONObject> top50List = jsonObjects;
//		        List<Double> rsiForMarket = new ArrayList<>();
//		       // JSONArray resultArray = new JSONArray();
//
//			  for( JSONObject obj : top50List) {
//				  MARKET = obj.getString("market");
//
//
//			  try {
//		            String jsonResponse = getApiResponse(MARKET);
//		            ArrayList<Double> closes = parseCloses(jsonResponse);
//		            ArrayList<Double> rsiValues = calculateRSI(closes, PERIOD);
//
//
//
//		            rsiForMarket.add(rsiValues.get(rsiValues.size() - 1));
//		        } catch (Exception e) {
//		            e.printStackTrace();
//
//		           rsiForMarket.add(-1.0);
//
//		        }
//
//			  }
//			  //rsiForMarket;
//
//
//
//			// String mk = null;
//			String cp = null;
//			String account = null;
//
//			try {
//				// mk = coinservice2.market7(); //마켓
//				cp = coinservice2.currentPrice7(); // 현재가
//				account = coinservice2.account7(member); // 잔고
//			} catch (Exception e) {
//				logger.error("사용자 {}의 API 호출에 실패했습니다: {}", userId, e.getMessage());
//				continue;
//			}
//
//			// JSONArray jsonArray = new JSONArray(mk);
//			JSONArray jsonArray2 = new JSONArray(cp); // 현재가
//			JSONArray jsonArray3 = new JSONArray(account); // 잔고


	}


	//rsi14
 	@ResponseBody
    @GetMapping("/rsi14")
    public List<Double> getCurrentRsi14() {

	  String API_URL = "https://api.upbit.com/v1/candles/minutes/1";
	  String MARKET;
	  int COUNT = 200;
	  int PERIOD = 14;
	  String a = coinservice2.rsiSearch();

	  JSONArray jsonArray = new JSONArray(a);
        List<JSONObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonobject = jsonArray.getJSONObject(i);
            jsonObjects.add(jsonobject);
        }

        List<JSONObject> top50List = jsonObjects;
        List<Double> rsiForMarket = new ArrayList<>();
       // JSONArray resultArray = new JSONArray();

	  for( JSONObject obj : top50List) {
		  MARKET = obj.getString("market");


	  try {
            String jsonResponse = getApiResponse(MARKET);
            ArrayList<Double> closes = parseCloses(jsonResponse);
            ArrayList<Double> rsiValues = calculateRSI(closes, PERIOD);

            // 최신 RSI 값 반환
            //return rsiValues.get(rsiValues.size() - 1);
           // JSONObject resultObj = new JSONObject();
           // resultObj.put("market", MARKET);
           // resultObj.put("rsi", rsiValues.get(rsiValues.size() - 1));
           // resultArray.put(resultObj);

            rsiForMarket.add(rsiValues.get(rsiValues.size() - 1));
        } catch (Exception e) {
            e.printStackTrace();
            //JSONObject errorObj = new JSONObject();
            //errorObj.put("market", MARKET);
            //errorObj.put("rsi", -1.0);
           // resultArray.put(errorObj);
           rsiForMarket.add(-1.0);
            //return -1;  // 에러 발생 시 -1 반환
        }

	  }
	  return rsiForMarket;
	  //return resultArray.toString();
    }

    private String getApiResponse(String MARKET2) throws Exception {

    	String API_URL = "https://api.upbit.com/v1/candles/minutes/1";
    	String MARKET = MARKET2;
    	//String MARKET = "KRW-BTC";
		int COUNT = 200;
		int PERIOD = 14;
        URL url = new URL(API_URL + "?market=" + MARKET + "&count=" + COUNT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        conn.disconnect();

        return content.toString();
    }

    private ArrayList<Double> parseCloses(String jsonResponse) {
        JSONArray jsonArray = new JSONArray(jsonResponse);
        ArrayList<Double> closes = new ArrayList<>();
        for (int i = jsonArray.length() - 1; i >= 0; i--) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            closes.add(jsonObject.getDouble("trade_price"));
        }
        return closes;
    }

    private ArrayList<Double> calculateRSI(ArrayList<Double> closes, int period) {
        ArrayList<Double> auList = new ArrayList<>();
        ArrayList<Double> adList = new ArrayList<>();
        ArrayList<Double> rsiList = new ArrayList<>();

        for (int i = 1; i < closes.size(); i++) {
            double change = closes.get(i) - closes.get(i - 1);
            auList.add(Math.max(change, 0));
            adList.add(Math.max(-change, 0));
        }

        double au = 0, ad = 0;
        for (int i = 0; i < period; i++) {
            au += auList.get(i);
            ad += adList.get(i);
        }
        au /= period;
        ad /= period;

        rsiList.add(100 - (100 / (1 + au / ad)));

        for (int i = period; i < auList.size(); i++) {
            au = (au * (period - 1) + auList.get(i)) / period;
            ad = (ad * (period - 1) + adList.get(i)) / period;
            rsiList.add(100 - (100 / (1 + au / ad)));
        }

        return rsiList;
    }

	/*
	 * // 이건 rsi 9랑 값이 같다!!! ( 업비트 rsi 방식 )
	 *
	 * @ResponseBody
	 *
	 * @GetMapping("/upbitRsi") public double getCurrentRsi() { String bitcoinSymbol
	 * = "bitcoin"; int period = 14; List<Double> closes = new ArrayList<>();
	 *
	 * try { // CoinGecko API를 통해 비트코인의 최근 14일 가격 데이터 가져오기 String apiUrl =
	 * "https://api.coingecko.com/api/v3/coins/" + bitcoinSymbol + "/market_chart";
	 * String params = "?vs_currency=usd&days=15&interval=daily"; URL url = new
	 * URL(apiUrl + params); HttpURLConnection conn = (HttpURLConnection)
	 * url.openConnection(); conn.setRequestMethod("GET");
	 *
	 * BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(conn.getInputStream())); StringBuilder response = new
	 * StringBuilder(); String line; while ((line = reader.readLine()) != null) {
	 * response.append(line); } reader.close();
	 *
	 * // JSON 데이터 파싱 JSONArray pricesArray = new
	 * JSONObject(response.toString()).getJSONArray("prices"); for (int i = 0; i <
	 * pricesArray.length(); i++) { JSONArray priceData =
	 * pricesArray.getJSONArray(i); double closePrice = priceData.getDouble(1); //
	 * 종가는 배열의 두 번째 요소입니다. closes.add(closePrice); } } catch (IOException e) {
	 * e.printStackTrace(); return -1; // 오류 발생 시 -1 반환 }
	 *
	 * // 데이터가 부족한 경우 처리 if (closes.size() < period + 1) { return -1; // 데이터가 부족한 경우
	 * -1 반환 }
	 *
	 * // RSI 계산 double sumGain = 0.0; double sumLoss = 0.0;
	 *
	 * // 초기 SMA (Simple Moving Average) 계산 for (int i = 1; i <= period; i++) {
	 * double change = closes.get(i) - closes.get(i - 1); if (change >= 0) { sumGain
	 * += change; } else { sumLoss -= change; } } double avgGain = sumGain / period;
	 * double avgLoss = sumLoss / period;
	 *
	 * // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산 for (int i =
	 * period; i < closes.size(); i++) { double change = closes.get(i) -
	 * closes.get(i - 1); double gain = (change >= 0) ? change : 0; double loss =
	 * (change < 0) ? -change : 0;
	 *
	 * avgGain = (avgGain * (period - 1) + gain) / period; avgLoss = (avgLoss *
	 * (period - 1) + loss) / period; }
	 *
	 * double rs = avgGain / avgLoss; double rsi = 100 - (100 / (1 + rs));
	 *
	 * return rsi; }
	 *
	 *
	 *
	 *
	 * //일반 rsi ( 업비트 rsi 14랑 흡사 )
	 *
	 * @ResponseBody
	 *
	 * @GetMapping("/rsi") public double getCurrentRsi2() { String bitcoinSymbol =
	 * "bitcoin"; int period = 14; List<Double> closes = new ArrayList<>();
	 *
	 * try { // CoinGecko API를 통해 비트코인의 최근 15일 가격 데이터 가져오기 String apiUrl =
	 * "https://api.coingecko.com/api/v3/coins/" + bitcoinSymbol + "/market_chart";
	 * String params = "?vs_currency=usd&days=15&interval=daily"; // 15일을 가져와서 14일
	 * 간의 변화를 계산 URL url = new URL(apiUrl + params); HttpURLConnection conn =
	 * (HttpURLConnection) url.openConnection(); conn.setRequestMethod("GET");
	 *
	 * BufferedReader reader = new BufferedReader(new
	 * InputStreamReader(conn.getInputStream())); StringBuilder response = new
	 * StringBuilder(); String line; while ((line = reader.readLine()) != null) {
	 * response.append(line); } reader.close();
	 *
	 * // JSON 데이터 파싱 JSONArray pricesArray = new
	 * JSONObject(response.toString()).getJSONArray("prices"); for (int i = 0; i <
	 * pricesArray.length(); i++) { JSONArray priceData =
	 * pricesArray.getJSONArray(i); double closePrice = priceData.getDouble(1); //
	 * 종가는 배열의 두 번째 요소입니다. closes.add(closePrice); } } catch (IOException e) {
	 * e.printStackTrace(); return -1; // 오류 발생 시 -1 반환 }
	 *
	 * // 데이터가 부족한 경우 처리 if (closes.size() < period + 1) { return -1; // 데이터가 부족한 경우
	 * -1 반환 }
	 *
	 * // RSI 계산 double[] rsiValues = calculateRsi(closes, period);
	 *
	 * return rsiValues[rsiValues.length - 1]; // 최신 RSI 값 반환 }
	 *
	 * private double[] calculateRsi(List<Double> closes, int period) { double[]
	 * rsiValues = new double[closes.size()]; double sumGain = 0.0; double sumLoss =
	 * 0.0;
	 *
	 * // 초기 SMA (Simple Moving Average) 계산 for (int i = 1; i <= period; i++) {
	 * double change = closes.get(i) - closes.get(i - 1); if (change >= 0) { sumGain
	 * += change; } else { sumLoss -= change; } } double avgGain = sumGain / period;
	 * double avgLoss = sumLoss / period;
	 *
	 * // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산 for (int i =
	 * period; i < closes.size(); i++) { double change = closes.get(i) -
	 * closes.get(i - 1); double gain = (change >= 0) ? change : 0; double loss =
	 * (change < 0) ? -change : 0;
	 *
	 * avgGain = (avgGain * (period - 1) + gain) / period; avgLoss = (avgLoss *
	 * (period - 1) + loss) / period;
	 *
	 * double rs = avgGain / avgLoss; double rsi = 100 - (100 / (1 + rs));
	 * rsiValues[i] = rsi; }
	 *
	 * return rsiValues; }
	 *
	 * //업비트 rsi14 적용
	 *
	 * @ResponseBody
	 *
	 * @GetMapping("/rsi14") public double getCurrentRsi14() { String market =
	 * "KRW-BTC"; int period = 14; List<BigDecimal> closes = new ArrayList<>();
	 *
	 * OkHttpClient client = new OkHttpClient();
	 *
	 * // Upbit API를 통해 비트코인의 최근 100개의 3분봉 가격 데이터 가져오기 String apiUrl =
	 * "https://api.upbit.com/v1/candles/minutes/3?market=" + market + "&count=100";
	 * Request request = new Request.Builder() .url(apiUrl) .get() .build();
	 *
	 * try (Response response = client.newCall(request).execute()) { if
	 * (!response.isSuccessful()) throw new IOException("Unexpected code " +
	 * response);
	 *
	 * String responseBody = response.body().string();
	 *
	 * // JSON 데이터 파싱 JSONArray candlesArray = new JSONArray(responseBody); for (int
	 * i = 0; i < candlesArray.length(); i++) { JSONObject candle =
	 * candlesArray.getJSONObject(i); BigDecimal closePrice =
	 * candle.getBigDecimal("trade_price"); closes.add(closePrice); } } catch
	 * (IOException e) { e.printStackTrace(); return -1; // 오류 발생 시 -1 반환 }
	 *
	 * // 데이터가 부족한 경우 처리 if (closes.size() < period + 1) { return -1; // 데이터가 부족한 경우
	 * -1 반환 }
	 *
	 * // RSI 계산 double rsi = calculateRsi14(closes, period);
	 *
	 * return rsi; // 최신 RSI 값 반환 }
	 *
	 * private double calculateRsi14(List<BigDecimal> closes, int period) {
	 * BigDecimal sumGain = BigDecimal.ZERO; BigDecimal sumLoss = BigDecimal.ZERO;
	 *
	 * // 초기 SMA (Simple Moving Average) 계산 for (int i = 1; i <= period; i++) {
	 * BigDecimal change = closes.get(i).subtract(closes.get(i - 1)); if
	 * (change.compareTo(BigDecimal.ZERO) >= 0) { sumGain = sumGain.add(change); }
	 * else { sumLoss = sumLoss.add(change.abs()); } } BigDecimal avgGain =
	 * sumGain.divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
	 * BigDecimal avgLoss = sumLoss.divide(BigDecimal.valueOf(period), 4,
	 * RoundingMode.HALF_UP);
	 *
	 * // 초기 RS (Relative Strength) 및 RSI (Relative Strength Index) 계산 for (int i =
	 * period; i < closes.size(); i++) { BigDecimal change =
	 * closes.get(i).subtract(closes.get(i - 1)); BigDecimal gain =
	 * (change.compareTo(BigDecimal.ZERO) >= 0) ? change : BigDecimal.ZERO;
	 * BigDecimal loss = (change.compareTo(BigDecimal.ZERO) < 0) ? change.abs() :
	 * BigDecimal.ZERO;
	 *
	 * avgGain = (avgGain.multiply(BigDecimal.valueOf(period -
	 * 1)).add(gain)).divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
	 * avgLoss = (avgLoss.multiply(BigDecimal.valueOf(period -
	 * 1)).add(loss)).divide(BigDecimal.valueOf(period), 4, RoundingMode.HALF_UP);
	 *
	 * double rs = avgGain.divide(avgLoss, 4, RoundingMode.HALF_UP).doubleValue();
	 * double rsi = 100 - (100 / (1 + rs)); return rsi; }
	 *
	 * return -1; // 데이터가 부족한 경우 -1 반환 }
	 */

    //rsi 스케줄러



    //거래대금 50개 상위
    @ResponseBody
    @GetMapping("/rsiSearch")
    public String rsiSearch() {

    	String a = coinservice2.rsiSearch();


        return a;

    }



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





