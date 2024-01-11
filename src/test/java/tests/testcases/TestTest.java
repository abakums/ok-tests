package tests.testcases;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.qameta.allure.Description;
import pages.MainPage;
import tests.TestBase;

public class TestTest extends TestBase {

    private static MainPage mainPage;

    @BeforeAll
    public static void init() {
        mainPage = new MainPage();
    }

    @Test
    @Description("Нажатие на кнопку 'Открыть боковое меню'")
    void test(){

        assert mainPage.sideMenuBtn.exists();
    }
}