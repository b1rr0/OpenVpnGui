package tokyo.ronin.openvpngui.console;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import tokyo.ronin.openvpngui.storage.ConfigModel;

public class ConsoleCommands {
    public static boolean sessionStart(ConfigModel configModel) {
        String path = configModel.getFile()
                .getPath();
        String login = configModel.getLogin();
        String pass = configModel.getPassword();
        return sessionStart(path, login, pass);
    }

    private static boolean sessionStart(String filePath, String user, String pass) {
        var process = bashProcess();
        var p_stdin = createBufferedWriterToConsole(bashProcess());

        filePath = "'" + filePath + "'";
        writeAndFlush(ConsoleConstant.SESSION_START + filePath, p_stdin);
        writeAndFlush(user, p_stdin);
        writeAndFlush(pass, p_stdin);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(ConsoleConstant.CONNECTED)) {
                    return true;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    private static void writeAndFlush(String data, BufferedWriter p_stdin) {
        try {
            p_stdin.write(data);
            p_stdin.newLine();
            p_stdin.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void stop(String path) {
        BufferedWriter p_stdin = createBufferedWriterToConsole();
        writeAndFlush(generateDisconnectCommand(path), p_stdin);
    }
    private static String generateDisconnectCommand(String path) {
        return ConsoleConstant.SESSION_MANAGE + path + ConsoleConstant.DISCONNECT;
    }
    private static BufferedWriter createBufferedWriterToConsole() {
        return new BufferedWriter(new OutputStreamWriter(bashProcess().getOutputStream()));
    }

    private static BufferedWriter createBufferedWriterToConsole(Process process) {
        return new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
    }

    public static List<String> activeSessions() {
        var p = bashProcess();
        BufferedWriter p_stdin = createBufferedWriterToConsole(p);
        writeAndFlush(ConsoleConstant.SESSION_LIST, p_stdin);

        try (Scanner scanner = new Scanner(p.getInputStream())) {
            List<String> list = new ArrayList<>();
            String line = scanner.nextLine();
            if (!line.contains(ConsoleConstant.DELIMITER)) {
                return List.of(line);
            }

            line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while (!line.contains(ConsoleConstant.DELIMITER) && scanner.hasNextLine()) {
                System.out.println(line);
                line = scanner.nextLine();
                if (line.contains(ConsoleConstant.PATH)) list.add(stringBuilder.toString());
                stringBuilder.append(line)
                        .append(System.lineSeparator());
            }
            list.add(stringBuilder.toString());
            return list;
        }
    }


    public static void stopSessions(List<String> sessions) {
        List<String> processedSessions = new ArrayList<>();
        for (String session : sessions) {
            var split = session.split(System.lineSeparator());
            processedSessions.add(split[0].replace(ConsoleConstant.PATH, "")
                    .trim());
        }
        stopList(processedSessions);
    }

    public static String version() {
        var process = bashProcess();
        BufferedWriter p_stdin = createBufferedWriterToConsole(process);
        writeAndFlush(ConsoleConstant.VERSION, p_stdin);
        try (Scanner scanner = new Scanner(process.getInputStream())) {
            StringBuilder stringBuilder = new StringBuilder();
            String st = "";
            while (!st.endsWith(".") && scanner.hasNext()) {
                st = scanner.nextLine();
                stringBuilder.append(st)
                        .append(System.lineSeparator());
            }
            return stringBuilder.toString();
        }
    }

    private static void stopList(List<String> list) {
        for (String s : list) {
            Thread thread = new Thread(() -> stop(s));
            thread.start();
        }
    }

    private static Process bashProcess() {
        ProcessBuilder builder = new ProcessBuilder(ConsoleConstant.BASH);
        Process p;
        try {
            p = builder.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return p;
    }
}
