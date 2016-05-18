<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/style.css'/>" />
<title>ユーザー編集画面</title>
</head>
<body>

<a href="userManagement">ユーザー管理画面へ</a>

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

	<h2>
		<c:out value="${title}"></c:out>
	</h2>
	<form:form modelAttribute="userEditForm">
		<table>
			<tr>
				<td>
					<div class="label"><form:label path="name">名前</form:label></div>
				</td>
				<td>
					<form:input path="name" size="20" value="${user.name}" />
				</td>
			</tr>
			<tr>
				<td>
					<div class="label"><form:label path="loginId">ログインID</form:label></div>
				</td>
				<td>
					<form:input path="loginId" size="20" value="${user.loginId}" />
				</td>
			</tr>
			<tr>
				<td>
					<div class="label"><form:label path="password">パスワード</form:label></div>
				</td>
				<td>
					<form:input path="password" type="password" size="20" />
				</td>
			</tr>
			<tr>
				<td>
					<div class="label"><form:label path="confirm">パスワード(確認用)</form:label></div>
				</td>
				<td>
					<form:input path="confirm" type="password" size="20" />
				</td>
			</tr>
			<tr>
				<td>
					<div class="label"><form:label path="branchId">所属支店</form:label></div>
				</td>
				<td>
					<form:select path="branchId">
						<option value="0">支店選択</option>
						<c:forEach items="${branches}" var="branch">
							<c:choose>
								<c:when test="${branch.id == user.branchId}">
									<option value="${branch.id}" selected>${branch.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${branch.id}">${branch.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>
					<div class="label"><form:label path="departmentId">所属部署</form:label></div>
				</td>
				<td>
					<form:select path="departmentId">
						<option value="0">部署選択</option>
						<c:forEach items="${departments}" var="department">
							<c:choose>
								<c:when test="${department.id == user.departmentId}">
									<option value="${department.id}" selected>${department.name}</option>
								</c:when>
								<c:otherwise>
									<option value="${department.id}">${department.name}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="変更"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>