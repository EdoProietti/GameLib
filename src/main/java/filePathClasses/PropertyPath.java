package filePathClasses;

public class PropertyPath {

    private static final String QUERY= "src/main/resources/utils/query.properties";

    public PropertyPath(){
        // Non serve inizializzare alcun attributo.
    }

    public static String getQueryPath(){
        return QUERY;
    }
}
