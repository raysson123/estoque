
$(document).ready(function () {
    carregarObjetos(); // Carrega os fornecedores e produtos ao carregar a página

    $("#adicionarLinhaBtn").on("click", function () {
        capturarDadosValidar();
        // Adiciona uma nova linha na tabela ao clicar no botão "Adicionar Linha"
    });

    $("#capturarDadosBtn").on("click", function () {
        capturarDados(); // Captura os dados preenchidos nas linhas da tabela ao clicar no botão "Capturar Dados"
    });
});

var objetos = {}; // Variável para armazenar os objetos (fornecedores e produtos)
let count = 0; // Contador para os produtos adicionados dinamicamente

// Carrega os fornecedores na lista suspensa
function carregarFornecedor() {
    objetos.fornecedores.forEach(function (fornecedor) {
        var option = $("<option>").attr("value", fornecedor.id).text(fornecedor.nomeFornecedor);
        $('#fornecedor').append(option);
    });
}

// Carrega os fornecedores e produtos após a página ser carregada
function carregarObjetos() {
    Promise.all([listarTudo('/fornecedo/list'), listarTudo('/produtos/list')])
        .then(function (responses) {
            objetos.fornecedores = responses[0]; // Armazena os fornecedores na propriedade "fornecedores" de objetos
            objetos.produtos = responses[1]; // Armazena os produtos na propriedade "produtos" de objetos
            carregarFornecedor();
            adicionarProduto(); // Adiciona a primeira linha na tabela após carregar os fornecedores e produtos
        })
        .catch(function (error) {
            console.error(error);
        });
}

// Adiciona uma nova linha na tabela de produtos
function adicionarProduto() {
    let newCard = `
        <div class="card card-primary" data-id="${count}">
            <div class="card-header">
             <h4 class="card-title w-100">
              <a class="d-block w-100" data-toggle="collapse" href="#collapse${count}">
                
                        Produto ${count + 1}
                    </a>
                </h4>
            </div>
            <div id="collapse${count}" class=" collapse show" data-bs-parent="#accordion">
                <div class="card-body">
                    <div class="form-group input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Produto:</span>
                        <select class="produtor form-control form-select tab-link" id="produto${count}" aria-label="Username"
                            aria-describedby="basic-addon1" name="produto[]">
                            <option disabled="" selected="">Selecione uma categoria</option>
                            <option value="1">raysson</option>
                            <option value="2">test1</option>
                            <option value="3">ddr3</option>
                            <option value="9">fdfdsfsd</option>
                        </select>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <div class="form-group input-group mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-default">Modelo:</span>
                                <input type="text" class="form-control tab-link" id="modelo${count}" aria-label="Sizing example input"
                                    aria-describedby="inputGroup-sizing-default" name="modelo[]">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group input-group mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-default">Nº Série:</span>
                                <input type="text" class="form-control tab-link" aria-label="Sizing example input"
                                    aria-describedby="inputGroup-sizing-default" id="numeroSerie${count}" name="numeroSerie[]">
                            </div>
                        </div>
                    </div>
                    <div class="row g-3">
                        <div class="col">
                            <div class="form-group input-group mb-3">
                                <span class="input-group-text">R$</span>
                                <input type="text" class="form-control valor tab-link" id="valor${count}" aria-label="Amount">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group input-group mb-3">
                                <span class="input-group-text" id="inputGroup-sizing-default">Quantidade:</span>
                                <input type="number" class="form-control tab-link" aria-label="Sizing example input"
                                    aria-describedby="inputGroup-sizing-default" id="quantidade${count}" name="quantidade[]" min="1"
                                    oninput="validarQuantidade(this)">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    `;

    const newRow = $('<tr>')
        .attr('id', 'produtoLinha' + count)
        .addClass('dadosProdutos')
        .attr('data-id', count);

    // Cria as células da linha da tabela
    const produtoValor = $('<td>').attr('id', 'produtoValor' + count);
    const modeloValor = $('<td>').attr('id', 'modeloValor' + count);
    const numeroSerieValor = $('<td>').attr('id', 'numeroSerieValor' + count);
    const valorP = $('<td>').attr('id', 'valorP' + count);
    const quantidade = $('<td>').attr('id', 'quantidadeT' + count);

    newRow.append(produtoValor, modeloValor, numeroSerieValor, valorP, quantidade);
    $('#tabelaProdutos').append(newRow);
    $('#accordion').append(newCard);

    $('.valor').maskMoney({
        prefix: 'R$ ',
        allowNegative: false,
        thousands: '.',
        decimal: ','
    });
    updateProductValues();
    count++;
}

// Atualiza os valores da linha da tabela quando os campos são alterados
function updateProductValues() {
    $('.tab-link').on('keyup change', function () {
        let rowId = $(this).closest('.card').data('id');
        const produto = $('#produto' + rowId).val();
        const modelo = $('#modelo' + rowId).val();
        const numeroSerie = $('#numeroSerie' + rowId).val();
        const valor = $('#valor' + rowId).val();
        const quantidade = $('#quantidade' + rowId).val();

        $('#produtoValor' + rowId).text(produto);
        $('#modeloValor' + rowId).text(modelo);
        $('#numeroSerieValor' + rowId).text(numeroSerie);
        $('#valorP' + rowId).text(valor);
        $('#quantidadeT' + rowId).text(quantidade);
    });
}

// Valida se a quantidade é maior que zero
function validarQuantidade(input) {
    if (input.value <= 0) {
        input.value = 1; // Limpa o valor se for menor ou igual a 0
    }
}

// Captura os dados preenchidos nas linhas da tabela
function capturarDados() {
    var dadosCapturados = [];

    let fornecedor = $('#fornecedor').val();
    let data = $('#data_aqui').val();

    // Verifica se fornecedor e data foram selecionados
    if (fornecedor === "" || data === "") {
        exibirAlertaErro("Por favor, selecione um fornecedor e forneça a data");
        return;
    }

    // Verifica se existem campos vazios
    if (validarCamposVazios()) {
        exibirAlertaErro("Por favor, preencha todos os campos antes de prosseguir.");
        return;
    }

    // Itera sobre cada linha de dadosProdutos
    $(".dadosProdutos").each(function () {
        let linha = $(this);
        let rowId = linha.data('id');
        let produto = linha.find('#produtoValor' + rowId).text();
        let modelo = linha.find("#modeloValor" + rowId).text();
        let numeroSerie = linha.find("#numeroSerieValor" + rowId).text();
        let valor = converterStringParaBigDecimal(linha.find("#valorP" + rowId).text());
        let quantidade = linha.find("#quantidadeT" + rowId).text();

        // Cria um objeto com os dados da linha atual
        let dadosLinha = {
            fornecedorId: fornecedor,
            produtosId: produto,
            modelo: modelo,
            numeroDeSerie: numeroSerie,
            precoDeCompra: valor,
            dataDeAquisicao: data,
            quantidadeDisponivel: quantidade
        };

        dadosCapturados.push(dadosLinha);
    });
    EviarPOST("/dadosprodutos/cadatralist",dadosCapturados)
        .then(function (responses) {
          console.log(responses) // Adiciona a primeira linha na tabela após carregar os fornecedores e produtos
            // Recarregar a página atual usando jQuery
            // Recarregar uma nova URL
            window.location.href = responses.url;

        })
        .catch(function (error) {
            console.error(error);
        });

}

// Valida se existem campos vazios nas linhas da tabela
function validarCamposVazios() {
    let camposVazios = false;

    $(".dadosProdutos").each(function () {
        let rowId = $(this).data('id');
        let linha = $(this);
        let modelo = linha.find("#modeloValor" + rowId).text();
        let numeroSerie = linha.find("#numeroSerieValor" + rowId).text();
        let valor = linha.find("#valorP" + rowId).text();
        let quantidade = linha.find("#quantidadeT" + rowId).text();
        if (modelo === "" || numeroSerie === "" || valor === "" || quantidade === "") {
            camposVazios = true;
            return false; // Encerra o loop caso haja campos vazios
        }
    });

    return camposVazios;
}

// Captura os dados preenchidos nas linhas da tabela e valida se existem campos vazios antes de prosseguir
function capturarDadosValidar() {
    if (validarCamposVazios()) {
        exibirAlertaErro("Por favor, preencha todos os campos antes de prosseguir.")

        return;
    }
    $('#collapse' + (count - 1)).collapse('hide');
    adicionarProduto();

    // Resto do código para capturar os dados...
}

function converterStringParaBigDecimal(valor) {
    // Remover o símbolo de moeda e quaisquer outros caracteres não numéricos
    valor = valor.replace(/[^\d.,]/g, '');

    // Substituir vírgulas por pontos para obter o formato correto de decimal
    valor = valor.replace(',', '.');

    // Criar um objeto BigDecimal

    return valor;
}