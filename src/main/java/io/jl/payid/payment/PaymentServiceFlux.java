package io.jl.payid.payment;

import io.jl.payid.entity.Account;
import io.jl.payid.entity.AccountRepository;
import io.jl.payid.entity.AddressRepository;
import io.jl.payid.resources.Address;
import io.jl.payid.resources.PaymentInformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author @favalos
 */
@Service
public class PaymentServiceFlux implements PaymentService<Mono<PaymentInformation>> {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Mono<PaymentInformation> getPaymentInformation(String payid) {

        Mono<Account> accountMono = this.accountRepository.getAccountByPayid(payid);
        Mono<PaymentInformation> paymentInformationMono = accountMono.flatMap(this::getPaymentInformation);

        return paymentInformationMono;
    }

    private Mono<PaymentInformation> getPaymentInformation(final Account account) {

        Mono<List<Address>> addressesMono =  addressRepository.findAddressFor(account.getId())
                .map(TransformerHelper::transformAddress)
                .collect(Collectors.toList());

        Mono<PaymentInformation> paymentInformationMono = addressesMono.map(addresses -> {
            PaymentInformation.PaymentInformationBuilder builder = PaymentInformation.builder();
            builder.payId(account.getPayId())
                    .addresses(addresses);

            return builder.build();
        });

        return paymentInformationMono;
    }

}
