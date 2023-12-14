package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;

/**
 * Класс помощник для выполнения bash команд
 */
public class DeviceHelper {

    /**
     * Выполняет bash скрипт с гарантированием возрата полной информации из консоли
     *
     * @param command bash скрипт
     * @return результат скрипта
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static String executeSh(String command) throws IOException, ExecutionException, InterruptedException {
        Process p = Runtime.getRuntime().exec(command);
        FutureTask<String> future = new FutureTask<>(() -> new BufferedReader(new InputStreamReader(p.getInputStream()))
                .lines().map(Object::toString)
                .collect(Collectors.joining("\n")));
        new Thread(future).start();
        return future.get();
    }

    /**
     * Выполняет bash скрипт через терминал с обработкой exception
     * Не гарантирует полного получения результата выполнения команды, так как нет обратного Callback
     * Подходит для выполнения скрипта без возвращения результата
     *
     * @param command bash команда
     * @return результат скрипты
     */
    public static String executeBash(String command) {
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        final String[] message = {""};

        new Thread(() -> {
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while (true) {
                try {
                    if ((line = input.readLine()) == null) {
                        break;
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                message[0] += line + "\n";
            }
            System.out.println(message[0]);
        }).start();
        try {
            p.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message[0];
    }
}
