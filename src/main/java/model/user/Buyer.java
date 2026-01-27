package model.user;

import factoryDAO.FactoryDAO;
import model.library.Library;
import model.ShoppingCart;

public class Buyer extends User {
    private ShoppingCart cart;
    private Library library;

    public Buyer(String username, String password) {
        super(username, password, UserType.BUYER);
        this.cart = new ShoppingCart();
        this.library = FactoryDAO.getInstance().createLibraryDAO().getLibrary(username);
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public void freeCart(){
        this.cart = new ShoppingCart();
    }

    public Library getLibrary() {
        this.library = FactoryDAO.getInstance().createLibraryDAO().getLibrary(this.getUsername());
        return library;
    }
}