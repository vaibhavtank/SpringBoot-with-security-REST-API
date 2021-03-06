package com.spring.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.spring" })
@EnableTransactionManagement
@Import({ SecurityConfig.class })
@PropertySource("classpath:application.properties")
public class AppConfig extends WebMvcConfigurerAdapter {

	
	@Autowired
	Environment environment;
	
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter#addResourceHandlers(org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry)
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	
	}

	/**
	 * @return SessionFactory
	 */
	@Autowired
	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource());
		builder.scanPackages("com.spring.core.model").addProperties(getHibernateProperties());
		return builder.buildSessionFactory();
	}

	/**
	 * @return Properties
	 */
	private Properties getHibernateProperties() {
		System.out.println("environment msg properties : "+environment.getRequiredProperty("msg"));
		Properties prop = new Properties();
		prop.put("hibernate.format_sql", "true");
		prop.put("hibernate.show_sql", "true");
		prop.put("hibernate.hbm2ddl.auto", "update");
		prop.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		prop.put("hibernate.connection.pool_size", "20");
		return prop;
	}

	/**
	 * @return BasicDataSource
	 */
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.mysql.jdbc.Driver");
		boolean remote = false;
		String DBNAME = ((remote) ? "test" : "test");
		String USERNAME = ((remote) ? "root" : "root");
		String PASSWORD = ((remote) ? "Admin_Local@B18" : "Admin_Local@B18");
		ds.setUrl("jdbc:mysql://localhost:3306/" + DBNAME);
		ds.setUsername(USERNAME);
		ds.setPassword(PASSWORD);
		return ds;
	}

	/**
	 * @param s
	 * @return HibernateTransactionManager
	 */
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(s);
		return txManager;
	}

	/**
	 * @return InternalResourceViewResolver
	 */
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
}
