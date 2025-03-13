package phuKien;

import Sneakers.SneakersDAO;
import Sneakers.SneakersDTO;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        
        
        try {
            phuKienDAO dao = new phuKienDAO();
            List<phuKienDTO> phuKienList = dao.getAllPK();
            if (phuKienList.isEmpty()) {
                System.out.println("Danh sach phu kien rong.");
            } else {
                System.out.println("Danh sach phu kien:");
                for (phuKienDTO pk : phuKienList) {
                    System.out.println(pk);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
       
    }
}

