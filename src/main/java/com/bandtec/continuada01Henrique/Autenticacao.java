package com.bandtec.continuada01Henrique;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/autenticacao")
public class Autenticacao {
    String usuario = "fulano";
    String senha = "123loko";
    String dataLogin = "";
    boolean auth= false;

    @PostMapping("/entrar")
    public String logar(@RequestParam(value = "usuario") String usuario, @RequestParam(value = "senha") String senha) {

        if (usuario.equals(this.usuario)) {
            if (senha.equals(this.senha)) {
                this.auth = true;
                dataLogin = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

                System.out.println("Autenticado com sucesso!");
                return "Autenticado com sucesso!";
            }
        }

        System.out.println("Login ou senha incorretos");
        return "Login ou senha incorretos";
    }

    @DeleteMapping("")
    public void deletar() {
        this.auth = false;
    }

    @GetMapping("")
    public String pegarUsuarioAutenticado() {
        if (this.auth == true) {
            return "{ \"usuario\": \"" + this.usuario + "\", \"entrada\": \"" + this.dataLogin + "\"}";
        } else {
            return "Usuário não autenticado";
        }
    }
}
