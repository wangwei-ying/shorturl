package com.github.wwying.service;


import com.github.wwying.entity.ShortUrl;

public interface ShortUrlService {

    /**
     * description:
     * @author yingww
     * @date 2019-11-21
     * @param url 长链接
     * @return 返回短链接
     */
    String shortUrl(String url);

    /**
     * description: 获取域名
     * @author yingww
     * @date 2019-11-27
     * @param token
     * @return
     */
    ShortUrl getByToken(String token);
    /**
     * description: 清理过期短域名
     * @author yingww
     * @date 2019-11-25
     */
    void clear();
}
