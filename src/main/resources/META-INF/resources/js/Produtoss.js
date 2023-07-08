$(document).ready(function () {

    $('#FormeC').validate({
        rules: {
            nomeProdutos: {
                required: true,
            },
            categoria: {
                required: true,

            },

        },
        messages: {
            nome: "Digitar nome do produto",
            categoria: "Selesine uma Cartegoria",

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
            $('input').val('');
            $('#descricaoAtul').val("");
            // Converter o array de objetos para um objeto
            formData.forEach(function (input) {
                DADOS[input.name] = input.value;
            });

            EviarPOST('/produtos/cadastros', DADOS)
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


            atualizar("/produtos/atulizar/" + dados.id, dados)
                .then(function (response) {
                    listTabelas();
                    exibirAlertaSuccess(response.mensagem);
                    $('#modal-lg').modal('hide');
                })
                .catch(function (error) {
                    console.error(error);
                });
        }
    });


    $('#example2').on('click', '.excluir', function () {
        let rowId = $(this).attr('data-row-id');
        excluir('/produtos/excluir/' + rowId)
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

            if (data.id == rowId) {
                console.log('Dados da linha selecionada:', data);
                $('#nomeProdutosAtul').val(data.nomeProdutos);
                $('#categoriaAtul').val(data.nomeCategoria);
                $('#descricaoAtul').val(data.descricao);

                $('#editarId').val(data.id);
                //console.log('Dados da linha selecionada:', data);
                return false; // Parar a iteração
            }
        });

        // Obter o ID da linha selecionada


        // Faça o que deseja com o ID da linha selecionada
    });

    listarCategorias();
    listTabelas();

});

function listTabelas() {
    listarTudo('/produtos/list')
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
                displayLength: 5,
                data: response,
                columns: [
                    {
                        data: 'id', title: 'ID'
                    },
                    {
                        data: 'nomeProdutos', title: 'Nome do Produto'
                    },
                    {
                        data: 'nomeCategoria', title: 'Categoria'
                    },
                    {
                        data: 'descricao', title: 'Descrição'
                    },

                    {
                        data: 'posueEstoque',
                        title: 'Possui Prudos',
                        render: function (data) {
                            return data ? 'Sim' : 'Não';
                        }
                    },
                    {
                        data: null,
                        title: 'Excluir',
                        render: function (data, type, row) {
                            if (!row.posueEstoque) {

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

function listarCategorias() {
    listarTudo('/cartegoria/list')
        .then(function (response) {
            // Preencher o select com as categorias
            response.forEach(function (categoria) {
                $('<option>', {
                    value: categoria.nomeCategoria,
                    text: categoria.nomeCategoria
                }).appendTo('select#categoria');
                $('<option>', {
                    value: categoria.nomeCategoria,
                    text: categoria.nomeCategoria
                }).appendTo('select#categoriaAtul');
            });
        })
        .catch(function (error) {
            console.error(error);
        });

}