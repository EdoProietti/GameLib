package Model.Library;

import Model.User.Buyer;

import java.util.ArrayList;
import java.util.List;

public class LibraryMemoryDAO extends LibraryDAO{
    private static LibraryMemoryDAO instance;
    private final List<Library> cache;

    private LibraryMemoryDAO(){
        cache = new ArrayList<>();
    }

    public static LibraryMemoryDAO getInstance(){
        if(instance == null){
            instance = new LibraryMemoryDAO();
        }
        return instance;
    }

    public Library getLibrary(String username){
        Library library = new Library();
        cache.add(library);
        return library;
    }
}
