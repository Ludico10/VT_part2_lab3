package server.service;

import server.model.Role;

public interface Authentication {
    Role getRole(Object key);
    void addUser(Object key, Role role);
}
