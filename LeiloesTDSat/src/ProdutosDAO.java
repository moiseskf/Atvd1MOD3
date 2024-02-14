/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    private conectaDAO conexao;
    private Connection conn;

    public ProdutosDAO() {
        this.conexao = new conectaDAO();
        this.conn = this.conexao.connectDB(); // objeto "conn" esta recebendo a conexao retornada por conexao.getConexao()
    }

    PreparedStatement prep; //manipulacao de dados
    ResultSet resultset; // consultas
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public void cadastrarProduto(ProdutosDTO produto) {
        int status;

        try {
            prep = conn.prepareStatement("INSERT INTO produtos(nome,valor) VALUES(?,?)");

            prep.setString(1, produto.getNome());
            prep.setString(2, Integer.toString(produto.getValor()));

            status = prep.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());

        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {

        return listagem;
    }

}
