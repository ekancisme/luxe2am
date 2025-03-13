/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class CartDAO {

    private Connection connection;
    private PreparedStatement ps;
    ResultSet rs;

    public boolean addToCart(String cartId, String SneakerID, float price, float totalPrice, int quantity, String image, String userID, String invId) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblCart(cartId, SneakerID, price, totalPrice, quantity,image,userID,invId) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        boolean response = false;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setString(1, cartId);
            ps.setString(2, SneakerID.trim());
            ps.setFloat(3, price);
            ps.setFloat(4, totalPrice);
            ps.setInt(5, quantity);
            ps.setString(6, image);
            ps.setString(7, userID);
            ps.setString(8, invId);
            response = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return response;
    }

    public boolean removeFromCart(String cartId) {
        String sql = "DELETE FROM tblCart WHERE cartId = ?;";
        boolean response = true;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cartId);
            response = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception ex) {
        }
        return response;
    }
    public void removeFromCart2(String userID) {
        String sql = "DELETE FROM tblCart WHERE userID = ?;";
        boolean response = true;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, userID);
            response = ps.executeUpdate() > 0 ? true : false;
        } catch (Exception ex) {
        }
    }

    public List<CartDTO> getCart(String userID) throws SQLException {
        List<CartDTO> cartList = new ArrayList<CartDTO>();
        CartDTO cart;
        String sql = "SELECT * FROM tblCart WHERE userID = ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, userID); // Set the userID parameter
            rs = ps.executeQuery();
            while (rs.next()) {
                cart = new CartDTO(rs.getString("cartId"), rs.getString("SneakerID"), rs.getString("invId"), rs.getString("userID"), rs.getString("image"), rs.getFloat("price"), rs.getFloat("totalPrice"), rs.getInt("quantity"));
                cartList.add(cart);
            }
        } catch (Exception ex) {
            // Handle your exception here
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return cartList;
    }

    public boolean updateCart(CartDTO cart) throws ClassNotFoundException {
        String sql = "UPDATE tblCart SET totalPrice = ?, quantity = ? WHERE cartId = ?;";
        boolean response = false;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);

            ps.setFloat(1, cart.getPrice() * cart.getQuantity()); // Set totalPrice
            ps.setInt(2, cart.getQuantity()); // Set quantity
            ps.setString(3, cart.getCartId()); // Set cartId

            response = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            // Handle your exception here
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                // Handle your exception here
            }
        }
        return response;
    }

    public CartDTO getCartByGlassesId(String SneakerID, String userID) throws ClassNotFoundException {
        String sql = "SELECT * FROM tblCart WHERE SneakerID = ? and userID =?;";
        CartDTO cart = null;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, SneakerID);
            ps.setString(2, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String cartId = rs.getString("cartId");

                String invId = rs.getString("invId");
                float price = rs.getFloat("price");
                float totalPrice = rs.getFloat("totalPrice");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("image");
                cart = new CartDTO(cartId, SneakerID, invId, userID, image, price, totalPrice, quantity);
            }
        } catch (SQLException ex) {
            // Handle your exception here
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                // Handle your exception here
            }
        }
        return cart;
    }

    public CartDTO getCartById(String cartId) throws ClassNotFoundException {
        String sql = "SELECT * FROM tblCart WHERE cartId = ?;";
        CartDTO cart = null;
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, cartId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String SneakerID = rs.getString("SneakerID");
                String userID = rs.getString("userID");
                String invId = rs.getString("invId");
                float price = rs.getFloat("price");
                float totalPrice = rs.getFloat("totalPrice");
                int quantity = rs.getInt("quantity");
                String image = rs.getString("image");
                cart = new CartDTO(cartId, SneakerID, invId, userID, image, price, totalPrice, quantity);
            }
        } catch (SQLException ex) {
            // Handle your exception here
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                // Handle your exception here
            }
        }
        return cart;
    }

    public void updateInvId(CartDTO cart) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE tblCart SET invId = ? WHERE cartId = ?";
        connection = DBUtils.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, cart.getInvId());
        ps.setString(2, cart.getCartId());
        ps.executeUpdate();
    }

    public boolean removePurchasedItems(String userID, List<String> purchasedSneakerIDs) throws SQLException, ClassNotFoundException {
        if (purchasedSneakerIDs.isEmpty()) {
            return false; // Không có sản phẩm nào để xóa
        }
        String sql = "DELETE FROM tblCart WHERE userID = ? AND SneakerID IN ("
                + String.join(",", purchasedSneakerIDs.stream().map(id -> "?").toArray(String[]::new)) + ")";

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userID);

            // Gán giá trị cho từng SneakerID
            for (int i = 0; i < purchasedSneakerIDs.size(); i++) {
                ps.setString(i + 2, purchasedSneakerIDs.get(i));
            }

            return ps.executeUpdate() > 0;
        }
    }

}
