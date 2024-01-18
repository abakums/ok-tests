package common.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import common.pages.navigator.Navigator;
import common.pages.navigator.Route;
import io.appium.java_client.MobileBy;

public class MainPage extends DefaultControls {
    private final SelenideElement rememberCHBX = $(MobileBy.id("ru.ok.android:id/md_promptCheckbox"));
    private final SelenideElement exit = $(MobileBy.xpath("//android.widget.TextView[@resource-id=\"ru.ok.android:id/md_buttonDefaultPositive\"]"));


    public void logout(){
        Navigator navigator = new Navigator();

        navigator.routeTo(Route.SIDE_MENU.MORE.LOG_OUT);

        if (rememberCHBX.exists())
        {
            rememberCHBX.click();
            exit.click();
        }
    }
}
