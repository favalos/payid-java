package io.jl.payid.config;

import io.jl.payid.payment.PaymentHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;

/**
 * @author @favalos
 */
@Configuration
public class WebRouterConfig {

    @Bean
    public RouterFunction routePaymentInformation(PaymentHandler paymentHandler) {
        return route()
                .GET("/{payid}", paymentHandler::getPaymentInformation)
                .before(paymentHandler::validateVersion)
                .before(paymentHandler::validateAccept)
                .build();
    }
}
