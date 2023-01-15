package br.ifpe.transtech.transtech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.transtech.transtech.model.Candidatura;
import br.ifpe.transtech.transtech.model.EmpresaDAO;
import br.ifpe.transtech.transtech.model.Usuario;
import br.ifpe.transtech.transtech.model.UsuarioDAO;
import br.ifpe.transtech.transtech.model.Vaga;
import br.ifpe.transtech.transtech.model.VagaDao;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioDAO daoUsu;

    @Autowired
    private EmpresaDAO daoEmp;

    @Autowired
    private VagaDao daoVaga;

    @GetMapping("/entrarUsu")
    public String entrarUsu() {
        return "entrarUsu";
    }

    @GetMapping("/homeUsuario")
    public String homeUsuario() {
        return "homeUsuario";
    }

    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(Usuario usuario) {
        return "cadastro-usuario";
    }

    @PostMapping("/salvarUsuario")
    public String efetuarLoginUsuario(String email, String senha, Usuario usuario,
            HttpSession session, RedirectAttributes ra) {
        if (this.daoUsu.existsByEmail(usuario.getEmail()) || this.daoEmp.existsByEmail(usuario.getEmail())) {
            ra.addFlashAttribute("msg", "E-mail já cadastrado");
            return "redirect:/cadastroUsuario";

        } else if (usuario.getEmail() == "" || usuario.getSenha() == "") {
            ra.addFlashAttribute("msg", "Preencha todos os campos");
            return "redirect:/cadastroUsuario";
        } else {
            this.daoUsu.save(usuario);
            return "entrarUsu";
        }

    }

    @GetMapping("/alteracaoSenhaUsuario")
    public String alteracaoSenhaUsuario() {
        return "alteracaoSenhaUsuario";
    }

    @PostMapping("/efetuarLoginUsuario")
    public String efetuarLoginUsuario(String email, String senha, HttpSession sessao,
            RedirectAttributes ra) {
        Usuario usuario = this.daoUsu.findByEmailAndSenha(email, senha);
        if (this.daoUsu.existsByEmailAndSenha(email, senha) && usuario != null) {
            sessao.setAttribute("usuarioLogado", true);
            sessao.setAttribute("usuarioLogado", usuario);
            Usuario usuario2 = this.daoUsu.findByEmail(email);
            sessao.setAttribute("email", usuario2.getEmail());
            return "redirect:/homeUsuario";
        } else if (email == "" || senha == "") {
            ra.addFlashAttribute("msg", "Preencha todos os campos");
            return "redirect:/entrarUsu";
        } else {
            ra.addFlashAttribute("msg", "E-mail ou Senha Incorretos");
            return "redirect:/entrarUsu";
        }
    }

    @PostMapping("/alteracaoSenhaUsuario")
    public String alterarSenhaUsuario(long codRecuperacao, String email, String senha, RedirectAttributes ra) {
        Usuario usuario = this.daoUsu.findByEmailAndCodRecuperacao(email, codRecuperacao);
        if (usuario == null) {
            ra.addFlashAttribute("mensagemErro", "Usuário/senha inválidos");
            return "redirect:/";
        } else {
            usuario.setSenha(senha);
            daoUsu.save(usuario);
        }
        System.out.println(usuario);
        return "redirect:/entrarUsu";
    }

    @GetMapping("/formUsuario")
    public String formUsuario(Candidatura candidatura, Integer codigoVaga, Model model) {
        Vaga vaga = this.daoVaga.findById(codigoVaga).orElse(null);
        model.addAttribute("vaga", vaga);
        return "form-usuario";
    }
}
