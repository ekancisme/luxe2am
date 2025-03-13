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
import user.UserDAO;
import user.UserDTO;
import user.UserError;

/**
 *
 * @author hd
 */
@WebServlet(name = "CreateUserController", urlPatterns = {"/CreateUserController"})
public class CreateUserController extends HttpServlet {

    private static final String ERROR="register.jsp";
    private static final String SUCCESS="login.jsp";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url= ERROR;
        UserDAO dao= new UserDAO();
        UserError userError= new UserError();
        try {
            String userID= request.getParameter("userID");
            String fullName= request.getParameter("fullName");
            String roleID= "US";
            String password= request.getParameter("password");
            String confirm= request.getParameter("confirm");
            String gmail= request.getParameter("gmail");
            String address= request.getParameter("address");
            Integer status= 1;
            
            boolean checkValidation= true;
            
            if(!password.equals(confirm)){
                userError.setConfirm("hai Password khong giong nhau");
                checkValidation= false;
            }
            if(checkValidation){
                UserDTO user= new UserDTO(userID, fullName, password, roleID, gmail, address, status);
                boolean checkInsert= dao.insertUser(user);
                if(checkInsert){
                    url= SUCCESS;
                }
            }else{
                request.setAttribute("USER_ERROR", userError);
            }
        } catch (Exception e) {
            log("Error at CreateController: "+ e.toString());
            if(e.toString().contains("duplicate")){
                userError.setUserID("Trung id roi!");
                request.setAttribute("USER_ERROR", userError);
            }
            
        }finally{
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
