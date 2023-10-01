package us.jameschan.springboard.config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JsonConfig {
    @Bean
    public Gson generalGson() {
        return new Gson();
    }
}
