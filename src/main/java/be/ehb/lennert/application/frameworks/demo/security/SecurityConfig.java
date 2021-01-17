package be.ehb.lennert.application.frameworks.demo.security;// src/main/java/com/auth0/example/security/SecurityConfig.java

import be.ehb.lennert.application.frameworks.demo.security.AudienceValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${auth0.audience}")
    private String audience;

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuer;

    @Bean
    JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder)
                JwtDecoders.fromOidcIssuerLocation(issuer);

        OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
        OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
        OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

        jwtDecoder.setJwtValidator(withAudience);

        return jwtDecoder;
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth
                        .mvcMatchers(HttpMethod.POST,"/api/user/**").authenticated()
                        .mvcMatchers(HttpMethod.POST,"/api/order/**").authenticated()
                        .mvcMatchers(HttpMethod.POST,"/api/order/**/**").authenticated()
                        .mvcMatchers(HttpMethod.POST,"/api/**").hasAuthority("write:products")
                        .mvcMatchers(HttpMethod.DELETE,"/api/**").hasAuthority("write:products")
                        .mvcMatchers(HttpMethod.GET,"/payment/**").authenticated()
                        .mvcMatchers(HttpMethod.POST,"/payment/**").authenticated()
                        .mvcMatchers(HttpMethod.GET,"/api/products/**").permitAll()

                        .anyRequest().authenticated()
                )

                .cors()
                .and().oauth2ResourceServer().jwt()
        ;
    }
}