package nick.pack.config;

import nick.pack.models.Permission;
import nick.pack.models.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                //Защита от csrf угроз
                csrf().disable().
                //Обращение к способу авторизации
                authorizeRequests().
                        antMatchers("/").permitAll(). //Url '/' с html-страницой index будет доступен всем
                        anyRequest().authenticated().
                        //Шифрование basic64
                        and().httpBasic();
    }

    //Данный метод позволяет указать, где будут храниться данные пользователя
    //В данном случае, для тестирования, я указал, что данные будут храниться в RAM
    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new InMemoryUserDetailsManager(
                User.builder()
                        .username("Andrey")
                        .password(passwordEncoder().encode("1111"))
                        .roles(Role.ADMIN.name())
                        .authorities(Role.ADMIN.getAuthorities())
                        .build(),
                User.builder()
                        .username("Alexander")
                        .password(passwordEncoder().encode("2222"))
                        .roles(Role.USER.name())
                        .authorities(Role.USER.getAuthorities())
                        .build()
        );
    }
    //Данный метод позволяет указать, каким шифровальщиком будет шифроваться пароль, в данном случае - BCrypt
    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
    //При указании данных настроек, application.yml не будет учитываться
}
