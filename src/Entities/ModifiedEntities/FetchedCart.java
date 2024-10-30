package Entities.ModifiedEntities;

import Entities.DatabaseEntities.Cart;
import Entities.DatabaseEntities.Item;

public class FetchedCart extends Cart {

	Item cartItem;

	public FetchedCart(int customerID, int itemID, int quantity, Item cartItem) {
		super(customerID, itemID, quantity);
		this.cartItem = cartItem;
	}

	public Item getCartItem() {
		return cartItem;
	}

	public void setCartItem(Item cartItem) {
		this.cartItem = cartItem;
	}

}
