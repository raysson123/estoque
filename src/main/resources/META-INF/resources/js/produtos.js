// tudo para funcinar  atulizar deletar  cadatra  elitar produtos{
$(document).ready(function () {

    list();
// Função para enviar o formulário de cadastro
    $('#formularioProduto').submit(function (event) {
        event.preventDefault();
        let nomeInput = $('#nome');
        let categoriaSelect = $('#categoria');
        let descricaoTextarea = $('#descricao');

        // Verificar se todos os campos estão preenchidos
        if (nomeInput.val() === '' || categoriaSelect.val() === '' || descricaoTextarea.val() === '') {
            alert('Por favor, preencha todos os campos!');
        } else {
            let dados = {
                nomeProdutos: nomeInput.val(),
                nomeCategoria: categoriaSelect.val(),
                descricao: descricaoTextarea.val()
            };

            // Chamar a função para enviar o cadastro
            enviarCadastro(dados);
        }
    });

    $('#formularioProdutoatulizar').submit(function (event) {
        event.preventDefault();
        let nomeInput = $('#editarNome');
        let categoriaSelect = $('#editarCategoria');
        let descricaoTextarea = $('#editarDescricao');
        let editarId = $('#editarId');

        // Verificar se todos os campos estão preenchidos
        if (nomeInput.val() === '' || categoriaSelect.val() === '' || descricaoTextarea.val() === '') {
            alert('Por favor, preencha todos os campos!');
        } else {
            let dados = {
                nomeProdutos: nomeInput.val(),
                nomeCategoria: categoriaSelect.val(),
                descricao: descricaoTextarea.val(),
                id: editarId.val()
            };

            // Chamar a função para enviar a atualização
            enviarAtualizar(dados);
        }
    });
    $("#abrirModalCategoria").click(function () {
        tabelacategoria();
        $("#categoriaCadastro").modal("show");
    });
    listarcategoria();
    $('#imprimirConsole').click(function () {
        let editarCategoria = $('input#editarCategoria');

        let dados = {
            nomeCategoria: editarCategoria.val(),

        };
        enviarCategoria(dados)
        tabelacategoria();
    });

});

// Função para enviar o cadastro via requisição AJAX
function enviarCadastro(dados) {
    exibirPreloader(true); // Exibir o preloader antes de enviar a requisição

    fetch("/produtos/cadastros", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Ocorreu um erro ao enviar a solicitação.");
            }
        })
        .then(function (data) {
            // Callback para tratar a resposta de sucesso
            data.mensagem = undefined;
            console.log("Solicitação enviada com sucesso!");
            console.log(data);
            exibirAlerta('Solicitação enviada com sucesso!', 'success');
            $('input').val('');

            // Chamar a função para preencher os dados da tabela após o cadastro
            list();
        })
        .catch(function (error) {
            // Callback para tratar a resposta de erro
            console.log("Ocorreu um erro ao enviar a solicitação.");
            console.log(error);
            exibirAlerta('Solicitação enviada com sucesso!', 'danger');
        })
        .finally(function () {
            // Ocultar o preloader após a finalização da requisição
            exibirPreloader(false);
        });
}

function enviarCategoria(dados) {
    //exibirPreloader(true); // Exibir o preloader antes de enviar a requisição

    fetch("/cartegoria/salvar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Ocorreu um erro ao enviar a solicitação.");
            }
        })
        .then(function (data) {
            // Callback para tratar a resposta de sucesso
            data.mensagem = undefined;
            console.log("Solicitação enviada com sucesso!");

            exibirAlerta('Solicitação enviada com sucesso!', 'success');
            $('input').val('');

            // Chamar a função para preencher os dados da tabela após o cadastro
            list();
        })
        .catch(function (error) {
            // Callback para tratar a resposta de erro
            console.log("Ocorreu um erro ao enviar a solicitação.");
            console.log(error);
            exibirAlerta('Solicitação enviada com sucesso!', 'danger');
        })
        .finally(function () {
            // Ocultar o preloader após a finalização da requisição
            exibirPreloader(false);
        });
}

// Função para enviar a atualização via requisição AJAX
function enviarAtualizar(dados) {
    exibirPreloader(true); // Exibir o preloader antes de enviar a requisição

    fetch(`/produtos/atulizar/${dados.id}`, {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(dados)
    })
        .then(function (response) {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Ocorreu um erro ao enviar a solicitação.");
            }
        })
        .then(function (data) {
            // Callback para tratar a resposta de sucesso
            data.mensagem = undefined;
            console.log("Solicitação enviada com sucesso!");
            console.log(data);
            exibirAlerta('Solicitação enviada com sucesso!', 'success');
            $('input').val('');


            // Fechar a modal
            $('#modalEditar').modal('hide');


            // Chamar a função para preencher os dados da tabela após a atualização
            list();
        })
        .catch(function (error) {
            // Callback para tratar a resposta de erro
            console.log("Ocorreu um erro ao enviar a solicitação.");
            console.log(error);
            alert(error.message);
            exibirAlerta('Solicitação enviada com sucesso!', 'danger');
        })
        .finally(function () {
            // Ocultar o preloader após a finalização da requisição
            exibirPreloader(false);
        });
}

// Função para preencher os dados da tabela
function list() {
    exibirPreloader(true); // Exibir o preloader antes de fazer a requisição

    const tableBody = $('table#produto tbody');

    fetch('/produtos/list', {
        method: 'GET',
    })
        .then(response => response.json())
        .then(data => {
            // Limpar os dados da tabela anteriormente preenchida
            tableBody.empty();

            data.forEach(function (item) {
                const newRow = $('<tr>');
                newRow.attr('data-id', item.id); // Adiciona o atributo 'data-id' com o ID do item
                let possuiEstoque = item.posueEstoque;
                newRow.append($('<td>').text(item.nomeProdutos));
                newRow.append($('<td>').text(item.descricao));
                newRow.append($('<td>').text(item.nomeCategoria));
                newRow.append($('<td>').text(possuiEstoque ? 'Sim' : 'Não'));

                // Botão de exclusão
                if (!possuiEstoque) {
                    let deleteButton = $('<button>')
                        .addClass('btn btn-danger btn-sm mr-1')
                        .data('id', item.id) // Armazena o ID do item como atributo de dados
                        .click(deletarItem); // Associa o evento de clique à função deletarItem
                    let deleteIcon = $('<i>').addClass('fas fa-trash-alt'); // Ícone de exclusão
                    deleteButton.prepend(deleteIcon);
                    newRow.append($('<td>').append(deleteButton));
                } else {
                    newRow.append($('<td>')); // Espaço vazio caso não haja estoque
                }

                // Botão de edição
                let editButton = $('<button>')
                    .addClass('btn btn-primary btn-sm')
                    .data('id', item.id)
                    .click(editarItem); // Associa o evento de clique à função editarItem
                let editIcon = $('<i>').addClass('fas fa-pen'); // Ícone de edição
                editButton.append(editIcon);
                newRow.append($('<td>').append(editButton));

                tableBody.append(newRow);
            });
        })
        .catch(error => {
            console.error('Ocorreu um erro:', error);
        })
        .finally(function () {
            // Ocultar o preloader após o preenchimento da tabela
            exibirPreloader(false);
        });
}

// Função para excluir um item
function deletarItem() {
    exibirPreloader(true); // Exibir o preloader antes de enviar a requisição
    let id = $(this).data('id'); // Obtém o ID do item do atributo de dados do botão
    // Implemente a lógica para excluir o item com o ID especificado
    console.log('Excluir item com ID:', id);

    fetch(`/produtos/excluir/${id}`, {method: 'DELETE'})
        .then(response => response.json())
        .then(response => {
            console.log(response);
            exibirAlerta('Operação concluída com êxito', 'success');
            // Aqui você pode atualizar a tabela ou a lista de itens, se necessário
            list();
        })
        .catch(err => {
            console.error(err);
            exibirAlerta('Ocorreu um erro ao excluir o produto', 'danger');

        }).finally(function () {
        // Ocultar o preloader após a finalização da requisição
        exibirPreloader(false);
    });
}

// Função para editar um item
function editarItem() {
    let id = $(this).data('id'); // Obtém o ID do item do atributo de dados do botão
    // Implemente a lógica para editar o item com o ID especificado

    // Encontra a linha da tabela com o ID correspondente
    let linha = $('table tbody tr[data-id="' + id + '"]');
    linha.attr('data-id');
    // Preencher a modal com os dados do objeto linhaObj
    $('#editarNome').val(linha.find('td:eq(0)').text());
    $('#editarCategoria').val(linha.find('td:eq(2)').text());
    $('#editarDescricao').val(linha.find('td:eq(1)').text()); // Corrigir o método val()
    $('#editarId').val(id); // Corrigir o método val()

    // Abrir a modal
    $('#modalEditar').modal('show');
}

// Função para exibir ou ocultar o preloader
function exibirPreloader(exibir) {
    if (exibir) {
        $('#preloader').show();
        //  $('table#produto').hide();
    } else {
        $('#preloader').hide();
        $('table#produto').show();
    }
}

function exibirAlerta(mensagem, tipo) {
    const alerta = $('<div/>', {
        'class': 'alert alert-' + tipo,
        'text': mensagem
    });

    $('body').append(alerta);

    setTimeout(function () {
        alerta.remove();
    }, 3000);
}

///} finalisare fuçoes para produto
// fuçoes para cadtraer categoria listar e deletar
function listarCategorias() {
    return fetch('/cartegoria/list', {method: 'GET'})
        .then(response => response.json())
        .then(response => {

            return response; // Retorna o array de objetos
        })
        .catch(err => {
            console.error(err);
            throw new Error('Ocorreu um erro ao listar as categorias.');
        });
}

function listarcategoria() {
    listarCategorias()
        .then(function (categorias) {
            // Limpar o select antes de preenchê-lo novamente
            $('select#categoria').each(function () {
                $('option:not(:first)', this).remove();
            });
            // Preencher o select com as categorias
            categorias.forEach(function (categoria) {

                const option = $('<option>').val(categoria.nomeCategoria).text(categoria.nomeCategoria);
                $('select#categoria').append(option);
            });

        })
        .catch(function (error) {
            // Tratar o erro ocorrido durante a obtenção das categorias
            console.error(error);
            // Exibir uma mensagem de erro para o usuário, se necessário
        });
    listarCategorias()
        .then(function (categorias) {
            // Limpar o select antes de preenchê-lo novamente

            $('select#editarCategoria').each(function () {
                $('option:not(:first)', this).remove();
            });
            // Preencher o select com as categorias
            categorias.forEach(function (categoria) {
                const option = $('<option>').val(categoria.nomeCategoria).text(categoria.nomeCategoria);
                $('select#editarCategoria').append(option);
            });

        })
        .catch(function (error) {
            // Tratar o erro ocorrido durante a obtenção das categorias
            console.error(error);
            // Exibir uma mensagem de erro para o usuário, se necessário
        });
}



function tabelacategoria() {
    listarCategorias()
        .then(function (categorias) {
            let tbody = $('table#categoriatabela tbody');
            tbody.empty();

            categorias.forEach(function (categoria) {
                let row = $('<tr>');
                let idCell = $('<td>').text(categoria.id);
                let nomeCategoriaCell = $('<td>').text(categoria.nomeCategoria);
                let possuiProdutoCell = $('<td>').text(categoria.possuiProduto ? 'Sim' : 'Não');
                let deleteButtontd = $('<td>');
                let editButtontd = $('<td>');

                if (!categoria.possuiProduto) {
                    // Botão de exclusão
                    let deleteButton = $('<button>')
                        .addClass('btn btn-danger btn-sm mr-1')
                        .data('id', categoria.id)
                        .click(function () {
                            let id = $(this).data('id');
                            deletarcategoria(id);
                        });
                    let deleteIcon = $('<i>').addClass('fas fa-trash-alt');
                    deleteButton.append(deleteIcon);
                    deleteButtontd.append(deleteButton);
                }

                // Botão de edição
                let editButton = $('<button>')
                    .addClass('btn btn-primary btn-sm')
                    .data('id', categoria.id)
                    .click(function () {
                        let id = $(this).data('id');
                        editarCategoria(id);
                    });
                let editIcon = $('<i>').addClass('fas fa-pen');
                editButton.append(editIcon);
                editButtontd.append(editButton);

                row.append(idCell, nomeCategoriaCell, possuiProdutoCell, deleteButtontd, editButtontd);
                tbody.append(row);
            });
        })
        .catch(function (error) {
            console.error(error);
            // Exibir uma mensagem de erro para o usuário, se necessário
        });
}

function editarCategoria(id){
    console.log(id);
}
function deletarcategoria(id){
    console.log(id);
    fetch(`/cartegoria/excluir/${id}`, {method: 'DELETE'})
        .then(response => response.json())
        .then(response => {
            console.log(response);
            exibirAlerta('Operação concluída com êxito', 'success');
            // Aqui você pode atualizar a tabela ou a lista de itens, se necessário
            tabelacategoria();
            listarcategoria();
        })
        .catch(err => {
            console.error(err);
            exibirAlerta('Ocorreu um erro ao excluir o produto', 'danger');

        }).finally(function () {
        // Ocultar o preloader após a finalização da requisição
        exibirPreloader(false);
    });



}

