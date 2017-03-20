package com.niit.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.dao.UserDAO;
import com.niit.daoimpl.UserDAOImpl;
import com.niit.model.Blog;
import com.niit.model.Comment;
import com.niit.model.Friend;
import com.niit.model.Job;
import com.niit.model.JobApply;
import com.niit.model.Message;
import com.niit.model.OutputMessage;
import com.niit.model.User;


@Configuration
@ComponentScan("com.niit")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    DriverManagerDataSource dataSource = new DriverManagerDataSource();
	    dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:XE");
	    dataSource.setUsername("ADMIN");
	dataSource.setPassword("admin");
	    return dataSource;
	}
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
//	   properties.put("hibernate.hbm2ddl.auto", "update");
	    return properties;
	}
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	 LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 sessionBuilder.addProperties(getHibernateProperties());
	sessionBuilder.addAnnotatedClasses(User.class);
	sessionBuilder.addAnnotatedClasses(Friend.class);
	sessionBuilder.addAnnotatedClasses(Job.class);
	sessionBuilder.addAnnotatedClasses(Blog.class);
	sessionBuilder.addAnnotatedClasses(JobApply.class);
	sessionBuilder.addAnnotatedClasses(Comment.class);
	sessionBuilder.addAnnotatedClasses(OutputMessage.class);
	sessionBuilder.addAnnotatedClasses(Message.class);	
     return sessionBuilder.buildSessionFactory();
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	return transactionManager;
	}
	@Autowired
	   @Bean(name = "userDao")
	   public UserDAO getCategoryDao(SessionFactory sessionFactory) {
	   return new UserDAOImpl(sessionFactory);
	   }
}
