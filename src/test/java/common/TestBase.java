package common;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.qameta.allure.Allure.step;
import static setup.helper.Constants.SCREENSHOT_TO_SAVE_FOLDER;
import static setup.helper.DeviceHelper.executeBash;
import static setup.helper.RunHelper.runHelper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import common.pages.AuthorizationPage;
import common.pages.MainPage;
import common.pages.navigator.Navigator;
import io.qameta.allure.Allure;
import io.qameta.allure.selenide.AllureSelenide;
import setup.config.ConfigReader;
import setup.listeners.AllureListener;

/**
 * Базовый тестовый класс
 */
@ExtendWith(AllureListener.class)
public class TestBase {
    protected Navigator navigator = new Navigator();
    protected MainPage mainPage = new MainPage();


    @BeforeAll
    public static void setup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        Configuration.reportsFolder = SCREENSHOT_TO_SAVE_FOLDER;

        Configuration.browser = runHelper().getDriverClass().getName();
        Configuration.startMaximized = false;
        Configuration.browserSize = null;
        Configuration.timeout = 10000;
        //disableAnimationOnEmulator();
    }

    /**
     * Отключение анимаций на эмуляторе чтобы не лагало
     */
    private static void disableAnimationOnEmulator() {
        executeBash("adb -s shell settings put global transition_animation_scale 0.0");
        executeBash("adb -s shell settings put global window_animation_scale 0.0");
        executeBash("adb -s shell settings put global animator_duration_scale 0.0");
    }

    /**
     * Проверка скриншота с эталоном для проверки верстки
     * @param actualScreenshot актуальный скриншот
     * @param expectedFileName название файла для сравнений
     */
    public void assertScreenshot(File actualScreenshot, String expectedFileName) {
        expectedFileName = expectedFileName.replace("()", ".png");
        String expectedScreensDir = "src/test/resources/expectedScreenshots/";

        if (ConfigReader.testConfig.isScreenshotsNeedToUpdate()) {
            try {
                Files.move(actualScreenshot.toPath(), new File(expectedScreensDir + expectedFileName).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return;
        }

        BufferedImage expectedImage = ImageComparisonUtil
                .readImageFromResources(expectedScreensDir + expectedFileName);

        BufferedImage actualImage = ImageComparisonUtil
                .readImageFromResources(SCREENSHOT_TO_SAVE_FOLDER + actualScreenshot.getName());

        File resultDestination = new File("diff/diff_" + expectedFileName);

        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage, resultDestination)
                .compareImages();

        if (imageComparisonResult.getImageComparisonState().equals(ImageComparisonState.MISMATCH)) {
            try {
                byte[] diffImageBytes = Files.readAllBytes(resultDestination.toPath());
                AllureListener.saveScreenshot(diffImageBytes);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
    }

    /**
     * Перед каждый тестом открываем приложение
     */
    @BeforeEach
    public void startDriver() {
        step("Открыть приложение", (Allure.ThrowableRunnableVoid) Selenide::open);
        //TODO login
        AuthorizationPage authorizationPage = new AuthorizationPage();
        authorizationPage.login();
    }

    /**
     * После каждого теста закрываем AndroidDriver чтобы тест атомарным был
     */
    @AfterEach
    public void afterEach() {
        //TODO logout
        MainPage mainPage = new MainPage();
        mainPage.logout();
        step("Закрыть приложение", Selenide::closeWebDriver);
    }
}
