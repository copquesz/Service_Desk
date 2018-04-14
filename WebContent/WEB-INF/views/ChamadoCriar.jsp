<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Selecionar Fila</title>

    <!-- Favicon -->
    <link rel="shortcut icon" href="assets/img/favicon.png" type="image/x-icon">
    
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">
        <h3 class="page-header orange">Criar Chamados</h3>
        <c:if test="${mensagem == 'sucesso'}">
            <div class="alert alert-success" role="alert">
                <p>Chamado cadastrado com sucesso!</p>
                <hr>
                <p><b>Fila: </b>${chamado.fila.nome}</p>
                <p><b>Descrição: </b>${chamado.descricao}</p>
                <p><b>Status: </b>${chamado.status}</p>
            </div>
        </c:if>
        <c:if test="${mensagem == 'falhou'}">
            <div class="alert alert-danger" role="alert">
                <p>Ocorreu um erro ao cadastrar o projeto!</p>
                <p>Tente novamente.</p>
            </div>
        </c:if>
        <form action="criar_chamado" method="post">
            <div class="row justify-content-md-center">
                <div class="form-group col-md-8">
                    <label for="fila">Escolha a Fila:</label>
                    <select class="form-control" name="fila.id">
                        <c:forEach var="fila" items="${filas}">
                            <option value="${fila.id}">${fila.nome}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-group col-md-8">
                    <label for="descricao">Descrição:</label>
                    <textarea class="form-control" name="descricao" id="descricao" rows="4"></textarea>
                </div>
            </div>
            <div id="actions" class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">Adicionar</button>
                    <a href="index" class="btn btn-default">Cancelar</a>
                </div>
            </div>
        </form>
    </div>
    <script src="assets/js/jquery.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
</body>

</html>