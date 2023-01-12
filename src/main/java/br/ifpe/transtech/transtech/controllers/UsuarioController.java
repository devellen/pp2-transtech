package br.ifpe.transtech.transtech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.transtech.transtech.model.Candidatura;
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
    public String salvarUsuario(Usuario usuario) {
        daoUsu.save(usuario);
        System.out.println(usuario);
        return "index";
    }

    @GetMapping("/alteracaoSenhaUsuario")
    public String alteracaoSenhaUsuario(){
        return "alteracaoSenhaUsuario";
    }

    @PostMapping("/efetuarLoginUsuario")
    public String efetuarLoginUsuario(String email, String senha, RedirectAttributes ra, HttpSession session) {
        Usuario usuario = this.daoUsu.findByEmailAndSenha(email, senha);
        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/homeUsuario";
        } else {
            ra.addFlashAttribute("mensagemErro", "Usuário/senha inválidos");
            return "redirect:/";
        }
    }

    @PostMapping("/alteracaoSenhaUsuario")
    public String alterarSenhaEmpresa(long codRecuperacao, String email, String senha, RedirectAttributes ra) {
        Usuario usuario = this.daoUsu.findByEmailAndCodRecuperacao(email, codRecuperacao);
        if(usuario == null) {
            ra.addFlashAttribute("mensagemErro", "Usuário/senha inválidos");
            return "redirect:/";
        } else {
            usuario.setSenha(senha);
            daoUsu.save(usuario);
        }
        System.out.println(usuario);
        return "alterarSenhaEmpresa";
    }

    @GetMapping("/formUsuario")
    public String formUsuario(Candidatura candidatura,Integer codigoVaga, Model model) {
     Vaga vaga =  this.daoVaga.findById(codigoVaga).orElse(null);
     model.addAttribute("vaga", vaga);
    	return "form-usuario";
    }
}
