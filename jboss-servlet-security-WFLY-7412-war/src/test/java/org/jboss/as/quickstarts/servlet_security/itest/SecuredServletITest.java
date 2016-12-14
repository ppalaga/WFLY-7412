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
                .header("Authorization", Credentials.basic("quickstartUser1", "quickstartPwd1!")) //
                .build();
            Response response = client.newCall(request).execute();
            Assert.assertEquals(200, response.code());
            String body = response.body().string();
            Assert.assertTrue("Body should contain 'Principal  : quickstartUser1'", body.contains("Principal  : quickstartUser1"));
    }

    @Test
    public void badGuy() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/jboss-servlet-security-WFLY-7412-war/SecuredServlet") //
                .header("Authorization", Credentials.basic("guest1", "guestPwd1!")) //
                .build();
            Response response = client.newCall(request).execute();
            Assert.assertEquals(403, response.code());
    }

    @Test
    public void goodGuy2() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/jboss-servlet-security-WFLY-7412-war/SecuredServlet") //
                .header("Authorization", Credentials.basic("quickstartUser2", "quickstartPwd2!")) //
                .build();
            Response response = client.newCall(request).execute();
            Assert.assertEquals(200, response.code());
            String body = response.body().string();
            Assert.assertTrue("Body should contain 'Principal  : quickstartUser2'", body.contains("Principal  : quickstartUser2"));
    }

    @Test
    public void badGuy2() throws IOException {
        Request request = new Request.Builder()
                .url("http://localhost:8080/jboss-servlet-security-WFLY-7412-war/SecuredServlet") //
                .header("Authorization", Credentials.basic("guest2", "guestPwd2!")) //
                .build();
            Response response = client.newCall(request).execute();
            Assert.assertEquals(403, response.code());
    }

}
