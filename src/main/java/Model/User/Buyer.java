package Model.User;

import Model.Library.Library;
import Model.ShoppingCart;

public class Buyer extends User {
    private ShoppingCart cart;
    private Library library;

    public Buyer(String username, UserType type) {
        super(username, type);
        this.cart = new ShoppingCart();
    }

    public void linkLibrary(Library library){
        this.library = library;
    }
}