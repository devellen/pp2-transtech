package br.ifpe.transtech.transtech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.transtech.transtech.model.Empresa;
import br.ifpe.transtech.transtech.model.EmpresaDAO;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmpresaController {

    @Autowired
    private EmpresaDAO daoEmp;

    @GetMapping("/entrarEmp")
    public String entrarEmp() {
        return "entrarEmp";
    }

    @GetMapping("/cadastroEmpresa")
    public String cadastroEmpresa(Empresa empresa) {
        return "cadastro-empresa";
    }

    @PostMapping("/salvarEmpresa")
    public String salvarEmpresa(Empresa empresa) {
        daoEmp.save(empresa);
        System.out.println(empresa);
        return "index";
    }

    @PostMapping("/efetuarLoginEmpresa")
    public String efetuarLoginEmpresa(String email, String senha, RedirectAttributes ra, HttpSession session) {
        Empresa empresa = this.daoEmp.findByEmailAndSenha(email, senha);
        if (empresa != null) {
            session.setAttribute("empresaLogado", empresa);
            return "redirect:/homeEmpresa";
        } else {
            ra.addFlashAttribute("mensagemErro", "Empresa/senha inválidos");
            return "redirect:/";
        }
    }

    @GetMapping("/homeEmpresa")
    public String homeEmpresa() {
        return "homeEmpresa";
    }

    @GetMapping("/formEmpresa")
    public String formEmpresa() {
        return "form-empresa";
    }
}
