package tn.esprit.spring;
import org.apache.log4j.Logger;  

import java.util.EnumSet;

import javax.faces.webapp.FacesServlet;
import javax.servlet.DispatcherType;

import org.ocpsoft.rewrite.servlet.RewriteFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import tn.esprit.spring.config.LoginFilter;

@SpringBootApplication
@EnableAutoConfiguration
public class TimesheetApplication {

	   static Logger log = Logger.getLogger(TimesheetApplication.class.getName());  

	
	public static void main(String[] args) {
		   log.debug("Hello this is a debug message");  
		      log.info("Hello this is an info message");  
		SpringApplication.run(TimesheetApplication.class, args);}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		FacesServlet servlet = new FacesServlet();
		return new ServletRegistrationBean(servlet, "*.jsf"); }

	@Bean
	public FilterRegistrationBean rewriteFilter() {
		FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
		rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST, DispatcherType.ASYNC, DispatcherType.ERROR));
		rwFilter.addUrlPatterns("/*");
		return rwFilter;
	}


	@Bean
	public FilterRegistrationBean loginFilter() {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.addUrlPatterns("/pages/*");
		registration.setFilter(new LoginFilter());
		return registration;
	}
 
}
