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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import user.UserDAO;
import user.UserDTO;

/**
 *
 * @author hd
 */
@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    private static final String ERROR = "login.jsp";
    private static final String AD = "AD";
    private static final String ADMIN_PAGE = "admin.jsp";
    private static final String US = "US";
    private static final String USER_PAGE = "home.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            String userID = request.getParameter("userIDorEmail");
            String password = request.getParameter("password");
            UserDAO dao = new UserDAO();
            UserDTO loginUser = dao.checkLogin(userID, password);
            boolean rememberMe = request.getParameter("rememberMe") != null;
            if (loginUser != null) {
                if (rememberMe) {
                        Cookie userCookie = new Cookie("username", userID);
                        userCookie.setMaxAge(60 * 60 * 24 * 7);
                        userCookie.setPath("/");
                        response.addCookie(userCookie);

                        Cookie passCookie = new Cookie("password", password);
                        passCookie.setMaxAge(60 * 60 * 24 * 7);
                        passCookie.setPath("/");
                        response.addCookie(passCookie);
                    } else {
                        Cookie userCookie = new Cookie("username", "");
                        userCookie.setMaxAge(0);
                        userCookie.setPath("/");
                        response.addCookie(userCookie);

                        Cookie passCookie = new Cookie("password", "");
                        passCookie.setMaxAge(0);
                        passCookie.setPath("/");
                        response.addCookie(passCookie);
                    }
                HttpSession session = request.getSession();
                session.setAttribute("LOGIN_USER", loginUser);

                String roleID = loginUser.getRoleID();
                if (AD.equals(roleID)) {
                    url = ADMIN_PAGE;
                } else if (US.equals(roleID)) {
                    url = USER_PAGE;
                } else {
                    request.setAttribute("message", "Your role is not support!");
                }
            } else {
                request.setAttribute("message", "Incorrect userID or Password");
            }

        } catch (Exception e) {
            log("Error at LoginController: " + e.toString());
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
