package org.jboss.as.quickstarts.login.module1;

import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.jboss.security.auth.spi.UsersRolesLoginModule;

public class LoginModule1 extends UsersRolesLoginModule {
    private static final Logger log = Logger.getLogger(LoginModule1.class);
    @Override
    public boolean login() throws LoginException {
        boolean login = super.login();
        log.infof("login = %s, principal = %s", login, getIdentity());
        return login;
    }
}