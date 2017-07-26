package org.com.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
@PropertySource(value="classpath:ds-hib.properties")
public class HibernateConfig {

	@Autowired
	private Environment  environment;
	
	@Bean
	public DataSource dataSource(){
		System.out.println("creating data source");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("ds.database-driver"));
		dataSource.setUrl(environment.getRequiredProperty("ds.url"));
		dataSource.setUsername(environment.getRequiredProperty("ds.username"));
		dataSource.setPassword(environment.getRequiredProperty("ds.password"));
		return dataSource;
	}
	
	public Properties hibernateProperties(){
		Properties prop = new Properties();
		prop.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		prop.put("current_session_context_class", environment.getRequiredProperty("current_session_context_class"));
		return prop;
	}
	
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() throws IOException{
		System.out.println("creating session factory");
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setHibernateProperties(hibernateProperties());
		factoryBean.setPackagesToScan(new String[]{"org.com.entity"});
		factoryBean.afterPropertiesSet();
		return factoryBean.getObject();
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer() {
	    ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator();
	    resourceDatabasePopulator.addScript(new ClassPathResource("/data.sql"));

	        DataSourceInitializer dataSourceInitializer = new DataSourceInitializer();
	        dataSourceInitializer.setDataSource(dataSource());
	        dataSourceInitializer.setDatabasePopulator(resourceDatabasePopulator);
	        return dataSourceInitializer;
	    }
	
}
