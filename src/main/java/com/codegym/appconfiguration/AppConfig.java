package com.codegym.appconfiguration;

//import com.codegym.formatter.CountryFormatter;
import com.codegym.model.TouristArea;
import com.codegym.service.province.IProvinceService;
import com.codegym.service.province.ProvinceService;
import com.codegym.service.touristArea.TouristAreaService;
import com.codegym.service.typesOfTourism.ITypesOfTourismService;
import com.codegym.service.typesOfTourism.TypesOfTourismService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.format.FormatterRegistry;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration // ????nh ?????u ????y l?? file c???u h??nh d??? ??n Spring
@EnableWebMvc // ????nh d???u d??? ??n n??y h??? tr??? m?? h??nh MVC
@EnableTransactionManagement // ????nh d???u d??? ??n c?? h??? tr??? transaction
@EnableJpaRepositories("com.codegym.repository") // ????nh d???u d??? ??n c?? s??? d???ng jpa repository v?? ???????ng d???n
@ComponentScan("com.codegym.controller")// cho Spring bi???t ph???i t??m controller ??? ????u
@EnableSpringDataWebSupport
public class AppConfig implements WebMvcConfigurer, ApplicationContextAware {

    private ApplicationContext applicationContext; // khai b??o 1 Spring Container

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    //3 h??m ti???p theo c???u h??nh Thymleaf:
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views"); // ti???n t???
        templateResolver.setSuffix(".html"); // h???u t???
        templateResolver.setTemplateMode(TemplateMode.HTML); // ki???u views
        templateResolver.setCharacterEncoding("UTF-8"); // ?????nh d???ng ch???
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setCharacterEncoding("UTF-8");// ?????nh d???ng ch???
        return viewResolver;
    }

    //5 h??m ti???p theo c???u h??nh JPA
    @Bean
    @Qualifier(value = "entityManager")
    public EntityManager entityManager(EntityManagerFactory entityManagerFactory) {
        return entityManagerFactory.createEntityManager();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("com.codegym.model"); // cung c???p v??? tr?? c??c model m?? EntityManager c???n t???o

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // lo???i driver ??ang d??ng
        dataSource.setUrl("jdbc:mysql://localhost:3306/mycountry"); // csdl ??ang d??ng
        dataSource.setUsername("root"); // t??i kho???n sql
        dataSource.setPassword("123456"); // m???t kh???u sql
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    public Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // h??? tr??? upload c???u tr??c b???ng
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); // lo???i csdl l?? MySQL5
        return properties;
    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry .addResourceHandler("/**") .addResourceLocations("/assets/");
//        registry.addResourceHandler("/nhuanh/**") //???????ng d???n ???o thay th??? cho ???????ng d???n th???t b??n d?????i (ng???n h??n)
//                .addResourceLocations("file:" + "/Users/daonhuanh/Downloads/Codegym/nal/");
//    }

//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(new CountryFormatter(applicationContext.getBean(CountryService.class)));
//    }


//    @Bean(name = "multipartResolver")
//    public CommonsMultipartResolver getResolver() throws IOException {
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setMaxUploadSizePerFile(52428800); //k??ch th?????c t???i ??a
//        return resolver;
//    }

    @Bean
    public IProvinceService iProvinceService(){
        return new ProvinceService();
    }

//    @Bean
//    public ITypesOfTourismService iTypesOfTourismService(){
//        return new TypesOfTourismService();
//    }
}