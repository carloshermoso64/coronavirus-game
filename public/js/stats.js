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

    var staticUrl = 'http://localhost:8080/dsaApp/stats/userranking';
    var teste = false;
    var nameConfere = "Calculadora";
    var vetNivel = new Array();
    var vetExp = new Array();
    var nomeCookie = getCookie('cookieName');

    // pega o nome
    $.getJSON(staticUrl, function(data){
        $(data).each(function(index, value){
            switch(index) {
              case 0:
                var nome = "🥇 "+value.name;
                break;
              case 1:
                var nome = "🥈 "+value.name;
                break;
              case 2:
                var nome = "🥉 "+value.name;
                break;
              case data.length-1:
                var nome = "💩 "+value.name;
                break;
              default:
                var nome = value.name;
            }

            if (nomeCookie.toLowerCase() == value.name.toLowerCase()) {
                console.log(nomeCookie+" "+value.name);
                // compara aqui
                // vai colorir de rosa
                var record='<tr class="corUser"><td>'+(index+1)+"</td><td>"+nome+"</td><td>"+value.level+"</td><td>"+value.exp+"</td></tr>";
            }else{
                var record="<tr><td>"+(index+1)+"</td><td>"+nome+"</td><td>"+value.level+"</td><td>"+value.exp+"</td></tr>";
            }
            vetNivel.push(value.level);
            vetExp.push(value.exp);
        
            $("table").append(record);
        });
        if(!teste){
                teste=true;
                graficos(vetExp,"expChart","Exp");
                graficos(vetNivel,"levelChart","Level");
            }
    });
});

function graficos(vetNivel,name,nameSet) {
        //var a = ['1','1','1','2','2','3','4'];
        // Contar a quantidade de cada um do unique
        var counts = {};
        for (var i = 0; i < vetNivel.length; i++) {
            counts[vetNivel[i]] = 1 + (counts[vetNivel[i]] || 0);
        }

        var lItem = [];
        var lValues = [];
        for (var key in counts) {
            // check if the property/key is defined in the object itself, not in parent
            if (counts.hasOwnProperty(key)) {
                lItem.push(key);
                lValues.push(counts[key]);
                //console.log(key);
                //console.log(counts[key]);
            }
        }
        MostraGraficos(lItem,lValues,name,nameSet);
}

function MostraGraficos(label,valores,name,nameSet) {

            //var unique = Array.from(new Set(a));
        var ctx = document.getElementById(name).getContext('2d');
            var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'bar',
            responsive: true,
            // The data for our dataset
            data: {
                // O reduzido
                labels: label,
                datasets: [{
                    label: nameSet,
                    backgroundColor: '#95A0E8',
                    hoverBackgroundColor: '#A4AEF7',
                    borderColor: '#95A0E8',
                    data: valores
                }]
            },

            // Configuration options go here
            options: {

                legend: {
                 display: false,
                 labels: {
                      fontColor: '#FFFFFF'
                     }
                  },
                title: {
                    display: false
                },
                scales: {
                    yAxes: [{
                        scaleLabel: {
                            fontColor: '#FFFFFF',
                            display: true,
                            labelString: 'Users',
                        },
                        ticks: {
                            beginAtZero: true,
                            stepSize: 1,
                            fontColor: '#FFFFFF'
                        }
                    }],
                    xAxes: [{
                        scaleLabel: {
                            fontColor: '#FFFFFF',
                            display: true,
                            labelString: nameSet,
                        },
                        ticks: {
                            fontColor: '#FFFFFF'
                        }
                    }]
                }
            }
        });

}