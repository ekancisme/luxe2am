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
@WebServlet(name = "SearchPriceController", urlPatterns = {"/SearchPriceController"})
public class SearchPriceController extends HttpServlet {

    private static final String ERROR = "shop.jsp";
    private static final String SUCCESS = "shop.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        phuKienDAO dao = new phuKienDAO();
        List<phuKienDTO> glassesList = new ArrayList<>();
        try {
            float minPrice = Float.parseFloat(request.getParameter("minPrice"));
            float maxPrice = Float.parseFloat(request.getParameter("maxPrice"));
            List<phuKienDTO> listSesion = (List<phuKienDTO>) session.getAttribute("myList");
            if (listSesion.size() > 0) {
                glassesList = dao.getAllByPriceRange(listSesion, minPrice, maxPrice);
            } else {
                glassesList = dao.getAllByPriceRange(minPrice, maxPrice);
            }
            if (glassesList.size() > 0) {
                request.setAttribute("Glasses_List", glassesList);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at SearchPriceController:" + e.toString());
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
