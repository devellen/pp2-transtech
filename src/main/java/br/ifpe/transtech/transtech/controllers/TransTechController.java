package br.ifpe.transtech.transtech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.ifpe.transtech.transtech.model.Empresa;
import br.ifpe.transtech.transtech.model.EmpresaDAO;
import br.ifpe.transtech.transtech.model.Usuario;
import br.ifpe.transtech.transtech.model.UsuarioDAO;

@Controller
public class TransTechController {

    @Autowired
    private UsuarioDAO daoUsu;

    @Autowired
    private EmpresaDAO daoEmp;

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/quemSomos")
        public String quemSomos() {
            return "quemSomos";
        }

    @GetMapping("/cadastroEmpresa")
        public String cadastroEmpresa(Empresa empresa) {
            return "cadastro-empresa";
        }

    @GetMapping("/cadastroUsuario")
        public String cadastroUsuario(Usuario usuario) {
            return "cadastro-usuario";
        }

    @PostMapping("/salvarUsuario")
    public String salvarUsuario(Usuario usuario) {
		daoUsu.save(usuario);
		System.out.println(usuario);
		return "index";
	}

    @PostMapping("/salvarEmpresa")
    public String salvarEmpresa(Empresa empresa) {
		daoEmp.save(empresa);
		System.out.println(empresa);
		return "index";
	}
}
