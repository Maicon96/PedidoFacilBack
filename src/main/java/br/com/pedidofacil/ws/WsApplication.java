package br.com.pedidofacil.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import br.com.pedidofacil.ws.interceptors.TokenFilterSecurity;

@SpringBootApplication
public class WsApplication {

	@Bean
	public FilterRegistrationBean filtroJWT() {
		FilterRegistrationBean frb =  new FilterRegistrationBean();
		frb.setFilter(new TokenFilterSecurity());
		frb.addUrlPatterns("/security/*");//se tiver essa linha na rota, vai fazer o interceptor
		
		return frb;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(WsApplication.class, args);
	}

}
