package com.github.wwying.service.impl;


import com.github.wwying.entity.ShortUrl;
import com.github.wwying.enums.SysParamConstants;
import com.github.wwying.repository.ShortUrlRepository;
import com.github.wwying.service.ShortUrlService;
import com.github.wwying.service.TokenCreator;
import com.github.wwying.vo.ShortUrlVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class ShortUrlServiceImpl implements ShortUrlService {



    @Autowired
    private ShortUrlRepository shortUrlRepository;

    @Value("${shortUrl.shortServer:http://127.0.0.1:8080/s/}")
    private String shortServer;

    private TokenCreator tokenCreator;

    public ShortUrlServiceImpl(TokenCreator tokenCreator){
        this.tokenCreator= tokenCreator;
    }


    @Override
    public String shortUrl(String url) {

        try {
            new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException("url格式异常!");
        }


        String   urlServer = shortServer;

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setUrl(url);
        shortUrl.setHashCode(url.hashCode());
        shortUrl.setEnabled(SysParamConstants.ENABLED_YES.getKey());
        List<ShortUrl> list = shortUrlRepository.listByHashCodeAndUrlAndEnabled(shortUrl);
        if (list.size() > 0) {
            return urlServer + list.get(0).getShortToken();
        }
        shortUrl = new ShortUrl();
        shortUrl.setUrl(url);
        shortUrl.setEnabled(SysParamConstants.ENABLED_YES.getKey());
        shortUrl.setHashCode(url.hashCode());
        shortUrl = shortUrlRepository.save(shortUrl);
        String token = tokenCreator.creatToken(shortUrl.getId());
        ShortUrl updateShortUrl = new ShortUrl();
        updateShortUrl.setId(shortUrl.getId());
        updateShortUrl.setShortToken(token);
        shortUrlRepository.updateShortToeknById(updateShortUrl);
        return urlServer + token;
    }

    @Override
    public ShortUrl getByToken(String token) {
        try {

            ShortUrl shortUrl = new ShortUrl();
            shortUrl.setShortToken(token);
            shortUrl = shortUrlRepository.getByShortToekn(shortUrl);
            return shortUrl;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }



    @Override
    public void clear() {

    }
}
