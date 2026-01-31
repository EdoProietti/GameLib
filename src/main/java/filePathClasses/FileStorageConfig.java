package filePathClasses;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStorageConfig {
    private static final String BASE_DATA_DIR = "data";
    private static final Path PUBLISHER_FILE = Paths.get(BASE_DATA_DIR,"publisher.json");
    private static final Path BUYER_FILE = Paths.get(BASE_DATA_DIR,"buyer.json");

    private FileStorageConfig(){
        // non deve essere inizializzato nessun attributo.
    }

    public static Path getPublisherFilePath(){
        return PUBLISHER_FILE;
    }

    public static Path getBuyerFilePath(){
        return BUYER_FILE;
    }
}
