package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Classe responsável por estabelecer conexão com o banco de dados
 * e disponibilizar meios de recuperar e editar registros da base de dados.
 * @author Eduardo Comin
 * @since 01/06/2009
 */
public class Conecta {
    //Pode ser alterado para Mysql...
    private String DRIVER = "com.mysql.jdbc.Driver";
    private String BD = "rally";
    private String URL = "jdbc:mysql://localhost:3306/"+BD+"?useTimezone=true&serverTimezone=UTC";
    private String USERNAME = "root";
    private String PASSWORD = "root";
    private Connection conexao;
    private Statement stm;
    private PreparedStatement pstm;
    
    private String msg;

    public Conecta() {
        this.msg = this.iniciaConexao();
    }

    public Conecta(String bd, String user, String senha) {
        this.BD = bd;
        this.USERNAME = user;
        this.PASSWORD = senha;
        this.msg = this.iniciaConexao();

    }

    public String iniciaConexao() {
        try {
            //somente em versões antigas é necessário...
            //Class.forName(this.DRIVER);
            Class.forName(this.DRIVER);
            this.conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            // Definimos o objeto responsável por executar os comandos
            //this.stm = this.getConexao().createStatement();
            return "sucesso";
             
        //} catch (ClassNotFoundException e) {
          //  this.conexao = null;
            //return "Não foi possivel encontrar o driver de banco: " + e.getMessage();
        } catch (Exception e) {
            this.conexao = null;
            return "Erro: " + e.getMessage();
        }
    }
    
    public PreparedStatement getPS(String sql) {
        try {
            return this.getConexao().prepareStatement(sql);
        } catch (SQLException ex) {
            System.err.println("Errol: "+ex.getMessage());
            return null;
        }
    }

    public String fechaConexao() {
        try {
            if (this.getConexao() != null) {
                this.getConexao().close();
                this.conexao = null;
            }
            if (this.getStm() != null) {
                this.stm = null;
            }
            return "Conexão Encerrada";
        } catch (SQLException ex) {
            return "Houve erro no fechamento da conexão! "+ex.getMessage();
        }
    }

    public Connection getConexao() {
        return conexao;
    }

    public Statement getStm() {
        return stm;
    }
    
    public String getMsg() {
        return msg;
    }

    public PreparedStatement getPstm() {
        return pstm;
    }

    public void setPstm(PreparedStatement pstm) {
        this.pstm = pstm;
    }
    
    
    
    
}