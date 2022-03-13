package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ProdutoDAO;

@WebServlet(urlPatterns = "/deletar-produto")
public class DeletarProduto extends HttpServlet {
    private ProdutoDAO dao = new ProdutoDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer codigo = Integer.parseInt(request.getParameter("id"));
            dao.deletar(codigo);
            response.sendRedirect(request.getContextPath() + "/listar-produto");			
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
}
