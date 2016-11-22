package ie.cit.group3.controller;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 * @author john murphy
 * 
 * This class is used to provide to do the following:
 * 		1) internationalisation support
 *		2) Provide simple automated controllers pre-configured for some basic user URL inputs (using addViewControllers())
 *		3) Provides a Bean that can access the MySQL database - used by WebSecurityConfigurerAdapter in 'MvcConfig.java'
 *
 */
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter 
{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) 
	{
		registry.addViewController("/home").setViewName("home"); //addViewControllers() method overriding the method of same name in WebMvcConfigurerAdapter().
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/hello").setViewName("home");
		registry.addViewController("/login").setViewName("login");  //Registers login form
	}

	@Value("${spring.datasource.driverClassName}") //imports value of driverClassName from yml file
	private String databaseDriverClassName;

	@Value("${spring.datasource.url}")
	private String datasourceUrl;

	@Value("${spring.datasource.username}")
	private String databaseUsername;

	@Value("${spring.datasource.password}")
	private String databasePassword;

	@Bean  //Now have bean that can be autowired into any other bean.
	public DataSource datasource() {
		org.apache.tomcat.jdbc.pool.DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource();
		ds.setDriverClassName(databaseDriverClassName);
		ds.setUrl(datasourceUrl);
		ds.setUsername(databaseUsername);
		ds.setPassword(databasePassword);

		return ds;
	}

	//Support for internationalisation.  Taken from Donna's Lecture material
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

	@Bean
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
		localeChangeInterceptor.setParamName("language");
		return localeChangeInterceptor;
	}

	@Bean(name = "localeResolver")
	public CookieLocaleResolver localeResolver() {
		CookieLocaleResolver localeResolver = new CookieLocaleResolver();
		Locale defaultLocale = new Locale("en");
		localeResolver.setDefaultLocale(defaultLocale);
		return localeResolver;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:lang/messages");
		messageSource.setCacheSeconds(10); //reload messages every 10 seconds
		return messageSource;
	}

}