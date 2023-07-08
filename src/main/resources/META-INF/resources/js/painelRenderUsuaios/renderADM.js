function rederisar() {
    var UL1 = $('<ul class="nav nav-pills flex-column mb-auto"></ul>');


    var liHome = $('<li class="nav-item">\n' +
        '                <a href="\\" class="nav-link active" aria-current="page">\n' +
        '                    <svg class="bi pe-none me-2" width="16" height="16">\n' +
        '                        <use xlink:href="#home" />\n' +
        '                    </svg>\n' +
        '                    Home\n' +
        '                </a>\n' +
        '            </li>');
    var liSolicta = $('<li>\n' +
        '                <a href="\\solictar" class="nav-link link-body-emphasis" data-bs-toggle="collapse" data-bs-target="#solecit-collapse"\n' +
        '                   aria-expanded="false">\n' +
        '                    <svg class="bi pe-none me-2" width="16" height="16">\n' +
        '                        <use xlink:href="#table" />\n' +
        '                    </svg>\n' +
        '                    Solicitações\n' +
        '                </a>\n' +
        '                <div class="collapse" id="solecit-collapse">\n' +
        '                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">\n' +
        '                        <li><a href="\\solictar\\entrada" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Entrada</a></li>\n' +
        '                        <li><a href="\\solictar\\emadameto" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Enadameto</a></li>\n' +
        '                        <li><a href="\\solictar\\finalizadas" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Finalizadas</a></li>\n' +
        '                    </ul>\n' +
        '                </div>\n' +
        '            </li>');
    var liEstoque = $(' <li>\n' +
        '                <a href="\\estoque" class="nav-link link-body-emphasis" data-bs-toggle="collapse" data-bs-target="#estoque-collapse"\n' +
        '                   aria-expanded="false">\n' +
        '                    <svg class="bi pe-none me-2" width="16" height="16">\n' +
        '                        <use xlink:href="#grid" />\n' +
        '                    </svg>\n' +
        '                    Estoque\n' +
        '                </a>\n' +
        '                <div class="collapse" id="estoque-collapse">\n' +
        '                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">\n' +
        '                        <li><a href="\\estoque\\listadeestoques" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Lista de Estoques</a></li>\n' +
        '                        <li><a href="\\estoque\\listadecategoria" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Lista de categoria</a></li>\n' +
        '                        <li><a href="\\estoque\\produtos" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Produtos</a></li>\n' +
        '                    </ul>\n' +
        '                </div>\n' +
        '            </li>');
    var liUsuário = $('<li>\n' +
        '                <a href="\\usuário" class="nav-link link-body-emphasis" data-bs-toggle="collapse" data-bs-target="#usuario-collapse"\n' +
        '                   aria-expanded="false">\n' +
        '                    <svg class="bi pe-none me-2" width="16" height="16">\n' +
        '                        <use xlink:href="#people-circle" />\n' +
        '                    </svg>\n' +
        '                    Usuário\n' +
        '                </a>\n' +
        '                <div class="collapse" id="usuario-collapse">\n' +
        '                    <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">\n' +
        '                        <li><a href="\\usuário\\list" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Lista de Usuários</a></li>\n' +
        '                        <li><a href="\\usuário\\cadastro" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Cadastro de Usuário</a></li>\n' +
        '                    </ul>\n' +
        '                </div>\n' +
        '            </li>');
    UL1.append(liHome);
    UL1.append(liEstoque);
    UL1.append(liSolicta);
    UL1.append(liUsuário);
    $('#renderisar_menu').append(UL1);

}
