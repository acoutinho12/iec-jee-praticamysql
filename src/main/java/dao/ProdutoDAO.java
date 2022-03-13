package dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Categoria;
import model.Produto;

public class ProdutoDAO {
	public String inserir(Produto produto) {		
		String retorno = "falha";
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			stmt.execute("INSERT INTO produto (nome,preco,codigoCategoria) VALUES ('"+produto.getNome() + "','"+produto.getPreco() + "','"+produto.getCategoria().getCodigo() + "')");
			retorno = "sucesso";
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.fecharConexao();
		}		
		return retorno;
	}
	
	public List<Produto> listar(int codigoCategoria){
		List<Produto> produtos = new ArrayList<Produto>();
		Conexao conn = new Conexao();
		try {
			final String whereCondition = codigoCategoria != -1 ? " where produto.codigoCategoria ="+codigoCategoria : "";
			Statement stmt = (Statement) conn.getConn().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT produto.nome, produto.codigo, preco, codigoCategoria, categoria.nome as nomeCategoria from produto inner join categoria on categoria.codigo = produto.codigoCategoria" + whereCondition );
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("codigoCategoria"));
				categoria.setNome(rs.getString("nomeCategoria"));
				Produto produto = new Produto();
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getFloat("preco"));
				produto.setCategoria(categoria);
				produtos.add(produto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.fecharConexao();
		}
		return produtos;
	}

    public Produto porCodigo(Integer id) throws Exception {
        Conexao conn = new Conexao();
		Produto produto = new Produto();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT produto.nome, produto.codigo, preco, codigoCategoria, categoria.nome as nomeCategoria from produto inner join categoria on categoria.codigo = produto.codigoCategoria where produto.codigo = " + id);
			if (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setCodigo(rs.getInt("codigoCategoria"));
				categoria.setNome(rs.getString("nomeCategoria"));
				produto.setCodigo(rs.getInt("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(rs.getFloat("preco"));
				produto.setCategoria(categoria);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.fecharConexao();
		}
		return produto;
    }

    public void editar(Produto produto) throws Exception {
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			final var update = "update produto set nome ='"+ produto.getNome() +"', preco ='"+produto.getPreco()+"', codigoCategoria ='"+produto.getCategoria().getCodigo()+"' where codigo = " + produto.getCodigo();
			stmt.executeUpdate(update);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.fecharConexao();
		}
    }

	public void deletar(Integer codigo) throws Exception {
		Conexao conn = new Conexao();
		try {
			Statement stmt = (Statement) conn.getConn().createStatement();
			final var update = "delete from produto where codigo = " + codigo;
			stmt.executeUpdate(update);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			conn.fecharConexao();
		}

	}

}
