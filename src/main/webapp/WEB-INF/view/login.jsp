<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/style.css'/>" />
<title>ログイン</title>

</head>
<body>

<c:if test="${ not empty errorMessages }">
	<div class="errorMessages">
		<ul> <%-- <ul>は順序のないリストを表示する歳際に使用する。順序のあるリスト表示は<ol> --%>
			<c:forEach items="${errorMessages}" var="messages"> <%-- 配列をループ処理 --%>
				<li><font size="4" color="#ff0000"><c:out value="${messages}" /><br /></font></li>
			</c:forEach>
		</ul>
	</div>
	<c:remove var="errorMessages" scope="session"/>
</c:if>

<div id="login">
<table>
	<tr>
		<th colspan="2">
			<div class="login_title">${title}</div>
		</th>
	</tr>
	<form:form modelAttribute="LoginForm">
		<tr>
			<th>
				<div class="login_label"><label>ログインID</label></div>
			</th>
			<th>
				<div class="login_form"><input name="loginId" maxlength="20"/></div>
			</th>
		</tr>
		<tr>
			<th>
				<div class="login_label"><label>パスワード</label></div>
			</th>
			<th>
				<div class="login_form"><input name="password" type="password" maxlength="20"/></div>
			</th>
		</tr>
		<tr>
			<th colspan="2">
				<div class="submit"><input type="submit" value="ログイン"/></div>
			</th>
		</tr>
		</form:form>
</table>
</div>
</body>
</html>