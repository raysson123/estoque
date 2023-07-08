$(document).ready(function () {

    $('#FormeC').validate({
        rules: {
            nomeFornecedor: {
                required: true,
            },
            nomeDeContato: {
                required: true,

            },
            numeroDeTelefone: {
                required: true,
                telefone: true
            },
            numeroEmail: {
                required: true,
                email: true,
            },
            numeroCNPJ: {
                required: true,
                cnpj: true
            },
        },
        messages: {
            nomeFornecedor: "Digitar nome do Fornecedor",
            nomeDeContato: "Digitar nome do Contato",
            numeroDeTelefone:{
                required: "Digite o número de telefone",
                telefone: "Digite um número de telefone válido"
            } ,
            numeroEmail: {
                required: "Insira um endereço de e-mail",
                email: "Por favor insira um endereço de e-mail válido"
            },

            numeroCNPJ: {
                required: "Digite o número do CNPJ",
                cnpj: "Digite um CNPJ válido"
            },
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
        submitHandler: function () {
            let formData = $('#FormeC').serializeArray();
            let DADOS = { };

            // Converter o array de objetos para um objeto
            formData.forEach(function (input) {
                DADOS[input.name] = input.value;
            });

            EviarPOST('/fornecedo/cadastros', DADOS)
                .then(function (response) {
                    exibirAlertaSuccess(response.mensagem);
                    console.log(response);
                    listTabelas();
                })
                .catch(function (error) {
                    exibirAlertaErro(error.mensagem);
                    console.error(error);
                });
        }
    });
    $('#FormeA').validate({
        rules: {
            nomeFornecedorAtul: {
                required: true,
            },
            nomeDeContatoAtul: {
                required: true,

            },
            numeroDeTelefoneAtul: {
                required: true,
                telefone: true,
            },
            numeroEmailAtul: {
                required: true,
                email: true,
            },
            numeroCNPJAtul: {
                required: true,
                cnpj: true
            },
        },
        messages: {
            nomeFornecedorAtul: "Digitar nome do Fornecedor",
            nomeDeContatoAtul: "Digitar nome do Contato",
            numeroDeTelefoneAtul: "Digitar o Numero de Telefone",
            numeroEmailAtul: {
                required: "Insira um endereço de e-mail",
                email: "Por favor insira um endereço de e-mail válido"
            },

            numeroCNPJAtul: {
                required: "Digite o número do CNPJ",
                cnpj: "Digite um CNPJ válido"
            },
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
        submitHandler: function (form, event) {
            event.preventDefault(); // Impede o comportamento padrão de envio do formulário

            let formData = $(form).serializeArray();
            let dados = { };

            // Converter o array de objetos para um objeto
            formData.forEach(function (input) {
                dados[input.name] = input.value;
            });


            atualizar("/fornecedo/atulizar/" + dados.id, dados)
                .then(function(response) {
                    listTabelas();
                    exibirAlertaSuccess(response.mensagem);
                    $('#modal-lg').modal('hide');
                })
                .catch(function(error) {
                    console.error(error);
                });
        }
    });
    // Adicione um método personalizado para validar o formato do telefone
    $.validator.addMethod("telefone", function (value, element) {
        // Remova todos os caracteres não numéricos do valor do campo
        var numeroTelefone = value.replace(/\D/g, '');

        // Verifique se o número de telefone possui 10 dígitos
        return this.optional(element) || numeroTelefone.length === 11;
    }, "Digite um número de telefone válido");
// Adicione um método personalizado para validar o formato do CNPJ
    $.validator.addMethod("cnpj", function (value, element) {
        // Remova todos os caracteres não numéricos do valor do campo
        var cnpj = value.replace(/\D/g, '');

        // Verifique se o CNPJ possui 14 dígitos
        if (cnpj.length !== 14) {
            return false;
        }

        // Verifique se todos os dígitos são iguais (ex.: 00.000.000/0000-00)
        if (/^(\d)\1+$/.test(cnpj)) {
            return false;
        }

        // Validação do dígito verificador
        var size = cnpj.length - 2;
        var numbers = cnpj.substring(0, size);
        var digits = cnpj.substring(size);
        var sum = 0;
        var pos = size - 7;

        for (var i = size; i >= 1; i--) {
            sum += numbers.charAt(size - i) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }

        var result = sum % 11 < 2 ? 0 : 11 - (sum % 11);
        if (result !== parseInt(digits.charAt(0), 10)) {
            return false;
        }

        size += 1;
        numbers = cnpj.substring(0, size);
        sum = 0;
        pos = size - 7;

        for (var j = size; j >= 1; j--) {
            sum += numbers.charAt(size - j) * pos--;
            if (pos < 2) {
                pos = 9;
            }
        }

        result = sum % 11 < 2 ? 0 : 11 - (sum % 11);
        if (result !== parseInt(digits.charAt(1), 10)) {
            return false;
        }

        return true;
    }, "Digite um CNPJ válido");

    $('#example2').on('click', '.excluir', function () {
        let rowId = $(this).attr('data-row-id');
        excluir('/fornecedo/excluir/' + rowId )
            .then(function (response) {
                listTabelas();
                exibirAlertaSuccess(response.mensagem);
            })
            .catch(function (error) {
                console.log(error);
            });

        // Faça o que deseja com o ID da linha selecionada
    });

    // Manipulador de evento para o botão "Editar"
    $('#example2').on('click', '.editar', function () {
        var rowId = $(this).attr('data-row-id');
        $('#example2').DataTable().rows().every(function () {
            var data = this.data();
            //console.log('Dados da linha selecionada:', data);
            if (data.id == rowId) {
                $('#nomeFornecedorAtul').val(data.nomeFornecedor);
                $('#nomeDeContatoAtul').val(data.nomeDeContato);
                $('#numeroDeTelefoneAtul').val(data.numeroDeTelefone);
                $('#numeroCNPJAtul').val(data.numeroCNPJ);
                $('#numeroEmailAtul').val(data.numeroEmail);
                $('#editarId').val(data.id);
                //console.log('Dados da linha selecionada:', data);
                return false; // Parar a iteração
            }
        });

        // Obter o ID da linha selecionada


        // Faça o que deseja com o ID da linha selecionada
    });


    //Money Euro
    $('[data-mask]').inputmask()
    listTabelas();

});

function listTabelas() {
    listarTudo('/fornecedo/list')
        .then(function (response) {
            // preencherTabela(response);

            if ($.fn.DataTable.isDataTable('#example2')) {
                $('#example2').DataTable().destroy();
            }

            $('#example2').DataTable({
                destroy: true,
                "paging": true,
                "lengthChange": false,
                "searching": false,
                "ordering": true,
                "info": true,
                "autoWidth": false,
                "responsive": true,
                displayLength: 3,
                data: response,
                columns: [
                    {
                        data: 'id', title: 'ID'
                    },
                    {
                        data: 'nomeDeContato', title: 'Nome de Contato'
                    },
                    {
                        data: 'nomeFornecedor', title: 'Nome do Fornecedor'
                    },
                    {
                        data: 'numeroCNPJ', title: 'Número CNPJ'
                    },
                    {
                        data: 'numeroDeTelefone', title: 'Número de Telefone'
                    },
                    {
                        data: 'numeroEmail', title: 'Email'
                    },
                    {
                        data: 'posueprudos',
                        title: 'Possui Prudos',
                        render: function (data) {
                            return data ? 'Sim' : 'Não';
                        }
                    },
                    {
                        data: null,
                        title: 'Excluir',
                        render: function (data, type, row) {
                            if (!row.posueprudos) {

                                return "<button class='btn btn-danger excluir'data-row-id='" + data.id + "'>" +
                                    "<i class='fa-solid fa-trash-can'></i></button>";
                            }
                            return "";
                        }
                    },
                    {
                        data: null,
                        title: 'Editar',
                        render: function (data, type, row) {
                            return "<button class='btn btn-primary editar' data-row-id='" + data.id + "' data-toggle='modal' data-target='#modal-lg'>" +
                                "<i class='fa-solid fa-pen-to-square'></i></button>";
                        }
                    }
                ]
            });
        })
        .catch(function (error) {
            console.error(error);
        });
}