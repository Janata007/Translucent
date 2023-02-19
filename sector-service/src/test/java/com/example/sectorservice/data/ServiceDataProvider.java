package com.example.sectorservice.data;

import org.testng.annotations.DataProvider;

public class ServiceDataProvider {
    @DataProvider(name = "createSector")
    public static Object[][] createSector() {
        return new Object[][] {{"{\n" +
            "    \"name\":\"Automation_test_sector\",\n" +
            "    \"code\":\"AT01\",\n" +
            "    \"offeredServices\":[\"ORGANIZATION\"]\n" +
            "}"}};
    }

    @DataProvider(name = "createCompany")
    public static Object[][] createCompany() {
        return new Object[][] {{"{\n" +
            "    \"name\":\"Software development\",\n" +
            "    \"description\":\"Software development company\"\n" +
            "}"}};
    }

    @DataProvider(name = "getSector")
    public static Object[][] getSector() {
        return new Object[][] {{1}};
    }

    @DataProvider(name = "getCompany")
    public static Object[][] getCompany() {
        return new Object[][] {{2}};
    }

    @DataProvider(name = "SectorToCompany")
    public static Object[][] SectorToCompany() {
        return new Object[][] {{2, 1}};
    }
}
