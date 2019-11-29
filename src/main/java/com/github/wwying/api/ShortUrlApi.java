package com.github.wwying.api;


import com.github.wwying.entity.ShortUrl;
import com.github.wwying.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class ShortUrlApi {

    @Autowired
    private ShortUrlService shortUrlService;

    @GetMapping("/s/{token}")
    public String s(@PathVariable String token){
        ShortUrl shortUrl = null;
        try {
            shortUrl = shortUrlService.getByToken(token);
            if(shortUrl==null){
                return "not found";
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return "not found";
        }
        return "redirect:" + shortUrl.getUrl();
    }


    @RequestMapping(value = "/shortUrl",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public Response shortUrl(@RequestParam("url") String url){
        String shortUrl = null;
        try {
            shortUrl = shortUrlService.shortUrl(url);
            if(shortUrl==null){
                return Response.ApiError("系统异常!");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Response.ApiError("系统异常!");
        }
        return Response.ApiSuccess(shortUrl);
    }

}
