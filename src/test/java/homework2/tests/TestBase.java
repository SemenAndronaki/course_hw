package homework2.tests;

import com.codeborne.selenide.Configuration;
import homework2.pages.RegistrationPage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1600x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }
}
