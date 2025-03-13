/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invoice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Admin
 */
public class InvoiceDAO {

    private Connection connection;
    private PreparedStatement ps;
    ResultSet rs;

    public List<InvoiceDTO> getAllInvoices() throws SQLException {
    List<InvoiceDTO> invoiceList = new ArrayList<>();
    String sql = "SELECT * FROM tblInvoice ORDER BY dateBuy DESC"; // Sắp xếp theo ngày mới nhất
    try {
        connection = DBUtils.getConnection();
        ps = connection.prepareStatement(sql);
        rs = ps.executeQuery();
        while (rs.next()) {
            InvoiceDTO invoice = new InvoiceDTO(
                    rs.getString("invId"),
                    rs.getString("userID"),
                    rs.getString("dateBuy"),
                    rs.getString("gmail"),
                    rs.getString("address"),
                    rs.getFloat("total")
            );
            invoiceList.add(invoice);
        }
    } catch (Exception ex) {
        ex.printStackTrace(); // In lỗi để dễ debug
    } finally {
        if (rs != null) rs.close();
        if (ps != null) ps.close();
        if (connection != null) connection.close();
    }
    return invoiceList;
}


    public boolean createInvoice(InvoiceDTO invoice) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tblInvoice (invId, userID, dateBuy, gmail, address, total) VALUES (?, ?, ?, ?, ?, ?)";
        boolean response = false;

        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.setString(1, invoice.getInvId());
                ps.setString(2, invoice.getUserID());
                ps.setString(3, invoice.getDateBuy());
                ps.setString(4, invoice.getGmail());
                ps.setString(5, invoice.getAddress());
                ps.setFloat(6, invoice.getTotal());
                response = ps.executeUpdate() > 0;
            }
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

    public List<InvoiceDTO> getInvoicesByUser(String userID) throws ClassNotFoundException, SQLException {
        List<InvoiceDTO> invoiceList = new ArrayList<InvoiceDTO>();
        InvoiceDTO invoice;
        String sql = "SELECT * FROM tblInvoice WHERE userID = ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, userID);

            rs = ps.executeQuery();
            while (rs.next()) {
                invoice = new InvoiceDTO(
                        rs.getString("invId"),
                        rs.getString("userID"),
                        rs.getString("dateBuy"),
                        rs.getString("gmail"),
                        rs.getString("address"),
                        rs.getFloat("total")
                );
                invoiceList.add(invoice);
            }
        } catch (Exception ex) {
            // Handle exception
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return invoiceList;
    }

    public boolean invoiceExists(String invId) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT invId FROM tblInvoice WHERE invId = ?";
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {

                ps = conn.prepareStatement(sql);
                ps.setString(1, invId);
                rs = ps.executeQuery();
                if (rs.next()) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return false;
    }

    public List<String> getPurchasedProductIds(String invId) throws SQLException, ClassNotFoundException {
        List<String> productIds = new ArrayList<>();
        String sql = "SELECT productId FROM tblInvoice WHERE invId = ?";

        try (Connection conn = DBUtils.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, invId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productIds.add(rs.getString("productId"));
            }
        }
        return productIds;
    }

}
