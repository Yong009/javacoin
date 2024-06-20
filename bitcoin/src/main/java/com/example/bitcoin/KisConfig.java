package com.example.bitcoin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class KisConfig {


	@Bean
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }

	public static final String REST_BASE_URL = "https://openapi.koreainvestment.com:9443";
	public static final String WS_BASE_URL = "ws://ops.koreainvestment.com:21000";
	public static final String APPKEY = "PSKs0RRJqLoIX17saBUAGHuEMB637QGV571U";       // your APPKEY
	public static final String APPSECRET = "Q9Ul35SBhS0lITcIhctv5nX9o0LjhfrZz9Lb0F+FHIh95ICyDnepsFpEa3qQbJEjwxeaAhL//Kj9d+cs3ZsZJj9h3nwRdTxTWhuKnNc/cp2TLSOmgSh2IGsIUUrOluguzEpZ+tgn9AKDzjBrW6kUiMg5DoBKCI65G30HW3+eUFbzKZpTcFs=";  // your APPSECRET

	public static final String FHKUP03500100_PATH = "/uapi/domestic-stock/v1/quotations/inquire-daily-indexchartprice";
	public static final String FHKST03030100_PATH = "/uapi/overseas-price/v1/quotations/inquire-daily-chartprice";
}
