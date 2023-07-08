function exibirAlertaSuccess(mensagem) {
    const alerta = $('<div/>', {
        'class': 'alert alert-success floating-alert',
        'text': mensagem
    });

    $('body').append(alerta);

    setTimeout(function () {
        alerta.remove();
    }, 3000);
}
function exibirAlertaErro(mensagem) {
    const alerta = $('<div/>', {
        'class': 'alert alert-danger floating-alert',
        'text': mensagem
    });

    $('body').append(alerta);

    setTimeout(function () {
        alerta.remove();
    }, 3000);
}