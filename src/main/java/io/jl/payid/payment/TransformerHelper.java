package io.jl.payid.payment;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jl.payid.entity.Address;
import io.jl.payid.resources.AddressDetailsType;
import io.jl.payid.resources.CryptoAddressDetails;
import io.jl.payid.resources.ACHAddressDetails;

import static io.jl.payid.payment.PaymentConstants.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author @favalos
 */
public class TransformerHelper {

    public static io.jl.payid.resources.Address transformAddress(Address address) {

        AddressDetailsType addressDetails = extractAddressDetails(address);
        String addressDetailsType = addressDetails.getClass().getSimpleName();

        io.jl.payid.resources.Address.AddressBuilder builder = io.jl.payid.resources.Address.builder();

        builder.addressDetailsType(addressDetailsType)
                .addressDetails(addressDetails)
                .environment(address.getEnvironment())
                .paymentNetwork(address.getPaymentNetwork());

        return builder.build();
    }

    public static AddressDetailsType extractAddressDetails(Address address) {

        Map<String,String> details = readAddressDetails(address);

        return extractCryptoAddress(details)
                .or(() -> extractFiatAddress(details))
                .orElseThrow();
    }

    public static Optional<AddressDetailsType> extractFiatAddress(Map<String, String> details) {

        if(!details.containsKey(ACCOUNT_NUMBER))
            return Optional.empty();

        ACHAddressDetails.ACHAddressDetailsBuilder builder = ACHAddressDetails.builder();
        builder.accountNumber(details.get(ACCOUNT_NUMBER));
        builder.routingNumber(details.get(ROUTING_NUMBER));

        return Optional.of(builder.build());
    }

    public static Optional<AddressDetailsType> extractCryptoAddress(Map<String, String> details) {

        if(!details.containsKey(ADDRESS))
            return Optional.empty();

        CryptoAddressDetails.CryptoAddressDetailsBuilder builder = CryptoAddressDetails.builder();
        builder.address(details.get(ADDRESS));
        builder.tag(details.get(TAG));

        return Optional.of(builder.build());
    }

    public static Map<String, String> readAddressDetails(Address address) {

        Map<String,String> details = address.getDetails().mapInputStream(is -> {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> map = null;
            try {
                map = objectMapper.readValue(is, Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return map;
        });

        return details;
    }

}
