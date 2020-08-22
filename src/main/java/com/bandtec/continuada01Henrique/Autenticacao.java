package com.bandtec.continuada01Henrique;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autenticacao")
public class Autenticacao {
    String usuario = "fulano";
    String senha = "123loko";

    @PostMapping("/entrar")
    public void logar(@RequestBody String usuario, @RequestBody String senha) {
        if (usuario == this.usuario) {
            if (senha.equals(this.senha)) {
                System.out.println("Autenticado com sucesso!");
                return;
            }
        }

        System.out.println("Login ou senha incorretos");
    }
}
