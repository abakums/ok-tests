package common.pages;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import java.util.Arrays;
import java.util.Map;

import common.model.AuthorizationField;
import io.appium.java_client.MobileBy;

public class AuthorizationPage {

    private final SelenideElement loginBtn = $(MobileBy.id("ru.ok.android:id/login_button"));

    //TODO properties
    final Map<AuthorizationField, String> map = AuthorizationField.build("Student106Y", "w41gTqkeZM");


    public void login(){
        Arrays.stream(AuthorizationField.ALL_FIELDS).forEach(
                f -> $(f.by()).setValue(map.get(f))
        );

        loginBtn.click();

        $(MobileBy.id("ru.ok.android:id/action_bar_root")).should(appear);
    }


}
