/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets.listadores;

import dao.DAOCampeonato;
import dao.DAOCategoria;
import dao.DAOParticipantes;
import dao.DAOPiloto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Participantes;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "ListParticipantes", urlPatterns = {"/ListParticipantes"})
public class ListParticipantes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListParticipantes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<input type=\"button\" value=\"retornar\" onclick=\"history.back()\">");
            DAOParticipantes dp = new DAOParticipantes();
            List<Participantes> cats;
            try {
                cats = dp.listarParticipantes();
                DAOPiloto dpil = new DAOPiloto();
                DAOCategoria dcat = new DAOCategoria();
                DAOCampeonato dcamp = new DAOCampeonato();
                for(Participantes model : cats) {
                    out.println("<div>"+
                            model.getIdParticipante()+" | "+
                            dpil.getinfoByID(model.getIdPiloto())+
                            dcamp.getinfoByID(model.getIdCampeonato())+
                            dcat.getinfoByID(model.getIdCategoria())+
                            "</div>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListPilotos.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
