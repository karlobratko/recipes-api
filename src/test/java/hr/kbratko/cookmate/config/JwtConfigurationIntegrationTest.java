package hr.kbratko.cookmate.config;

import hr.kbratko.cookmate.properties.JwtProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.BDDAssertions.then;

@SpringBootTest(classes = {JwtConfiguration.class})
@ExtendWith(SpringExtension.class)
@TestPropertySource(JwtConfigurationIntegrationTest.Constants.APPLICATION_PROPERTIES)
@DisplayName("JwtConfiguration Integration Test")
class JwtConfigurationIntegrationTest implements ConfigurationIntegrationTest {

  @Autowired
  private JwtProperties jwtProperties;

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Constants {

    public static final String APPLICATION_PROPERTIES = "classpath:application.yml";

    public static final String TEST_SECRET = "test-secret";

    public static final Long TEST_VALIDITY_DURATION_IN_MS = 86400000L;

  }

  @Nested
  @DisplayName("WHEN properties are requested")
  class When_propertiesRead {

    @Test
    @DisplayName("GIVEN properties are read from file " +
      "... THEN properties are valid")
    public void Given_propertiesReadFromFile_Then_validProperties() {
      // GIVEN
      // ... properties are read from file

      // WHEN
      // ... properties are requested

      // THEN
      // ... properties are valid
      then(jwtProperties).satisfies(it -> {
        then(it.secret()).isEqualTo(Constants.TEST_SECRET);
        then(it.validityDurationInMs().toMillis()).isEqualTo(Constants.TEST_VALIDITY_DURATION_IN_MS);
      });
    }
  }

}