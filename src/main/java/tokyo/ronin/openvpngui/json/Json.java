package tokyo.ronin.openvpngui.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import tokyo.ronin.openvpngui.storage.ConfigModel;

public class Json {
    public static String PATH = "storage.json";
    private static final ObjectMapper mapper = new ObjectMapper();

    public static void writeConfigToFile(List<ConfigModel> configModelList) {
        try {
            File file = new File(PATH);
            mapper.writeValue(file, configModelList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<ConfigModel> readConfigFromFile() {
        try {
            return mapper.readValue(new File(PATH), new TypeReference<>() {
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
