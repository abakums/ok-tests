package pages;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;

import io.appium.java_client.MobileBy;

public class MainPage {
    private final SelenideElement sideMenuBtn = $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Открыть боковое меню\"]"));
    private final SelenideElement moreBtn = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Ещё\"]"));
    private final SelenideElement exitBtn = $(MobileBy.xpath("//android.widget.TextView[@resource-id=\"ru.ok.android:id/nav_menu_item_row_name\" and @text=\"Выйти\"]"));
    private final SelenideElement rememberCHBX = $(MobileBy.id("ru.ok.android:id/md_promptCheckbox"));
    private final SelenideElement exit = $(MobileBy.xpath("//android.widget.TextView[@resource-id=\"ru.ok.android:id/md_buttonDefaultPositive\"]"));


    public void logout(){
        sideMenuBtn.click();
        moreBtn.click();
        exitBtn.click();
        rememberCHBX.click();
        exit.click();
    }
}
