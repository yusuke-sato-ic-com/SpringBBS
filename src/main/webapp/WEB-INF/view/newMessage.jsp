<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/style.css'/>" />
<title>新規投稿画面</title>

</head>
<body>
<%--
<div id="menu">
		<p><a href="./">ホームへ戻る</a></p><br />
		<div class="home-name"><c:out value="${loginUser.name}"/>&nbsp;</div>
</div>
 --%>
 <a href="./">ホームへ戻る</a>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul>
			<c:forEach items="${errorMessages}" var="messages">
				<font size="4" color="#ff0000"><c:out value="${messages}" /><br /></font>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session" />
</c:if>


<h2>${title}</h2>


<form:form modelAttribute="NewMessageForm">
	<table>
		<tr>
			<th><form:label path="title">件名</form:label></th>
			<td><form:input path="title" value="${message.title}" maxlength="50"/></td>
		</tr>
		<tr>
			<th><form:label path="category">カテゴリー</form:label></th>
			<td><form:select path="category">
				<option>未選択</option>
				<c:forEach items="${categories}" var="categories">
			<%--		<option value="${categories.category}" selected>${categories.category}</option>  --%>
					<c:choose>
						<c:when test="${message.category == categories.category}">
							<option value="${categories.category}" selected>${categories.category}</option>
						</c:when>
						<c:otherwise>
							<option value="${categories.category}">${categories.category}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</form:select></td>
		</tr>
		<tr>
			<th colspan="2"><form:label path="text">本文</form:label></th>
		</tr>
		<tr>
			<td colspan="2"><form:textarea path="text" cols="50" rows="20" ></form:textarea></td>
		</tr>
		<tr>
			<td><input name="userId" type="hidden" value="${loginUser.id}" /></td>
			<td><input type="submit" value="投稿" /></td>
		</tr>

</tbody>
</table>
</form:form>

</body>
</html>