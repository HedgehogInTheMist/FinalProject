<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties/messages" />

<html>

<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<title><fmt:message key="main"/></title>
</head>

<body>
	<p align="center">${user}, <fmt:message key="hello"/>!</p>
	<hr/><br>

	<form name="logOutForm" method="POST" action="controller">
		<p align="right">
		<input type="hidden" name="command" value="logout"/>
		<input type="submit" value="<fmt:message key="button.logout"/>"/>
		</p>
	</form>

</body>
</html>