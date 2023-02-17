package com.example.userservice.data;

import org.testng.annotations.DataProvider;

public class ServiceDataProvider {

    @DataProvider(name = "createNewUser")
    public static Object[][] createNewUser() {
        return new Object[][] {{"{\n" +
            "    \"userName\":\"ATSmokeUser\",\n" +
            "    \"firstName\":\"Smoke\",\n" +
            "    \"lastName\":\"User\",\n" +
            "    \"email\":\"smoke.user@mail\",\n" +
            "    \"sectorId\":\"4\",\n" +
            "    \"companyId\": \"1\",\n" +
            "    \"password\":\"user\",\n" +
            "    \"role\": \"MANAGER\",\n" +
            "    \"superiorId\": 0,\n" +
            "    \"workVisible\": \"false\",\n" +
            "    \"authority\": \"user_management\"\n" +
            "}"}};
    }

    @DataProvider(name = "authenticateUser")
    public static Object[][] authenticateUser() {
        return new Object[][] {{"{\n" +
            "    \"username\":\"ATSmokeUser\",\n" +
            "    \"password\":\"user\"\n" +
            "}"}};
    }

    @DataProvider(name = "addArrangement")
    public static Object[][] addArrangement() {
        return new Object[][] {{"{\n" +
            "    \"name\":\"Meeting01\",\n" +
            "    \"code\":\"M01\",\n" +
            "    \"duration\":\"1.5\",\n" +
            "    \"startTime\":\"2021-01-24T15:53:16\",\n" +
            "    \"endTime\":\"2021-01-24T15:53:16\"\n" +
            "}", 1}};
    }

    @DataProvider(name = "getSimpleUserWithId")
    public static Object[][] getSimpleUserWithId() {
        return new Object[][] {{1}};
    }

    @DataProvider(name = "getUserWithUsername")
    public static Object[][] getUserWithUsername() {
        return new Object[][] {{"ATSmokeUser"}};
    }
}
