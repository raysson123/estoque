$(document).ready(function () {

    // Recuperar os valores do localStorage
    var userName = localStorage.getItem('nome');

    if (userName.length > 14) {
        userName = userName.substring(0, 14) + " ...";
    }
    // Definir os valores nos campos usando jQuery
    $('#userName').text(userName);
    $('#sair').click(function (e) {
        e.preventDefault(); // Impede o comportamento padrão do link

        EviarPOST("/user/sair", userName)
            .then(function () {
                console.log("Logout realizado com sucesso");
                // Redirecionar para a página inicial
                window.location.href = "/";
            })
            .catch(function (error) {
                console.log("Erro ao realizar logout:", error);
            });

        // Exemplo simplificado

    });


});