package org.jboss.as.quickstarts.login.module2;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import javax.security.auth.login.LoginException;

import org.jboss.logging.Logger;
import org.jboss.security.auth.spi.UsersRolesLoginModule;

public class LoginModule2 extends UsersRolesLoginModule {
    private static final Logger log = Logger.getLogger(LoginModule2.class);

    @Override
    public boolean login() throws LoginException {
        boolean login = super.login();
        log.infof("login = %s, principal = %s", login, getIdentity());
        return login;
    }

    @Override
    protected Properties createRoles(Map<String, ?> options) throws IOException {
        Properties result = new Properties();
        result.put("quickstartUser2", "quickstarts");
        result.put("guest2", "guest");
        return result;
    }

    @Override
    protected Properties createUsers(Map<String, ?> options) throws IOException {
        Properties result = new Properties();
        result.put("quickstartUser2", "quickstartPwd2!");
        result.put("guest2", "guestPwd2!");
        return result;
    }

}