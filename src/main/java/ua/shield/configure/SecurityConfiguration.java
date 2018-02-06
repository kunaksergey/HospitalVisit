package ua.shield.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public Http401AuthenticationEntryPoint securityException401EntryPoint(){
        return new Http401AuthenticationEntryPoint("Bearer realm=\"webrealm\"");
    }

    @Autowired
    private Http401AuthenticationEntryPoint authEntrypoint;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/patient/**").hasRole("USER")
                .antMatchers("/api/secured/**").authenticated()
                .antMatchers(HttpMethod.POST,"/api/**").authenticated()
                .antMatchers(HttpMethod.PUT,"/api/**").authenticated()
                .antMatchers(HttpMethod.DELETE,"/api/**").authenticated()
                .antMatchers("/").permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authEntrypoint)
                .accessDeniedPage("/error-page");
        http
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();

        http.logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .permitAll();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
