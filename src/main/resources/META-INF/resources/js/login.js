$(document).ready(function () {

    $(".nav-link").click(function (e) {
        e.preventDefault();

        // Obtém o alvo do link clicado
        var target = $(this).attr("data-target");

        // Alterna entre os formulários de login e cadastro
        if (target === "login") {
            $("#login").show();
            $("#cadastro").hide();
            $(".nav-link[data-target='login']").addClass("active");
            $(".nav-link[data-target='cadastro']").removeClass("active");
        } else if (target === "cadastro") {
            $("#login").hide();
            $("#cadastro").show();
            $(".nav-link[data-target='login']").removeClass("active");
            $(".nav-link[data-target='cadastro']").addClass("active");
        }
    });

    $("#loginForm").submit(function (event) {
        event.preventDefault();

        var username = $('#username').val();
        var password = $('#password').val();

        var loginData = {
            email: username,
            senha: password
        };

        $.ajax({
            url: '/user/autenticar',
            type: 'POST',
            headers: {
                "Accept": "application/json",
                "Content-Type": "application/json"
            },
            data: JSON.stringify(loginData),
            success: function (response) {
                alert('Login realizado com sucesso!');
                console.log(response);
                localStorage.setItem('id', response.id);
                localStorage.setItem('nome', response.nomeUsuario);
                  window.location.href = "/";
            },
            error: function (error) {
                alert('Ocorreu um erro ao realizar o login.');
                console.log(error);
            }
        });
    });

    $('#cadastroForm').submit(function (event) {
        event.preventDefault();

        // Limpar mensagens de erro e estilos de campo inválido
        $('.form-group').removeClass('has-error');
        $('.help-block').remove();

        // Obter os valores dos campos
        var confirmarSenha = $('#confirmarSenha').val();
        var dados = {
            nome: $('#nome').val(),
            email: $('#email').val(),
            senha: $('#senha').val(),
            tipoUsuario: "ADMIN"
        };

        // Validar campos
        var isValid = true;

        if (dados.nome.trim() === '') {
            isValid = false;
            showValidationError('#nome', 'Campo obrigatório');
        }

        if (dados.email.trim() === '') {
            isValid = false;
            showValidationError('#email', 'Campo obrigatório');
        } else if (!validateEmail(dados.email)) {
            isValid = false;
            showValidationError('#email', 'E-mail inválido');
        }

        if (dados.senha.trim() === '') {
            isValid = false;
            showValidationError('#senha', 'Campo obrigatório');
        }

        if (confirmarSenha.trim() === '') {
            isValid = false;
            showValidationError('#confirmarSenha', 'Campo obrigatório');
        } else if (confirmarSenha !== dados.senha) {
            isValid = false;
            showValidationError('#confirmarSenha', 'As senhas não coincidem');
        }

        // Submeter o formulário se todos os campos forem válidos
        if (isValid) {
            $.ajax({
                url: "/user/salvar",
                method: "POST",
                dataType: "json",
                contentType: "application/json",
                data: JSON.stringify(dados),
                success: function (response) {
                    // Callback para tratar a resposta de sucesso
                    console.log("Solicitação enviada com sucesso!");
                    console.log(response);
                    alert(response);
                },
                error: function (xhr, status, error) {
                    // Callback para tratar a resposta de erro
                    console.log("Ocorreu um erro ao enviar a solicitação.");
                    console.log(error);
                    alert(error);
                }
            });
        }
    });

    // Função para validar o formato do e-mail usando regex
    function validateEmail(email) {
        var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    // Função auxiliar para exibir mensagem de erro de validação
    function showValidationError(fieldSelector, errorMessage) {
        $(fieldSelector)
            .closest('.form-group')
            .addClass('has-error')
            .after('<span class="help-block">' + errorMessage + '</span>');
    }
});
