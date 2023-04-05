package com.example.management_system.entities;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;
public class UserAdapter extends AbstractUserAdapterFederatedStorage {
    private User user;
//    private final String keycloakId;

    public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, User user) {
        super(session, realm, model);
        System.out.println("Hiiiiiiii");
        this.user = user;
        System.out.println("Wuff : " + user);
//        this.keycloakId = StorageId.keycloakId(model, user.getId());
    }

    @Override
    public String getUsername() {
        System.out.println("Hello" + user);
        return user.getUsername();
    }

    @Override
    public void setUsername(String username) {
        user.setUsername(username);
    }

    @Override
    public String getFirstName() {
        return user.getFirstName();
    }

    @Override
    public String getLastName() {
        return user.getLastName();
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }


}



