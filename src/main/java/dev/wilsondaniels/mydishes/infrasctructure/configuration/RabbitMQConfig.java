package dev.wilsondaniels.mydishes.infrasctructure.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue myDisghesQueue() {
        return new Queue("my_dishes", true);
    }
}
