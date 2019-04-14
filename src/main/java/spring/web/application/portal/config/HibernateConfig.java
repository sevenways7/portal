package spring.web.application.portal.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:application.properties"})
public class HibernateConfig {
    @Autowired
    private Environment environment;
    // If table not exist in database, we will get error:

    // Error creating bean with name 'sessionFactory'
    // defined in class path resource [spring/web/application/portal/config/HibernateConfig.class]:
    // Invocation of init method failed;
    // nested exception is org.hibernate.HibernateException: Missing table: comment

    // So, during creating "sessionFactory", Spring checks database for exist requirement tables!
    // What tables need to be present, defines in models, that are specified in "sessionFactory.setPackagesToScan()"

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[]{"spring.web.application.portal.model"});
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));

        //dataSource.setUrl(environment.getRequiredProperty("jdbc.url") + "?serverTimezone=" + TimeZone.getDefault().getID());
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));

        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));

        properties.put("hibernate.archive.autodetection", environment.getRequiredProperty("hibernate.archive.autodetection"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

        properties.put("hibernate.connection.CharSet", "UTF-8");
        properties.put("hibernate.connection.characterEncoding", "UTF-8");
        properties.put("hibernate.connection.useUnicode", true);

        return properties;
    }

    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(s);
        return txManager;
    }
}
