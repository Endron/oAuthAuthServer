package io.github.endron.oauth.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.SessionAttributes;

@SpringBootApplication
@EnableAuthorizationServer
@SessionAttributes("authorizationRequest")
public class Application extends AuthorizationServerConfigurerAdapter {

    public static void main(String... args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("testClient")
                .authorizedGrantTypes("authorization_code", "refresh_token").scopes("openid")
                .authorities("ROLE_OAUTH_CLIENT");
    }

    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .formLogin()
                        .permitAll()
                    .and()
                    .csrf()
                        .disable()
                    .authorizeRequests()
                        .anyRequest().authenticated();
        }
    }
}
