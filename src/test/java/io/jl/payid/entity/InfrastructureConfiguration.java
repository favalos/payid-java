package io.jl.payid.entity;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import io.r2dbc.spi.ConnectionFactoryOptions;
import io.r2dbc.spi.Option;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author @favalos
 */
@SpringBootApplication
@EnableR2dbcRepositories
@EnableTransactionManagement
public class InfrastructureConfiguration {

    @TestConfiguration
    static class ContextConfiguration {

        @Bean
        public ConnectionFactory connectionFactory() {

            ConnectionFactoryOptions.Builder connectionFactoryBuilder = ConnectionFactoryOptions.builder();
                connectionFactoryBuilder.option(Option.valueOf("host"), "localhost")
                        .option(Option.valueOf("user"), "postgres")
                        .option(Option.valueOf("password"), "")
                        .option(Option.valueOf("database"), "payid")
                        .option(Option.valueOf("driver"), "postgresql");

            return ConnectionFactories.get(connectionFactoryBuilder.build());
        }

    }
}
