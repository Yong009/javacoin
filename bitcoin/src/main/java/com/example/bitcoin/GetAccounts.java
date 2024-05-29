package com.example.bitcoin;


import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.CommentVO;
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.dto.OrderVO;
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
    public List<MemberVO> memberAll(){

        List<MemberVO> vo = coinservice2.getMemberAll();
        return vo;
    }

    //회원탈퇴
    @ResponseBody
    @PostMapping("/deleteMember")
    public void deleteMember(@RequestBody MemberVO vo){

        coinservice2.deleteMember(vo.getId());

    }



    //회원가입

    @GetMapping("/join")
    public void registerMember(MemberVO member){

        coinservice2.memberJoin(member);

    }

    //아이디 중복확인
    @GetMapping("/check")
    public int checkId(MemberVO member){
        int a =coinservice2.checkId(member);
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
    public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
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
    public void updateMember(@RequestBody MemberVO vo){

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

    //게시판 상세 페이지
    @GetMapping("/boardDetail")
    public String boardDetail(@AuthenticationPrincipal UserDetails userDetails, Model model){
    	model.addAttribute("user", userDetails.getUsername());
    	return "boardDetail";
    }

    //게시판 상세 내용
    @ResponseBody
    @PostMapping("/boardDetailAjax")
    public List<BoardVO> boardDetailAjax(@RequestBody BoardVO vo){

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
    public List<CommentVO> comment(@RequestBody CommentVO vo){

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
    public void deleteComment(@RequestBody CommentVO vo){

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
    public List<QuestionVO> getQuestion(){

    	List<QuestionVO> list = coinservice2.getQuestion();

    	return list;
    }

    //건의 사항 불러오기 ( 그 사람만 )
    @ResponseBody
    @GetMapping("/questionAjax2")
    public List<QuestionVO> getQuestion2(@RequestBody QuestionVO vo){

    	List<QuestionVO> list = coinservice2.getQuestion2(vo.getWriter());

    	return list;
    }

    //건의 사항 상세
    @ResponseBody
    @PostMapping("/questionDetailAjax")
    public List<QuestionVO> getQuestionDetail(@RequestBody QuestionVO vo){

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
    public String footer(){
        return "footer";
    }

    //회원 관리
    @GetMapping("/memberManage")
    public String memberManage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("user", userDetails.getUsername());
        return "memberManage";
    }

    //마켓 정보
    @ResponseBody
    @GetMapping("/market7")
    public String marketTest() {

        return coinservice2.market7();
    }

    //변동성 돌파 전략
    @ResponseBody
    @PostMapping("/autoTrade")
    public void auto(@RequestBody MemberVO member) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        coinservice2.autoOn(member.getId());

        String ac = member.getAccessCode();

        /*String ac = "EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8";*/
        String sc = member.getSecretCode();
        /*String sc = "1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG";*/
        String userId = member.getId();

        MemberVO vo = new MemberVO();
        boolean b1 = true;

        while (b1) {

            List<MemberVO> autoCheck = coinservice2.getCode(userId);
            //System.out.println(autoCheck.get(0).getAuto());
            String check = autoCheck.get(0).getAuto();
            if(check.equals("N")){
               b1 = false;

            }else{
                boolean b2 = true;
                //System.out.println(vo);
                vo.setAccessCode(ac);
                vo.setSecretCode(sc);
                String a = coinservice2.market7();
                String b = coinservice2.currentPrice7();
                String c = coinservice2.account7(vo);
                String market;
                String market2;
                String koreanName;
                String currency;
                String balance;
                String coin2;

                BigDecimal lowPrice;
                BigDecimal highPrice;
                BigDecimal prevClosePrice;
                BigDecimal targetPrice;
                BigDecimal minus;
                BigDecimal multi;
                BigDecimal dotFive = new BigDecimal(0.5);
                BigDecimal nowPrice;

                JSONArray jsonArray = new JSONArray(a);   //마켓
                JSONArray jsonArray2 = new JSONArray(b);  //현재가
                JSONArray jsonArray3 = new JSONArray(c);  //잔고

                //for(int i=0; i<jsonArray.length(); i++){
                //JSONObject jsonObject = jsonArray.getJSONObject(i);

                //market = jsonObject.getString("market");
                //koreanName = jsonObject.getString("korean_name");

                for (int j = 0; j < jsonArray2.length(); j++) {

                    JSONObject jsonObject2 = jsonArray2.getJSONObject(j);
                    market2 = jsonObject2.getString("market");

                    if (market2.contains("KRW-BTC")) {


                        highPrice = jsonObject2.getBigDecimal("high_price");
                        lowPrice = jsonObject2.getBigDecimal("low_price");
                        prevClosePrice = jsonObject2.getBigDecimal("prev_closing_price");
                        nowPrice = jsonObject2.getBigDecimal("trade_price");
                        minus = highPrice.subtract(lowPrice);
                        multi = minus.multiply(dotFive);
                        targetPrice = prevClosePrice.add(multi);
                        //System.out.println(dotFive);

                        // add 덧셈 subtract 뺄셈, multiply 곱셈, divide 나눗셈


                        //-1 작은 경우,  0 같은 경우, 1 큰경우
                        if (targetPrice.compareTo(nowPrice) <= 0) {

                            //System.out.println("목표 타겟 도달!!!");

                            for (int i = 0; i < jsonArray3.length(); i++) {

                                JSONObject jsonObject3 = jsonArray3.getJSONObject(i);

                                currency = jsonObject3.getString("currency");
                                //balance  = jsonObject3.getString("balance");

                                if (currency.equals("KRW")) {

                                    OrderVO vo2 = new OrderVO();
                                    vo2.setCoin("KRW-BTC");
                                    vo2.setAccessCode(vo.getAccessCode());
                                    vo2.setSecretCode(vo.getSecretCode());
                                    vo2.setOrderType("bid");
                                    vo2.setPrice("6000");
                                    coinservice2.order7(vo2);

                                    while (b2) {

                                        String d = coinservice2.account7(vo);
                                        JSONArray jsonArray4 = new JSONArray(d);

                                        for (int idx = 0; idx < jsonArray4.length(); idx++) {


                                            JSONObject jsonObject4 = jsonArray4.getJSONObject(idx);
                                            coin2 = jsonObject4.getString("currency");

                                            if (coin2.equals("BTC") ) {

                                                LocalTime now = LocalTime.now();
                                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                                                String formatedNow = now.format(formatter);
                                                //System.out.println(formatedNow);
                                                if(formatedNow.equals("09:00")) {

                                                balance = jsonObject4.getString("balance");
                                                //System.out.println(balance);
                                                OrderVO vo3 = new OrderVO();
                                                vo3.setCoin("KRW-BTC");
                                                vo3.setAccessCode(vo.getAccessCode());
                                                vo3.setSecretCode(vo.getSecretCode());
                                                vo3.setOrderType("ask");
                                                vo3.setVolume(balance);

                                                coinservice2.sell7(vo3);
                                                b2 = false;

                                                try {
                                                    Thread.sleep(30000);
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }

                                               }
                                            }

                                        }

                                        try {
                                            Thread.sleep(1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }
                                }
                            }


                        } else {

                            //System.out.println("목표 타겟 미 도달!!");

                        }


                    }
                }
            }



        }
    }



    //변동성 돌파 전략 자동 끄기
    @ResponseBody
    @PostMapping("/autoStop")
    public void autoStop(@RequestBody MemberVO vo) {

        coinservice2.autoStop7(vo.getId());
    }

	/* rsi자동매매
	 * @GetMapping("/rsi") public void rsiAuto() {
	 *
	 * final int minutes = 30; final String market = "KRW-BTC"; final int maxCount =
	 * 200; // 업비트 캔들 API 호출 (Docs:
	 * https://docs.upbit.com/reference/%EB%B6%84minute-%EC%BA%94%EB%93%A4-1)
	 * List<MinuteCandleRes> candleResList =
	 * marketPriceReaderService.getCandleMinutes(minutes, market, maxCount); if
	 * (CollectionUtils.isEmpty(candleResList)) { return null; }
	 *
	 * // 지수 이동 평균은 과거 데이터부터 구해주어야 합니다. candleResList = candleResList.stream()
	 * .sorted(Comparator.comparing(CandleRes::getTimestamp)) // 오름차순 (과거 순)
	 * .collect(Collectors.toList()); // Sort
	 *
	 * double zero = 0; List<Double> upList = new ArrayList<>(); // 상승 리스트
	 * List<Double> downList = new ArrayList<>(); // 하락 리스트 for (int i = 0; i <
	 * candleResList.size() - 1; i++) { // 최근 종가 - 전일 종가 = gap 값이 양수일 경우 상승했다는 뜻 /
	 * 음수일 경우 하락이라는 뜻 double gapByTradePrice = candleResList.get(i +
	 * 1).getTradePrice().doubleValue() -
	 * candleResList.get(i).getTradePrice().doubleValue(); if (gapByTradePrice > 0)
	 * { // 종가가 전일 종가보다 상승일 경우 upList.add(gapByTradePrice); downList.add(zero); }
	 * else if (gapByTradePrice < 0) { // 종가가 전일 종가보다 하락일 경우
	 * downList.add(gapByTradePrice * -1); // 음수를 양수로 변환해준다. upList.add(zero); }
	 * else { // 상승, 하락이 없을 경우 종가 - 전일 종가 = gap은 0이므로 0값을 넣어줍니다. upList.add(zero);
	 * downList.add(zero); } }
	 *
	 * double day = 14; // 가중치를 위한 기준 일자 (보통 14일 기준) double a = (double) 1 / (1 +
	 * (day - 1)); // 지수 이동 평균의 정식 공식은 a = 2 / 1 + day 이지만 업비트에서 사용하는 수식은 a = 1 / (1
	 * + (day - 1))
	 *
	 * // AU값 구하기 double upEma = 0; // 상승 값의 지수이동평균 if
	 * (CollectionUtils.isNotEmpty(upList)) { upEma = upList.get(0).doubleValue();
	 * if (upList.size() > 1) { for (int i = 1 ; i < upList.size(); i++) { upEma =
	 * (upList.get(i).doubleValue() * a) + (upEma * (1 - a)); } } }
	 *
	 * // AD값 구하기 double downEma = 0; // 하락 값의 지수이동평균 if
	 * (CollectionUtils.isNotEmpty(downList)) { downEma =
	 * downList.get(0).doubleValue(); if (downList.size() > 1) { for (int i = 1; i <
	 * downList.size(); i++) { downEma = (downList.get(i).doubleValue() * a) +
	 * (downEma * (1 - a)); } } }
	 *
	 * // rsi 계산 double au = upEma; double ad = downEma; double rs = au / ad; double
	 * rsi = 100 - (100 / (1 + rs));
	 *
	 * }
	 */

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
    @GetMapping("/memberAuto")
    public List<MemberVO> memberAuto (@RequestBody MemberVO vo){

        List<MemberVO> member = coinservice2.getMemberAuto(vo.getAuto());
        return member;
    }
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


