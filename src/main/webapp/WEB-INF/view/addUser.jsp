<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>회원가입</h1>
	<form method=post action="/addUserAction">
		<table border="1">
			<tr>
				<td>아이디</td>
				<th><input type="text" name="username"></th>
			</tr>
			<tr>
				<td>비밀번호</td>
				<th><input type="password" name="password"></th>			
			</tr>
		</table>
		<button type="submit">가입</button>
	</form>
</body>
</html>