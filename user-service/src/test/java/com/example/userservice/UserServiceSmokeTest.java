package com.example.userservice;

import com.example.userservice.data.ServiceDataProvider;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

@Slf4j
public class UserServiceSmokeTest{

    private String userToken;

    public UserServiceSmokeTest() {
    }

    @Test(priority = 1, dataProvider = "createNewUser", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createUserTest(String body) throws Exception {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost request = new HttpPost("http://localhost:9002/users/save");
        StringEntity entity = new StringEntity(body);
        request.addHeader("content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 2, dataProvider = "authenticateUser", dataProviderClass = ServiceDataProvider.class, description = "Verify successful authentication of user", alwaysRun = true)
    public void authenticateUserTest(String body) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9002/users/authenticate");
            StringEntity entity = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.setEntity(entity);
            response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            userToken = responseBody.substring(13, responseBody.length() - 2);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 3, description = "Verify successful authentication of user", alwaysRun = true)
    public void getAllUsersTest() {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            log.info("TOKEN " + userToken);
            HttpGet request = new HttpGet("http://localhost:9002/users/all");
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        log.info(response.toString());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 4, dataProvider = "getSimpleUserWithId", dataProviderClass = ServiceDataProvider.class, description = "Verify successful authentication of user", alwaysRun = true)
    public void getSimpleUserWithIdTest(int userId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            log.info("TOKEN " + userToken);
            HttpGet request = new HttpGet("http://localhost:9002/users/simpleUser/" + userId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        log.info(response.toString());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 5, dataProvider = "getUserWithUsername", dataProviderClass = ServiceDataProvider.class, description = "Verify successful authentication of user", alwaysRun = true)
    public void getUserWithUsername(String username) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            log.info("TOKEN " + userToken);
            HttpGet request = new HttpGet("http://localhost:9002/users/user?username=" + username);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        log.info(response.toString());
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

}
