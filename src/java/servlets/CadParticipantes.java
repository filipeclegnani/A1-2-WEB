/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DAOCampeonato;
import dao.DAOCategoria;
import dao.DAOParticipantes;
import dao.DAOPiloto;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Campeonato;
import model.Categoria;
import model.Participantes;
import model.Piloto;
import util.ConvInt;

/**
 *
 * @author Filipe
 */
@WebServlet(name = "CadParticipantes", urlPatterns = {"/CadParticipantes"})
public class CadParticipantes extends HttpServlet {

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
            out.println("<title>Cadastrar Participantes</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<form action=\"CadParticipantes\" method=\"POST\">");
            out.println("Piloto:");
            out.println("<select name=\"Piloto\" size=\"1\"><br>");
            try {
                DAOPiloto dc = new DAOPiloto();
                List<Piloto> cats = dc.listarPilotos();
                for(Piloto model : cats) {
                    out.println("<option>"+model.getId()+" - "+model.getNome()+ "</option>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadParticipantes.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</select ><br>");
            out.println("Campeonato:");
            out.println("<select name=\"Campeonato\" size=\"1\"><br>");
            try {
                DAOCampeonato dc = new DAOCampeonato();
                List<Campeonato> cats = dc.listarCampeonato();
                for(Campeonato model : cats) {
                    out.println("<option>"+model.getId()+" - "+model.getNome()+ "</option>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadParticipantes.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</select><br>");
            out.println("Categoria:");
            out.println("<select name=\"Categoria\" size=\"1\"><br>");
            try {
                DAOCategoria dc = new DAOCategoria();
                List<Categoria> cats = dc.listarCategoria();
                for(Categoria model : cats) {
                    out.println("<option>"+model.getId()+" - "+model.getDescricao() + "</option>");
                }
            } catch (SQLException ex) {
                Logger.getLogger(CadParticipantes.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</select><br>");
            out.println("<form>");
            out.println("</body>");
            out.println("</html>");
            out.println("<input type=\"submit\" value=\"Cadastrar\"/>");
            out.println("<input type=\"button\" value=\"retornar\" onclick=\"history.back()\">");
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
        Participantes pilo = new Participantes();
        
        // programação de cadastro
        String resultado;
        DAOParticipantes dc = new DAOParticipantes();
        System.out.println(""+ConvInt.parseInt2("123"));
        try{
            System.out.println("132" + ConvInt.parseInt2(request.getParameter("Piloto"))
                    + ConvInt.parseInt2(request.getParameter("Campeonato")) 
                    + ConvInt.parseInt2(request.getParameter("Categoria")));
            pilo.setIdPiloto(ConvInt.parseInt2(request.getParameter("Piloto")));
            pilo.setIdCampeonato(ConvInt.parseInt2(request.getParameter("Campeonato")));
            pilo.setIdCategoria(ConvInt.parseInt2(request.getParameter("Categoria")));
            resultado = "Cadastro: " + dc.cadastrarParticipante(pilo) + "<div></div>BD: " + dc.getCon().getMsg();
        } catch(Exception e){
            resultado = "erro " + e.toString();
            System.err.println(resultado);
        }
        
        request.setAttribute("msg", resultado);
        request.getRequestDispatcher("resultado cadastro.jsp").forward(request, response);
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
