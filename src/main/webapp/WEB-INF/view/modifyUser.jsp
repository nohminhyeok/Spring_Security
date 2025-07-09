<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 정보 수정</h1>
	<form method="post" action="/modifyUserAction">
		<table border="1">
			<tr>
				<th>아이디</th>
				<td><input type="text" name="username" value="${loginUsername }"></td>
			</tr>
			<tr>
				<th>기존 비밀번호</th>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<th>변경할 비밀번호</th>
				<td><input type="password" name="changePw"></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
</body>
</html>