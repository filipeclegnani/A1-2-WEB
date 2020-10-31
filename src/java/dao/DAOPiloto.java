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
import model.Piloto;
import util.Conecta;

/**
 *
 * @author Filipe
 */
public class DAOPiloto {
    private Conecta con;

    public DAOPiloto() {
        this.con = new Conecta();
        System.out.println(this.con.getMsg());
    }

    public Conecta getCon() {
        return con;
    }

    public void setCon(Conecta con) {
        this.con = con;
    }
    
    public String cadastrarPiloto(Piloto obj) {
        try {
            String sql = "insert into piloto "
                    + "(nome, email, cpf, dataNascimento, cidade, uf, modelo_moto, cilindrada, marca) values"
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.con.getPS(sql);
            ps.setString(1, obj.getNome());
            ps.setString(2, obj.getEmail());
            ps.setString(3, obj.getCpf());
            ps.setDate(4, obj.getDataNascimento());
            ps.setString(5, obj.getCidade());
            ps.setString(6, obj.getUF());
            ps.setString(7, obj.getModeloMoto());
            ps.setString(8, obj.getCilindrada());
            ps.setString(9, obj.getMarca());
            ps.execute();
            ps.close();
            return "Sucesso";
        } catch (SQLException e) {
            return "Erro: " + e.getMessage();
        } catch (Exception e){
            return "Erro: " + e.getMessage();
        }
    }
    
    public String alteraCategoria(Piloto obj){
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
    
    public String excluirCategoria(Piloto obj){
        
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
    
    public List<Piloto> listarPilotos() throws SQLException {
     

            //1 passo criar a lista
            List<Piloto> lista = new ArrayList<Piloto>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from Piloto";
            PreparedStatement stmt = this.con.getPS(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Piloto obj = new Piloto();
                
                obj.setId(rs.getInt("idPiloto"));
                obj.setNome(rs.getString("nome"));
                obj.setEmail(rs.getString("email"));
                obj.setCpf(rs.getString("cpf"));
                obj.setDataNascimento(rs.getDate("dataNascimento"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUF(rs.getString("uf"));
                obj.setModeloMoto(rs.getString("modelo_moto"));
                obj.setCilindrada(rs.getString("cilindrada"));
                obj.setMarca(rs.getString("marca"));

                lista.add(obj);
            }

            return lista;
    }
    
    public String getinfoByID(int id){
        try {
            List<Piloto> lp = this.listarPilotos();
            for(Piloto model : lp){
                if(model.getId() == id){
                    return  
                            model.getNome()+" | "+
                            model.getEmail()+" | "+
                            model.getCpf()+" | "+
                            model.getDataNascimento().toString()+" | "+
                            model.getCidade()+" | "+
                            model.getUF()+" | "+
                            model.getModeloMoto()+" | "+
                            model.getCilindrada()+" | "+
                            model.getMarca();
                }
            }
            return null;
        } catch (SQLException ex) {
            Logger.getLogger(DAOPiloto.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
