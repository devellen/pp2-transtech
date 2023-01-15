package br.ifpe.transtech.transtech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.transtech.transtech.model.Empresa;
import br.ifpe.transtech.transtech.model.EmpresaDAO;
import br.ifpe.transtech.transtech.model.UsuarioDAO;
import br.ifpe.transtech.transtech.model.Vaga;
import br.ifpe.transtech.transtech.model.VagaDao;
import jakarta.servlet.http.HttpSession;

@Controller
public class EmpresaController {

    @Autowired
    private EmpresaDAO daoEmp;

    @Autowired
    private UsuarioDAO daoUsu;

    @Autowired
    private VagaDao daoVaga;

    @GetMapping("/entrarEmp")
    public String entrarEmp() {
        return "entrarEmp";
    }

    @GetMapping("/cadastroEmpresa")
    public String cadastroEmpresa(Empresa empresa) {
        return "cadastro-empresa";
    }

    @PostMapping("/salvarEmpresa")
    public String efetuarLoginEmpresa(String email, String senha, Empresa empresa,
            HttpSession session, RedirectAttributes ra) {
        if (this.daoEmp.existsByEmail(empresa.getEmail()) || this.daoUsu.existsByEmail(empresa.getEmail())) {
            ra.addFlashAttribute("msg", "E-mail já cadastrado");
            return "redirect:/cadastroEmpresa";

        } else if (empresa.getEmail() == "" || empresa.getSenha() == "") {
            ra.addFlashAttribute("msg", "Preencha todos os campos");
            return "redirect:/cadastroEmpresa";
        } else {
            this.daoEmp.save(empresa);
            return "index";
        }
    }

    @GetMapping("/alteracaoSenhaEmpresa")
    public String alteracaoSenhaEmpresa() {
        return "alteracaoSenhaEmpresa";
    }

    @PostMapping("/efetuarLoginEmpresa")
    public String efetuarLoginEmpresa(String email, String senha, Empresa empresa, HttpSession sessao,
            RedirectAttributes ra, HttpSession session) {
        this.daoEmp.findByEmailAndSenha(email, senha);
        if (this.daoEmp.existsByEmailAndSenha(email, senha)) {
            sessao.setAttribute("empresaLogado", true);
            Empresa empresa2 = this.daoEmp.findByEmail(email);
            sessao.setAttribute("email", empresa2.getEmail());
            return "redirect:/homeEmpresa";
        } else if (email == "" || senha == "") {
            ra.addFlashAttribute("msg", "Preencha todos os campos");
            return "redirect:/entrarEmp";
        } else {
            ra.addFlashAttribute("msg", "Email ou Senha Incorretos");
            return "redirect:/entrarEmp";
        }
    }

    @PostMapping("/alteracaoSenhaEmpresa")
    public String alterarSenhaEmpresa(long codRecuperacao, String email, Empresa empresa, String senha,
            RedirectAttributes ra) {
        this.daoEmp.findByEmailAndCodRecuperacao(email, codRecuperacao);
        if (empresa == null) {
            ra.addFlashAttribute("mensagemErro", "Empresa/senha inválidos");
            return "redirect:/alteracaoSenhaEmpresa";
        } else {
            empresa.setSenha(senha);
            daoEmp.save(empresa);
        }
        System.out.println(empresa);
        return "index";
    }

    @GetMapping("/homeEmpresa")
    public String homeEmpresa(HttpSession session, Model model) {
        Object email;
        email = session.getAttribute("email");
        Empresa empresa = this.daoEmp.findByEmail(email);
        List<Vaga> listaVaga = daoVaga.listaVaga(empresa.getCodigo());
        model.addAttribute("listaVaga", listaVaga);
        return "homeEmpresa";
    }

    @GetMapping("/formEmpresa")
    public String formEmpresa(Vaga vaga) {
        return "form-empresa";
    }
}
