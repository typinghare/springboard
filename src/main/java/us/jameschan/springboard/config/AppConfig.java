package us.jameschan.springboard.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import us.jameschan.springboard.common.App;

@Configuration
public class AppConfig {
    private ApplicationContext applicationContext;

    @Autowired
    public AppConfig(ApplicationContext applicationContext) {
        // Inject the Spring application context instance to App by reflect
        App.setApplicationContext(applicationContext);
    }
}
