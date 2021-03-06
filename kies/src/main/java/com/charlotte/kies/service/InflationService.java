package com.charlotte.kies.service;

import com.charlotte.kies.model.InflationData;
import com.charlotte.kies.model.LobsterData;
import com.charlotte.kies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InflationService {


    private static String baseUrl = "https://api.stlouisfed.org/fred/series/observations?series_id=";
    private static String seriesId = "MEDCPIM158SFRBCLE";
    private static RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "&api_key=fb51b6beabf2af7644c3c2cbbcaac6f1";
    private static String fileType = "&file_type=json";
    private String token;

    @Autowired
    UserRepository userRepository;

    public InflationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    /**** Getters and Setters ****/
    public static String getBaseUrl() {
        return baseUrl;
    }

    public static void setBaseUrl(String baseUrl) {
        InflationService.baseUrl = baseUrl;
    }

    public static String getSeriesId() {
        return seriesId;
    }

    public static void setSeriesId(String seriesId) {
        InflationService.seriesId = seriesId;
    }

    public static RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public static void setRestTemplate(RestTemplate restTemplate) {
        InflationService.restTemplate = restTemplate;
    }

    public static String getFileType() {
        return fileType;
    }

    public static void setFileType(String fileType) {
        InflationService.fileType = fileType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /***** Methods *****/
    public static InflationData getHistoricalInflationData() {
        InflationData inflationData = new InflationData();
        try {
            ResponseEntity<InflationData> response = restTemplate.exchange(baseUrl+ seriesId + API_KEY + fileType, HttpMethod.GET,makeEntity(),InflationData.class);
            inflationData = response.getBody();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return inflationData;
    }

    private static HttpEntity<?> makeEntity() {
        HttpHeaders headers = userAgentHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(headers);
    }

    public static HttpHeaders userAgentHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Mozilla/5.0 Firefox/26.0");
        return headers;

    }

}
