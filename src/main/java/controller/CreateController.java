/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Sneakers.SneakersDAO;
import Sneakers.SneakersDTO;
import Sneakers.SneakersError;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import phuKien.phuKienDAO;
import phuKien.phuKienDTO;

/**
 *
 * @author hd
 */
@MultipartConfig
@WebServlet(name = "CreateController", urlPatterns = {"/CreateController"})
public class CreateController extends HttpServlet {

    private static final String ERROR = "createSneakers.jsp";
    private static final String SUCCESS = "createSneakers.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        SneakersDAO dao = new SneakersDAO();
        phuKienDAO dao1=new phuKienDAO();
        SneakersError SneakersError = new SneakersError();
        try {
            Part filePart = request.getPart("image"); // Retrieves <input type="file" name="image">
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
            InputStream fileContent = filePart.getInputStream();
            // Write the file to your server
            File uploads = new File(getServletContext().getRealPath("/") + "/images");
            File file = new File(uploads, fileName);
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String brand = request.getParameter("brand");
            float price = Float.parseFloat(request.getParameter("price"));
            String description = request.getParameter("description");
            String type = request.getParameter("type");
            Integer status = 1;

            boolean checkValidation = true;
            if (checkValidation) {
                phuKienDTO phukien = new phuKienDTO(id, name, description, type,brand, fileName, price, status);
                boolean checkInsert = dao1.addNewPK(phukien);
                if (checkInsert) {
                    url = SUCCESS;
                    request.setAttribute("message", "Create successfully.");
                }
            } else {
                request.setAttribute("Glasses_ERROR", SneakersError);
            }
        } catch (Exception e) {
            log("Error at CreateController: " + e.toString());

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
