package server.service;

import server.service.impl.AuthenticationImpl;
import server.service.impl.InfoServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();
    private final Authentication authentication = new AuthenticationImpl();
    private final InfoService info = new InfoServiceImpl();

    private ServiceFactory() {}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public Authentication getAuthenticationService() {
        return authentication;
    }

    public InfoService getInfoService() {
        return info;
    }
}
