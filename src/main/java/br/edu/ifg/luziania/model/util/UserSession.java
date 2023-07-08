package br.edu.ifg.luziania.model.util;

import br.edu.ifg.luziania.model.dto.UserSessionDTO;
import jakarta.enterprise.context.SessionScoped;

import java.io.Serializable;
import java.util.UUID;

@SessionScoped
public class UserSession implements Serializable {
    private UserSessionDTO userSessionDTO;
    private boolean sessionActive = false;
    private String token;
    private long expirationTime;

    public UserSessionDTO getUserSessionDTO() {
        return userSessionDTO;
    }

    public void setUserSessionDTO(UserSessionDTO userSessionDTO) {
        this.userSessionDTO = userSessionDTO;
        sessionActive = true;
        generateToken();
        updateExpirationTime();
    }

    public void clearSession() {
        userSessionDTO = null;
        sessionActive = false;
        token = null;
        expirationTime = 0;
    }

    public boolean isSessionActive() {
        checkExpirationTime(); // Verifica se o tempo expirou e atualiza a sessão, se necessário
        return sessionActive;
    }


    public String getToken() {
        return token;
    }

    public boolean isTokenValid() {
        checkExpirationTime(); // Verifica se o tempo expirou e atualiza a sessão, se necessário
        return sessionActive && System.currentTimeMillis() < expirationTime;
    }

    public void generateToken() {
        // Gera um novo token aleatório usando UUID
        this.token = UUID.randomUUID().toString();
    }

    public void updateExpirationTime() {
        // Define o tempo de expiração para 30 minutos a partir de agora
        expirationTime = System.currentTimeMillis() + 30 * 60 * 1000; // 30 minutos em milissegundos
    }

    private void checkExpirationTime() {
        if (sessionActive && System.currentTimeMillis() >= expirationTime) {
            clearSession(); // Limpa a sessão se o tempo expirou
        }
    }
}
