package mm.com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private AuthenticationEntryPoint authEntryPoint;
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
 
        // Tất cả các request gửi tới Web Server yêu cầu phải được xác thực
        // (authenticated).
        http.authorizeRequests().antMatchers("/admin","/admin/*", "/admin/*/*", "/admin/*/*/*").authenticated();
        http.authorizeRequests().anyRequest().permitAll();
        
 
        // Sử dụng AuthenticationEntryPoint để xác thực user/password
        http.httpBasic().authenticationEntryPoint(authEntryPoint);
    }
 
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         
        String password = "Tru0ngth@nhbjnh";
        String encrytedPassword = this.passwordEncoder().encode(password);        
                  
        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> //
        mngConfig = auth.inMemoryAuthentication();
 
        // Spring auto add ROLE_
        UserDetails u1 = User.withUsername("thanhbinhtruong678").password(encrytedPassword).roles("USER").build();        
 
        mngConfig.withUser(u1);        
    }
 
}