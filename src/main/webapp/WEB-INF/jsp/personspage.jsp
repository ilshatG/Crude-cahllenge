<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Пример CRUD</title>
</head>
<body>
<h1>Список людей</h1>
<p>Для сброса фильтра достаточно нажать на кнопку "поиск" при пустом поле для поиска.</p>
<p>Успешно завершенные операции удаления, добавления и редактирования сбрасывают фильтр и переводят показ списка на первую страницу.</p>
<c:url var="saveUrl" value="find" />
<form:form modelAttribute="personAttribute" method="GET" action="${saveUrl}">

    <table>
			<tr>
			 <td><form:label path="name">Имя:</form:label></td>

			<td><form:input path="name" size="20"/></td>
			<td><input type="submit" value="Поиск" /></td>
			</tr>
	</table>

</form:form>

<c:url var="addUrl" value="/add" />
<p><a href="${addUrl}">Добавить человека</a></p>

<table style="border: 1px solid; text-align:center">
	<thead style="background:#99CCFF">
		<tr>
			<th>Имя</th>
			<th>Возраст</th>
			<th>Админ?</th>
			<th >Дата создания</th>
			<th colspan="2"></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${persons}" var="person">
			<c:url var="editUrl" value="/edit?id=${person.id}" />
			<c:url var="deleteUrl" value="/delete?id=${person.id}" />
		<tr>
			<td><c:out value="${person.name}" /></td>
			<td><c:out value="${person.age}" /></td>
			<td><c:out value="${person.isAdmin}" /></td>
			<td><c:out value="${person.createdDate}" /></td>
			<td><a href="${editUrl}">Редактировать</a></td>
			<td><a href="${deleteUrl}">Удалить</a></td>

		</tr>
	</c:forEach>
	</tbody>
</table>


<c:url var="nextUrl" value="/next" />
<c:url var="prevUrl" value="/prev" />
<p><a href="${prevUrl}"><<Предыдущая</a> Страница №${pageNumber} из ${pageCount} <a href="${nextUrl}">Следующая>></a></p>


</body>
</html>