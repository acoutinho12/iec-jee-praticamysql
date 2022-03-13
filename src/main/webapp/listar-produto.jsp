<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <!DOCTYPE html>
        <html>

        <head>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
            <script type="text/javascript">
                $(function () {
                    $('#filter').on('keyup', function () {
                        var value = $(this).val();
                        $('tr.linha-produto').hide();
                        $('tr.linha-produto').filter(function () {
                            return $(this).find('td.nome').text().toLowerCase().indexOf(value.toLowerCase()) != -1;
                        }).show();
                    });
                });
            </script>
            <meta charset="ISO-8859-1">
            <title>Produtos</title>
        </head>

        <body>
            <center>
                <h1>Produtos</h1>
                <h2>
                    <a href="inserir-produto">Inserir Novo Produto</a>
                    &nbsp;&nbsp;&nbsp;
                </h2>
            </center>
            <div align="center">
                Filtrar por nome<input type="text" id="filter" />
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>Lista de Produtos</h2>
                    </caption>
                    <tr>
                        <th>Codigo</th>
                        <th>Nome</th>
                        <th>Preço</th>
                        <th>Categoria</th>
                        <th>Acoes</th>
                    </tr>
                    <c:forEach var="produto" items="${listaProduto}">
                            <tr class="linha-produto">
                                <td>
                                    <c:out value="${produto.codigo}" />
                                </td>
                                <td class="nome">
                                    <c:out value="${produto.nome}" />
                                </td>
                                <td>
                                    <c:out value="${produto.preco}" />
                                </td>
                                <td>
                                    <c:out value="${produto.categoria.nome}" />
                                </td>

                                <td>
                                    <a href="editar-produto?id=<c:out value='${produto.codigo}' />">Alterar</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="deletar-produto?id=<c:out value='${produto.codigo}' />">Deletar</a>
                                </td>
                            </tr>
                    </c:forEach>
                </table>
            </div>
        </body>

        </html>