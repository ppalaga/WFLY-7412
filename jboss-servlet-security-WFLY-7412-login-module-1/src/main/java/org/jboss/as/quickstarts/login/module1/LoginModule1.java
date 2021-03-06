package org.jboss.as.quickstarts.login.module1;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

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

    @Override
    protected Properties createRoles(Map<String, ?> options) throws IOException {
        Properties result = new Properties();
        result.put("quickstartUser1", "quickstarts");
        result.put("guest1", "guest");
        return result;
    }

    @Override
    protected Properties createUsers(Map<String, ?> options) throws IOException {
        Properties result = new Properties();
        result.put("quickstartUser1", "quickstartPwd1!");
        result.put("guest1", "guestPwd1!");
        return result;
    }



}