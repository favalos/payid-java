package io.jl.payid.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.Nullable;

import java.util.UUID;

/**
 * @author @favalos
 */
@Data
@Table(value = "account")
public class Account {

    @Id
    private final UUID id;

    private final String payId;

    @Nullable
    private final String identityKey;

    public Account(UUID id, String payId, @Nullable String identityKey) {
        this.id = id;
        this.payId = payId;
        this.identityKey = identityKey;
    }
}
