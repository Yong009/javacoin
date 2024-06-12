package com.example.bitcoin.service.serviceimpl;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.bitcoin.dto.BoardVO;
import com.example.bitcoin.dto.CommentVO;
import com.example.bitcoin.dto.MarketVO;
import com.example.bitcoin.dto.MemberVO;
import com.example.bitcoin.dto.OrderVO;
import com.example.bitcoin.dto.PagingVO;
import com.example.bitcoin.dto.PriceVO;
import com.example.bitcoin.dto.QuestionVO;
import com.example.bitcoin.dto.TickerVO;
import com.example.bitcoin.mapper.coinmapper;
import com.example.bitcoin.service.coinservice;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import okhttp3.OkHttpClient;


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

        String secretKey = vo.getSecretCode();

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


        return responseBody;
    }

    //매수 하기
    @Override
    public String order7(OrderVO vo) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        String accessKey = vo.getAccessCode();

        String secretKey = vo.getSecretCode();

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

        String secretKey = vo.getSecretCode();

        String serverUrl = "https://api.upbit.com";

        HashMap<String, String> params = new HashMap<>();
        params.put("market", vo.getCoin());
        //params.put("market", "KRW-BTC");
        //params.put("market", "KRW-BTC");
        params.put("side", vo.getOrderType());  //bid : 매수 ,  ask: 매도
        params.put("volume", vo.getVolume());
        //params.put("price", vo.getPrice());
        params.put("ord_type", "market");   // limit : 지정가 주문 , price : 시장가 주문(매수),  market: 시장가 주문(매도), best: 최유리 주문(time_in_force 설정 필수 )


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


    //자동매매 끄기
    @Override
	public void autoStop7(String id) {
		coinmappers.autoStop(id);

	}

    @Override
    public void autoOn(String id) {
        coinmappers.autoOn(id);
    }

    @Override
    public List<MemberVO> getMemberAuto(String auto) {
        return coinmappers.getMemberAuto(auto);
    }

    @Override
    public void updateMember(MemberVO vo) {
        coinmappers.updateMember(vo);
    }

    @Override
    public List<MemberVO> getMemberAll() {

        List<MemberVO> vo = coinmappers.getMemberAll();
        return vo;
    }

    @Override
    public void deleteMember(String id) {

        coinmappers.deleteMember(id);
    }
    //회원가입
    @Override
    public void memberJoin(MemberVO member) {
        coinmappers.membereJoin(member);
    }

    @Override
    public int checkId(MemberVO member) {

        int a = coinmappers.checkId(member);

        return a;
    }

	@Override
	public void insertBoard(BoardVO vo) {

		coinmappers.insertBoard(vo);

	}

	@Override
	public List<BoardVO> getListDetail(Long seq) {

		List<BoardVO> list = coinmappers.getListDetail(seq);

		return list;
	}

	@Override
	public void updateBoard(BoardVO vo) {

		coinmappers.updateBoard(vo);

	}

	@Override
	public void updateView(BoardVO vo) {

		coinmappers.updateView(vo);

	}

	@Override
	public void deleteBoard(BoardVO vo) {

		coinmappers.deleteBoard(vo);

	}

	@Override
	public List<CommentVO> getComment(CommentVO vo) {

		List<CommentVO> list = coinmappers.getComment(vo);

		return list;
	}

	@Override
	public void deleteComment(CommentVO vo) {

		coinmappers.deleteComment(vo);

	}

	@Override
	public void insertComment(CommentVO vo) {

		coinmappers.insertComment(vo);

	}

	@Override
	public List<QuestionVO> getQuestion() {

		List<QuestionVO> list =  coinmappers.getQuestion();
		return list;
	}

	@Override
	public List<QuestionVO> getQuestion2(String writer) {

		List<QuestionVO> list = coinmappers.getQuestion2(writer);
		return list;
	}

	@Override
	public List<QuestionVO> getQuestionDetail(Long seq) {

		List<QuestionVO> list = coinmappers.getQuestionDetail(seq);
		return list;
	}

    @Override
    public List<MemberVO> autoCheck() {

        List<MemberVO> list = coinmappers.autoCheck();
        return list;
    }

	@Override
	public void manageAuto(String id) {

		coinmappers.manageAuto(id);

	}

	@Override
	public List<BoardVO> getLists(int page) {
		int offSet = page - 1;
		return coinmappers.getLists(offSet);
	}

	@Override
	public int getMax() {

		int a = coinmappers.getMax();


		return a;
	}

    //자동매매 금액 저장
    @Override
    public void saveAutoPrice(MemberVO vo) {

        coinmappers.saveAutoPrice(vo);
    }

    //자동매매중 매수 금액
    @Override
    public void saveOrderPrice(MemberVO vo) {
        coinmappers.saveOrderPrice(vo);
    }

	@Override
	public void updatePrice(PriceVO vo) {
		coinmappers.savePrice(vo);

	}

	@Override
	public List<PriceVO> getPriceList(PriceVO vo) {

		return coinmappers.getPriceList(vo);
	}

}