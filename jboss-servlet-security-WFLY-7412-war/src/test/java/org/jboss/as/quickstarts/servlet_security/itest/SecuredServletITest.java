package org.jboss.as.quickstarts.servlet_security.itest;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SecuredServletITest {

    private static final OkHttpClient client = new OkHttpClient();

    @Test
    public void goodGuy() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/jboss-servlet-security-WFLY-7412-war/SecuredServlet") //
                .header("Authorization", Credentials.basic("quickstartUser", "quickstartPwd1!")) //
                .build();
            Response response = client.newCall(request).execute();
            Assert.assertEquals(200, response.code());
            String body = response.body().string();
            Assert.assertTrue("Body should contain 'Principal  : quickstartUser'", body.contains("Principal  : quickstartUser"));
    }

    @Test
    public void badGuy() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/jboss-servlet-security-WFLY-7412-war/SecuredServlet") //
                .header("Authorization", Credentials.basic("guest", "guestPwd1!")) //
                .build();
            Response response = client.newCall(request).execute();
            Assert.assertEquals(403, response.code());
    }

}
