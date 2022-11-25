$("#btnUpdate").click(function() {
	// val() : form 태그 객체에 들어있는 데이터를 불러옴
	var dataParam = {
		"id": $("#id").val(),
		"name": $("#name").val(),
		"password": $("#password").val(),
		"email": $("#email").val(),
		"addr": $("#addr").val(),
		"memo": $("#memo").val()
	}

	$.ajax({
		type: "PUT",
		url: "/update/",
		data: JSON.stringify(dataParam),
		contentType: "application/json;charset=utf-8"
	})
		.done(function(resp) {
			alert(resp + ' 수정 성공')
			location.href = "/list"
		})
		.fail(function(e) {
			alert(e + ' 수정 실패')
		})
})