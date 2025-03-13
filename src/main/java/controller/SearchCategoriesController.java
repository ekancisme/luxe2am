/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Sneakers.SneakersDAO;
import Sneakers.SneakersDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import phuKien.phuKienDAO;
import phuKien.phuKienDTO;

/**
 *
 * @author hd
 */
@WebServlet(name = "SearchCategoriesController", urlPatterns = {"/SearchCategoriesController"})
public class SearchCategoriesController extends HttpServlet {

    private static final String ERROR = "shop.jsp";
    private static final String SUCCESS = "shop.jsp";
    private static final String empty = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        phuKienDAO dao = new phuKienDAO();
        try {
            String type = request.getParameter("type");
            String brand = request.getParameter("brand");
            List<phuKienDTO> phuKienList = null;
            if (brand == null && type != null) {
                phuKienList = dao.getAllPkByType(type);
            } else if (type == null && brand != null) {
                phuKienList = dao.getAllPkByBrand(brand);
            } else if (type != null && brand != null) {
                phuKienList = dao.getAllPkByTypeBrand(type, brand);
            } else if (brand == null && type.equals("all")) {
                phuKienList = dao.getAllPK();
            }
            if (phuKienList.size() > 0) {
                HttpSession session = request.getSession();
                List<phuKienDTO> listSesion = phuKienList;
                session.setAttribute("myList", listSesion);
                request.setAttribute("Glasses_List", phuKienList);
                url = SUCCESS;
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("myList", dao.getAllPK());
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at SearchController:" + e.toString());
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
