package homework2.pages;

import com.codeborne.selenide.SelenideElement;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    //elements
    private final String FORM_TITLE = "Student Registration Form";

    SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            userEmailInput = $("#userEmail"),
            genderSelector = $x("//*[contains(text(),'Male')]"),
            userNumberInput = $("#userNumber"),
            dateOfBirthSelector = $("#dateOfBirthInput"),
            datePickerMonthSelector = $(".react-datepicker__month-select"),
            datePickerYearSelector = $(".react-datepicker__year-select"),
            datePickerDaySelector = $x("//div[@class='react-datepicker__month']/div/div[text() = '10']"),
            resultTable = $(".table"),
            header = $(".practice-form-wrapper"),
            submitFormButton = $("#submit"),
            subjectInput = $("#subjectsInput"),
            hobbieCheckBox = $x("//*[@for='hobbies-checkbox-1']"),
            fileInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateDropdown = $("#react-select-3-input"),
            cityDropdown = $("#react-select-4-input");

    //actions
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage checkCurrentPageIsPracticeForm() {
        header.shouldHave(text(FORM_TITLE));
        return this;
    }

    public RegistrationPage setFirstName() {
        firstNameInput.setValue("Semen");
        return this;
    }

    public RegistrationPage setLastName() {
        lastNameInput.setValue("Andronaki");
        return this;
    }

    public RegistrationPage setUserEmail() {
        userEmailInput.setValue("andronaki@gmail.com");
        return this;
    }

    public RegistrationPage selectGender() {
        genderSelector.click();
        return this;
    }

    public RegistrationPage submitForm() {
        submitFormButton.click();
        return this;
    }

    public RegistrationPage setUserNumber() {
        userNumberInput.setValue("1234567890");
        return this;
    }

    public RegistrationPage openDatepeeker() {
        dateOfBirthSelector.click();
        return this;
    }

    public RegistrationPage selectYearOfBirth() {
        datePickerYearSelector.selectOption("1995");
        return this;
    }

    public RegistrationPage selectMonthOfBirth() {
        datePickerMonthSelector.selectOption("April");
        return this;
    }

    public RegistrationPage selectDayOfBirth() {
        datePickerDaySelector.click();
        return this;
    }

    public RegistrationPage setSubject() {
        subjectInput.setValue("english").pressEnter();
        return this;
    }

    public RegistrationPage selectHobbie() {
        hobbieCheckBox.click();
        return this;
    }

    public RegistrationPage uploadPicture(File file) {
        fileInput.uploadFile(file);
        return this;
    }

    public RegistrationPage setCurrentAddress() {
        currentAddressInput.setValue("st Pushkina");
        return this;
    }

    public RegistrationPage selectStateDropdown() {
        stateDropdown.setValue("u").pressEnter();
        return this;
    }

    public RegistrationPage setCityDropdown() {
        cityDropdown.setValue("u").pressEnter();
        return this;
    }

    public RegistrationPage fillRegistrationForm(File file) {
        setFirstName();
        setLastName();
        setUserEmail();
        selectGender();
        setUserNumber();
        fillDate();
        setSubject();
        selectHobbie();
        uploadPicture(file);
        setCurrentAddress();
        selectStateDropdown();
        setCityDropdown();
        return this;
    }

    public RegistrationPage fillDate() {
        openDatepeeker();
        selectMonthOfBirth();
        selectYearOfBirth();
        selectDayOfBirth();
        return this;
    }

    public RegistrationPage checkResult() {
        resultTable.shouldHave(text("Semen Andronaki"), text("andronaki@gmail.com"), text("Male"),
                text("1234567890"), text("1234567890"), text("10 April,1995"), text("English"),
                text("Sports"), text("pic.jpg"), text("st Pushkina"), text("Uttar Pradesh Lucknow"));
        return this;
    }
}
