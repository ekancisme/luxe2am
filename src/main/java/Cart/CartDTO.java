/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

/**
 *
 * @author Admin
 */

public class CartDTO {
    private String cartId;
    private String SneakerID;
    private String invId;
    private String userID;
    private String image;
    private float price;
    private float totalPrice;
    private Integer quantity;

    public CartDTO() {
    }
    
    public CartDTO(String cartId, String SneakerID, String invId, String userID, String image, float price, float totalPrice, Integer quantity) {
        this.cartId = cartId;
        this.SneakerID = SneakerID;
        this.invId = invId;
        this.userID = userID;
        this.image = image;
        this.price = price;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
    }

    public String getInvId() {
        return invId;
    }

    public void setInvId(String invId) {
        this.invId = invId;
    }

    

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getSneakerID() {
        return SneakerID;
    }

    public void setSneakerID(String SneakerID) {
        this.SneakerID = SneakerID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    
}
