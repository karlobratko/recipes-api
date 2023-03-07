package hr.kbratko.cookmate.config;

import hr.kbratko.cookmate.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({JwtProperties.class})
@RequiredArgsConstructor
public class JwtConfiguration {

  private JwtProperties jwtProperties;

}
