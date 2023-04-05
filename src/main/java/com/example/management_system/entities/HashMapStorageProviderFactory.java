package com.example.management_system.entities;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.List;

public class HashMapStorageProviderFactory implements UserStorageProviderFactory<HashMapStorageProvider> {

    public static final String PROVIDER_NAME = "hashmap-user-store";
    HashMapUserStore userStore = new HashMapUserStore();

    @Override
    public HashMapStorageProvider create(KeycloakSession session, ComponentModel model) {
        return new HashMapStorageProvider(session, model, userStore);
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property("myParam", "My Param", "Some Description", ProviderConfigProperty.STRING_TYPE,
                        "some value", null)
                .build();
    }
}