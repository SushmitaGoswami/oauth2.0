package com.sushmita.github.grant_type.pkce_authorization_grant_type;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public class Main {
    public static void main(String[] args) {
        try {
            PKCEUtil pkce = new PKCEUtil();

            String codeVerifier = pkce.generateCodeVerifier();
            System.out.println("Code verifier: " + codeVerifier);

            String codeChallenge = pkce.generateCodeChallenge(codeVerifier);
            System.out.println("Code challenge: " + codeChallenge);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

    }
}
