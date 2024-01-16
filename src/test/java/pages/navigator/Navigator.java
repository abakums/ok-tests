package pages.navigator;

import static com.codeborne.selenide.Selenide.$;

import com.codeborne.selenide.SelenideElement;
import io.appium.java_client.MobileBy;
import model.FieldTitle;

public class Navigator {
    private final SelenideElement sideMenuBtn = $(MobileBy.xpath("//android.widget.ImageButton[@content-desc=\"Открыть боковое меню\"]"));
    private final StringBuilder breadcrumbs = new StringBuilder();

    public String getBreadcrumbs() {
        return breadcrumbs.toString();
    }

    public void openSideMenu() {
        sideMenuBtn.click();
    }

    public void routeTo(FieldTitle route) {
        if (route instanceof Route.SIDE_MENU) {
            handleSideMenu((Route.SIDE_MENU) route);
        } else if (route instanceof Route.BOTTOM_MENU) {
            handleBottomMenu((Route.BOTTOM_MENU) route);
        } else if (route instanceof Route.SIDE_MENU.MORE) {
            handleMoreMenu((Route.SIDE_MENU.MORE) route);
        } else {
            throw new IllegalArgumentException("Неизвестная группа навигации");
        }
    }

    private void appendBreadcrumb(String breadcrumb) {
        breadcrumbs.append(breadcrumb);
        breadcrumbs.append("/");
    }

    private void handleSideMenu(Route.SIDE_MENU route) {
        openSideMenu();
        SelenideElement menuItem = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"" + route.toString() + "\"]"));
        assert menuItem.exists();
        appendBreadcrumb("Боковое меню");
        appendBreadcrumb(route.toString());
    }

    private void handleBottomMenu(Route.BOTTOM_MENU route) {
        SelenideElement menuItem = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"" + route.toString() + "\"]"));
        assert menuItem.exists();
        appendBreadcrumb("Нижнее меню");
        appendBreadcrumb(route.toString());
    }

    private void handleMoreMenu(Route.SIDE_MENU.MORE route) {
        openSideMenu();
        SelenideElement moreMenu = $(MobileBy.xpath("//android.view.ViewGroup[@content-desc=\"Ещё\"]"));

        if(moreMenu.exists()) moreMenu.click();

        appendBreadcrumb("Боковое меню/Ещё");
        appendBreadcrumb(route.toString());
    }
}
