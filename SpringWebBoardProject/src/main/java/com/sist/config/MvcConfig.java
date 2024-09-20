package com.sist.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan(basePackages = {"com.sist.*"})
@EnableAspectJAutoProxy
@MapperScan(basePackages = {"com.sist.mapper"})
public class MvcConfig implements WebMvcConfigurer{

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// TODO Auto-generated method stub
		configurer.enable();
		// HandlerMapping을 메모리 할당하는 역할 => Model안에서 RequestMapping, GetMapping
	}

	
	@Bean("viewResolver")
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver wr = new InternalResourceViewResolver();
		wr.setPrefix("/");
		wr.setSuffix(".jsp");
		return wr;
		
	}
	
	@Bean("ds")
	public DataSource dataSource()
	{
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		ds.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
		ds.setUsername("hr");
		ds.setPassword("happy");
		return ds;
	}
}
