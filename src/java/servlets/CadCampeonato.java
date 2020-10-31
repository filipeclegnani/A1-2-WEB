/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DAOCampeonato;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Campeonato;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "CadCampeonato", urlPatterns = {"/CadCampeonato"})
public class CadCampeonato extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
        Campeonato camp = new Campeonato();
        DAOCampeonato dc = new DAOCampeonato();
        String resultado;
        
        try{
            camp.setNome(request.getParameter("Nome"));
            camp.setDatInicio(Date.valueOf(request.getParameter("DataInicio")));
            camp.setDatFim(Date.valueOf(request.getParameter("DataFim")));
            //camp.setRegulamento(null);
            resultado = "Cadastro: " + dc.cadastrarCampeonato(camp) + "<div></div>BD: " + dc.getCon().getMsg();
        } catch(Exception e){
            resultado = "erro " + e.toString();
        }
        request.setAttribute("msg", resultado);
        request.getRequestDispatcher("resultado cadastro.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    // </editor-fold>

}
