package model;

import model.game.Game;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<Game> cart;

    public ShoppingCart(){
        this.cart = new ArrayList<>();
    }

    public boolean isInCart(Game game){
        if(cart.isEmpty()){
            return false;
        }
        for(Game g: this.cart){
            if(game.equals(g)){
                return true;
            }
        }
        return false;
    }

    public void addGame(Game game){
        if(!this.isInCart(game)){
            this.cart.add(game);
        }
    }

    public BigDecimal getCartTotal(){
        BigDecimal total = BigDecimal.valueOf(0);
        for(Game g: this.cart){
            total = total.add(g.getPrice());
        }
        return total;
    }

    public List<Game> getItems(){
        return this.cart;
    }

}
