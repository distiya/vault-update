package com.topcoder.poc.vaultupdate.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Slf4j
public class HelloControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testWhenGivenCorrectCredentials(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("vaultUser","vaultPassword");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> exchange = this.restTemplate.exchange("http://localhost:" + port + "/hello", HttpMethod.GET, request, String.class);
        log.info("{}",exchange.getBody());
        Assertions.assertEquals("Hello World",exchange.getBody());
    }

    @Test
    void testWhenGivenInvalidCredentials(){
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth("vaultUser","vaultPassword1");
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
        ResponseEntity<String> exchange = this.restTemplate.exchange("http://localhost:" + port + "/hello", HttpMethod.GET, request, String.class);
        log.info("{}",exchange.getBody());
        Assertions.assertNull(exchange.getBody());
    }

}
