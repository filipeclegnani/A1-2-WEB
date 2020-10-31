/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Campeonato;
import model.Categoria;
import util.Conecta;

/**
 *
 * @author Filipe
 */
public class DAOCampeonato {
    private Conecta con;

    public DAOCampeonato() {
        this.con = new Conecta();
        System.out.println(this.con.getMsg());
    }

    public Conecta getCon() {
        return con;
    }

    public void setCon(Conecta con) {
        this.con = con;
    }
    
    public String cadastrarCampeonato(Campeonato obj) {
        try {
            String sql = "insert into campeonato "
                    + "(nome, datInicio, datFim) values"
                    + "(?, ?, ?)";
            PreparedStatement ps = this.con.getPS(sql);
            ps.setString(1, obj.getNome());
            ps.setDate  (2, obj.getDatInicio());
            ps.setDate  (3, obj.getDatFim());
            //ps.setBlob  (4, obj.getRegulamento());
            ps.execute();
            ps.close();
            return "Sucesso";
        } catch (SQLException ex) {
            return "ErroSQL: " + ex.getMessage();
        } catch (Exception e){
            return "Erro: " + e.getMessage();
        }
    }
    
    public String alteraCategoria(Campeonato obj){
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
    
    public String excluirCategoria(Campeonato obj){
        
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
    
    public List<Campeonato> listarCampeonato() throws SQLException {
     

            //1 passo criar a lista
            List<Campeonato> lista = new ArrayList<Campeonato>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from Campeonato";
            PreparedStatement stmt = this.con.getPS(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Campeonato obj = new Campeonato();
                
                obj.setId(rs.getInt("idCampeonato"));
                obj.setNome(rs.getString("nome"));
                obj.setDatInicio(rs.getDate("datInicio"));
                obj.setDatFim(rs.getDate("datFim"));
                obj.setRegulamento(rs.getBlob("regulamento"));

                lista.add(obj);
            }

            return lista;
    }
    public String getinfoByID(int id){
        try {
            List<Campeonato> lp = this.listarCampeonato();
            for(Campeonato model : lp){
                if(model.getId() == id){
                    return "| " + 
                            model.getNome()+" | "+
                            model.getDatInicio()+" | "+
                            model.getDatFim();
                }
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPiloto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
