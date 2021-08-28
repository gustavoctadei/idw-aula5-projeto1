/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import model.Usuario;
import util.FacesUtils;

/**
 *
 * @author Gustavo
 */

@ManagedBean
@Named
@ViewScoped
public class UsuarioBean {
    
    private Usuario usuario;
    private String confirmaSenha;

    public UsuarioBean() {
        usuario = (Usuario) FacesUtils.getAtributoFlash("currentUsuario");
        
        if (usuario == null) usuario = new Usuario();
    }
    
    public String novo() {
        return "usuario";
    }
    
    public String administrativo() {
        return "/admin/principal";
    }
    
    public String salvar() {
        
        Boolean novoUsuario = usuario.getIdUsuario() == null;
        
        if (!this.usuario.getSenha().equals(this.confirmaSenha)) {
            FacesUtils.mensagem("A senha não foi confirmada corretamente.", FacesMessage.SEVERITY_WARN);
            return null;
        }
        
        this.usuario.setAtivo(true);
        
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.salvar(this.usuario);
        
        if (novoUsuario) {
            FacesUtils.putAtributoFlash("currentUsuario", this.usuario);
            return "usuarioSucesso";
        }
        else return "/admin/principal";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }
    
}
