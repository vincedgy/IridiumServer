package org.vincedgy.iridium.config;

import java.util.Properties;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource({ "classpath:persistence-mysql.properties" })
@ComponentScan({ "org.vincedgy.iridium" })
public class HibernateConfiguration {

	@Autowired
	private Environment env;

	@Bean(name="sessionFactory")
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(
				dataSource());
		builder.scanPackages("org.vincedgy.iridium.model").addProperties(
				hibernateProperties());

		return builder.buildSessionFactory();
	}

	private Properties hibernateProperties() {
	      return new Properties() {
	       private static final long serialVersionUID = 1L;

			{
	            setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
	            setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
	            setProperty("hibernate.globally_quoted_identifiers", "true");
	         }
	      };
	   }

	@Bean(name = "dataSource")
	public BasicDataSource dataSource() {

		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
		dataSource.setUrl(env.getProperty("jdbc.url"));
		dataSource.setUsername(env.getProperty("jdbc.user"));
		dataSource.setPassword(env.getProperty("jdbc.pass"));
		return dataSource;
	}

	// Create a transaction manager
	@Bean(name="transactionManager")
	public HibernateTransactionManager txManager() {
		return new HibernateTransactionManager(sessionFactory());
	}
}