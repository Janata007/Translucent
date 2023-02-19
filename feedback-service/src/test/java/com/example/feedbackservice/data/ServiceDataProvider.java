package com.example.feedbackservice.data;

import org.testng.annotations.DataProvider;

public class ServiceDataProvider {
    @DataProvider(name = "createArrangementFeedback")
    public static Object[][] createArrangementFeedback() {
        return new Object[][] {{"{\n" +
            "    \"description\":\"short and sweet\",\n" +
            "    \"percent\":\"78\",\n" +
            "    \"grade\":\"8\",\n" +
            "    \"userFromId\":\"\"\n" +
            "}", 1, 1}};
    }

    @DataProvider(name = "createTaskFeedback")
    public static Object[][] createTaskFeedback() {
        return new Object[][] {{"{\n" +
            "    \"description\":\"nice\",\n" +
            "    \"percent\":\"78\",\n" +
            "    \"grade\":\"8\"\n" +
            "}", 1, 1}};
    }

    @DataProvider(name = "createUserFeedback")
    public static Object[][] createUserFeedback() {
        return new Object[][] {{"{\n" +
            "    \"description\":\"very professional and efficient\",\n" +
            "    \"percent\":\"78\",\n" +
            "    \"grade\":\"8\"\n" +
            "}", 1, 2}};
    }

    @DataProvider(name = "getFeedbackForUser")
    public static Object[][] getFeedbackForUser() {
        return new Object[][] {{1}};
    }

    @DataProvider(name = "getFeedbackForTask")
    public static Object[][] getFeedbackForTask() {
        return new Object[][] {{1}};
    }

    @DataProvider(name = "getFeedbackForArrangement")
    public static Object[][] getFeedbackForArrangement() {
        return new Object[][] {{1}};
    }
}
