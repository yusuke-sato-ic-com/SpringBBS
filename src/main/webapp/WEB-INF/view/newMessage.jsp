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
<title>新規投稿</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>

</head>
<body>
<%--
<div id="menu">
		<p><a href="./">ホームへ戻る</a></p><br />
		<div class="home-name"><c:out value="${loginUser.name}"/>&nbsp;</div>
</div>
 --%>
	<p id="menu"><a href="./">ホームへ戻る</a></p>
	<div class="home-name"><c:out value="${loginUser.name}"/>&nbsp;</div>

<script>
$(function() {
	$("a").mouseover(function(){
		$("a").css("background-color", "#def7fe");
	}).mouseout(function(){
		$("a").css("background-color", "");
	});
});

</script>

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


<div id="new_message">
<form:form modelAttribute="NewMessageForm">
	<table>
		<tr>
			<td colspan="4">
				<div class="title"><c:out value="${title}"></c:out></div>
			</td>
		</tr>
		<tr>
			<th>
				<div class="message_label"><form:label path="title">件名</form:label></div>
			</th>
			<td>
				<form:input path="title" value="${message.title}" maxlength="50"/>
			</td>
			<th>
				<div class="message_label"><form:label path="category">カテゴリー</form:label></div>
			</th>
			<td>
				<form:select path="category">
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
				</form:select>
			</td>
		</tr>
		<tr>
			<th>
				<div class="message_label"><form:label path="text">本文</form:label></div>
			</th>
		</tr>
		<tr>
			<td colspan="4">
				<form:textarea path="text" cols="68" rows="25" ></form:textarea>
			</td>
		</tr>
		<tr>
			<td>
				<input name="userId" type="hidden" value="${loginUser.id}" />
			</td>
			<td colspan="2">
				<div class="submit"><input type="submit" value="投稿" /></div>
			</td>
		</tr>
	</table>
</form:form>
</div>

</body>
</html>