package io.shopr;

import io.shopr.testutils.TestConfig;
import io.shopr.testutils.TestdataManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(TestConfig.class));

    @Test
    public void test_autoconfiguration_for_testdatamanager() {
        contextRunner
                .withUserConfiguration(ShoprApplication.class)
                .run((context) -> {
                    TestdataManager manager = context.getBean(TestdataManager.class);
                    assertThat(manager).isNotNull();
                    assertThat(manager.getEntityManager()).isNotNull();
                });
    }
}
