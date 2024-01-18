package common.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;

public class DefaultControls {

    protected SelenideElement sideMenuBtn = $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Открыть боковое меню\"]"));
    protected SelenideElement notification = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Notifications\"]"));
    protected SelenideElement search = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Menu\"]"));

    void openSideMenu(){
        sideMenuBtn.click();
    }
}
