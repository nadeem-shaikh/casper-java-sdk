package com.casper.sdk.domain;

import com.casper.sdk.json.serialize.PublicKeyJsonSerializer;
import com.casper.sdk.json.deserialize.SignatureJsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Signature domain type used in deployment approvals.
 */
@JsonDeserialize(using = SignatureJsonDeserializer.class)
@JsonSerialize(using = PublicKeyJsonSerializer.class)
public class Signature extends PublicKey {

    public Signature(final byte[] bytes, final KeyAlgorithm keyAlgorithm) {
        super(bytes, keyAlgorithm);
    }

    public Signature(final String signature) {
        super(signature);
    }
}