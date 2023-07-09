$(document).ready(function () {

    $('#FormeC').validate({
        rules: {
            nomeCartegoria: {
                required: true,
            },


        },
        messages: {
            nomeCartegoria: "Digitar nome da Cartegoria",

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
            let formData = $('#FormeC').serializeArray();
            let dado = { };

            // Converter o array de objetos para um objeto
            formData.forEach(function (input) {
                dado[input.name] = input.value;
            });

            let dados = {
                nomeCategoria: dado.nomeCartegoria,

            };

            EviarPOST('/cartegoria/salvar', dados)
                .then(function (response) {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error("Ocorreu um erro ao enviar a solicitação.");
                    }
                })
                .then(function (response) {
                    exibirAlertaSuccess(response.mensagem);
                    console.log(response);
                    listTabelas();
                })
                .catch(function (error) {
                    // Callback para tratar a resposta de erro
                    console.log("Ocorreu um erro ao enviar a solicitação.");
                    console.log(error);
                    exibirAlertaErro(error.mensagem);
                    console.error(error);
                });
        }
    });
    $('#FormeA').validate({
        rules: {
            nomeCartegoriaAtul: {
                required: true,
            },

        },
        messages: {
            nomeCartegoriaAtul: "Digitar nome do Cartegoria",
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


            atualizar("/cartegoria/atulizar/" + dados.id, dados)
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


    $('#example2').on('click', '.excluir', function () {
        let rowId = $(this).attr('data-row-id');
        excluir('/cartegoria/excluir/' + rowId )
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
        let rowId = $(this).attr('data-row-id');
        $('#example2').DataTable().rows().every(function () {
            let data = this.data();
            //console.log('Dados da linha selecionada:', data);
            if (data.id == rowId) {
                $('#cartegoriaAtul').val(data.nomeCategoria);
                $('#editarId').val(data.id);
                //console.log('Dados da linha selecionada:', data);
                return false; // Parar a iteração
            }
        });

        // Obter o ID da linha selecionada


        // Faça o que deseja com o ID da linha selecionada
    });


    //Money Euro

    listTabelas();

});
function listTabelas() {
    listarTudo('/cartegoria/list')
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
                        data: 'nomeCategoria', title: 'Nome de Categoria'
                    },
                    {
                        data: 'possuiProduto',
                        title: 'Possui Prudos',
                        render: function (data) {
                            return data ? 'Sim' : 'Não';
                        }
                    },
                    {
                        data: null,
                        title: 'Excluir',
                        render: function (data, type, row) {
                            if (!row.possuiProduto) {

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