<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="properties/messages" />

<html lang="${language}">

<head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
	<title><fmt:message key="page.login"/></title>
</head>

<body>

	<form>
		<select id="language" name="language" onchange="submit()">
			<option value="ru" ${language == 'ru' ? 'selected' : ''}>RU</option>
			<option value="en" ${language == 'en' ? 'selected' : ''}>EN</option>
		</select>
	</form>
    
	<%--<p align="right"><ct:today format="dd.MM.yyyy"/></p>--%>
	<form name="LoginForm" method="POST" action="controller">
		<input type="hidden" name="command" value="login"/>
		<p align="center"><fmt:message key="login"/>:<br>
		<input type="text" name="login" value=""/>
		<br><br><fmt:message key="password"/>:<br>
		<input type="password" name="password" value=""/>
		<br></p>
		<div class="err">${errorLoginPassMessage}</div>		
		<p align="center">
		<input type="submit" value="<fmt:message key="button.login"/>"/>
		</p>		
	</form>
	<%--<form name="RegistrationForm" method="POST" action="controller">
		<input type="hidden" name="command" value="registerChoice"/>
		<p align="center">
		<input type="submit" value="<fmt:message key="button.register"/>"/>
		</p>
	</form>--%>
</body>
</html>