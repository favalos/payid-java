package io.jl.payid.resources;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author @favalos
 */
@Data
@Builder
public class Address {

    @NonNull
    private final String paymentNetwork;

    private final String environment;

    @NonNull
    private final String addressDetailsType;

    @NonNull
    private final AddressDetailsType addressDetails;

    public Address(@NonNull String paymentNetwork,
                   String environment,
                   @NonNull String addressDetailsType,
                   @NonNull AddressDetailsType addressDetails) {

        this.paymentNetwork = paymentNetwork;
        this.environment = environment;
        this.addressDetailsType = addressDetailsType;
        this.addressDetails = addressDetails;
    }
}
