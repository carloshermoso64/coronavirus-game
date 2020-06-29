// "http://147.83.7.204:8080/dsaApp/user/adduser" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"name\": \"Novo\", \"email\": \"teste@mail.com\", \"password\": \"senha\"}"

$(document).ready(function(){

	// tem que ter pelo menos o ID
	var idCookie = getCookie('cookieId');
	console.log("DOCOK");
	// Para atualizar os dados tem que estar logado
	if(idCookie) {
		console.log(idCookie);
	}else{
		location.href = 'login.html';
	}

	$(document).ready(function(){
    var x = getCookie('cookieName');
    if (x) {
        document.getElementById("nomeNavBarra").textContent = x;
        // Esconde pede login
    }else{
    	location.href = 'login.html';
    }

});

});

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

	$('#botaoUpdate').on('click', function(e){
		e.preventDefault();
		console.log("ATUALIZANDO");
		//Vai atualizar os dados aqui
		//curl -X PUT "http://localhost:8080/dsaApp/user/1t8bTj5D" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"id\": \"1t8bTj5D\", \"name\": \"user07\", \"email\": \"user01@mail.com\", \"password\": \"asdfghjk\", \"exp\": 0, \"level\": 1, \"adminRights\": \"\"}"
	
		// INICIO EXEMPLO
		var reqid = 'http://localhost:8080/dsaApp/user/search/'+getCookie('cookieId');
		// Se foi completo solicitar o id

		var username = document.getElementById("textUser");
		var passuser = document.getElementById("textPass");
		var mailuser = document.getElementById("textMail");

		if(username.value.trim() == '' || passuser.value.trim() == '' || mailuser.value.trim() == ''){
			if(username.value.trim() == '') {
				shakeError("textUser",0);
			}

			if(passuser.value.trim() == '') {
				shakeError("textPass",0);
			}

			if(mailuser.value.trim() == '') {
				shakeError("textMail",0);
			}
			return;
		}

		$.getJSON(reqid, function(data){
			var User = {
				"id": getCookie('cookieId'),
				"name": document.getElementById("textUser").value,
				"email": document.getElementById("textMail").value,
				"password": document.getElementById("textPass").value,
				"exp": data.exp,
				"level": data.level,
				"adminRights": ""
			};

			// Faco o post aqui

			$.ajax({
				type: "PUT",
				url: 'http://localhost:8080/dsaApp/user/'+User.id,
				// The key needs to match your method's input parameter (case-sensitive).
				data: JSON.stringify(User),
				contentType: "application/json; charset=utf-8",
				dataType: "json",
				success: function(data) {
					console.log("Sucesso");
					console.log(data.status);
					alert("Try again later!");
				},
				complete: function(data) {
					console.log("Completo");
					// Faz login aqui
					var inputUser = document.getElementById("textUser").value;
					var inputPass = document.getElementById("textPass").value;

					var Credentials = {
						"name": User.name,
						"password": User.password
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
					        console.log(data.status);
					        setCookie('cookieToken',data.responseText,7);
					        setCookie('cookieName',Credentials.name,7);
					    	setCookie('cookieId',User.id,7);
					    	location.href = 'chat.html';
					    }
					});

					console.log(data.status);
				}
			});

		});

	});
});

// Se nao for null esta logado
function getCookie(name) {
    var nameEQ = name + "=";
    var ca = document.cookie.split(';');
    for(var i=0;i < ca.length;i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1,c.length);
        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
    }
    return null;
}

function setCookie(name,value,days) {
    var expires = "";
    if (days) {
        var date = new Date();
        date.setTime(date.getTime() + (days*24*60*60*1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + (value || "")  + expires + "; path=/";
}
