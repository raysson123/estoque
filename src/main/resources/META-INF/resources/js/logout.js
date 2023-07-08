$("#logout").click(function (event) {
    event.preventDefault();

    $.ajax({
        url: '/login/sair',
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        success: function (response) {
            alert('Logout realizado com sucesso!');
            console.log(response);
            window.location.href = "/";
        },
        error: function (error) {
            alert('Ocorreu um erro ao realizar o logout.');
            console.log(error);
        }
    });
});