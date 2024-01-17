package common.pages.navigator;

import common.model.FieldTitle;

public class Route {
    public enum SIDE_MENU implements FieldTitle {
        NOTIFICATIONS("Оповещения"),
        EVENTS("События"),
        GUESTS("Гости"),
        FRIENDS("Друзья"),
        PHOTOS("Фото"),
        VIDEOS("Видео"),
        GROUPS("Группы"),
        GAMES("Игры"),
        HOLIDAYS("Праздники"),
        GIFTS("Подарки"),
        BOOKMARKS("Закладки"),
        ;

        public enum MORE implements FieldTitle {
            APPS("Приложения"),
            MOMENTS("Моменты"),
            ADVERSTISING_AREA("Рекламный кабинет"),
            NOW_ON_OK("Сейчас в ОК"),
            TOP_UP_BALANCE("Пополнить счёт"),
            PAYMENTS_AND_SUBSCRIPTIONS("Платежи и подписки"),
            CONFIDENTIALITY_POLICY("Политика конфиденциальности"),
            HELP("Помощь"),
            SETTINGS("Настройки"),
            LOG_OUT("Выйти"),
            ;
            final String title;

            MORE(String title) {
                this.title = title;
            }

            @Override
            public String toString() {
                return title;
            }
        }

        final String title;

        SIDE_MENU(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }

    public enum BOTTOM_MENU implements FieldTitle {
        NEWS("News Feed"),
        DISCUSSIONS("Discussions"),
        MESSAGES("Messages"),
        MUSIC("Music"),
        CLIPS("Clips");

        final String title;

        BOTTOM_MENU(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}

