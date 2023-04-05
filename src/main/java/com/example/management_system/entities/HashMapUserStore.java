package com.example.management_system.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class HashMapUserStore {

    private HashMap<String, User> hashMapStorage;
    private List<User> users = new ArrayList<>();

    public HashMapUserStore(){
        users.add(new User("1","ashu","dhamaniya"));
        users.add(new User("2","aman","jain"));
    }

    List<User> getAllUsers() {
        return users;
    }

    int getUsersCount() {
        return users.size();
    }

    User findUserById(String id) {
        System.out.println("Id : - " + id);
        System.out.println(users.stream().filter(user -> user.getUsername().equals(id)));
        return users.stream().filter(user -> user.getUsername().equals(id)).findFirst().orElse(null);
    }

    User findUserByUsernameOrEmail(String username) {
        return users.stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username))
                .findFirst().orElse(null);
    }

    List<User> findUsers(String query) {
        return users.stream()
                .filter(user -> query.equalsIgnoreCase("*") || user.getUsername().contains(query))
                .collect(Collectors.toList());
    }

    boolean updateCredentials(String username, String password) {
        findUserByUsernameOrEmail(username).setPassword(password);
        return true;
    }

}
