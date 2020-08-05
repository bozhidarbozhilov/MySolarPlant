//package com.bozhilov.mysolarplant.web.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Description;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
//import org.thymeleaf.spring5.ISpringTemplateEngine;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//import org.thymeleaf.templatemode.TemplateMode;
//import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
//import org.thymeleaf.templateresolver.ITemplateResolver;
//
//import java.util.Collections;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//    public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";
//
//    @Bean
//    @Description("Thymeleaf template engine with Spring integration")
//    public ISpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
//
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
//        templateEngine.addDialect(new Java8TimeDialect());
//        templateEngine.setTemplateResolver(templateResolver);
//        templateEngine.addTemplateResolver(textTemplateResolver());
//        templateEngine.addTemplateResolver(htmlTemplateResolver());
//
//        return templateEngine;
//    }
//
//
//    private ITemplateResolver textTemplateResolver() {
//        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setOrder(2);
//        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
//        templateResolver.setPrefix("classpath:/templates/");
//        templateResolver.setSuffix(".txt");
//        templateResolver.setTemplateMode(TemplateMode.TEXT);
//        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
//        templateResolver.setCacheable(false);
//        return templateResolver;
//    }
//
//    private ITemplateResolver htmlTemplateResolver() {
//        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setOrder(1);
//        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
//        templateResolver.setPrefix("classpath:/templates/");
//        templateResolver.setSuffix(".html");
//        templateResolver.setTemplateMode(TemplateMode.HTML);
//        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
//        templateResolver.setCacheable(false);
//        return templateResolver;
//    }
//}
