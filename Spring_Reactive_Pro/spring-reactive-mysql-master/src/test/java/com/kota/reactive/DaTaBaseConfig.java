package com.kota.reactive;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.h2.engine.Database;
import org.springframework.context.annotation.Bean;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;

@Component
public class DaTaBaseConfig {

    @Bean
    public ConnectionFactory connectionFactory() {

        // postgres
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url("r2dbc:h2:mem://./testdb")
                        //.host("localhost")
                        .username("sa")
                        .password("password")
                        .build()
        );
    }


    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                //.bindMarkers(() -> BindMarkersFactory.named(":", "", 20).create())
                .namedParameters(true)
                .build();
    }
}
