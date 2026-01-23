package Model.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryMemoryDAO extends LibraryDAO{
    private static LibraryMemoryDAO instance;
    private List<Library> cache;

    private LibraryMemoryDAO(){
        cache = new ArrayList<>();
    }

    public static LibraryMemoryDAO getInstance(){
        if(instance == null){
            instance = new LibraryMemoryDAO();
        }
        return instance;
    }
}
