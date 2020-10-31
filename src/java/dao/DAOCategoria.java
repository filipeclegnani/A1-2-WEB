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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Categoria;
import util.Conecta;

/**
 *
 * @author Filipe
 */
public class DAOCategoria {
    private Conecta con;

    public DAOCategoria() {
        this.con = new Conecta();
        System.out.println(this.con.getMsg());
    }

    public Conecta getCon() {
        return con;
    }

    public void setCon(Conecta con) {
        this.con = con;
    }
    
    public String cadastrarPiloto(Categoria obj) {
        try {
            String sql = "insert into piloto "
                    + "(nome, email, cpf, dataNascimento, cidade, uf, modelo_moto, cilindrada, marca) values"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.con.getPS(sql);
            ps.setString(1, obj.getDescricao());
            ps.execute();
            ps.close();
            return "Sucesso";
        } catch (SQLException e) {
            return "Erro: " + e.getMessage();
        } catch (Exception e){
            return "Erro: " + e.getMessage();
        }
    }
    
    public String alteraCategoria(Categoria obj){
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
    
    public String excluirCategoria(Categoria obj){
        
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
    
    public List<Categoria> listarCategoria() throws SQLException {
     

            //1 passo criar a lista
            List<Categoria> lista = new ArrayList<Categoria>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from Categoria";
            PreparedStatement stmt = this.con.getPS(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Categoria obj = new Categoria();
                
                obj.setId(rs.getShort("idCategoria"));
                obj.setDescricao(rs.getString("descricao"));
                //obj.setIdcategoria(rs.getInt("idcategoria"));

                lista.add(obj);
            }

            return lista;
    }
    
    public String getinfoByID(int id){
        try {
            List<Categoria> lp = this.listarCategoria();
            for(Categoria model : lp){
                if(model.getId() == id){
                    return 
                            model.getDescricao() + " | ";
                }
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPiloto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
