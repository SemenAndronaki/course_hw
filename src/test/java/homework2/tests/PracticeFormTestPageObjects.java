package homework2.tests;

import homework2.pages.RegistrationPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.qameta.allure.Allure.step;

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

    @Test
    @Owner("test")
    @Description("test description")
    public void fillFormTestLambdaSteps() {
        step("open page", () -> {
            registrationPage
                    .openPage()
                    .checkCurrentPageIsPracticeForm();
        });
        step("fill registration form ", () -> {
            registrationPage
                    .fillRegistrationForm(new File("src/test/resources/pic.jpg"))
                    .submitForm();
        });
        step("check result", () -> {
            registrationPage
                    .checkResult();
        });
    }
}
