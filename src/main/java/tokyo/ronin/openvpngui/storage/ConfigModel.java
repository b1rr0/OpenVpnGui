package tokyo.ronin.openvpngui.storage;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;
import java.util.StringJoiner;

public class ConfigModel {

    private String login;
    private String password;
    private File file;

    @JsonIgnore
    private boolean connected;

    @JsonIgnore
    private  String path;

    public String getLogin() {
        return login;
    }

    public ConfigModel setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public ConfigModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ConfigModel setPath(String path) {
        this.path = path;
        return this;
    }

    public File getFile() {
        return file;
    }

    public ConfigModel setFile(File file) {
        this.file = file;
        return this;
    }

    public boolean isConnected() {
        return connected;
    }

    public ConfigModel setConnected(boolean connected) {
        this.connected = connected;
        return this;
    }
}
