package test;

import org.testng.annotations.BeforeMethod;
import taf.service.RestClientService;

public class BaseTest {
    protected RestClientService client = new RestClientService();

    @BeforeMethod
    public void reinitialize() {
        client = new RestClientService();
    }
}
