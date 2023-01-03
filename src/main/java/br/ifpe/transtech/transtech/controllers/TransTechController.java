package br.ifpe.transtech.transtech.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import br.ifpe.transtech.transtech.model.Empresa;
import br.ifpe.transtech.transtech.model.Vaga;
import br.ifpe.transtech.transtech.model.VagaDao;

import jakarta.servlet.http.HttpSession;

@Controller
public class TransTechController {

    @Autowired
    private VagaDao daoVaga;

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
    public String listarVagas( @DefaultValue("1") Integer page, Model model) {
    	if (page == null) {
    		page = 0;
    	}
        model.addAttribute("lista", daoVaga.findAll(PageRequest.of(page, 6)));
        return "vagas";
    }

    @PostMapping("/salvarVaga")
    public String salvarVaga(Vaga vaga, HttpSession session) {
        Empresa empresa = (Empresa) session.getAttribute("empresaLogado");
        vaga.setEmpresa(empresa);
        daoVaga.save(vaga);
        System.out.println(vaga);
        return "redirect:/homeEmpresa";
    }

    @GetMapping("/detalheVaga")
    public String detalheVaga(Integer codigo, Model model) {
    	 Vaga id= daoVaga.findById(codigo).orElse(null);
    	System.out.println(id);
        model.addAttribute("lista2", id);
        return "detalheVaga";
    }
    
    @GetMapping("/detalheVagaEmpresa")
    public String detalheVagaEmpresa(Integer codigo, Model model) {
    	 Vaga id= daoVaga.findById(codigo).orElse(null);
    	System.out.println(id);
        model.addAttribute("lista2", id);
        return "detalheVagaEmpresa";
    }
}
