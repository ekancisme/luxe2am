/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;
import user.UserError;

/**
 *
 * @author hd
 */
@WebServlet(name = "UpdateUserController2", urlPatterns = {"/UpdateUserController2"})
public class UpdateUserController2 extends HttpServlet {

    private static final String ERROR = "user.jsp";
    private static final String SUCCESS = "user.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    String url = ERROR;
    UserError userError= new UserError();
    try {
        String userID = request.getParameter("userID");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String roleID = request.getParameter("roleID");
        String gmail = request.getParameter("gmail");
        String address = request.getParameter("address");

        UserDTO user= new UserDTO(userID, fullName, password, roleID, gmail, address,1);
        UserDAO dao = new UserDAO();
        
        
        boolean checkUpdate = dao.update(user);
        if (checkUpdate) {
            url = SUCCESS;
            request.setAttribute("message", "Update success!");
        } else {
            request.setAttribute("message", "Update fail!");
        }

    } catch (Exception e) {
        log("Error at updateUserController2: " + e.toString());
        e.printStackTrace();
        
    } finally {
        request.getRequestDispatcher(url).forward(request, response);
    }
}

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
