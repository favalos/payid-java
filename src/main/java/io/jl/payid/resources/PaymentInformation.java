package io.jl.payid.resources;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Collections;
import java.util.List;

/**
 * @author @favalos
 */
@Data
@Builder
public class PaymentInformation {

    private final String payId;

    @NonNull
    private final List<Address> addresses;

    private final String memo;

    private final String identity;

    private final ProofOfControlSignature proofOfControlSignature;

    public PaymentInformation(String payId,
                              @NonNull List<Address> addresses,
                              String memo,
                              String identity,
                              ProofOfControlSignature proofOfControlSignature) {

        this.payId = payId;
        this.addresses = Collections.unmodifiableList(addresses);
        this.memo = memo;
        this.identity = identity;
        this.proofOfControlSignature = proofOfControlSignature;
    }
}
