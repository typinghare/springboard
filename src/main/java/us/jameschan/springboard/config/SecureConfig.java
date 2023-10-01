package us.jameschan.springboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@PropertySource({
    "classpath:.env.properties"
})
public class SecureConfig {
    @Value("${encoder.secret}")
    private String encoderSecret;

    @Value("${encoder.saltLength}")
    private String encoderSaltLength;

    @Value("${encoder.iterations}")
    private String encoderIterations;

    @Bean
    public String encoderSecret() {
        return encoderSecret;
    }

    @Bean
    public String encoderSaltLength() {
        return encoderSaltLength;
    }

    @Bean
    public String encoderIterations() {
        return encoderIterations;
    }
}
