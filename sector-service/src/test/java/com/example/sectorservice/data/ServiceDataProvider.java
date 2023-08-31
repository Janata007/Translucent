package com.example.sectorservice.data;

import lombok.Data;
import org.testng.annotations.DataProvider;

public class ServiceDataProvider {
    private static int code=0;
    @DataProvider(name = "createSector")
    public static Object[][] createSector() {
        code++;
        return new Object[][] {{"{\n" +
            "    \"name\":\"Automation_test_sector01\",\n" +
            "    \"code\":\"AT001\",\n" +
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
    @DataProvider(name = "authenticateUser")
    public static Object[][] authenticateUser(){
        return new Object[][]{{
            "{\n" +
                "    \"username\":\"mscott\",\n" +
                "    \"password\":\"user\"\n" +
                "}"
        }};
    }

    @DataProvider(name = "getSector")
    public static Object[][] getSector() {
        return new Object[][] {{1}};
    }

    @DataProvider(name = "getCompany")
    public static Object[][] getCompany() {
        return new Object[][] {{5}};
    }

    @DataProvider(name = "SectorToCompany")
    public static Object[][] SectorToCompany() {
        return new Object[][] {{5, 4}};
    }
}
