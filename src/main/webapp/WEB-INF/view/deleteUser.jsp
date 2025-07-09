<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원 탈퇴</h1>
	<form method="post" action="/deleteUserAction" onsubmit="return confirm('정말 탈퇴하시겠습니까?');">
		<input type="hidden" name="username" value="${loginUsername }">
		<input type="password" name="password" placeholder="비밀번호 확인" required>
		<button type="submit">회원 탈퇴</button>
	</form>
</body>
</html>