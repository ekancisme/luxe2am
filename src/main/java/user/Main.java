package user;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        try {
            List<UserDTO> userList = userDAO.getAllUsers();
            if (userList.isEmpty()) {
                System.out.println("Danh sach nguoi dung rong.");
            } else {
                System.out.println("Danh sach nguoi dung:");
                for (UserDTO user : userList) {
                    System.out.println("UserID: " + user.getUserID()
                            + ", FullName: " + user.getFullName()
                            + ", RoleID: " + user.getRoleID()
                            + ", Gmail: " + user.getGmail()
                            + ", Address: " + user.getAddress());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
