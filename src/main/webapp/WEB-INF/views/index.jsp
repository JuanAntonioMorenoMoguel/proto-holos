<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Pantalla de Inicio - JSP con Spring Boot</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/GlobalStyles.css">
    <script src="../../js/index.js"></script>
</head>
<body>
    <h1>¡Bienvenido a JSP con Spring Boot!</h1>
    <p>Mensaje: <strong>${mensaje}</strong></p>
</body>
</html>