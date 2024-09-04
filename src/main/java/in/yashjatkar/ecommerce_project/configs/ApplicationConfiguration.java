package in.yashjatkar.ecommerce_project.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public RestTemplate CreaterestTemplate(){
        return new RestTemplate();
    }
}
