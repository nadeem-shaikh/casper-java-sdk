package com.casper.sdk.how_to.common;

import com.casper.sdk.CasperSdk;
import com.casper.sdk.types.KeyAlgorithm;
import com.casper.sdk.types.PublicKey;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.Ed25519PublicKeyParameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Shared methods for the How Tos
 */
public abstract class Methods {

    protected String getPublicKeyAccountHex(final AsymmetricCipherKeyPair keyPair) {
        final PublicKey publicKey = new PublicKey(((Ed25519PublicKeyParameters) keyPair.getPublic()).getEncoded(), KeyAlgorithm.ED25519);
        return publicKey.toAccountHex();
    }

    protected AsymmetricCipherKeyPair getUserKeyPair(int userNumber, final String defaultNctlHome, final CasperSdk casperSdk) throws IOException {

        final String userNPath = String.format("%s/assets/net-1/users/user-%d", getNctlHome(defaultNctlHome), userNumber);
        final FileInputStream publicKeyIn = new FileInputStream(new File(userNPath, "public_key.pem"));
        final FileInputStream privateKeyIn = new FileInputStream(new File(userNPath, "secret_key.pem"));

        return casperSdk.loadKeyPair(publicKeyIn, privateKeyIn);
    }

    protected String getNctlHome(final String defaultNctlHome) {
        String nctlHome = System.getProperty("nctl.home");
        if (nctlHome == null) {
            nctlHome = defaultNctlHome;
        }
        // Replace user home '~' tilda with full path to user home directory
        nctlHome = nctlHome.replaceFirst("^~", System.getProperty("user.home"));
        return nctlHome;
    }

    protected AsymmetricCipherKeyPair getNodeKeyPair(final int nodeNumber, final String defaultNctlHome, final CasperSdk casperSdk) throws IOException {

        final String userNPath = String.format("%s/assets/net-1/nodes/node-%d/keys", getNctlHome(defaultNctlHome), nodeNumber);
        final FileInputStream publicKeyIn = new FileInputStream(new File(userNPath, "public_key.pem"));
        final FileInputStream privateKeyIn = new FileInputStream(new File(userNPath, "secret_key.pem"));

        return casperSdk.loadKeyPair(publicKeyIn, privateKeyIn);
    }

}
