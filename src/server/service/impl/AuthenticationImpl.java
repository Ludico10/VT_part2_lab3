package server.service.impl;

import server.model.Role;
import server.service.Authentication;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationImpl implements Authentication {
    public Map<Object, Role> users = new HashMap<>();

    @Override
    public Role getRole(Object key) {
        Role res = Role.NBD;
        if (users.containsKey(key)) {
            res = users.get(key);
        }
        return res;
    }

    @Override
    public void addUser(Object key, Role role) {
        users.put(key, role);
    }
}
