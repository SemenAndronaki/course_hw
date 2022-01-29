package homework2;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.visible;

public class PracticeFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1600x1080";
        Configuration.holdBrowserOpen = false;
    }

    @Test
    public void fillFormTest() {
        open("https://demoqa.com/automation-practice-form");
        fillForm();
        $(By.id("submit")).click();
        checkResult();
    }

    private void fillForm() {
        $(By.id("firstName")).setValue("Semen");
        $(By.id("lastName")).setValue("Andronaki");
        $(By.id("userEmail")).setValue("andronaki@gmail.com");
        $(By.xpath("//*[contains(text(),'Male')]")).click();
        $(By.id("userNumber")).setValue("1234567890");

        $(By.id("dateOfBirthInput")).click();
        $(By.className("react-datepicker__month-select")).selectOption("April");
        $(By.className("react-datepicker__year-select")).selectOption("1995");
        $x("//div[@class='react-datepicker__month']/div/div[text() = '10']").click();

        $(By.id("subjectsInput")).setValue("english").pressEnter();
        $(By.xpath("//*[@for='hobbies-checkbox-1']")).click();

        File file = new File("src/test/resources/pic.jpg");
        $(By.id("uploadPicture")).uploadFile(file);

        $(By.id("currentAddress")).setValue("st Pushkina");
        $(By.id("react-select-3-input")).setValue("u").pressEnter();
        $(By.id("react-select-4-input")).setValue("u").pressEnter();
    }

    private void checkResult() {
        $(byText("Semen Andronaki")).shouldBe(visible);
        $(byText("andronaki@gmail.com")).shouldBe(visible);
        $(byText("Male")).shouldBe(visible);
        $(byText("1234567890")).shouldBe(visible);
        $(byText("10 April,1995")).shouldBe(visible);
        $(byText("English")).shouldBe(visible);
        $(byText("Sports")).shouldBe(visible);
        $(byText("pic.jpg")).shouldBe(visible);
        $(byText("st Pushkina")).shouldBe(visible);
        $(byText("Uttar Pradesh Lucknow")).shouldBe(visible);
    }
}
