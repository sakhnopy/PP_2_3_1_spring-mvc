package ru.kata.course.config;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("ru.kata.course")
@PropertySource("classpath:hibernate.properties")
@EnableTransactionManagement
@EnableWebMvc
public class SpringConfig implements WebMvcConfigurer {
    private final Environment environment;
    private final ApplicationContext applicationContext;

    @Autowired
    public SpringConfig(Environment environment, ApplicationContext applicationContext) {
        this.environment = environment;
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setSuffix(".html");

        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

   @Bean
    DataSource dataSource() {
       DriverManagerDataSource dataSource = new DriverManagerDataSource();

       dataSource.setDriverClassName(environment.getRequiredProperty("hibernate.connection.driver_class"));
       dataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
       dataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
       dataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));

       return dataSource;
   }

   @Bean
   public LocalContainerEntityManagerFactoryBean getEntityManager() {
       Properties properties = new Properties();
       properties.getProperty(environment.getRequiredProperty("hibernate.dialect"));
       properties.getProperty(environment.getRequiredProperty("hibernate.show_sql"));

       LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
       factoryBean.setJpaVendorAdapter(factoryBean.getJpaVendorAdapter());
       factoryBean.setDataSource(dataSource());
       factoryBean.setJpaProperties(properties);
       factoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
       factoryBean.setPackagesToScan("ru.kata.course.model");
       return factoryBean;
   }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManager().getObject());
        return transactionManager;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        registry.viewResolver(resolver);
    }



}