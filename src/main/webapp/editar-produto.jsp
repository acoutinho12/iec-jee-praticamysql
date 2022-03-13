<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
	<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Editar Produto</title>
	</head>

	<body>
		<center>
			<h1>Editar Produto</h1>
			<form action="editar-produto" method="POST">
				<input type="hidden" name="codigo" value="${produto.codigo}" />
				<div>
					Nome: <input type="text" name="nome" id="nome" value="${produto.nome}">
				</div>
				<div>
					Preço: <input type="text" name="preco" id="preco" value="${produto.preco}">
				</div>
				<div>
					<select name='codigoCategoria' id="codigoCategoria">
						<option value="${produto.categoria.codigo}" selected>${produto.categoria.nome}</option>
						<c:forEach items="${categorias}" var="cat">
							<c:if test="${cat.codigo != produto.categoria.codigo}">
								<option value="<c:out value='${cat.codigo}' />"><c:out value='${cat.nome}' /></option>
							</c:if>
							<c:out value='${cat.codigo}'></c:out>
						</c:forEach>
					</select>
				</div>
				<input type="submit" value="Editar">
			</form>
			<a href="listar-produto"> Voltar </a>
		</center>
	</body>

	</html>