package file_path_classes;

public class PropertyPath {

    private static final String QUERY= "src/main/resources/utils/query.properties";
    private static final String CONFIG="src/main/resources/utils/config.properties";

    private PropertyPath(){
        // Non serve inizializzare alcun attributo.
    }

    public static String getQueryPath(){
        return QUERY;
    }

    public static String getConfigPath(){
        return CONFIG;
    }
}
