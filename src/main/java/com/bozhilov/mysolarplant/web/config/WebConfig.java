package com.bozhilov.mysolarplant.web.config;

import com.bozhilov.mysolarplant.web.interceptors.LogInterceptor;
import com.bozhilov.mysolarplant.web.interceptors.PageTitleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final PageTitleInterceptor titleInterceptor;
    private final LogInterceptor logInterceptor;

    @Autowired
    public WebConfig(PageTitleInterceptor titleInterceptor, LogInterceptor logInterceptor) {
        this.titleInterceptor = titleInterceptor;
        this.logInterceptor = logInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(titleInterceptor);
        registry.addInterceptor(logInterceptor);
    }
}
