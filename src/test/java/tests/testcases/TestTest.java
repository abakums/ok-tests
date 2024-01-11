package tests.testcases;

import static com.codeborne.selenide.Selenide.$;

import org.junit.jupiter.api.Test;

import io.appium.java_client.MobileBy;
import io.qameta.allure.Description;
import tests.TestBase;

public class TestTest extends TestBase {

    @Test
    @Description("Нажатие на кнопку 'Открыть боковое меню'")
    void test(){
        var button = $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Открыть боковое меню\"]"));

        assert button.exists();
    }
}
