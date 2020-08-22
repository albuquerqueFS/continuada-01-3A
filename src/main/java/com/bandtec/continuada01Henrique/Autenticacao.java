package com.bandtec.continuada01Henrique;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/autenticacao")
public class Autenticacao implements Autenticar {

    // Suposta conta de admin
    String adminUsuario = "admin";
    String adminSenha = "admin";

    // Suposta conta de cliente
    String clientUsuario = "fulano";
    String clientSenha = "123loko";

    String dataLogin = "";
    boolean auth = false;
    boolean admin = false;

    @PostMapping("/entrar")
    public String logar(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "senha") String senha) {

        if (usuario.equals(this.adminUsuario)) {
            if (senha.equals(this.adminSenha)) {
                this.auth = true;
                this.admin = true;
                dataLogin = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                return "Autenticado com sucesso!";
            }
        }

        if (usuario.equals(this.clientUsuario)) {
            if (senha.equals(this.clientSenha)) {
                this.auth = true;
                this.admin = false;
                dataLogin = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
                return "Autenticado com sucesso!";
            }
        }
        return "Login ou senha incorretos";
    }

    @DeleteMapping("")
    public void deletar() {
        this.auth = false;
        this.admin = false;
    }

    @GetMapping("")
    public String pegarUsuarioAutenticado() {
        if (isAuth()) {
            if (isAdmin()) {
                return "{ \"usuario\": \"" + this.adminUsuario + "\", \"entrada\": \"" + this.dataLogin + "\"}";
            }
            return "{ \"usuario\": \"" + this.clientUsuario + "\", \"entrada\": \"" + this.dataLogin + "\"}";
        } else {
            return "Usuário não autenticado";
        }
    }

    @Override
    public boolean isAuth() {
        return this.auth;
    }

    @Override
    public boolean isAdmin() {
        return this.admin;
    }
}
