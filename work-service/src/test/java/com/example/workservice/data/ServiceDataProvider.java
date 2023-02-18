package com.example.workservice.data;

import org.testng.annotations.DataProvider;

public class ServiceDataProvider {
    @DataProvider(name = "createTask")
    public static Object[][] createTask() {
        return new Object[][] {{"{\n" +
            "    \"name\":\"AutomationTask\",\n" +
            "    \"priority\":\"MEDIUM\",\n" +
            "    \"description\":\"\",\n" +
            "    \"arrangementId\":1,\n" +
            "    \"dateDue\":\"\",\n" +
            "    \"finished\":false,\n" +
            "    \"accepted\":\"\"\n" +
            "}", 1, 1}};
    }

    @DataProvider(name = "removeTask")
    public static Object[][] removeTask() {
        return new Object[][] {{1}};
    }

    @DataProvider(name = "getTaskForUser")
    public static Object[][] getTaskForUser() {
        return new Object[][] {{1}};
    }

    @DataProvider(name = "setTaskFinished")
    public static Object[][] setTaskFinished() {
        return new Object[][] {{1}};
    }
}
