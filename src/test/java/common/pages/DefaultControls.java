package common.pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;

public class DefaultControls {

    private SelenideElement notification = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Notifications\"]"));
    private SelenideElement search = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Menu\"]"));
}
