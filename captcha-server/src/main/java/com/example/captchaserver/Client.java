package com.example.captchaserver;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.util.UUID;

public class Client {
    private UUID publicKey;
    private UUID privateKey;

    public Client(UUID passedPublicKey, UUID passedPrivateKey) {
        publicKey  = passedPublicKey;
        privateKey = passedPrivateKey;
    }

    public String publicKey() {
        return publicKey.toString();
    }

    public String privateKey() {
        return privateKey.toString();
    }

    public String toJSON() {
        return String.format("{\"secret\": \"%s\", \"public\": \"%s\"}",privateKey, publicKey);
    }
}


