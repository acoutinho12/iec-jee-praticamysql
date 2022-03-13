package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CategoriaDAO;
import dao.ProdutoDAO;
import model.Categoria;
import model.Produto;

/**
 * Servlet implementation class InserirProduto
 */
@WebServlet("/inserir-produto")
public class InserirProduto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ProdutoDAO dao = new ProdutoDAO();
	private CategoriaDAO categoriaDao = new CategoriaDAO();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		Float preco = Float.parseFloat(request.getParameter("preco"));
		Integer codigoCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));

		Produto produto = new Produto();
		Categoria categoria = new Categoria();
		categoria.setCodigo(codigoCategoria);
		categoria.setNome("");
		produto.setNome(nome);
		produto.setPreco(preco);
		produto.setCategoria(categoria);

		String retorno = dao.inserir(produto);

		if (retorno.equals("sucesso")) {
			response.sendRedirect(request.getContextPath() + "/listar-produto");
		} else {
			PrintWriter out = response.getWriter();
			out.print("<html>");
			out.print("<h2> Nao foi possivel inserir o produto!</h2>");
			out.print("<br");
			out.print("<a href = 'index.jsp'> Voltar </a>");
			out.print("</html>");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listaCategoria(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listaCategoria(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Categoria> categorias = categoriaDao.listar();
		request.setAttribute("categorias", categorias);
		RequestDispatcher dispatcher = request.getRequestDispatcher("inserir-produto.jsp");
		dispatcher.forward(request, response);
	}
}
