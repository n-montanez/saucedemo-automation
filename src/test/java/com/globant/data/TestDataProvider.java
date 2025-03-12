package com.globant.data;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name = "user-info")
    public Object[][] userInfo() {
        return new Object[][] {
                {"John", "Doe", "07008"}
        };
    }
}
