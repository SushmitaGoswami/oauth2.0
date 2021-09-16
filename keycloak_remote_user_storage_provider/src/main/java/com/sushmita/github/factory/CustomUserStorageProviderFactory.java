package com.sushmita.github.factory;

import com.sushmita.github.provider.CustomUserStorageProvider;
import com.sushmita.github.service.CustomerUserStorageService;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CustomUserStorageProviderFactory
        implements UserStorageProviderFactory<CustomUserStorageProvider> {

    private String ID = "user-storage-provider";

    @Value("${resource.server.uri}")
    private String resourceServerURI;


    public CustomUserStorageProvider create(KeycloakSession keycloakSession,
                                            ComponentModel componentModel
                                            ) {

        //resourceServerURI = "http://172.26.93.29:8900";
        return new CustomUserStorageProvider(keycloakSession, componentModel,
                buildHttpClient(resourceServerURI));
    }


    private CustomerUserStorageService buildHttpClient(String uri) {

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target(uri);

        return target.proxyBuilder(CustomerUserStorageService.class)
                .classloader(CustomerUserStorageService.class.getClassLoader()).build();

    }

    public String getId() {
        return ID;
    }
}
