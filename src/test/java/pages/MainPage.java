package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;

public class MainPage {
    public final SelenideElement sideMenuBtn = $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Открыть боковое меню\"]"));


}
