package com.example.feedbackservice;


import com.example.feedbackservice.data.ServiceDataProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FeedbackServiceSmokeTest {
    private String userToken;

    public FeedbackServiceSmokeTest() {
    }

    @BeforeClass
    public void initialize() {
        userToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtc2NvdHQiLCJleHAiOjE2NzY4MzI0MjEsImlhdCI6MTY3NjgxMDgyMX0.Xz8KjS6pjX8kyS8V3F75VB8oXeDmp-ZWR_hKOwIVZYk";

    }

    @Test(priority = 1, dataProvider = "createArrangementFeedback", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createArrangementFeedbackTest(String body, int arrangementId, int fromUserId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request =
                new HttpPost("http://localhost:9006/feedback/arrangement/" + arrangementId + "/" + fromUserId);
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

    @Test(priority = 2, dataProvider = "createTaskFeedback", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createTaskFeedbackTest(String body, int taskId, int fromUserId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9006/feedback/task/" + taskId + "/" + fromUserId);
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

    @Test(priority = 3, dataProvider = "createUserFeedback", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createUserFeedbackTest(String body, int userForId, int userFromId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9006/feedback/user/" + userForId + "/" + userFromId);
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

    @Test(priority = 4, dataProvider = "getFeedbackForUser", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void getFeedbackForUserTest(int userId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet("http://localhost:9006/feedback/user/for/" + userId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 5, dataProvider = "getFeedbackForTask", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void getFeedbackForTaskTest(int taskId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet("http://localhost:9006/feedback/task/for/" + taskId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        //todo: set taskId from response of this test
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 6, dataProvider = "getFeedbackForArrangement", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void getFeedbackForArrangementTest(int arrangementId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet("http://localhost:9006/feedback/arrangement/" + arrangementId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

}
