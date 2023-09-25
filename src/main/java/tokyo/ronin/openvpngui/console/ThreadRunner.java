package tokyo.ronin.openvpngui.console;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import tokyo.ronin.openvpngui.help.AlertShower;
import tokyo.ronin.openvpngui.storage.ConfigModel;

public class ThreadRunner {

    public static boolean executeSessionStart(ConfigModel configModel) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<?> future = executor.submit(() -> {
           ConsoleCommands.sessionStart(configModel);
        });

        try {
            future.get(5, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            future.cancel(true);
            AlertShower.showList();
            return false;
        } catch (Exception e) {
            return false;
        } finally {
            executor.shutdownNow();
        }
        return true;
    }
}
