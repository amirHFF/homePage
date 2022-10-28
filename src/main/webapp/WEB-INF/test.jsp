<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: ARTA
  Date: 8/27/2021
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome to Spring Boot Security</h1>
<c:url value='/login' var="loginUrl"/>
<form  name="f" method="POST" action="${loginUrl}">
    User Name : <input type="text" name="username" /><br><br>
    Password  : <input type="password" name="password" /><br><br>
    <input type="submit" name="submit"/>
</form>
</body>
</html>
