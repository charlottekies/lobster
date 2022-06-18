package com.charlotte.kies.service;

import com.charlotte.kies.model.LobsterData;
import com.charlotte.kies.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LobsterService {


    private static String baseUrl = "https://api.stlouisfed.org/fred/series/observations?series_id=";
    private static String seriesId = "WPU02230503";
    private static RestTemplate restTemplate = new RestTemplate();
    private static final String API_KEY = "&api_key=fb51b6beabf2af7644c3c2cbbcaac6f1";
    private static String fileType = "&file_type=json";
    private String token;

    @Autowired
    UserRepository userRepository;

    public LobsterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(String seriesId) {
        this.seriesId = seriesId;
    }

    public RestTemplate getRestTemplate() {
        return restTemplate;
    }

    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
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

    public static LobsterData getHistoricalPriceData() {
        LobsterData lobsterData = new LobsterData();
        try {
            ResponseEntity<LobsterData> response = restTemplate.exchange(baseUrl+ seriesId + API_KEY + fileType, HttpMethod.GET,makeEntity(),LobsterData.class);
            lobsterData = response.getBody();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return lobsterData;
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

    public LobsterData getAllHistoricalPriceData() {
        return new LobsterData();
    }


}
