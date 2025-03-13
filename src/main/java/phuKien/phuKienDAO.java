package phuKien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class phuKienDAO {

    public List<phuKienDTO> getAllPK() throws SQLException, ClassNotFoundException {
        List<phuKienDTO> phuKienDTOs = new ArrayList<>();
        String sql = "SELECT * FROM tblPhuKiens";

        try (Connection connection = DBUtils.getConnection(); PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                phuKienDTOs.add(new phuKienDTO(
                        rs.getString("pkID"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("brand"),
                        rs.getString("image"),
                        rs.getDouble("price"),
                        rs.getInt("status")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phuKienDTOs;
    }

    // Lấy danh sách phụ kiện theo loại (type)
    public List<phuKienDTO> getAllPkByType(String type) throws SQLException, ClassNotFoundException {
        List<phuKienDTO> phuKienDTOs = new ArrayList<>();
        String sql = "SELECT * FROM tblPhuKiens WHERE type = ?";

        try (Connection connection = DBUtils.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, type);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phuKienDTOs.add(new phuKienDTO(
                            rs.getString("pkID"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("image"),
                            rs.getDouble("price"),
                            rs.getInt("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phuKienDTOs;
    }

    // Lấy danh sách phụ kiện theo loại (type) và thương hiệu (brand)
    public List<phuKienDTO> getAllPkByTypeBrand(String type, String brand) throws SQLException, ClassNotFoundException {
        List<phuKienDTO> phuKienDTOs = new ArrayList<>();
        String sql = "SELECT * FROM tblPhuKiens WHERE type = ? AND brand = ?";

        try (Connection connection = DBUtils.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, type);
            ps.setString(2, brand);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phuKienDTOs.add(new phuKienDTO(
                            rs.getString("pkID"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("image"),
                            rs.getDouble("price"),
                            rs.getInt("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phuKienDTOs;
    }

    public List<phuKienDTO> getAllPkByBrand(String brand) throws SQLException, ClassNotFoundException {
        List<phuKienDTO> phuKienDTOs = new ArrayList<>();
        String sql = "SELECT * FROM tblPhuKiens WHERE brand = ?";

        try (Connection connection = DBUtils.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, brand);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phuKienDTOs.add(new phuKienDTO(
                            rs.getString("pkID"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("image"),
                            rs.getDouble("price"),
                            rs.getInt("status")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phuKienDTOs;
    }

    // Thêm phụ kiện mới vào CSDL
    public boolean addNewPK(phuKienDTO pk) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO tblPhuKiens (pkID, name, description, type, brand, image, price, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBUtils.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, pk.getPkID());
            ps.setString(2, pk.getName());
            ps.setString(3, pk.getDescription());
            ps.setString(4, pk.getType());
            ps.setString(5, pk.getBrand());
            ps.setString(6, pk.getImage());
            ps.setDouble(7, pk.getPrice());
            ps.setInt(8, pk.getStatus());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu có lỗi
    }

    public List<phuKienDTO> getAllByPriceRange(float minPrice, float maxPrice) throws SQLException, ClassNotFoundException {
        List<phuKienDTO> phuKienList = new ArrayList<>();
        String sql = "SELECT * FROM tblPhuKiens WHERE price BETWEEN ? AND ?";

        try (Connection connection = DBUtils.getConnection(); PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setFloat(1, minPrice);
            ps.setFloat(2, maxPrice);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    phuKienDTO pk = new phuKienDTO(
                            rs.getString("pkID"),
                            rs.getString("name"),
                            rs.getString("description"),
                            rs.getString("type"),
                            rs.getString("brand"),
                            rs.getString("image"),
                            rs.getDouble("price"),
                            rs.getInt("status")
                    );
                    if (pk.getStatus() == 1) { // Chỉ lấy phụ kiện có trạng thái hợp lệ
                        phuKienList.add(pk);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phuKienList;
    }

    public List<phuKienDTO> getAllByPriceRange(List<phuKienDTO> phuKienList, float minPrice, float maxPrice) {
        List<phuKienDTO> filteredList = new ArrayList<>();

        for (phuKienDTO pk : phuKienList) {
            if (pk.getPrice() >= minPrice && pk.getPrice() <= maxPrice && pk.getStatus() == 1) {
                filteredList.add(pk);
            }
        }

        return filteredList;
    }

}
