package com.sushmita.github.provider;

import com.sushmita.github.model.UserDTO;
import com.sushmita.github.service.CustomerUserStorageService;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.UserCredentialStore;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomUserStorageProvider implements UserStorageProvider,
        UserLookupProvider,
        CredentialInputValidator{

    private KeycloakSession session;
    private ComponentModel model;
    private CustomerUserStorageService usersService;

    @Value("${resource.server.uri}")
    private String resourceServerURI;

    public CustomUserStorageProvider(KeycloakSession session,
                                     ComponentModel model,
                                     CustomerUserStorageService usersService) {
        this.session = session;
        this.model = model;
        this.usersService = usersService;
    }

    public boolean supportsCredentialType(String s) {
        return PasswordCredentialModel.TYPE.equals(s);
    }

    public boolean isConfiguredFor(RealmModel realmModel, UserModel userModel, String s) {
        if (!supportsCredentialType(s)) {
            return false;
        }
        return !getCredentialStore().getStoredCredentialsByType(realmModel, userModel, s).isEmpty();
    }

    private UserCredentialStore getCredentialStore() {
        return session.userCredentialManager();
    }
    public boolean isValid(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        System.out.println("getUserdetails: " + userModel.getUsername());
        UserDTO userDTO = usersService.verifyUserPassword(userModel.getUsername(),
                credentialInput.getChallengeResponse());

        if (userDTO == null)
            return false;

        return true;
    }

    public void close() {

    }

    public UserModel getUserById(String s, RealmModel realmModel) {
        return null;
    }

    public UserModel getUserByUsername(String s, RealmModel realmModel) {
        System.out.println("getUser: " + s );

        UserDTO userDetails =
                usersService.getUserDetails(s);

        System.out.println("getUserdetails: " + userDetails.getUserName());

        UserModel userModel = null;

        if(userDetails != null) {
            userModel = createUserModel(userDetails.getUserName(), realmModel);
        }

        return userModel;
    }


    private UserModel createUserModel(String username, RealmModel realm) {
        return new AbstractUserAdapter(session, realm, model) {
            @Override
            public String getUsername() {
                return username;
            }
        };
    }

    public UserModel getUserByEmail(String s, RealmModel realmModel) {
        return null;
    }
}
