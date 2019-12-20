package com.github.wwying.api;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicHttpResponse;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class ShortUrlApiTest {


    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void before(){
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void s() throws Exception {
        MockHttpServletRequestBuilder re = MockMvcRequestBuilders.get("/shortUrl").param("url", "http://baidu.com");
        mvc.perform(re).andExpect(new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                String retu=result.getResponse().getContentAsString();
                JSONObject jsonObject = new JSONObject(retu);
                int status = jsonObject.getInt("status");
                if(HttpStatus.OK.value() !=status){
                    throw new RuntimeException("失败");

                }
                String url = jsonObject.getString("data");
                Pattern pattern =Pattern.compile(".*/s/(.+)");
                Matcher matcher = pattern.matcher(url);
                matcher.matches();
                String urlEnd = matcher.group(1);
                mvc.perform(MockMvcRequestBuilders.get("/s/"+urlEnd)).andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws Exception {
                        String header = result.getResponse().getHeader(HttpHeaders.LOCATION);
                        Assert.assertTrue( "http://baidu.com".equals(header));
                    }

                });
            }
        });

    }

    @Test
    public void shortUrl() {
    }
}