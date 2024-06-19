package com.example.bitcoin;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.CommentVO;
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.dto.OrderVO;
import com.example.bitcoin.dto.PagingVO;
import com.example.bitcoin.dto.PriceVO;
import com.example.bitcoin.dto.QuestionVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;
import com.example.bitcoin.service.serviceimpl.coinserviceimpl;

@Controller
public class GetAccounts {


    @Autowired
    coinservice coinservice2;

    @Autowired
    coinmapper coinmapper4;

    @Autowired
    coinserviceimpl coinserviceimpl2;

    public GetAccounts(coinserviceimpl coinserviceimpl2) {
        this.coinserviceimpl2 = coinserviceimpl2;
    }


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

    @GetMapping("/error")
    public String error() {

        return "error";

    }

    //회원가입 페이지
    @GetMapping("/memberJoin")
    public String joinPage() {
        return "memberJoin";
    }

    //차트
    @GetMapping("/chart")
    public String chart() {
        return "chart";

    }

    //차트2
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

    //회원탈퇴
    @ResponseBody
    @PostMapping("/deleteMember")
    public void deleteMember(@RequestBody MemberVO vo) {

        coinservice2.deleteMember(vo.getId());

    }

    //회원가입

    @GetMapping("/join")
    public void registerMember(MemberVO member) {

        coinservice2.memberJoin(member);

    }

    //아이디 중복확인
    @GetMapping("/check")
    public int checkId(MemberVO member) {
        int a = coinservice2.checkId(member);
        return a;
    }

    //이미지
    @GetMapping("/bit")
    public String image() {
        return "bitcoin.jpg";
    }

    //이미지2
    @GetMapping("/bit2")
    public String image2() {
        return "Bitcoin_Cash.png";
    }

    @GetMapping("/home")
    public String logout() {
        return "home";
    }

    //주문하기 페이지
    @GetMapping("/orderPage")
    public String orderPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "order";
    }

    //자동매매 페이지
    @GetMapping("/changeAuto")
    public String chagePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "changeAuto";
    }

    //자동매매 모니터링 페이지
    @GetMapping("/monitoring")
    public String monitoring(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "monitoring";
    }

    //마이 페이지
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

    //코드 저장
    @ResponseBody
    @PostMapping("/saveCode")
    public boolean saveCode(@RequestBody MemberVO vo) {

        List<MemberVO> vo2 = new ArrayList<>();

        boolean a = coinservice2.saveCode(vo);

        return a;
    }

    //회원 정보 수정
    @ResponseBody
    @PostMapping("/updateMember")
    public void updateMember(@RequestBody MemberVO vo) {

        coinservice2.updateMember(vo);
    }

    //게시판 페이지 이동
    @GetMapping("/board")
    public String board(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "board";
    }

    //게시판 페이지 이동(비회원용)
    @GetMapping("/board2")
    public String board2() {

        return "board2";
    }

    //게시판 상세 페이지 이동 ( 비회원용 )
    @GetMapping("/boardDetail2")
    public String boardDetail2() {

        return "boardDetail2";
    }


    //게시판 글쓰기 페이지
    @GetMapping("/boardwrite")
    public String write(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "write";
    }

    @ResponseBody
    //게시판 글쓰기 등록
    @PostMapping("/insertBoard")
    public void insertBoard(@RequestBody BoardVO vo) {

        coinservice2.insertBoard(vo);
    }

    //게시판 전체 리스트
    @ResponseBody
    @GetMapping("/boardListAjax")
    public List<BoardVO> boardListAjax() {

        List<BoardVO> list = coinservice2.getList();
        return list;
    }

    //게시판 전체 개수

    @ResponseBody
    @GetMapping("/boardMax")
    public int boardMax() {

        return coinservice2.getMax();
    }

	/*
	 * // 회원 총 수
	 *
	 * @ResponseBody
	 *
	 * @GetMapping("/memberMax") public int memberMax() { return
	 * coinservice2.getMemberMax(); }
	 */

    // 회원 페이징
    @ResponseBody
    @PostMapping("/memberListAjax2")
    public List<MemberVO> memberListAjax2(@RequestBody PagingVO vo){

    	List<MemberVO> list = coinservice2.getMemberLists(vo);

    	return list;
    }



    //게시판 페이징 적용
    @ResponseBody
    @GetMapping("/boardListAjax2")
    public List<BoardVO> boardListAjax2(@RequestBody PagingVO vo) {

        List<BoardVO> list = coinservice2.getLists(vo.getPage());

        return list;
    }

    //게시판 상세 페이지
    @GetMapping("/boardDetail")
    public String boardDetail(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "boardDetail";
    }

    //게시판 상세 내용
    @ResponseBody
    @PostMapping("/boardDetailAjax")
    public List<BoardVO> boardDetailAjax(@RequestBody BoardVO vo) {

        List<BoardVO> list = coinservice2.getListDetail(vo.getSeq());

        return list;
    }

    //게시판 수정하기
    @ResponseBody
    @PostMapping("/updateBoard")
    public void updateBoard(@RequestBody BoardVO vo) {

        coinservice2.updateBoard(vo);
    }

    //조회수 증가
    @ResponseBody
    @PostMapping("/updateView")
    public void updateView(@RequestBody BoardVO vo) {

        coinservice2.updateView(vo);
    }

    //게시판 글 삭제
    @ResponseBody
    @PostMapping("/deleteBoard")
    public void deleteBoard(@RequestBody BoardVO vo) {

        coinservice2.deleteBoard(vo);
    }

    //댓글 불러오기
    @ResponseBody
    @PostMapping("/comment")
    public List<CommentVO> comment(@RequestBody CommentVO vo) {

        List<CommentVO> list = coinservice2.getComment(vo);

        return list;
    }

    //댓글 등록
    @ResponseBody
    @PostMapping("/insertComment")
    public void insertComment(@RequestBody CommentVO vo) {

        coinservice2.insertComment(vo);
    }

    //댓글 삭제
    @ResponseBody
    @PostMapping("/deleteComment")
    public void deleteComment(@RequestBody CommentVO vo) {

        coinservice2.deleteComment(vo);
    }

    //건의 사항 페에지
    @GetMapping("/question")
    public String questionPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "question";
    }

    //건의 사항 불러오기(전체)
    @ResponseBody
    @GetMapping("/questionAjax")
    public List<QuestionVO> getQuestion() {

        List<QuestionVO> list = coinservice2.getQuestion();

        return list;
    }

    //건의 사항 불러오기 ( 그 사람만 )
    @ResponseBody
    @GetMapping("/questionAjax2")
    public List<QuestionVO> getQuestion2(@RequestBody QuestionVO vo) {

        List<QuestionVO> list = coinservice2.getQuestion2(vo.getWriter());

        return list;
    }

    //건의 사항 상세
    @ResponseBody
    @PostMapping("/questionDetailAjax")
    public List<QuestionVO> getQuestionDetail(@RequestBody QuestionVO vo) {

        List<QuestionVO> list = coinservice2.getQuestionDetail(vo.getSeq());

        return list;
    }

    //건의 사항 상세 페이지
    @ResponseBody
    @GetMapping("/questionDetail")
    public String questionDetailPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "questionDetail";
    }


    //헤더 호출
    @GetMapping("/header.html")
    public String header(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "header";
    }

    //헤더2 호출
    @GetMapping("/header2.html")
    public String header2() {

        return "header2";
    }

    //푸터 호출
    @GetMapping("/footer.html")
    public String footer() {
        return "footer";
    }

    //회원 관리
    @GetMapping("/memberManage")
    public String memberManage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "memberManage";
    }

    //마켓 정보
    @ResponseBody
    @GetMapping("/market7")
    public String marketTest() {

        return coinservice2.market7();
    }

    //거래 내역 페이지
    @GetMapping("/buySellPage")
    public String buySellPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "buySell";
    }

    //주문 리스트
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
                /*"9ca023a5-851b-4fec-9f0a-48cd83c2eaae"*/
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
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

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

    //자동매매 on
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

    // 변동성 돌파 전략 스케줄러

    //변동성 돌파 전략 스케줄러 ( 5초마다 계속 )
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
        BigDecimal lowPrice, highPrice, prevClosePrice, targetPrice, minus, multi, nowPrice, lowPrice5, highPrice5,lowPrice2, highPrice2;
        BigDecimal dotFive = new BigDecimal(0.5);

        LocalTime now3 = LocalTime.now();
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("HH:mm");
        String formatedNow3 = now3.format(formatter3);

        if(formatedNow3.equals("08:59")) {
        	String mk = null;

        	try {
        		mk = coinservice2.currentPrice7();
        	} catch (Exception e) {
        		 logger.error("전일 고가, 저가의 API 호출에 실패했습니다: {}",e.getMessage());

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

            if ((accc == null || accc.equals(' ')) || (sece == null || sece.equals(' ')) || (autoPrice == null || autoPrice.equals(' '))) {

                logger.info("사용자 {}의 secret코드 또는 access코드 저장하지 않거나 가격을 입력하지 않았습니다. 저장 및 입력 되어야 자동매매 프로그램을 작동시킬 수 있습니다.!!", userId);
                continue;
            }

            MemberVO member = new MemberVO();
            member.setAccessCode(accc);
            member.setSecretCode(sece);
            member.setAutoPrice(autoPrice);

            //String mk = null;
            String cp = null;
            String account = null;

            try {
                // mk = coinservice2.market7();              //마켓
                cp = coinservice2.currentPrice7();       //현재가
                account = coinservice2.account7(member); //잔고
            } catch (Exception e) {
                logger.error("사용자 {}의 API 호출에 실패했습니다: {}", userId, e.getMessage());
                continue;
            }

            //JSONArray jsonArray = new JSONArray(mk);
            JSONArray jsonArray2 = new JSONArray(cp);         //현재가
            JSONArray jsonArray3 = new JSONArray(account);    //잔고

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

                        try {
                            coinservice2.sell7(vo3);
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

            if (hasKrwBtc == false && formatedNow2 != "09:00") {

                for (j = 0; j < jsonArray3.length(); j++) {
                    JSONObject jsonObject5 = jsonArray3.getJSONObject(j);
                    currency = jsonObject5.getString("currency");

                    if (currency.equals("KRW")) {
                        balance = jsonObject5.getString("balance");
                        BigDecimal big = new BigDecimal(balance);
                        BigDecimal Dec = big.setScale(0, BigDecimal.ROUND_DOWN); //숫자를 정수화
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

								/*
								 * highPrice2 = pv.get(0).getHighPrice(); lowPrice2 = pv.get(0).getLowPrice();
								 */
                            	//if(highPrice2 == null && highPrice2.equals(' ') && lowPrice2 == null && lowPrice2.equals(' ')) {
                            	highPrice2 = jsonObject2.getBigDecimal("high_price");
                                   lowPrice2 = jsonObject2.getBigDecimal("low_price");
                            	//}
                                prevClosePrice = jsonObject2.getBigDecimal("prev_closing_price");
                                nowPrice = jsonObject2.getBigDecimal("trade_price");
                                minus = highPrice2.subtract(lowPrice2);
                                //minus = highPrice.subtract(lowPrice);
                                multi = minus.multiply(dotFive);
                                targetPrice = prevClosePrice.add(multi);
                                nowPrice2 = nowPrice.toString();


                                if (targetPrice.compareTo(nowPrice) <= 0) {
                                    logger.info("사용자 {}의 목표 타겟 도달: 현재 가격 = {}, 목표 가격 = {}, 매수 금액 = {}", userId, nowPrice, targetPrice,autoPrice);
                                    OrderVO vo2 = new OrderVO();
                                    vo2.setCoin("KRW-BTC");
                                    vo2.setAccessCode(member.getAccessCode());
                                    vo2.setSecretCode(member.getSecretCode());
                                    vo2.setOrderType("bid");
                                    vo2.setPrice(member.getAutoPrice());
                                    //vo2.setPrice(balances2);

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
                                    logger.info("사용자 {}의  목표가에 오지 않아 매수 주문 실행 안함: 코인 = KRW-BTC, 매수 금액 = {}, 목표 가격 = {}", userId, autoPrice,targetPrice);
                                }
                            }
                        }
                    }

                }
            }

        }
    }


    //자동 매매 전일 저가, 고가 저장
    @ResponseBody
    @PostMapping("/highLowPrice")
    public void highLowPrice(@RequestBody PriceVO vo) {

    	coinservice2.updatePrice(vo);

    }

    //저가 , 고가 불러오기
    @ResponseBody
    @GetMapping("/yesterdayPrice")
    public List<PriceVO> yesterdayPrice(){

    	List<PriceVO> list = coinservice2.getPriceList();

    	return list;
    }


    //자동 매매 금액 저장
    @ResponseBody
    @PostMapping("/saveAutoPrice")
    public void saveAutoPrice(@RequestBody MemberVO vo) {

        coinservice2.saveAutoPrice(vo);
    }

    //자동매매 시 매수 금액 저장
    @ResponseBody
    @PostMapping("/saveOrderPrice")
    public void saveOrderPrice(@RequestBody MemberVO vo) {

        coinservice2.saveOrderPrice(vo);
    }

    //수익률 계산
    @ResponseBody
    @PostMapping("/profit")
    public List<MemberVO> myAutoProfit(@RequestBody MemberVO vo) {

    	List<MemberVO> list = coinservice2.getCode(vo.getId());
    	list.get(0).getAutoPrice();
    	list.get(0).getOrderPrice();


    	return list;

    }

    @ResponseBody
    @GetMapping("/rsi")
    public void rsi() {
    }

    //관리자가 자동매매 끄기
    @ResponseBody
    @PostMapping("/manageAuto")
    public void manageAuto(@RequestBody MemberVO vo) {
        coinservice2.manageAuto(vo.getId());
    }

    //현재가 정보
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


    //매도 하기 test
    @ResponseBody
    @PostMapping("/sell7")
    public String sellTest(@RequestBody OrderVO vo) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        String a = coinservice2.sell7(vo);
        return a;
    }

    //모니터링
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
        String data = "{\n" +
                "    \"CANO\": \"종합계좌번호\",\n" +
                "    \"ACNT_PRDT_CD\": \"계좌상품코드\",\n" +
                "    \"ACNT_PWD\": \"계좌비밀번호\",\n" +
                "    \"PDNO\": \"상품번호\",\n" +
                "    \"ORD_DVSN\": \"주문구분\",\n" +
                "    \"ORD_QTY\": \"주문수량\",\n" +
                "    \"ORD_UNPR\": \"주문단가\",\n" +
                "    \"CTAC_TLNO\": \"연락전화번호\"\n" +
                "}";
        //httpPostBodyConnection(url,data,tr_id);

        //public static void httpPostBodyConnection(String UrlData, String ParamData,String TrId) throws IOException {
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
        String data = "{\n" +
                "    \"CANO\": \"종합계좌번호\",\n" +
                "    \"ACNT_PRDT_CD\": \"계좌상품코드\",\n" +
                "    \"ACNT_PWD\": \"계좌비밀번호\",\n" +
                "    \"PDNO\": \"상품번호\",\n" +
                "    \"ORD_UNPR\": \"주문단가\",\n" +
                "    \"ORD_DVSN\": \"주문구분\",\n" +
                "    \"CMA_EVLU_AMT_ICLD_YN\": \"CMA평가금액포함여부\",\n" +
                "    \"OVRS_ICLD_YN\": \"해외포함여부\"\n" +
                "}";


        String totalUrl = "";
        totalUrl = url.trim().toString();

        URL url2 = null;
        HttpURLConnection conn = null;

        String responseData = "";
        BufferedReader br = null;

        StringBuffer sb = new StringBuffer();
        String returnData = "";

        try{
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
        } catch (IOException e){
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
                if (br != null){
                    br.close();
                }
            } catch (IOException e){
                throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
            }
        }
    }

    // 한국투자증권 test 국내 주식 시세
    @GetMapping("/koreaPrice")
    public void koreaPrice() throws UnsupportedEncodingException {
        // 국내 주식 시세 조회
        String url = "https://openapi.koreainvestment.com:9443/uapi/domestic-stock/v1/quotations/inquire-price";
        String tr_id = "FHKST01010100";
        String data = "?fid_cond_mrkt_div_code=J" + //FID 조건 시장 분류 코드
                "&fid_input_iscd=000660"; //FID 입력 종목코드

        String totalUrl = "";
        totalUrl = url.trim().toString();

        URL url2 = null;
        HttpURLConnection conn = null;

        String responseData = "";
        BufferedReader br = null;

        StringBuffer sb = new StringBuffer();
        String returnData = "";


        try{
            url2 = new URL(totalUrl+data);
            conn = (HttpURLConnection) url2.openConnection();
            conn.setRequestMethod("GET");
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

            conn.connect();

            br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        } catch (IOException e){
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
                if (br != null){
                    br.close();
                }
            } catch (IOException e){
                throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
            }
        }
    }



}


//}




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


