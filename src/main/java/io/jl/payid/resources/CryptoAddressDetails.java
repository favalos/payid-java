package io.jl.payid.resources;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

/**
 * @author @favalos
 */
@Data
@Builder
public class CryptoAddressDetails implements AddressDetailsType {

    @NonNull
    private final String address;

    private final String tag;

    public CryptoAddressDetails(@NonNull String address, String tag) {
        this.address = address;
        this.tag = tag;
    }
}
