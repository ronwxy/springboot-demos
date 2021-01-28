package cn.jboost.springboot.security;

import cn.jboost.springboot.security.phone.PhoneAuthenticationProvider;
import cn.jboost.springboot.security.phone.PhoneUserDetailsService;
import cn.jboost.springboot.security.username.CompositeUserDetailsChecker;
import cn.jboost.springboot.security.username.RetryLimitAfterPasswordFailedChecker;
import cn.jboost.springboot.security.username.UsernameAuthenticationProvider;
import cn.jboost.springboot.security.username.UsernameUserDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author ronwxy
 * @Date 2020/8/10 14:32
 * @Version 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UsernameAuthenticationProvider usernameAuthenticationProvider = new UsernameAuthenticationProvider();
        usernameAuthenticationProvider.setUserDetailsService(new UsernameUserDetailsService());
        usernameAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        List<UserDetailsChecker> checkers = new ArrayList<>(1);
        checkers.add(new RetryLimitAfterPasswordFailedChecker());
        usernameAuthenticationProvider.setAfterPasswordInvalidChecker(new CompositeUserDetailsChecker(checkers));
        auth.authenticationProvider(usernameAuthenticationProvider);

        PhoneAuthenticationProvider phoneAuthenticationProvider = new PhoneAuthenticationProvider();
        phoneAuthenticationProvider.setUserDetailsService(new PhoneUserDetailsService());
        auth.authenticationProvider(phoneAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .and()
//                .formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("doLogin")
//               .and()
                .csrf()
                .disable();
        http.addFilterAt(jboostAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public JboostAuthenticationFilter jboostAuthenticationFilter() throws Exception {
        JboostAuthenticationFilter authenticationFilter = new JboostAuthenticationFilter();
        authenticationFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                ResponseEntity respBean = ResponseEntity.ok("登录成功!");
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        authenticationFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                resp.setContentType("application/json;charset=utf-8");
                PrintWriter out = resp.getWriter();
                ResponseEntity respBean = new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        });
        authenticationFilter.setAuthenticationManager(super.authenticationManagerBean());
        authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        return authenticationFilter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("jboost123"));
    }
}
