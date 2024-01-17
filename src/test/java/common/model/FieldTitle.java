package common.model;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public interface FieldTitle {
    // экранирование кавычек
    static String escape(String s) {
        if (s.contains("\"")) {
            return "concat(\"" + s.replaceAll("\"", "\",'\"',\"") + "\")";
        } else {
            return "\"" + s + "\"";
        }
    }

    static By byFieldTitle(String title, String type) {
        switch (type) {
            default:
                return MobileBy.xpath(".//*[text()=" + escape(title) + "]/");
        }
    }
    static By byFieldTitle(String title) {
        return byFieldTitle(title, "");
    }

    default By by() {
        return byFieldTitle(toString());
    }

    default String trim() {
        return this.toString().replaceAll(" ", "").trim();
    }
}
