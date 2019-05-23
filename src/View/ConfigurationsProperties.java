package View;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationsProperties {
    private static Properties prop = new Properties();

    public static void addProp(String name, String value) {
        FileOutputStream output = null;

        try {
            output = new FileOutputStream("Resources/config.properties");
            //this.prop.setProperty("generateAlgorithm", "MyMazeGenerator");
            //this.prop.setProperty("solveAlgorithm", "BFS");
            prop.setProperty(name, value);
            prop.store(output, (String)null);
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException var10) {
                    var10.printStackTrace();
                }
            }

        }

    }

    public static String getProp(String name){
        return prop.getProperty(name);
    }
}
