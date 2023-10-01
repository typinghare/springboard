package us.jameschan.springboard.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebMvc
@PropertySource({
    "classpath:.env.properties"
})
public class DatabaseConfig implements WebMvcConfigurer {
    @Value("${spring.datasource.driver-class-name}")
    private String hibernateDriver;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    @Bean
    public DataSource dataSource() {
        // Register url parameters below
        final Map<String, String> urlParameters = new HashMap<>();
        urlParameters.put("createDatabaseIfNotExist", "true");

        // Splice parameters to into a string
        final StringBuilder urlStringBuilder = new StringBuilder("?");
        urlParameters.forEach((name, value) -> urlStringBuilder.append(name).append("=").append(value));

        // Create and return data source
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(hibernateDriver);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);
        dataSource.setUrl(databaseUrl + urlStringBuilder);

        return dataSource;
    }
}
