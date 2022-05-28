package com.tagfoster.crypto.xorutil;

import com.tagfoster.crypto.Cryptosystem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;


/**
 * This class implements NTRU encryption/decryption.  It store its NTRU
 * encryption parameters and keys in the "~/.ntrutil" folder.
 */
public final class XorUtil implements Cryptosystem {

    private static final String USER_STORE_FOLDER = System.getenv( "HOME" ) + "/.xorutil";
    private static final String KEY_FILENAME = USER_STORE_FOLDER + "/encryption_key";
    private final int messageLength;

    private volatile byte[] key = null;


    public XorUtil( int messageLength ) {
        this.messageLength = messageLength;
    }

    @NotNull
    public byte[] encrypt( @NotNull final byte[] message ) throws IOException {
        return xorMessage( message, getKey() );
    }

    @NotNull
    public byte[] decrypt( @NotNull final byte[] message ) throws IOException {
        return xorMessage( message, getKey() );
    }

    @NotNull
    private byte[] xorMessage( @NotNull byte[] message, @NotNull byte[] key ) {
        validateMessageLength( message, key );

        final byte[] messageEncrypted = new byte[message.length];
        for ( int i = 0; i < message.length; i++ ) {
            messageEncrypted[i] = (byte) (message[i] ^ key[i]);
        }

        return messageEncrypted;
    }

    private static void validateMessageLength(
        @NotNull byte[] message, @NotNull byte[] key
    ) {
        if ( message.length > key.length ) {
            throw new RuntimeException( String.format(
                "Unsupported message length.  Message Length: %d, Supported Max Message Length: %d",
                message.length, key.length
            ) );
        }
    }

    @NotNull
    private synchronized byte[] getKey() throws IOException {
        if ( key == null ) {
            key = readKey();
            if ( key == null ) {
                key = generateKey( messageLength );
                saveKey();
            }
        }

        return key;
    }

    @Nullable
    private byte[] readKey() throws IOException {
        new File( USER_STORE_FOLDER ).mkdirs();
        if ( !new File( getKeyFilename() ).exists() ) {
            return null;
        }

        return Files.readAllBytes( Paths.get( getKeyFilename() ) );
    }

    private void saveKey() throws IOException {
        new File( USER_STORE_FOLDER ).mkdirs();
        Files.write( Paths.get( getKeyFilename() ), getKey() );
    }

    @NotNull
    private String getKeyFilename() {
        return String.format("%s.%d", KEY_FILENAME, messageLength);
    }

    @NotNull
    private byte[] generateKey( int messageLength ) {
        final byte[] bytes = new byte[messageLength];
        final SecureRandom secureRandom = new SecureRandom();
        for ( int i = 0; i < bytes.length; i++ ) {
            bytes[i] = (byte) secureRandom.nextInt();
        }

        return bytes;
    }

}
