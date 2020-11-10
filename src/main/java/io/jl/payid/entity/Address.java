package io.jl.payid.entity;

import io.r2dbc.postgresql.codec.Json;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

/**
 * @author @favalos
 */
@Data
@Table(value = "account")
public class Address {

    @Id
    @NonNull
    private final long id;

    @NonNull
    private final UUID accountId;

    @NonNull
    private final String paymentNetwork;

    private final String environment;

    @NonNull
    private final Json details;

    private final String identityKeySignature;

    public Address(@NonNull long id, @NonNull UUID accountId, @NonNull String paymentNetwork, String environment, @NonNull Json details, String identityKeySignature) {
        this.id = id;
        this.accountId = accountId;
        this.paymentNetwork = paymentNetwork;
        this.environment = environment;
        this.details = details;
        this.identityKeySignature = identityKeySignature;
    }
}
