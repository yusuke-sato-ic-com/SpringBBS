<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='resources/css/style.css'/>" />
<title>ホーム画面</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/jquery-ui.min.js"></script>
<script
	src="http://ajax.googleapis.com/ajax/libs/jqueryui/1/i18n/jquery.ui.datepicker-ja.min.js"></script>

<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/redmond/jquery-ui.css">
<link rel="stylesheet"
	href="http://ajax.googleapis.com/ajax/libs/jqueryui/1/themes/ui-lightness/jquery-ui.css">

<script>
	$(function() {
		$("#fromDate").datepicker({
			dateFormat : "yy-mm-dd",
			maxDate : new Date(),
			onSelect : function(selectedDate) {
				$("#fromDate").datepicker("option", "showOn", 'button');
			}
		});
		$("#toDate").datepicker({
			dateFormat : "yy-mm-dd",
			maxDate : new Date(),
			onSelect : function(selectedDate) {
				$("#toDate").datepicker("option", "showOn", 'button');
			}
		});
	});
</script>

<script type="text/javascript">
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


<script type="text/javascript">
	$(function(){
		// formのsubmitイベントで起動
		$("#ajax-form").submit(function(event) {
			event.preventDefault(); // フォームのsubmit送信処理をキャンセル
			var form = $(this);
			$.ajax({
				type		: "POST", // method
				url			: "ajaxComment", // action
				dataType	: "json",
				data		: form.serialize(),
				timeout: 10000,
			})
			.done(function(data){
				// 通信が成功したときの処理
				console.log("通信成功");
				$("#ajax-comment").append(data.name + ' さんのコメント<br>' + data.text + '<br>' + data.insertDate + '<br>');

			})
			.fail(function(data){
				// 通信が失敗したときの処理
				console.log("通信失敗");
			})
			.always(function(data){
				// 通信が完了したとき
				console.log("通信完了");
			});
		});
	});

</script>


</head>
<body>
	<c:if test="${empty loginUser }">
		<c:if test="${ not empty errorMessages }">
			<div class="errorMessages">
				<ul>
					<%-- <ul>は順序のないリストを表示する歳際に使用する。順序のあるリスト表示は<ol> --%>
					<c:forEach items="${errorMessages}" var="messages">
						<%-- 配列をループ処理 --%>
							<li><font size="4" color="#ff0000"><c:out value="${messages}" /></font></li>
					</c:forEach>
				</ul>
			</div>
			<c:remove var="errorMessages" scope="session" />
		</c:if>
	</c:if>

	<c:if test="${ not empty loginUser }">
		<a href="newMessage">新規投稿</a>&nbsp;
		<c:if test="${(loginUser.departmentId) == 1 }">
			<a href="userManagement">ユーザー管理</a>&nbsp;
		</c:if>
		<a href="logout">ログアウト</a>&nbsp;
		<div class="home-name">
			<h2>
				<c:out value="${loginUser.name}" />
				&nbsp;
			</h2>
		</div>
	</c:if>


<table class="define">
	<form:form ModelAttribute="HomeForm" method="GET">
		<tr>
			<td>
				<label for="fromDate">日付範囲</label>
			</td>
			<td>
				<input name="fromDate" type="text" id="fromDate" placeholder="From" />
			</td>
			<td>
				<label for="toDate">～</label>
			</td>
			<td>
				<input name="toDate" type="text" id="toDate" placeholder="To" />
			</td>
		</tr>
		<tr>
			<td>
				<label for="categories">カテゴリー</label>
			</td>
			<td>
				<select name="categoryName">
					<option>すべて</option>
					<c:forEach items="${categories}" var="categories">
						<option value="${categories.category}">${categories.category}</option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="検索" />
			</td>
		</tr>
	</form:form>
</table>


	<c:if test="${ not empty errorMessages }">
		<div class="errorMessages">
			<ul>
				<c:forEach items="${errorMessages}" var="message">
					<font size="4" color="#ff0000"><c:out value="${message}"/><br /></font>
				</c:forEach>
			</ul>
		</div>
		<c:remove var="errorMessages" scope="session" />
	</c:if>

	<c:forEach items="${messages}" var="message">
		<div id="message">
		<div class="message-item">
			<c:out value="${message.name}" />
		</div>
		<div class="message-item">カテゴリー：</div>
		<c:out value="${message.category}" />
		<div class="message-item">件名：</div>
		<c:out value="${message.title}" />
		<div class="message-item">本文：</div>
		<c:out value="${message.text}"  escapeXml="false"/>
		<div class="message-date"><fmt:formatDate value="${message.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" /></div>
		<c:if test="${message.userId == loginUser.id || loginUser.departmentId == 2 || loginUser.departmentId == 3 && loginUser.branchId == message.branchId}">
			<form:form modelAttribute="HomeForm" onSubmit="return deleteDisp()">
				<input name="loginUserId" type="hidden" value="${loginUser.id}" id="loginUserId" />
				<input name="messageId" type="hidden" value="${message.id}" id="messageId" />
				<p class="home-submit">
					<input type="submit" value="投稿を削除" />
				</p>
			</form:form>
		</c:if>

		<c:forEach items="${comments}" var="comment">
			<c:if test="${message.id == comment.messageId }">
				<div class="comment">
					<div class="comment-name">
						<c:out value="${comment.name} さんのコメント" />
					</div>
					<div class="comment-text">
						<c:out value="${comment.text}"  escapeXml="false"/>
					</div>
					<div class="comment-date">
						<fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" />
					</div>
					<c:if test="${comment.userId == loginUser.id || loginUser.departmentId == 2 || loginUser.departmentId == 3 && loginUser.branchId == comment.branchId}">
						<form:form modelAttribute="HomeForm" onSubmit="return deleteDisp()"><br />
							<input name="loginUserId" type="hidden" value="${loginUser.id}" id="loginUserId" />
							<input name="commentId" type="hidden" value="${comment.id}" id="commentId" />
							<p class="home-submit">
								<input type="submit" value="コメントを削除" />
							</p>
						</form:form>
					</c:if>
				</div>
			</c:if>
		</c:forEach>
		<div id="ajax-comment">
			<div id="comment-name" value="">
				<c:out value="${comment.name}" />
			</div>
			<div id="comment-text">
				<c:out value="${comment.text}"  escapeXml="false"/>
			</div>
			<div id="comment-date">
				<fmt:formatDate value="${comment.insertDate}" pattern="yyyy/MM/dd HH:mm:ss" />
			</div>
		</div>

		<form:form id="ajax-form" ModelAttribute="newCommentForm" action="/SpringBBS/newComment">
			<label for="comment">コメントする</label><br />
			<input name="messageId" type="hidden" value="${message.id}" id="messageId" />
			<input name="userId" type="hidden" value="${loginUser.id}" />
			<textarea name="text" cols="68" rows="5" class="comment-box" ><c:if test="${comment.messageId == message.id}">${comment.text}</c:if></textarea><br />
		<%--	<c:choose>
				<c:when test="${comment.messageId == message.id}">
					<textarea name="text" cols="68" rows="5" class="comment-box" >${comment.text}</textarea><br />
				</c:when>
				<c:otherwise>
					<textarea name="text" cols="68" rows="5" class="comment-box" ></textarea><br />
				</c:otherwise>
			</c:choose>
		 --%>
			<p class="home-submit">
				<input type="submit" value="コメントを投稿"/>
			</p>
		</form:form>
		</div>
	</c:forEach>
</body>
</html>