package server;

import server.model.Role;

import java.util.HashMap;
import java.util.Map;

public class Authentication {
    public Map<Object, Role> users = new HashMap<Object, Role>();

    public Role getRole(Object key) {
        Role res = Role.NBD;
        if (users.containsKey(key)) {
            res = users.get(key);
        }
        return res;
    }

    public void addUser(Object key, Role role) {
        users.put(key, role);
    }
}
