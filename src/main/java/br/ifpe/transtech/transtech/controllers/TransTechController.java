package br.ifpe.transtech.transtech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.ifpe.transtech.transtech.model.Candidatura;
import br.ifpe.transtech.transtech.model.CandidaturaDAO;
import br.ifpe.transtech.transtech.model.Empresa;
import br.ifpe.transtech.transtech.model.EmpresaDAO;
import br.ifpe.transtech.transtech.model.Usuario;
import br.ifpe.transtech.transtech.model.Vaga;
import br.ifpe.transtech.transtech.model.VagaDao;
import jakarta.servlet.http.HttpSession;

@Controller
public class TransTechController {

    @Autowired
    private VagaDao daoVaga;

    @Autowired
    private CandidaturaDAO daoCandidatura;

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

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/listarVagas")
    public String listarVagas(@DefaultValue("1") Integer page, Model model, HttpSession session) {
        if (page == null) {
            page = 0;
        }

        Page<Vaga> vagas = daoVaga.findAll(PageRequest.of(page, 6));
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        for (Vaga x : vagas) {
            x.setVagaPreenchida(this.daoCandidatura.existsByVagaAndUsuario(x, usuario));
        }
        model.addAttribute("lista", vagas);

        return "vagas";
    }

    @PostMapping("/salvarVaga")
    public String salvarVaga(Vaga vaga, HttpSession session) {
        Object email;
        email = session.getAttribute("email");
        Empresa empresa = this.daoEmp.findByEmail(email);
        vaga.setEmpresa(empresa);
        daoVaga.save(vaga);
        System.out.println(vaga);
        return "redirect:/homeEmpresa";
    }

    @GetMapping("/detalheVaga")
    public String detalheVaga(Integer codigo, Model model) {
        Vaga id = daoVaga.findById(codigo).orElse(null);
        System.out.println(id);
        model.addAttribute("vaga", id);
        return "detalheVaga";
    }

    @GetMapping("/detalheVagaEmpresa")
    public String detalheVagaEmpresa(Integer codigo, Model model) {
        Vaga id = daoVaga.findById(codigo).orElse(null);
        System.out.println(id);
        model.addAttribute("vaga", id);
        model.addAttribute("candidaturas", this.daoCandidatura.findByVaga(id));
        return "detalheVagaEmpresa";
    }

    @GetMapping("/editarVaga")
    public String editarVaga(Integer codigo, Model model) {
        Vaga id = daoVaga.findById(codigo).orElse(null);
        System.out.println(id);
        model.addAttribute("vaga", id);
        model.addAttribute("candidaturas", this.daoCandidatura.findByVaga(id));
        return "form-empresa";
    }

    @GetMapping("/deletarVaga")
    public String deletarVaga(Integer codigo) {
        this.daoVaga.deleteById(codigo);
        return "redirect:/homeEmpresa";
    }

    @GetMapping("/acessoNegado")
    public String acessoNegado() {
        return "naoLogado";
    }

    @GetMapping("/redefinirSenha")
    public String redefinirSenha() {
        return "redefinirSenha";
    }

    @PostMapping("/salvarInscricao")
    public String salvarCandidatura(Candidatura candidatura, HttpSession session) {
        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        Vaga vaga;
        vaga = candidatura.getVaga();
        if (this.daoCandidatura.existsByVagaAndUsuario(vaga, usuario)) {
            return "redirect:/listarVagas?page=0";
        } else {
            candidatura.setUsuario(usuario);
            daoCandidatura.save(candidatura);
            System.out.println(candidatura);
            return "redirect:/listarVagas?page=0";
        }
    }

    @PostMapping("/pesquisaVaga")
    public String pesquisaVaga(String nome, Model model) {
        model.addAttribute("lista", daoVaga.findByNomeContainingIgnoreCase(nome));
        return "vagas";
    }
}
