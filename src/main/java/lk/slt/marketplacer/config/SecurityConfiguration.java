package lk.slt.marketplacer.config;

import org.keycloak.adapters.authorization.integration.jakarta.ServletPolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

import java.io.IOException;

/**
 * @author harindu.sul@gmail.com
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    String jwkSetUri;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        //Permit access
                        .requestMatchers("api/docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/products/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/categories/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/stores/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/v1/users/{userId}/stores/**").permitAll()
                        //Authenticate access
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2ResourceServer ->
                        oauth2ResourceServer
                                .jwt(jwt -> jwt.decoder(jwtDecoder()))
                ).addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        //.addFilterAfter(createPolicyEnforcerFilter(), BearerTokenAuthenticationFilter.class);
        return http.build();
    }


    private ServletPolicyEnforcerFilter createPolicyEnforcerFilter() {
        return new ServletPolicyEnforcerFilter(new ConfigurationResolver() {
            @Override
            public PolicyEnforcerConfig resolve(HttpRequest request) {
                try {
                    return JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"), PolicyEnforcerConfig.class);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(this.jwkSetUri).build();
    }
}
