package homework2;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.text;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1600x1080";
    }

    @Test
    public void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        fillForm();
        $("#submit").click();
        checkResult();
    }

    private void fillForm() {
        $("#firstName").setValue("Semen");
        $("#lastName").setValue("Andronaki");
        $("#userEmail").setValue("andronaki@gmail.com");
        $x("//*[contains(text(),'Male')]").click();
        $("#userNumber").setValue("1234567890");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1995");
        $x("//div[@class='react-datepicker__month']/div/div[text() = '10']").click();

        $("#subjectsInput").setValue("english").pressEnter();
        $x("//*[@for='hobbies-checkbox-1']").click();

        File file = new File("src/test/resources/pic.jpg");
        $("#uploadPicture").uploadFile(file);

        $("#currentAddress").setValue("st Pushkina");
        $("#react-select-3-input").setValue("u").pressEnter();
        $("#react-select-4-input").setValue("u").pressEnter();
    }

    private void checkResult() {
        $(".table").shouldHave(text("Semen Andronaki"), text("andronaki@gmail.com"), text("Male"),
                text("1234567890"), text("1234567890"), text("10 April,1995"), text("English"),
                text("Sports"), text("pic.jpg"), text("st Pushkina"), text("Uttar Pradesh Lucknow"));
    }
}
