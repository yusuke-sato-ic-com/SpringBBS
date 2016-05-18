<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/style.css'/>" />
<title>ユーザー管理画面</title>

<script type="text/javascript">
<!--
function offDisp(){
	if(window.confirm('本当に停止してよろしいですか？')){
		return true;
	} else {
		return false;
	}
}
// -->

<!--
function onDisp(){
	if(window.confirm('本当に復活させてよろしいですか？')){
		return true;
	} else {
		return false;
	}
}
// -->

<!--
function deleteDisp(){
	if(window.confirm('本当に削除してよろしいですか？')){
		return true;
	} else {
		return false;
	}
}
// -->
</script>

</head>
<body>

<a href="signup">ユーザー新規登録</a>
<a href="./">ホームへ戻る</a>

<h2>ユーザー管理画面</h2>

<table class="user_management">
	<tbody>
		<tr>
			<th>名前</th>
			<th>ログインID</th>
			<th>支店</th>
			<th>部署</th>
			<th>利用</th>
			<th colspan="2">編集</th>
		</tr>
	</tbody>
	<c:forEach items="${users}" var="user">
	<tbody>
		<tr>
			<td>${user.name}</td>
			<td>${user.loginId}</td>
			<td>${user.branchName}</td>
			<td>${user.departmentName}</td>
			<c:choose>
				<c:when test="${user.using == 1 }">
					<form:form modelAttribute="userManagementForm" onSubmit="return offDisp()">
						<input name="userId" type="hidden" value="${user.id}"/>
						<td><input name="using" type="submit" value="ON" /></td>
					</form:form>
				</c:when>
				<c:otherwise>
					<form:form modelAttribute="userManagementForm" onSubmit="return onDisp()">
						<input name="userId" type="hidden" value="${user.id}"/>
						<td><input name="using" type="submit" value="OFF" /></td>
					</form:form>
				</c:otherwise>
			</c:choose>
 			<form:form modelAttribute="userManagementForm" onSubmit="return deleteDisp()">
				<input name="userId" type="hidden" value="${user.id}"/>
				<td><input name="delete" type="submit" value="削除" /></td>
			</form:form>

			<form:form modelAttribute="userEditForm" method="GET" action="/SpringBBS/userEdit">
				<input name="userId" type="hidden" value="${user.id}"/>
				<td><input name="edit" type="submit" value="編集" /></td>
			</form:form>
		</tr>
	</tbody>
	</c:forEach>
</table>

</body>
</html>