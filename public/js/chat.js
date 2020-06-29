window.vComent = []; // create an empty array
$(document).ready(function(){

	// Vai pedir as coisas
	window.pFun = true;
	if(window.pFun){
		firstFunction();
	}

	var x = getCookie('cookieName');
    if (x) {
    	document.getElementById("nomeNavBarra").textContent = x;
    	document.getElementById("acaoLogin").textContent = "Logout";
    	document.getElementById("blocoFazerLogin").style.display = "none";
        // Esconde pede login
    }else{
    	document.getElementById("acaoLogin").textContent = "Login";
    	document.getElementById("blocoComentar").style.display = "none";
        // Mostra texto fazer login para interagir
    }

	// pega o nome
});


function firstFunction(data){
	var staticUrl = 'http://localhost:8080/dsaApp/chat';
	nomeCookie = getCookie('cookieName');
	console.log("PRIMEIRA OK");
	$.getJSON(staticUrl, function(data){
	// do some asynchronous work
	// and when the asynchronous stuff is complete
		$(data).each(function(index, value){
			if (nomeCookie.toLowerCase() == value.username.toLowerCase()) {
				//var teste = montaCartao(value.content,value.username,value.receivedDate, value.id,"justify-content-end");
				classe = "justify-content-end";
			}else{
				//var teste = montaCartao(value.content,value.username,value.receivedDate, value.id,"justify-content-start");
				classe = "justify-content-start"
			}
			window.vComent.push({
				sconteudo: value.content,
				suser: value.username,
				sdata: value.receivedDate.substr(0, 9),
				sid: value.id,
				sexp: "",
				simg: "",
				semail: "",
				sclasse: classe
			});
		});
		secondFunction();
	});
}

		function secondFunction(){
			console.log("SEGUNDA OK");
			// call first function and pass in a callback function which
			// first function runs when it has completed
					window.vComent.forEach(function(part, index, theArray){
						var reqid = 'http://localhost:8080/dsaApp/user/'+theArray[index].suser;
						// Se foi completo solicitar o id
							$.getJSON(reqid, function(data){
								var staticUrl = 'http://localhost:8080/dsaApp/user/image/'+data.id;
								//console.log(staticUrl);
								$.get(staticUrl)
								.done(function() { 
									theArray[index].simg = staticUrl;
									theArray[index].semail = data.email;
									theArray[index].sexp = data.exp;
									theArray[index].suser = theArray[index].suser.toUpperCase();
									theArray[index].sdata = theArray[index].sdata.substr(0, 9);
									if(index == vComent.length-1){
										thirdFunction(theArray);
									}
								}).fail(function() { 
									theArray[index].simg = "img/sem_imagem.jpg";
									theArray[index].semail = data.email;
									theArray[index].sexp = data.exp;
									theArray[index].suser = theArray[index].suser.toUpperCase();
									theArray[index].sdata = theArray[index].sdata.substr(0, 9);
									if(index == vComent.length-1){
										thirdFunction(theArray);
									}
								})
							});
						});
		}

		function thirdFunction(theArray3){
			console.log("TERCEIRA OK");

			theArray3.forEach(function(item, index, theArray){
					console.log(theArray[index]);
					var composicao = '<div class="row ';
					composicao+=theArray[index].sclasse;
					composicao+=' conjuntoChatt"><div class="col-md-10 col-lg-8 col-sm-12 float-right"> <div class="card mb-4';
					composicao+=' "><div class="card-header"><div class="media flex-wrap w-100 align-items-center"> <img src="';
					composicao+= theArray[index].simg;
					composicao+='" class="d-block ui-w-40 rounded-circle" alt=""><div class="media-body ml-3"> <a href="javascript:void(0)" data-abc="true">';
					composicao+= theArray[index].suser;
					composicao+= '</a><div class="text-muted small">';
					composicao+= theArray[index].semail;
					composicao+= '</div></div><div class="text-muted small ml-3"><div>Exp <strong>';
					composicao+= theArray[index].sexp;
					composicao+= '</strong></div><div><strong>';
					composicao+= theArray[index].sdata;
					composicao+= '</strong></div></div></div></div><div class="card-body"><p>';
					composicao+= theArray[index].sconteudo;
					composicao+= '</p></div></div></div></div>';
					document.getElementById("DIVCARTAO").insertAdjacentHTML("beforeend", composicao);
					window.scrollTo(0,document.body.scrollHeight);
			});
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
	$('#botaoPost').on('click', function(e){
	e.preventDefault();

	// Vai conferir se esta vazio ou nao

	var textChat = document.getElementById("textPost");

	if(textChat.value.trim() == '') {
		shakeError("textPost",0);
		return;
	}

	var ReceivedMessage = {
		"username": getCookie('cookieName'),
		"content": document.getElementById("textPost").value
	};
	
	console.log(ReceivedMessage);
	
	//curl -X POST "http://localhost:8080/dsaApp/chat" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"username\": \"juninho\", \"content\": \"Mensagem 2...\"}"
	$.ajax({
		type: "POST",
		url: "http://localhost:8080/dsaApp/chat",
		// The key needs to match your method's input parameter (case-sensitive).
		data: JSON.stringify(ReceivedMessage),
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data) {
			console.log("Sucesso");
		},
		complete: function(data) {
			location.reload();
			console.log("Completo");
		}
	});

  }); // end click event handler

});
