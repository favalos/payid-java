package io.jl.payid.resources;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author @favalos
 */
@Data
@Builder
public class ACHAddressDetails implements AddressDetailsType {

    @NonNull
    private final String accountNumber;

    @NonNull
    private final String routingNumber;

    public ACHAddressDetails(@NonNull String accountNumber, @NonNull String routingNumber) {
        this.accountNumber = accountNumber;
        this.routingNumber = routingNumber;
    }
}
