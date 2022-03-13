<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<!DOCTYPE html>
	<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Inserir Produto</title>
	</head>

	<body>
		<center>
			<h1>Inserir Produto</h1>
			<form action="inserir-produto" method="POST">
				<div>
					Nome: <input type="text" name="nome" id="nome">
				</div>
				<div>
					Preço: <input type="text" name="preco" id="preco">
				</div>
				<div>
					<select name='codigoCategoria' id="codigoCategoria">
						<c:forEach items="${categorias}" var="cat">
								<option value="<c:out value='${cat.codigo}' />"><c:out value='${cat.nome}' /></option>
							<c:out value='${cat.codigo}'></c:out>
						</c:forEach>
					</select>
				</div>
				<input type="submit" value="Inserir">
			</form>
			<a href="listar-produto"> Voltar </a>
		</center>
	</body>

	</html>