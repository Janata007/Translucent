package com.example.workservice;

import com.example.workservice.data.ServiceDataProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WorkServiceSmokeTest {
    private String userToken;
    private int taskId;

    public WorkServiceSmokeTest() {
    }

    @BeforeClass
    public void initialize() {
        userToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtc2NvdHQiLCJleHAiOjE2NzY3NDgwMjksImlhdCI6MTY3NjcyNjQyOX0.KW7IgRxuHCRHJKRf2pkti1oVr8F3ZLOyunEj1yg48gQ";
    }

    @Test(priority = 1, dataProvider = "createTask", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createTaskTest(String body, int byUserId, int forUserId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9007/work/" + byUserId + "/" + forUserId);
            StringEntity entity = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + userToken);
            request.setEntity(entity);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        //todo: set taskId from response of this test
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
    }

    @Test(priority = 2, dataProvider = "getTaskForUser", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void getTaskForUserTest(int userId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet("http://localhost:9007/work/tasks/" + userId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 3, dataProvider = "setTaskFinished", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void setTaskFinishedTest(int taskId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPut request = new HttpPut("http://localhost:9007/work/" + taskId + "/finished");
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 4, dataProvider = "removeTask", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void removeTaskTest(int taskId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9007/work/remove/" + taskId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

}
