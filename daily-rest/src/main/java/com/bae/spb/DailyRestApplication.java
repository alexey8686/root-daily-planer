package com.bae.spb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class DailyRestApplication {

  public static void main(String[] args) {
    SpringApplication.run(DailyRestApplication.class, args);
  }

  @Bean
  public CookieSerializer cookieSerializer() {
    DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
    defaultCookieSerializer.setCookieMaxAge(5000);
    defaultCookieSerializer.setCookieName("JSESSIONID");
    return defaultCookieSerializer;
  }

}
