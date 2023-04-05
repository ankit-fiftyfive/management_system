package com.example.management_system.entities;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputUpdater;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;
import org.keycloak.storage.user.UserRegistrationProvider;

public class HashMapStorageProvider implements UserLookupProvider, UserStorageProvider, UserQueryProvider,
        CredentialInputValidator, CredentialInputUpdater, UserRegistrationProvider {

    private HashMapUserStore hashMapUserStore;
    private KeycloakSession keycloakSession;
    private ComponentModel componentModel;
    protected Map<String, UserModel> loadedUsers = new HashMap<>();

    public HashMapStorageProvider(KeycloakSession session, ComponentModel model, HashMapUserStore hashMapUserStore){
        this.hashMapUserStore = hashMapUserStore;
        this.keycloakSession = session;
        this.componentModel = model;
    }


    @Override
    public void close() {

    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return credentialType.equals(PasswordCredentialModel.TYPE);
    }

    @Override
    public boolean updateCredential(RealmModel realmModel, UserModel userModel, CredentialInput credentialInput) {
        if (!supportsCredentialType(credentialInput.getType()) || !(credentialInput instanceof UserCredentialModel)) {
            return false;
        }
        UserCredentialModel cred = (UserCredentialModel) credentialInput;
        return hashMapUserStore.updateCredentials(userModel.getUsername(), cred.getChallengeResponse());
    }

    @Override
    public void disableCredentialType(RealmModel realmModel, UserModel userModel, String s) {

    }

    @Override
    public Stream<String> getDisableableCredentialTypesStream(RealmModel realmModel, UserModel userModel) {
        return Stream.empty();
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {

        try {
            String password = hashMapUserStore.findUserByUsernameOrEmail(user.getUsername()).getPassword();
            return credentialType.equals(PasswordCredentialModel.TYPE) && password != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        if (!supportsCredentialType(credentialInput.getType())) return false;

        try {
            String password = hashMapUserStore.findUserByUsernameOrEmail(user.getUsername()).getPassword();
            if (password == null) return false;
            return password.equals(credentialInput.getChallengeResponse());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private UserModel createAdapter(RealmModel realm, String username) {
        System.out.println("Hiiiiiii");
        return new UserAdapter(keycloakSession, realm, componentModel, hashMapUserStore.findUserByUsernameOrEmail(username));
    }

//    private UserModel createAdapter(RealmModel realm, String username) {
//        return new UserAdapter(keycloakSession, realm, componentModel, new User("5", "chirag", "chirag"));
//    }

    @Override
    public UserModel getUserById(RealmModel realmModel, String id) {
        String externalId = StorageId.externalId(id);
        System.out.println("print line 105 : " + externalId);
        return new UserAdapter(keycloakSession, realmModel, componentModel, hashMapUserStore.findUserById(externalId));
    }

    @Override
    public UserModel getUserByUsername(RealmModel realmModel, String username) {
        System.out.println("printing : " + loadedUsers.get(username));
        UserModel adapter = loadedUsers.get(username);
        if (adapter == null) {
            User user = hashMapUserStore.findUserByUsernameOrEmail(username);
            if (user != null) {
                adapter = createAdapter(realmModel, username);
                loadedUsers.put(username, adapter);
            }
        }
        return adapter;
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realm, String search) {
        return hashMapUserStore.findUsers(search).stream()
                .map(user -> new UserAdapter(keycloakSession, realm, componentModel, user));
    }

    @Override
    public UserModel getUserByEmail(RealmModel realmModel, String email) {
        return getUserByUsername(realmModel, email);
    }


    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, String search, Integer integer, Integer integer1) {
        return searchForUserStream(realmModel, search);
    }

    @Override
    public Stream<UserModel> searchForUserStream(RealmModel realmModel, Map<String, String> map, Integer integer, Integer integer1) {
        return hashMapUserStore.getAllUsers().stream()
                .map(user -> new UserAdapter(keycloakSession, realmModel, componentModel, user));
    }

    @Override
    public Stream<UserModel> getGroupMembersStream(RealmModel realmModel, GroupModel groupModel, Integer integer, Integer integer1) {
        return Stream.empty();
    }

    @Override
    public Stream<UserModel> searchForUserByUserAttributeStream(RealmModel realmModel, String s, String s1) {
        return Stream.empty();
    }

    @Override
    public UserModel addUser(RealmModel realmModel, String s) {
        return null;
    }

    @Override
    public boolean removeUser(RealmModel realmModel, UserModel userModel) {
        return false;
    }
}
