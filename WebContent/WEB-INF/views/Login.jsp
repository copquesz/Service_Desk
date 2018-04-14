<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Sistema de Chamados</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.10/css/all.css" integrity="sha384-+d0P83n9kaQMCwj8F4RJB66tzIwOKmrdb46+porD/OvrJ+37WqIM7UoBtwHO6Nlg" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <div class="row" style="padding-top: 15%;">
        <c:if test="${mensagem == 'falhou'}">
            <div class="alert alert-danger" role="alert">
                <p>Preencha os campos corretamente para efetuar o login!</p>
                <p><b>E-mail ou senha inválidos.</b></p>
            </div>
        </c:if>
        <div class="col-md-6 col-sm-12">
            <center><i class="fas fa-address-card" style="font-size: 250px;"></i></center>
        </div>
        <div class="col-md-6 col-sm-12">
            <form action="fazer_login">
              <div class="form-group">
                <label for="email">E-Mail:</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="Informe seu e-mail para acesso">
              </div>
              <div class="form-group">
                <label for="senha">Senha:</label>
                <input type="password" class="form-control" id="senha" name="senha" placeholder="Informe sua senha para acesso">
              </div>              
              <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>

</html>