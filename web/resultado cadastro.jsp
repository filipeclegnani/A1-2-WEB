<%-- 
    Document   : sucesso ao cadastrar
    Created on : 27/09/2020, 16:12:42
    Author     : Filipe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sucesso</title>
    </head>
    <body>
        <h1><%= request.getAttribute("msg") %></h1>
        <input type="button" value="Cadastra outro" onclick="history.back()">
    </body>
</html>
