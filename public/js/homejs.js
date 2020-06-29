$(document).ready(function(){
    var x = getCookie('cookieName');
    if (x) {
        document.getElementById("nomeNavBarra").textContent = x;
        document.getElementById("acaoLogin").textContent = "Logout";
        // Esconde pede login
    }else{
        document.getElementById("acaoLogin").textContent = "Login";
        // Mostra texto fazer login para interagir
    }
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