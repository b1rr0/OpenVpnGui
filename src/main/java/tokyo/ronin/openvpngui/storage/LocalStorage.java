package tokyo.ronin.openvpngui.storage;

import java.util.List;

import tokyo.ronin.openvpngui.json.Json;

public class LocalStorage {
    private static final List<ConfigModel> configModels = Json.readConfigFromFile();

    public static void add(ConfigModel configModel) {
        configModels.add(configModel);
        Json.writeConfigToFile(configModels);
    }

    public static List<ConfigModel> get() {
        return configModels;
    }

    public static  void  remove(ConfigModel configModel) {
        configModels.remove(configModel);
        Json.writeConfigToFile(configModels);
    }
}
