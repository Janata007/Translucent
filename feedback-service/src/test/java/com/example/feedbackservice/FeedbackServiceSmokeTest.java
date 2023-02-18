package com.example.feedbackservice;


import com.example.feedbackservice.data.ServiceDataProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

class FeedbackServiceSmokeTest {
    private String userToken;

    public FeedbackServiceSmokeTest() {
    }

    @BeforeClass
    public void initialize() {
        userToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtc2NvdHQiLCJleHAiOjE2NzY3NDgwMjksImlhdCI6MTY3NjcyNjQyOX0.KW7IgRxuHCRHJKRf2pkti1oVr8F3ZLOyunEj1yg48gQ";

    }

    @Test(priority = 1, dataProvider = "createArrangementFeedback", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createArrangementFeedbackTest(String body, int arrangementId, int fromUserId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9006/arrangement/" + arrangementId + "/" + fromUserId);
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

}
