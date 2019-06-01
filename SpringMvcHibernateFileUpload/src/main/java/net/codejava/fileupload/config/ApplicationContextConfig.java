package net.codejava.fileupload.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import net.codejava.fileupload.dao.FileUploadDAO;
import net.codejava.fileupload.dao.FileUploadDAOImpl;
import net.codejava.fileupload.dao.GeologicalSectionDAO;
import net.codejava.fileupload.dao.GeologicalSectionDAOImpl;
import net.codejava.fileupload.model.GeologicalSections;
import net.codejava.fileupload.model.UploadFile;

@Configuration
@ComponentScan("net.codejava.fileupload")
@EnableTransactionManagement
public class ApplicationContextConfig {
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
     
    //This is for Mysql Database
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	BasicDataSource dataSource = new BasicDataSource();
    	dataSource.setDriverClassName("com.mysql.jdbc.Driver");
    	dataSource.setUrl("jdbc:mysql://localhost:3306/testdb");
    	dataSource.setUsername("root");
    	dataSource.setPassword("12345");
    	
    	return dataSource;
    }
    

    //This is for SQlserver Database
//    @Bean(name = "dataSource")
//    public DataSource getDataSource() {
//    	BasicDataSource dataSource = new BasicDataSource();
//    	dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//    	dataSource.setUrl("jdbc:sqlserver://DIAMOND-DEV22:1433;databaseName=testDb");
//    	dataSource.setUsername("sa");
//    	dataSource.setPassword("Bebo@123");
//    	return dataSource;
//    }
    
    
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.show_sql", "true");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	return properties;
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
    	LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
    	getAnnotatedClasses(sessionBuilder);
    	return sessionBuilder.buildSessionFactory();
    }

	private void getAnnotatedClasses(LocalSessionFactoryBuilder sessionBuilder) {
		sessionBuilder.addAnnotatedClasses(UploadFile.class);
    	sessionBuilder.addAnnotatedClasses(GeologicalSections.class);
	}
    
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
			SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
    
    @Autowired
    @Bean(name = "fileUploadDao")
    public FileUploadDAO getUserDao(SessionFactory sessionFactory) {
    	return new FileUploadDAOImpl(sessionFactory);
    }
    
    
    @Autowired
    @Bean(name = "geologicalSectionDAO")
    public GeologicalSectionDAO getGeologicalDao(SessionFactory sessionFactory) {
    	return new GeologicalSectionDAOImpl(sessionFactory);
    }
    
    
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver getCommonsMultipartResolver() {
    	CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
    	multipartResolver.setMaxUploadSize(20971520); // 20MB
    	multipartResolver.setMaxInMemorySize(1048576);	// 1MB
    	return multipartResolver;
    }
}
