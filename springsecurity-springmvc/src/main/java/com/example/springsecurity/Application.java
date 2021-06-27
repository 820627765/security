package com.example.springsecurity;

import com.example.springsecurity.config.ApplicationConfig;
import com.example.springsecurity.config.WebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class Application extends AbstractAnnotationConfigDispatcherServletInitializer {
    //spring容器,相当于加载 application.xml
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ApplicationConfig.class};
    }

    //servletContext，相当于加载springmvc.xml
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    //url-mapping ,这个就相当于配置 web.xml
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
