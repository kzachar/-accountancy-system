package pl.coderstrust.accounting.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



@Component
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public class WebFilter implements Filter {

        private static final Logger logger = LoggerFactory.getLogger(WebFilter.class);

        private static final boolean CONDITION = true;

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {
            logger.debug("Initiating WebFilter >> ");
        }

        @Override
        public void doFilter(ServletRequest request, ServletResponse response,
                FilterChain chain) throws IOException, ServletException {

                chain.doFilter(requestWrapper, response); 

                // log your response here
        }

        @Override
        public void destroy() {
            logger.debug("Destroying WebFilter >> ");
        }
    @Bean
    public FilterRegistrationBean someFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(someFilter());
        registration.addUrlPatterns("/api/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("someFilter");
        registration.setOrder(1);
        return registration;
    }

    public Filter someFilter() {
        return new WebFilter();
    }
    }