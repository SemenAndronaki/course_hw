package homework2.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

import java.io.File;

public class PracticeFormTestPageObjects extends TestBase {

    @Test
    @Owner("test")
    @Description("test description")
    public void fillFormTest() {
        registrationPage
                .openPage()
                .checkCurrentPageIsPracticeForm()
                .fillRegistrationForm(new File("src/test/resources/pic.jpg"))
                .submitForm()
                .checkResult();
    }
}
