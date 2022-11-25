<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>상세보기</h3>
	아이디 : ${member.id}
	<br>
	이름 : ${member.name}
	<br>
	비밀번호 : ${member.password}
	<br>
	이메일 : ${member.email}
	<br>
	주소 : ${member.addr}
	<br>
	내용 : ${member.memo}
	<br>
	<a href="/update/${member.id}">수정하기</a>
</body>
</html>