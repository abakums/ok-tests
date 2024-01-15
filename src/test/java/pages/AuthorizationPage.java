package pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;

public class AuthorizationPage {

    //TODO enum
    //TODO properties
    private final SelenideElement loginField = $(MobileBy.xpath("//android.widget.EditText[@resource-id=\"ru.ok.android:id/text_login\"]"));
    private final SelenideElement passwordField = $(MobileBy.xpath("//android.widget.EditText[@resource-id=\"ru.ok.android:id/password_text\"]"));
    private final SelenideElement loginBtn = $(MobileBy.id("ru.ok.android:id/login_button"));

    public void login(){
        loginField.setValue("Student106Y");
        passwordField.setValue("w41gTqkeZM");
        loginBtn.click();

        $(MobileBy.id("ru.ok.android:id/action_bar_root")).should(appear);
    }

    public void logout(){
        MainPage mainPage = new MainPage();

        mainPage.logout();
    }

}
