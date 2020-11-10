package io.jl.payid.resources;


import lombok.Data;
import lombok.NonNull;

/**
 * @author @favalos
 */
@Data
public class ProofOfControlSignature {

    @NonNull
    private final String publicKey;

    @NonNull
    private final String payID;

    @NonNull
    private final String hashAlgorithm;

    @NonNull
    private final String signature;

    public ProofOfControlSignature(@NonNull String publicKey,
                                   @NonNull String payID,
                                   @NonNull String hashAlgorithm,
                                   @NonNull String signature) {

        this.publicKey = publicKey;
        this.payID = payID;
        this.hashAlgorithm = hashAlgorithm;
        this.signature = signature;
    }
}
