package common.model;

import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public enum AuthorizationField implements FieldTitle{
    LOGIN("e"),
    PASSWORD("Password")

    ;

    public static AuthorizationField[] ALL_FIELDS = {
        LOGIN, PASSWORD
    };

    public static Map<AuthorizationField, String> build(String login, String password){
        Map<AuthorizationField, String> map = new HashMap<>();
        map.put(LOGIN, login);
        map.put(PASSWORD, password);
        return map;
    }

    final String title;
    final By by;

    AuthorizationField(String title){
        this.title = title;
        this.by = null;
    }

    AuthorizationField(String title, By by){
        this.title = title;
        this.by = by;
    }

    @Override
    public String toString() {return title;}
}
