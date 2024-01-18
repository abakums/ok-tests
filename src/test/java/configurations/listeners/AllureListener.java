package configurations.listeners;

import com.codeborne.selenide.Selenide;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;

import java.lang.reflect.Method;

import io.qameta.allure.Attachment;

/**
 * Кастомный листенер для переопределения логики завершения теста
 */
public class AllureListener implements AfterTestExecutionCallback {
    /**
     * Метод добавления скриншота в аллюра отчета через аннотацию
     * @param screenShot байты скриншотов
     * @return
     */
    @Attachment(value = "Page screenshot", type = "image/png")
    public static byte[] saveScreenshot(byte[] screenShot) {
        return screenShot;
    }

    /**
     * Переопределение логики завершения тестов у juni5
     * @param context контекст теста
     */
    @Override
    public void afterTestExecution(ExtensionContext context) {
        Method testMethod = context.getRequiredTestMethod();
        String testName = testMethod.getName();
        boolean testFailed = context.getExecutionException().isPresent();
        if (testFailed) {
            if (!testName.contains("Screenshot")) {
                saveScreenshot(Selenide.screenshot(OutputType.BYTES));
            }
        }
    }
}

