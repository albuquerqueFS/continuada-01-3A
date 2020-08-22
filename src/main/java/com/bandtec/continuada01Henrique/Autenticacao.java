package com.bandtec.continuada01Henrique;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/autenticacao")
public class Autenticacao implements Autenticar {

    // Conta de admin
    String adminUsuario = "admin";
    String adminSenha = "admin";

    // Conta de cliente
    String clientUsuario = "fulano";
    String clientSenha = "123loko";

    String dataLogin = "";
    boolean auth = false;
    boolean admin = false;

    @PostMapping("/entrar")
    public String logar(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "senha") String senha) {

        if (usuario.equals(this.adminUsuario)) {
            return loginAdmin(usuario, senha);
        }

        if (usuario.equals(this.clientUsuario)) {
            return loginClient(usuario, senha);
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
                return "{ \"usuario\": \"" + this.adminUsuario + "\", \"tipo:\" \"administrador\", \"entrada\": \"" + this.dataLogin + "\"}";
            }
            return "{ \"usuario\": \"" + this.clientUsuario + "\", \"tipo:\" \"assistente\", \"entrada\": \"" + this.dataLogin + "\"}";
        } else {
            return "Usuário não autenticado";
        }
    }

    @Override
    public boolean isAuth() {
        return this.auth;
    }

    @Override
    public boolean isAdmin() { return this.admin; }

    @Override
    @ResponseBody
    public String loginAdmin(String login, String senha) {
        if (senha.equals(this.adminSenha)) {
            this.admin = true;
            this.auth = true;
            dataLogin = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            return "Autenticado com sucesso";
        }
        return "Senha incorreta";
    }

    @Override
    @ResponseBody
    public String loginClient(String login, String senha) {
        if (senha.equals(this.clientSenha)) {
            this.admin = false;
            this.auth = true;
            dataLogin = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
            return "Autenticado com sucesso";
        }
        return "Senha incorreta";
    }
}
