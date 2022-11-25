<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<body>
	<h3>수정하기</h3>
	<%-- readonly : form 태그로 데이터 넘어감 / disabled : form 태그로 데이터 안 넘어감(hidden으로 후속처리 필요) --%>
	아이디 :
	<input type="text" name="id" id="id" value="${member.id}" readonly="readonly">
	<br>
	이름 :
	<input type="text" name="name" id="name" value="${member.name}">
	<br>
	비밀번호 :
	<input type="password" name="password" id="password">
	<br>
	이메일 :
	<input type="email" name="email" id="email" value="${member.email}">
	<br>
	주소 :
	<input type="text" name="addr" id="addr" size="30" value="${member.addr}">
	<br>
	내용 :
	<textarea rows="5" cols="50" name="memo" id="memo">${member.memo}</textarea>
	<br>
	<button type="button" id="btnUpdate">수정 완료</button>
	<script src="/js/member.js"></script>
</body>
</html>