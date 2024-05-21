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
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.dto.OrderVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;
import com.example.bitcoin.service.serviceimpl.coinserviceimpl;

@Controller
public class GetAccounts<ChartData> {


    @Autowired
    coinservice coinservice2;

    @Autowired
    coinmapper coinmapper4;

    @Autowired
    coinserviceimpl coinserviceimpl2;

    public GetAccounts(coinserviceimpl coinserviceimpl2) {
        this.coinserviceimpl2 = coinserviceimpl2;
    }


    // 시작 시 첫 페이지
    @GetMapping("/")
    public String loginPage2() {
        return "/login.html";
    }

    // 로그인 페이지
    @GetMapping("/login")
    public String loginPage() {
        return "/login.html";
    }

    //회원가입 페이지
    @GetMapping("/memberJoin")
    public String joinPage() {
        return "/memberJoin.html";
    }

    //차트
    @GetMapping("/chart")
    public String chart() {
        return "/chart.html";

    }

    //차트2
    @GetMapping("/chart2")
    public String chart2() {
        return "/chart2.html";

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
        return "bitcoin_cash";
    }

    @GetMapping("/home")
    public String logout() {
        return "/home.html";
    }

    //주문하기 페이지
    @GetMapping("/orderPage")
    public String orderPage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "/order.html";
    }
    //자동매매 페이지
    @GetMapping("/changeAuto")
    public String chagePage(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "/changeAuto.html";
    }

    //자동매매 모니터링 페이지
    @GetMapping("/monitoring")
    public String monitoring(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        model.addAttribute("user", userDetails.getUsername());
        return "/monitoring.html";
    }

    //마이 페이지
    @GetMapping("/myPage")
    public String myPage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("user", userDetails.getUsername());
        return "/myContents.html";
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
        return "/mainPage.html";
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
    public String board() {

        return "/board.html";
    }

    //게시판 전체 리스트
    @ResponseBody
    @GetMapping("/boardListAjax")
    public List<BoardVO> boardListAjax() {

        List<BoardVO> list = coinservice2.getList();

        return list;
    }

    //헤더 호출
    @GetMapping("/header.html")
    public String header(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    	model.addAttribute("user", userDetails.getUsername());
        return "header.html";
    }

    //헤더2 호출
    @GetMapping("/header2.html")
    public String header2() {

        return "header2.html";
    }



    //푸터 호출
    @GetMapping("/footer.html")
    public String footer(){
        return "footer.html";
    }

    @GetMapping("/memberManage")
    public String memberManage(@AuthenticationPrincipal UserDetails userDetails, Model model){
        model.addAttribute("user", userDetails.getUsername());
        return "/memberManage.html";
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
            System.out.println(autoCheck.get(0).getAuto());
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
                        if (targetPrice.compareTo(nowPrice) > 0) {

                            System.out.println("목표 타겟 도달!!!");

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
                                    vo2.setPrice("10000");
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

                                                //if(formatedNow.equals("09:00")) {

                                                balance = jsonObject4.getString("balance");
                                                System.out.println(balance);
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

                                                //}
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

                            System.out.println("목표 타겟 미 도달!!");

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


