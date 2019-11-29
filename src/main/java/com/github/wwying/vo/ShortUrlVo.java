package com.github.wwying.vo;


import java.io.Serializable;
import org.springframework.format.annotation.DateTimeFormat;

/**
* @类名: ShortUrl
* @描述: 免票活动Vo
* @author: yangzy
* @日期: 2019-11-21 15:16:18
*/
public class ShortUrlVo implements Serializable{

private static final long serialVersionUID = 4635413972295999716L;

  //
  private String shortToekn;
  //
  private String url;
  //
  private String enabled;


  /**
  * 
  */
  public String getShortToekn() {
  return this.shortToekn;
  }

  /**
  * 
  */
  public void setShortToekn(String shortToekn) {
  this.shortToekn = shortToekn;
  }

  /**
  * 
  */
  public String getUrl() {
  return this.url;
  }

  /**
  * 
  */
  public void setUrl(String url) {
  this.url = url;
  }

  /**
  * 
  */
  public String getEnabled() {
  return this.enabled;
  }

  /**
  * 
  */
  public void setEnabled(String enabled) {
  this.enabled = enabled;
  }
}
