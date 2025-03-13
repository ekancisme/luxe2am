/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Cart.CartDAO;
import Invoice.InvoiceDAO;
import Invoice.InvoiceDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date; // Correct import for Date
import java.util.List;
import user.UserDAO;
import user.UserDTO;

@WebServlet(name = "CreateInvoiceController", urlPatterns = {"/CreateInvoiceController"})
public class CreateInvoiceController extends HttpServlet {

    private static final String ERROR = "checkout.jsp";
    private static final String SUCCESS = "thankyou.jsp";
    private static final String LOGIN_CONTROLLER = "login.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        InvoiceDAO dao = new InvoiceDAO();
            HttpSession session = request.getSession();

        try {
            String invId = request.getParameter("invId");
            String userID = request.getParameter("userID");
            String dateBuy = request.getParameter("dateBuy");
            String cartId = (String) session.getAttribute("Idcard");

            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateBuy = formatter.format(date);

            String gmail = request.getParameter("gmail");
            String address = request.getParameter("address");
            float total = Float.parseFloat(request.getParameter("total"));
            InvoiceDTO invoice = new InvoiceDTO(invId, userID, dateBuy, gmail, address, total);
            boolean checkInsert = dao.createInvoice(invoice);
            CartDAO cartDAO = new CartDAO();
            if (checkInsert) {
                System.out.println("da xoa: " + userID);
                cartDAO.removeFromCart2(userID);
                url = SUCCESS;
            }

        } catch (Exception e) {
            log("Error at CreateInvoiceController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
                            session.invalidate();

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
