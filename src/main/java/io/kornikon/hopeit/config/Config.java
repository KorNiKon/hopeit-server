package io.kornikon.hopeit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MongoConfig.class})
class Config {
}
