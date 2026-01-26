package Model.User;

import FactoryDAO.FactoryDAO;
import Model.Library.Library;
import Model.ShoppingCart;

public class Buyer extends User {
    private ShoppingCart cart;
    private Library library;

    public Buyer(String username, String password) {
        super(username, password, UserType.BUYER);
        this.cart = new ShoppingCart();
        this.library = FactoryDAO.getInstance().createLibraryDAO().getLibrary(username);
    }

    public void linkLibrary(Library library){
        this.library = library;
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void freeCart(){
        this.cart = new ShoppingCart();
    }

    public Library getLibrary() {
        return library;
    }
}