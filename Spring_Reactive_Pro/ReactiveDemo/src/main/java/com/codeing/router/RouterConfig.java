package com.codeing.router;

import com.codeing.handler.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Bean
    public RouterFunction< ServerResponse > routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customer", customerHandler::loadCustomer)
                .GET("/router/customer/stream", customerHandler::loadCustomerStream)
                .GET("/router/customer/{id}", customerHandler::loadCustomerFindById)
                .POST("/router/customer/save", customerHandler::saveCustomer)
                .build();
    }
}
