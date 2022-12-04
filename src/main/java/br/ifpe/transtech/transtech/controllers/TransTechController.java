package br.ifpe.transtech.transtech.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TransTechController {

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/quemSomos")
        public String quemSomos() {
            return "quemSomos";
        }

    @GetMapping("/cadastroEmpresa")
        public String cadastroEmpresa() {
            return "cadastro-empresa";
        }

    @GetMapping("/cadastroUsuario")
        public String cadastroUsuario() {
            return "cadastro-usuario";
        }
}
