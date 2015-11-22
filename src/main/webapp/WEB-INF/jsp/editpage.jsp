<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Редактируем</title>
</head>
<body>

<h1>Редактирование</h1>

<c:url var="saveUrl" value="edit?id=${personAttribute.id}" />
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
	<table>
		<tr>
			<td><form:label path="id">Id:</form:label></td>
			<td><form:input path="id" disabled="true"/></td>
		</tr>

		<tr>
        	<td><form:label path="createdDate">Дата создания:</form:label></td>
        	<td><form:input path="createdDate" disabled="true"/></td>
        </tr>


		<tr>
			<td><form:label path="name">Имя:</form:label></td>
			<td><form:input path="name"/></td>
		</tr>

		<tr>
			<td><form:label path="age">Возраст</form:label></td>
			<td><form:input path="age"/></td>
		</tr>

		<tr>
			<td><form:label path="isAdmin">Админ штоль?</form:label></td>
			<td><form:input path="isAdmin"/></td>
		</tr>
	</table>

	<input type="submit" value="Save" />
</form:form>

</body>
</html>