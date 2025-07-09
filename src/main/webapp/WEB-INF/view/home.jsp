<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>home</h1>
	<div>
		사용자 이름 : ${loginUsername }
	</div>
	
	<c:if test="${loginUsername eq 'anonymousUser'}">
		<div>
			<a href="/login">로그인</a>
		</div>
		<div>
			<a href="/addUser">회원가입</a>
		</div>
	</c:if>
	<c:if test="${!empty loginUsername && loginUsername ne 'anonymousUser'}">
		<div>
			<form action="/logout" method="post" style="display:inline;">
				<button type="submit">로그아웃</button>
			</form>
		</div>
		<div>
			<a href="/modifyUser">사용자 정보 수정</a>
		</div>
		<div>
			<a href="/deleteUser">회원 탈퇴</a>
		</div>
	</c:if>
</body>
</html>