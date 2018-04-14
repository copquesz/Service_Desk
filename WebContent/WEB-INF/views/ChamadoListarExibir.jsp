<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listar Chamados</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>

<body>
    <!-- Barra superior com os menus de navegação -->
    <c:import url="Menu.jsp" />
    <!-- Container Principal -->
    <div id="main" class="container">        
        <h3 class="page-header">Chamado(s) da Fila ${fila.nome}</h3>
        <c:if test="${mensagem == 'sucesso'}">
            <div class="alert alert-info" role="alert">
                <p>Chamado fechado com sucesso!</p>
            </div>
        </c:if>
        <c:if test="${not empty chamados}">
            <form action="fechar_chamado" method="post">
                <div class="table-responsive col-md-12">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th></th>
                                <th>Descrição</th>
                                <th>Abertura</th>
                                <th>Fechamento</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="chamado" items="${chamados}">
                                <tr>
                                    <c:if test="${chamado.status == 'Fechado'}">
                                        <td><input type="radio" class="radio" name="id" value="${chamado.id}" disabled></td>
                                    </c:if>
                                    <c:if test="${chamado.status == 'Aberto'}">
                                        <td><input type="radio" class="radio" name="id" value="${chamado.id}"></td>
                                    </c:if>
                                    <td>${chamado.descricao }</td>
                                    <td>
                                        <fmt:formatDate value="${chamado.abertura }" pattern="dd/MM/yyyy" />
                                    </td>
                                    <td>
                                        <fmt:formatDate value="${chamado.fechamento }" pattern="dd/MM/yyyy" />
                                    </td>
                                    <td>${chamado.status }</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <button type="submit" class="btn btn-primary" >Fechar Chamado</button>
                <a href="listar_filas_exibir" class="btn btn-default">Voltar</a>
            </form>
        </c:if>
        <c:if test="${empty chamados}">
            <div class="alert alert-info" role="alert">N&atilde;o h&aacute; chamados nesta fila.</div>
        </c:if>       
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>

</html>