package com.tiffanytimbric.crypto.nooputil;

import com.tiffanytimbric.crypto.CryptosystemBase;

import javax.annotation.Nonnull;


/**
 * This class implements NOOP encryption/decryption.
 */
public final class NoopUtil extends CryptosystemBase {

    public static final int DEFAULT_CHUNK_SIZE_ENCRYPT = 65536;
    public static final int DEFAULT_CHUNK_SIZE_DECRYPT = 65536;


    public NoopUtil() {
        super( DEFAULT_CHUNK_SIZE_ENCRYPT, DEFAULT_CHUNK_SIZE_DECRYPT );
    }

    @Nonnull
    public byte[] encrypt( @Nonnull final byte[] message ) {
        return message;
    }

    @Nonnull
    public byte[] decrypt( @Nonnull final byte[] bytes ) {
        return bytes;
    }

}
