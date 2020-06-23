package com.phones.phones.config;

import com.phones.phones.session.SessionBackOfficeFilter;
import com.phones.phones.session.SessionClientFilter;
import com.phones.phones.session.SessionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@org.springframework.context.annotation.Configuration
public class Configuration {

    //private final SessionFilter sessionFilter;
    private final SessionClientFilter sessionClientFilter;
    private final SessionBackOfficeFilter sessionBackOfficeFilter;

    @Autowired
    public Configuration(
            //final SessionFilter sessionFilter,
                         final SessionClientFilter sessionClientFilter,
                         final SessionBackOfficeFilter sessionBackOfficeFilter) {
      //  this.sessionFilter = sessionFilter;
        this.sessionClientFilter = sessionClientFilter;
        this.sessionBackOfficeFilter = sessionBackOfficeFilter;
    }

    /*
    @Bean
    public FilterRegistrationBean myFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionFilter);
        registration.addUrlPatterns("/api/*");
        return registration;
    }
     */

    @Bean
    public FilterRegistrationBean filterBackOffice() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionBackOfficeFilter);
        registration.addUrlPatterns("/api/backoffice/*");
        return registration;
    }

    @Bean
    public FilterRegistrationBean filterClient() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(sessionClientFilter);
        registration.addUrlPatterns("/api/clients/*");
        return registration;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
