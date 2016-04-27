<%@ taglib uri="/WEB-INF/tag/customTag.tld" prefix="ct" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties/messages" />

<html>

<head>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<title><fmt:message key="button.register"/></title>
</head>

<body>
	<p align="right"><ct:today format="dd.MM.yyyy"/></p>
	<form name="RegistrationForm" method="POST" action="controller">
		<input type="hidden" name="command" value="register"/>
		<p align="center"><fmt:message key="login"/>:<br>
		<input type="text" name="login" value=""/>
		<br><br><fmt:message key="password"/>:<br>
		<input type="password" name="password" value=""/>
		<br><br><fmt:message key="password.repeat"/>:<br>
		<input type="password" name="passwordRepeat" value=""/>
		<br></p>
		<div class="err">${errorRegistrationMessage}</div>
		<p align="center">
		<input type="submit" value="<fmt:message key="button.register"/>"/>
		</p>
	</form>
	<form name="logOutForm" method="POST" action="controller">
		<p align="center">
		<input type="hidden" name="command" value="logout"/>
		<input type="submit" value="<fmt:message key="button.back"/>"/>
		</p>
	</form>
</body>
</html>