package io.jl.payid.payment;

import io.jl.payid.resources.PaymentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

import static io.jl.payid.payment.PaymentConstants.*;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

/**
 * @author @favalos
 */
@Component
public class PaymentHandler {

    @Value("${payid.host}")
    private String host;

    @Autowired
    private PaymentService<Mono<PaymentInformation>> paymentService;

    public ServerRequest validateVersion(ServerRequest serverRequest) {

        String versionHeader = serverRequest.headers().firstHeader(PAYID_VERSION_HEADER);

        if(!PAYID_VERSION.equals(versionHeader))
            throw new RuntimeException("Not version supported.");

        return serverRequest;
    }

    public ServerRequest validateAccept(ServerRequest serverRequest) {

        List<MediaType> mediaTypeList = serverRequest.headers().accept();

        if(mediaTypeList.size() != 1 || !MediaType.valueOf("application/payid+json").equals(mediaTypeList.get(0)))
            throw new RuntimeException("Only application/payid+json is supported.");

        return serverRequest;
    }

    public Mono<ServerResponse> getPaymentInformation(ServerRequest request) {

        String payid = request.pathVariable("payid");
        payid = payid.toLowerCase();

        Mono<PaymentInformation> paymentInformationMono = this.paymentService
                .getPaymentInformation(payid + "$" + this.host);

        return paymentInformationMono.flatMap(paymentInformation -> ok().bodyValue(paymentInformation));
    }

}
