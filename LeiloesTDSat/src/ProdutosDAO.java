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
import java.util.List;
import javax.swing.table.DefaultTableModel;

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

    public int cadastrarProduto(ProdutosDTO produto) {
        int status;

        try {
            prep = conn.prepareStatement("INSERT INTO produtos(nome,valor) VALUES(?,?)");

            prep.setString(1, produto.getNome());
            prep.setString(2, Integer.toString(produto.getValor()));

            status = prep.executeUpdate();
            return status;
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        String sql = "SELECT * FROM produtos";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            ArrayList<ProdutosDTO> listaProduts = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();

                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setStatus(rs.getString("status"));
                produtos.setValor(rs.getInt("valor"));
                listaProduts.add(produtos);

            }

            return listaProduts;

        } catch (Exception e) {
            return null;
        }
    }

    public void venderProduto(int id) {
        try {
            String sql = "UPDATE produtos SET status = \"vendido\" WHERE id LIKE " + id;
            prep = conn.prepareStatement(sql);
            prep.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());

        }
    }

    public ArrayList<ProdutosDTO> listarProdutosVendidos() {
        String sql = "SELECT * FROM produtos where status LIKE \"vendido\" ";

        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            ArrayList<ProdutosDTO> listaProduts = new ArrayList<>();

            while (rs.next()) {
                ProdutosDTO produtos = new ProdutosDTO();

                produtos.setId(rs.getInt("id"));
                produtos.setNome(rs.getString("nome"));
                produtos.setStatus(rs.getString("status"));
                produtos.setValor(rs.getInt("valor"));
                listaProduts.add(produtos);

            }

            return listaProduts;

        } catch (Exception e) {
            return null;
        }
    }

}
