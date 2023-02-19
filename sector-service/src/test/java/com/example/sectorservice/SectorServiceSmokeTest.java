package com.example.sectorservice;

import com.example.sectorservice.data.ServiceDataProvider;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SectorServiceSmokeTest {
    private String userToken;

    public SectorServiceSmokeTest() {
    }

    @BeforeClass
    public void initialize() {
        userToken =
            "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtc2NvdHQiLCJleHAiOjE2NzY4MzI0MjEsImlhdCI6MTY3NjgxMDgyMX0.Xz8KjS6pjX8kyS8V3F75VB8oXeDmp-ZWR_hKOwIVZYk";
    }

    @Test(priority = 1, dataProvider = "createSector", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createSectorTest(String body) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9001/sector/");
            StringEntity entity = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + userToken);
            request.setEntity(entity);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
    }

    @Test(priority = 2, dataProvider = "createCompany", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void createCompanyTest(String body) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9001/company/");
            StringEntity entity = new StringEntity(body);
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + userToken);
            request.setEntity(entity);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 201);
    }

    @Test(priority = 3, dataProvider = "getSector", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void getSectorTest(int sectorId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet("http://localhost:9001/sector/" + sectorId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 4, dataProvider = "SectorToCompany", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void addSectorToCompanyTest(int companyId, int sectorId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9001/company/" + companyId + "/addSector/" + sectorId);
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 5, dataProvider = "getCompany", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void getCompanyTest(int companyId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpGet request = new HttpGet("http://localhost:9001/company/" + companyId);
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test(priority = 6, dataProvider = "SectorToCompany", dataProviderClass = ServiceDataProvider.class, description = "Verify successful creation of user", alwaysRun = true)
    public void removeSectorFromCompanyTest(int companyId, int sectorId) {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpResponse response = null;
        try {
            HttpPost request = new HttpPost("http://localhost:9001/company/" + companyId + "/removeSector/" + sectorId);
            request.addHeader("content-type", "application/json");
            request.addHeader("Authorization", "Bearer " + userToken);
            response = httpClient.execute(request);
        } catch (Exception e) {
        }
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }
}
