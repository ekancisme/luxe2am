/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Sneakers;

import Sneakers.SneakersDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Tony
 */
public class SneakersDAO {

    Connection connection;
    PreparedStatement ps;
    ResultSet rs;

    public List<SneakersDTO> getAllByIdAndName(String name) {
        List<SneakersDTO> glassesList = new ArrayList<SneakersDTO>();
        SneakersDTO glasses;
        String sql = "SELECT * FROM tblSneakers WHERE name LIKE ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, '%' + name + '%');

            rs = ps.executeQuery();
            while (rs.next()) {
                glasses = new SneakersDTO(
                        rs.getString("SneakerID"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status")
                );
                if (glasses.getStatus() == 1) {
                    glassesList.add(glasses);
                }

            }
        } catch (Exception ex) {
            // Handle exception
        }

        return glassesList;
    }

    public List<SneakersDTO> getAllByPriceRange(float minPrice, float maxPrice) throws SQLException {
        List<SneakersDTO> glassesList = new ArrayList<SneakersDTO>();
        SneakersDTO glasses;
        String sql = "SELECT * FROM tblSneakers WHERE price BETWEEN ? AND ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setFloat(1, minPrice);
            ps.setFloat(2, maxPrice);

            rs = ps.executeQuery();
            while (rs.next()) {
                glasses = new SneakersDTO(
                        rs.getString("SneakerID"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status")
                );
                if (glasses.getStatus() == 1) {
                    glassesList.add(glasses);
                }

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

        return glassesList;
    }

    public List<SneakersDTO> getAllByType(String type) {
        List<SneakersDTO> glassesList = new ArrayList<SneakersDTO>();
        SneakersDTO glasses;
        String sql = "SELECT * FROM tblSneakers WHERE type LIKE ?;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, '%' + type + '%');

            rs = ps.executeQuery();
            while (rs.next()) {
                glasses = new SneakersDTO(
                        rs.getString("SneakerID"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status")
                );
                if (glasses.getStatus() == 1) {
                    glassesList.add(glasses);
                }

            }
        } catch (Exception ex) {
            // Handle exception
        }

        return glassesList;
    }

    public List<SneakersDTO> getAllGlasses() throws SQLException {
        List<SneakersDTO> glassesList = new ArrayList<SneakersDTO>();
        SneakersDTO glasses;
        String sql = "SELECT * FROM tblSneakers;";
        try {
            connection = DBUtils.getConnection();
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                glasses = new SneakersDTO(
                        rs.getString("SneakerID"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("type"),
                        rs.getString("image"),
                        rs.getFloat("price"),
                        rs.getInt("status")
                );
                if (glasses.getStatus() == 1) {
                    glassesList.add(glasses);
                }

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

        return glassesList;
    }

    public boolean createGlasses(SneakersDTO glasses) throws ClassNotFoundException, SQLException {
        String sql = "INSERT INTO tblSneakers (SneakerID, name, description, type, image, price, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        boolean response = false;

        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.setString(1, glasses.getSneakerID().trim());
                ps.setString(2, glasses.getName());
                ps.setString(3, glasses.getDescription());
                ps.setString(4, glasses.getType());
                ps.setString(5, glasses.getImage());
                ps.setFloat(6, glasses.getPrice());
                ps.setInt(7, glasses.getStatus());
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

    public boolean deleteGlasses(String SneakerID) throws ClassNotFoundException, SQLException {
        String sql = "DELETE FROM tblSneakers WHERE SneakerID = ?";
        boolean response = false;

        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.setString(1, SneakerID.trim());
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

    public boolean updateGlasses(SneakersDTO glasses) throws ClassNotFoundException, SQLException {
        String sql = "UPDATE tblSneakers SET name = ?, description = ?, type = ?, image = ?, price = ?, status = ? WHERE SneakerID = ?";
        boolean response = false;

        try {
            connection = DBUtils.getConnection();
            if (connection != null) {
                ps = connection.prepareStatement(sql);
                ps.setString(1, glasses.getName());
                ps.setString(2, glasses.getDescription());
                ps.setString(3, glasses.getType());
                ps.setString(4, glasses.getImage());
                ps.setFloat(5, glasses.getPrice());
                ps.setInt(6, glasses.getStatus());
                ps.setString(7, glasses.getSneakerID().trim());
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

}
