package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdutoDAO;
import model.Produto;

@WebServlet("/listar-produto")
public class IndexProduto extends HttpServlet {
	private ProdutoDAO dao = new ProdutoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			listaProduto(request, response);
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listaProduto(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		Integer codigoCategoria = Integer
				.parseInt(request.getParameter("id") != null ? request.getParameter("id") : "-1");
		List<Produto> listaProduto = dao.listar(codigoCategoria);
		request.setAttribute("listaProduto", listaProduto);
		RequestDispatcher dispatcher = request.getRequestDispatcher("listar-produto.jsp");
		dispatcher.forward(request, response);
	}
}
