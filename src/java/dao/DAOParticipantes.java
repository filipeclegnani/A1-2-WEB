/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Participantes;
import util.Conecta;

/**
 *
 * @author Filipe
 */
public class DAOParticipantes {
    private Conecta con;

    public DAOParticipantes() {
        this.con = new Conecta();
        System.out.println(this.con.getMsg());
    }

    public Conecta getCon() {
        return con;
    }

    public void setCon(Conecta con) {
        this.con = con;
    }
    
    public String cadastrarParticipante(Participantes obj) {
        try {
            String sql = "insert into Participante "
                    + "(idPiloto, idCampeonato, idCategoria) values"
                    + "(?, ?, ?)";
            PreparedStatement ps = this.con.getPS(sql);
            ps.setInt(1, obj.getIdPiloto());
            ps.setInt(2, obj.getIdCampeonato());
            ps.setInt(3, obj.getIdCategoria());
            ps.execute();
            ps.close();
            return "Sucesso";
        } catch (SQLException e) {
            return "Erro: " + e.getMessage();
        } catch (Exception e){
            return "Erro: " + e.getMessage();
        }
    }
    
    public String alteraCategoria(Participantes obj){
        try {
            String sql = "update categoria set descricao=? where idcategoria=?";
            PreparedStatement ps = this.con.getPS(sql);
            //ps.setString(1, obj.getDescricao());
            //ps.setInt(2, obj.getIdcategoria());
            ps.execute();
            ps.close();
            return "Sucesso";
        } catch (SQLException e) {
            return "Erro: " + e.getMessage();
        } catch (Exception e){
            return "Erro: " + e.getMessage();
        }
    }
    
    public String excluirCategoria(Participantes obj){
        
        try {
            //1 passo  - criar o comando sql
            String sql = "delete from categoria where idcategoria = ?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = this.con.getPS(sql);
            //stmt.setInt(1, obj.getIdcategoria());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            return "Excluido com Sucesso!";

        } catch (SQLException erro) {
            return "Erro: " + erro;

        }
    }
    
    public List<Participantes> listarParticipantes() throws SQLException {
     

            //1 passo criar a lista
            List<Participantes> lista = new ArrayList<Participantes>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from Participante";
            PreparedStatement stmt = this.con.getPS(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Participantes obj = new Participantes();
                
                obj.setIdParticipante(rs.getInt("idParticipante"));
                obj.setIdPiloto(rs.getInt("idPiloto"));
                obj.setIdCampeonato(rs.getInt("idCampeonato"));
                obj.setIdCategoria(rs.getInt("idCategoria"));

                lista.add(obj);
            }

            return lista;
    }
    
    
}
