package com.centralstudenthub.config;

import com.centralstudenthub.authentication.JwtAuthenticationFilter;
import com.centralstudenthub.authentication.OAuth.CustomOAuth2UserService;
import com.centralstudenthub.repository.UserSessionInfoRepository;
import com.centralstudenthub.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private static final String[] WHITE_LIST_URLS = {
            "/login/oauth2/code/google",
            "/auth/**",
    };

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private CustomOAuth2UserService oauthUserService;

    @Autowired
    private UserSessionInfoRepository userSessionInfoRepository;

    @Autowired
    private JwtService jwtService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(WHITE_LIST_URLS).permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyRequest().authenticated()

                )
//                .formLogin(withDefaults())
//                .oauth2Login(oauth -> oauth
//                        .authorizationEndpoint(authorization -> authorization
//                                .baseUri("/login/oauth2/authorization/google")
//                        )
//                        .redirectionEndpoint(redirection -> redirection
//                                .baseUri("/login/oauth2/code/google")
//                        )
//                        .userInfoEndpoint(userInfo -> userInfo
//                                .userService(oauthUserService)
//                        )
//                        .defaultSuccessUrl("/auth/google",true)
//                        .successHandler((request, response, authentication) -> {
//                            //String gmail = ((OAuth2User)authentication.getPrincipal()).getAttribute("email");
//                            //response.sendRedirect("/auth/google/"+gmail);
//                            response.sendRedirect("/auth/google");
//                        })

 //               )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
