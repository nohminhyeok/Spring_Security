<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<h1>로그인 페이지</h1>
	<a href="/addUser">회원가입</a>
	<form method="post" action="/loginAction">
		<table border="1">
			<tr>
				<td>아이디</td>
				<th><input type="text" name="username"></th>
				<td>비밀번호</td>
				<th><input type="password" name="password"></th>
			</tr>
		</table>
		<button type="submit">로그인</button>
	</form>
</body>
</html>
