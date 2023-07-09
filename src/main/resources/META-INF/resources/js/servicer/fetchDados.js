function EviarPOST(url, dados) {
    return fetch(url, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
        .then(response => {
            if (response.ok) {
                return response;
            } else {
                throw new Error("Ocorreu um erro ao enviar a solicitação.");
            }
        })
        .catch(error => {
            return error;
        });
}

function atualizar(url, dados) {
    return fetch(url, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dados)
    })
        .then(response => response.json())
        .then(data => {
            // Aqui você pode trabalhar com o objeto JSON retornado
            return data
        })
        .catch(error => {
            throw new Error(`Erro na solicitação: ${error.message}`);
        });
}

function listarTudo(url) {
    return fetch(url, {
        method: "GET",
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Ocorreu um erro ao enviar a solicitação.");
            }
        })
        .catch(error => {
            return error
        });
}

function excluir(url) {
    return fetch(url, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json"
        }
    }).then(response => response.json())
        .then(data => {
             // Aqui você pode trabalhar com o objeto JSON retornado
            return data
        })

        .catch(error => {
            throw new Error(`Erro na solicitação: ${error.message}`);
        });
}
