package server.service;

import server.model.Role;

public interface Authentication {
    public Role getRole(Object key);
    public void addUser(Object key, Role role);
}
