package io.shopr;

import io.shopr.repositories.RepositoryAutoConfiguration;
import io.shopr.repositories.testutils.TestdataManager;
import io.shopr.testutils.TestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class ConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(TestConfig.class));

    @Test
    public void test_autoconfiguration_for_testdatamanager() {
        contextRunner
                .withUserConfiguration(RepositoryAutoConfiguration.class)
                .run((context) -> {
                    var manager = context.getBean(TestdataManager.class);
                    assertThat(manager).isNotNull();
                    assertThat(manager.getEntityManager()).isNotNull();
                });
    }
}
