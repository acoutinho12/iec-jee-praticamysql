package controller;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet("/editar-produto")
public class EditarProduto extends HttpServlet {
    private ProdutoDAO dao = new ProdutoDAO();
    private CategoriaDAO categoriaDao = new CategoriaDAO();
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Integer codigo = Integer.parseInt(request.getParameter("id"));
            Produto produto = dao.porCodigo(codigo);
            List<Categoria> categorias = categoriaDao.listar();
            if(produto == null) {
                PrintWriter out = response.getWriter();
                out.print("<html>");
                out.print("<h2> Nao foi possivel localizar a produto de codigo " + codigo + "</h2>");
                out.print("<br");
                out.print("<a href = 'index.jsp'> Voltar </a>");
                out.print("</html>");
            }else {
                request.setAttribute("produto", produto);
                request.setAttribute("categorias", categorias);
                RequestDispatcher dispatcher = request.getRequestDispatcher("editar-produto.jsp");
                dispatcher.forward(request, response);
            }
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer codigo = Integer.parseInt(request.getParameter("codigo"));
        String nome = request.getParameter("nome");
        Float preco = Float.parseFloat(request.getParameter("preco"));
        Integer codigoCategoria = Integer.parseInt(request.getParameter("codigoCategoria"));
        try {
            Produto produto = new Produto();
            Categoria categoria = new Categoria();
            categoria.setCodigo(codigoCategoria);
            categoria.setNome("");
            produto.setCodigo(codigo);
            produto.setNome(nome);
            produto.setPreco(preco);
            produto.setCategoria(categoria);
            dao.editar(produto);
            response.sendRedirect(request.getContextPath() + "/listar-produto");
        } catch (Exception ex) {
            throw new ServletException(ex);
        }

    } 

}
