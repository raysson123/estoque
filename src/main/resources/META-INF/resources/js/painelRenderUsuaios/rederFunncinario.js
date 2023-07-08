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
        '                        <li><a href="\\estoque\\produtos" class="link-body-emphasis d-inline-flex text-decoration-none rounded">Produtos</a></li>\n' +
        '                    </ul>\n' +
        '                </div>\n' +
        '            </li>');

    UL1.append(liHome);
    UL1.append(liEstoque);


    $('#renderisar_menu').append(UL1);

}
