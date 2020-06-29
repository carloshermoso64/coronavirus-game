// curl -X POST "http://147.83.7.204:8080/dsaApp/user/login" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"name\": \"juninhoojl\", \"password\": \"asdfghjk\"}"
$(document).ready(function(){

	console.log("DOCOK");
	// vai limpar os cookies relacionados
	cleanCookie("cookieId",);
	cleanCookie("cookieName");
	cleanCookie("cookieToken");


});

function cleanCookie(name) {
	var expires = "";
		var date = new Date();
		date.setTime(date.getTime() + (7*24*60*60*1000));
		expires = "; expires=" + date.toUTCString();
	document.cookie = name + "=" + ('' || "")  + expires + "; path=/";
}


function shakeError(namele,tipo) {

	var elemento = document.getElementById(namele);

	var corAnt = elemento.style.backgroundColor;

	if(tipo == 1){
		elemento.style.backgroundColor = '#ff4c4c';
	}

	elemento.classList.add('error');

	setTimeout(function() {
		elemento.classList.remove('error');
		elemento.style.backgroundColor = corAnt;
	}, 300);
}


$(function(){

	$('#botaoLogin').on('click', function(e){
	e.preventDefault();

	// Vai conferir se esta vazio ou nao
	var username = document.getElementById("textUser");
	var passuser = document.getElementById("textPass");

	if(username.value.trim() == '' || passuser.value.trim() == ''){

		if(username.value.trim() == '') {
			shakeError("textUser",0);
		}

		if(passuser.value.trim() == '') {
			shakeError("textPass",0);
		}
		return;
	}

	var inputUser = document.getElementById("textUser").value;
	var inputPass = document.getElementById("textPass").value;

	var Credentials = {
		"name": inputUser,
		"password": inputPass
	};

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/dsaApp/user/login",
		// The key needs to match your method's input parameter (case-sensitive).
		data: JSON.stringify(Credentials),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			console.log("Sucesso");
			console.log(data.status);

		},
		complete: function(data) {
			console.log("Completo");
			if(data.status == 409){
				shakeError("textUser",1);
				shakeError("textPass",1);
				//alert("Wrong user or password!");
			}else if(data.status != 201){
				alert("Communication error!");
			}
			console.log(data.status);
			setCookie('cookieToken',data.responseText,7);
			setCookie('cookieName',Credentials.name,7);
			console.log(data.responseText);
			var reqid = "http://localhost:8080/dsaApp/user/"+Credentials.name;
			// Se foi completo solicitar o id
			$.getJSON(reqid, function(data){
				setCookie('cookieId',data.id,7);
				console.log(data.id);
				location.href = 'chat.html';
			});
		}
	});

  }); // end click event handler

});

function setCookie(name,value,days) {
	var expires = "";
	if (days) {
		var date = new Date();
		date.setTime(date.getTime() + (days*24*60*60*1000));
		expires = "; expires=" + date.toUTCString();
	}
	document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}


