<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="join" method="post">
		Name :
		<input type="text" name="name" />
		<br>
		Password :
		<input type="password" name="password" />
		<br>
		Email :
		<input type="email" name="email" />
		<br>
		Addr :
		<input type="text" name="addr" size="30" />
		<br>
		Memo :
		<textarea rows="5" cols="50" name="memo"></textarea>
		<br>
		<button>Submit</button>
	</form>
</body>
</html>