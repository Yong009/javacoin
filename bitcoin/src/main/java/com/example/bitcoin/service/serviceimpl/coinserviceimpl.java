package com.example.bitcoin.service.serviceimpl;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.bitcoin.dto.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;


import org.springframework.stereotype.Service;

import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;


@Service
@Primary
public class coinserviceimpl implements coinservice {

    @Autowired
    coinmapper coinmappers;


    //PasswordEncoder passwordEncoder;

    //회원가입
    /*@Override
    public void join(MemberVO vo) {

        MemberVO member = MemberVO.createUser(vo.getId(), vo.getPassword(), passwordEncoder);
        coinmappers.join(member);


    }*/

    //로그인
    /*@Override
    public boolean findOne(String userId, String pw) {
        MemberVO member = coinmappers.getUserDetails(userId);

        if(bCryptPasswordEncoder.matches(pw, member.getPassword())){
            return true; // 입력한 비밀번호와 저장소의 비밀번호가 일치
        } else {
            return false;
        }

    }*/

    @Override
    public boolean findOne(String userId, String pw) {
        return false;
    }

    //코드 가져오기
    @Override
    public List<MemberVO> getCode(String id) {
        return coinmappers.getCode(id);
    }

    //코드 저장
    @Override
    public boolean saveCode(MemberVO vo) {

        return coinmappers.saveCode(vo);
    }

    //게시판 전체 리스트
    @Override
    public List<BoardVO> getList() {

        return coinmappers.getList();
    }

    //잔고 조회
    @Override
    public String account7(MemberVO vo) {

        String accessKey = vo.getAccessCode();
        /* String accessKey = "EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8"; */
        /*System.getenv("EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8");  */// access 코드
        String secretKey = vo.getSecretCode();
        /* String secretKey = "1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG"; */
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

    //현재가 조회
    @Override
    public String currentPrice7() {

        OkHttpClient client4 = new OkHttpClient();


        okhttp3.Request request4 = new okhttp3.Request.Builder()
                .url("https://api.upbit.com/v1/market/all")
                .get()
                .addHeader("accept", "application/json")
                .build();

        String responseBody = null;
        try {

            okhttp3.Response response4 = client4.newCall(request4).execute();
            responseBody = response4.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Gson gson = new Gson();
        Type marketListType = new TypeToken<ArrayList<MarketVO>>() {
        }.getType();
        ArrayList<MarketVO> allMarkets = gson.fromJson(responseBody, marketListType);


        List<String> krwMarkets = allMarkets.stream()
                .filter(market -> market.getMarket().startsWith("KRW-")) // getMarket()으로 수정
                .map(market -> market.getMarket()) // getMarket()으로 수정
                .collect(Collectors.toList());

        /*String marketParam = String.join(",", krwMarkets.subList(0, Math.min(krwMarkets.size(), 10)));*/
        String marketParam = String.join(",", krwMarkets);

        okhttp3.Request tickerRequest = new okhttp3.Request.Builder()
                .url("https://api.upbit.com/v1/ticker?markets=" + marketParam)  //marketParam
                .get()
                .addHeader("Accept", "application/json")
                .build();


        OkHttpClient client5 = new OkHttpClient();
        String tickerResponseBody = null;
        TickerVO tickerResponseBody2 = null;
        try {
            okhttp3.Response tickerResponse = client5.newCall(tickerRequest).execute();
            tickerResponseBody = tickerResponse.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }

        /* System.out.println(tickerResponseBody); */


        return tickerResponseBody;
    }

    //마켓 정보
    @Override
    public String market7() {
        OkHttpClient client4 = new OkHttpClient();


        okhttp3.Request request4 = new okhttp3.Request.Builder()
                .url("https://api.upbit.com/v1/market/all")
                .get()
                .addHeader("accept", "application/json")
                .build();

        String responseBody = null;
        try {

            okhttp3.Response response4 = client4.newCall(request4).execute();
            responseBody = response4.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /* System.out.println(responseBody); */
        return responseBody;
    }

    //매수 하기
    @Override
    public String order7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String accessKey = vo.getAccessCode();
        /* String accessKey = "EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8"; */
        /*System.getenv("EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8");  */// access 코드
        String secretKey = vo.getSecretCode();
        /* String secretKey = "1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG"; */
        /*System.getenv("1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG");  */// secret 코드
        String serverUrl = "https://api.upbit.com";

        HashMap<String, String> params = new HashMap<>();
        //vo.setCoin("KRW-BTC");
        params.put("market", vo.getCoin());
        //params.put("market", "KRW-BTC");
        //params.put("side", "bid");
        params.put("side", vo.getOrderType());               //bid : 매수 ,  ask: 매도
        //params.put("side", "bid");               //bid : 매수 ,  ask: 매도
//        if(vo.getOrderType() != "bid") {
//        	params.put("volume", vo.getVolume());
//        }
        //params.put("price", "10000");

        params.put("price", vo.getPrice());
        //params.put("price", "10000");
        params.put("ord_type", "price");   // limit : 지정가 주문 , price : 시장가 주문(매수),  market: 시장가 주문(매도), best: 최유리 주문(time_in_force 설정 필수 )

//        System.out.println(vo.getCoin());
//        System.out.println(vo.getOrderType());
//        System.out.println(vo.getPrice());
//        System.out.println(vo.getAccessCode());
//        System.out.println(vo.getSecretCode());

        ArrayList<String> queryElements = new ArrayList<>();
        for (Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));

            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    //매도하기
    @Override
    public String sell7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String accessKey = vo.getAccessCode();
        /* String accessKey = "EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8"; */
        /*System.getenv("EV9kG9xxOPFOiJZng83Zf0c2xyQIy3Gfdq6rf0W8");  */// access 코드
        String secretKey = vo.getSecretCode();
        /* String secretKey = "1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG"; */
        /*System.getenv("1PxWx72txMJq7xDpvxYYyD0NLzxYMBwV4r9Q8jGG");  */// secret 코드
        String serverUrl = "https://api.upbit.com";

        HashMap<String, String> params = new HashMap<>();
        params.put("market", vo.getCoin());
        //params.put("market", "KRW-BTC");
        //params.put("market", "KRW-BTC");
        params.put("side", vo.getOrderType());  //bid : 매수 ,  ask: 매도
        params.put("volume", vo.getVolume());
        //params.put("price", vo.getPrice());
        params.put("ord_type", "market");   // limit : 지정가 주문 , price : 시장가 주문(매수),  market: 시장가 주문(매도), best: 최유리 주문(time_in_force 설정 필수 )
//        System.out.println(vo.getCoin());
//        System.out.println(vo.getOrderType());
//        System.out.println(vo.getVolume());
//
//        System.out.println(vo.getAccessCode());
//        System.out.println(vo.getSecretCode());

        ArrayList<String> queryElements = new ArrayList<>();
        for (Map.Entry<String, String> entity : params.entrySet()) {
            queryElements.add(entity.getKey() + "=" + entity.getValue());
        }

        String queryString = String.join("&", queryElements.toArray(new String[0]));

        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(queryString.getBytes("UTF-8"));

        String queryHash = String.format("%0128x", new BigInteger(1, md.digest()));

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        String jwtToken = JWT.create()
                .withClaim("access_key", accessKey)
                .withClaim("nonce", UUID.randomUUID().toString())
                .withClaim("query_hash", queryHash)
                .withClaim("query_hash_alg", "SHA512")
                .sign(algorithm);

        String authenticationToken = "Bearer " + jwtToken;

        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(serverUrl + "/v1/orders");
            request.setHeader("Content-Type", "application/json");
            request.addHeader("Authorization", authenticationToken);
            request.setEntity(new StringEntity(new Gson().toJson(params)));

            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity, "UTF-8"));
            return EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}