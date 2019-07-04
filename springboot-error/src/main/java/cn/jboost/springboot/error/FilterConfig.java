package cn.jboost.springboot.error;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

import javax.servlet.*;
import java.io.IOException;

/***
 *
 * @Author ronwxy
 * @Date 2019/7/3 13:54   
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean exceptionFilter() {
        ExceptionFilter exceptionFilter = new ExceptionFilter();
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(exceptionFilter);
        filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        filterRegistrationBean.addUrlPatterns("/exception/filter");
        return filterRegistrationBean;
    }

    class ExceptionFilter implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            throw new RuntimeException("过滤器抛出异常");
        }
    }
}
