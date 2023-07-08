$(document).ready(function () {
    $('#loginForm').submit(function (event) {
        event.preventDefault(); // Impede o comportamento padrão de envio do formulário

        // Captura os valores de usuário e senha do formulário
        var username = $('#username').val();
        var password = $('#password').val();

        // Cria um objeto com os dados de autenticação
        var usuarioDTO = {
            email: username,
            senha: password
        };

        // Faz a requisição AJAX para o endpoint de autenticação
        login(usuarioDTO);
    });

    $('#login').validate({
        rules: {
            email: {
                required: true,
                email: true,
            },
            senha: {
                required: true,
                minlength: 5
            },
            terms: {
                required: true
            },
        },
        messages: {
            email: {
                required: "Insira um endereço de e-mail",
                email: "Por favor insira um endereço de e-mail válido"
            },
            senha: {
                required: "Forneça uma senha",
                minlength: "Sua senha deve ter pelo menos 5 caracteres"
            },
            terms: "Please accept our terms"
        },
        errorElement: 'span',
        errorPlacement: function (error, element) {
            error.addClass('invalid-feedback');
            element.closest('.form-group').append(error);
        },
        highlight: function (element, errorClass, validClass) {
            $(element).addClass('is-invalid');
        },
        unhighlight: function (element, errorClass, validClass) {
            $(element).removeClass('is-invalid');
        },
        submitHandler: function (form,event) {
            event.preventDefault();
            // Serializa os dados do formulário em um objeto
            var formData = $(form).serializeArray();
            var DADOS = {};

            // Converter o array de objetos para um objeto
            formData.forEach(function (input) {
                DADOS[input.name] = input.value;
            });

            // Resto do seu código...
            login(DADOS);


        }
    });
});

function login(dados) {
    EviarPOST("/login/autenticar", dados)
        .then(function (response) {
            return Promise.all([response.json(), response.headers.get('Authorization')]);
        })
        .then(function ([json, token]) {
            localStorage.setItem('token', token);
            localStorage.setItem('nome', json);
            console.log(json, token);
            window.location.href = "/";
            // Resto do seu código...
        })
        .catch(function (error) {
            console.log(error);
        });

}