/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DAOPiloto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Piloto;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "CadPiloto", urlPatterns = {"/CadPiloto"})
public class CadPiloto extends HttpServlet {

    

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
        Piloto pilo = new Piloto();
        
        // programação de cadastro
        String resultado;
        DAOPiloto dc = new DAOPiloto();
        try{
            pilo.setNome(request.getParameter("Nome"));
            pilo.setEmail(request.getParameter("Email"));
            pilo.setCpf(request.getParameter("CPF"));
            pilo.setDataNascimento(Date.valueOf(request.getParameter("Datanasc")));
            pilo.setCidade(request.getParameter("Cidade"));
            pilo.setUF(request.getParameter("UF"));
            pilo.setModeloMoto(request.getParameter("ModeloMoto"));
            pilo.setCilindrada(request.getParameter("Cilindradas"));
            pilo.setMarca(request.getParameter("Marca"));
            resultado = "Cadastro: " + dc.cadastrarPiloto(pilo) + "<div></div>BD: " + dc.getCon().getMsg();
        } catch(Exception e){
            resultado = "erro " + e.toString();
        }
        
        request.setAttribute("msg", resultado);
        request.getRequestDispatcher("resultado cadastro.jsp").forward(request, response);
    }

}
