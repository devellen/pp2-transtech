package br.ifpe.transtech.transtech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.transtech.transtech.model.Empresa;
import br.ifpe.transtech.transtech.model.EmpresaDAO;
import br.ifpe.transtech.transtech.model.Usuario;
import br.ifpe.transtech.transtech.model.UsuarioDAO;
import br.ifpe.transtech.transtech.model.Vaga;
import br.ifpe.transtech.transtech.model.VagaDao;
import jakarta.servlet.http.HttpSession;

@Controller
public class TransTechController {

    @Autowired
    private UsuarioDAO daoUsu;

    @Autowired
    private EmpresaDAO daoEmp;

    @Autowired
    private VagaDao daoVaga;

    @GetMapping("/entrarEmp")
    public String entrarEmp() {
        return "entrarEmp";
    }

    @GetMapping("/entrarUsu")
    public String entrarUsu() {
        return "entrarUsu";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/quemSomos")
    public String quemSomos() {
        return "quemSomos";
    }

    @GetMapping("/homeUsuario")
    public String homeUsuario(){
        return "homeUsuario";
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
    @PostMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	} 

    @GetMapping("/homeEmpresa")
    public String homeEmpresa() {
        return "homeEmpresa";
    }
    
    @GetMapping("/formEmpresa")
    public String formEmpresa() {
    	return "form-empresa";
    }

    @PostMapping("/salvarVaga")
    public String salvarVaga(Vaga vaga) {
    	daoVaga.save(vaga);
        System.out.println(vaga);
		return "homeEmpresa";
    	
    }
}
