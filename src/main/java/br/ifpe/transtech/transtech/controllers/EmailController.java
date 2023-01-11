package br.ifpe.transtech.transtech.controllers;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ifpe.transtech.transtech.dtos.EmailDto;
import br.ifpe.transtech.transtech.model.EmailModel;
import br.ifpe.transtech.transtech.model.Empresa;
import br.ifpe.transtech.transtech.model.EmpresaDAO;
import br.ifpe.transtech.transtech.model.Usuario;
import br.ifpe.transtech.transtech.model.UsuarioDAO;
import br.ifpe.transtech.transtech.services.EmailService;

@Controller
public class EmailController {

    @Autowired
    EmailService emailService;

    @Autowired
    EmpresaDAO empresaDAO;

    @Autowired
    UsuarioDAO usuarioDAO;

    @GetMapping("/sending-email")
    public String sendingEmail(String email, RedirectAttributes ra) {

        Random random = new Random();
        long num = random.nextLong(10000);
        
        Empresa empresa = this.empresaDAO.findByEmail(email);
        if (empresa != null) {
            EmailModel emailModel = new EmailModel();

            EmailDto emailDto = new EmailDto();
            emailDto.setEmailFrom("devtranstech@gmail.com");
            emailDto.setEmailTo(email);
            emailDto.setSubject("REDEFINIÇÃO DE SENHA");
            emailDto.setText("O código a ser usado para redefinição do seu e-mail é: "+ num + ".");

            BeanUtils.copyProperties(emailDto, emailModel);
            emailService.sendEmail(emailModel);
            empresa.setCodRecuperacao(num);
            this.empresaDAO.save(empresa);

            return "redirect:/alteracaoSenhaEmpresa";

        } else {
            ra.addFlashAttribute("mensagemErro", "Email inválido");
        }

        Usuario usuario = this.usuarioDAO.findByEmail(email);
        if (usuario != null) {
            EmailModel emailModel = new EmailModel();

            EmailDto emailDto = new EmailDto();
            emailDto.setEmailFrom("julio.cdol1@gmail.com");
            emailDto.setEmailTo(email);
            emailDto.setSubject("REDEFINIÇÃO DE SENHA");
            emailDto.setText("O código a ser usado para redefinição do seu e-mail é: "+ num + ".");

            BeanUtils.copyProperties(emailDto, emailModel);
            emailService.sendEmail(emailModel);
            usuario.setCodRecuperacao(num);
            this.usuarioDAO.save(usuario);

            return "redirect:/alteracaoSenhaUsuario";

        } else {
            ra.addFlashAttribute("mensagemErro", "Email inválido");
        }

        return "redirect:/acessoNegado";
    }

}
