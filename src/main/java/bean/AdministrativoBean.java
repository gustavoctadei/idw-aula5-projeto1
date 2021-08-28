/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.UsuarioDao;
import java.util.List;
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
public class AdministrativoBean {
    
    private List<Usuario> listaUsuario;
    private Usuario currentUsuario;
    
    private UsuarioDao usuarioDao = new UsuarioDao();

    public AdministrativoBean() {
        listaUsuario = usuarioDao.listar();
    }
    
    public void ativar() {
        currentUsuario.setAtivo(true);
        usuarioDao.salvar(currentUsuario);
        
        currentUsuario = new Usuario();
    }
    
    public void desativar() {
        currentUsuario.setAtivo(false);
        usuarioDao.salvar(currentUsuario);
        
        currentUsuario = new Usuario();
    }
    
    public String editar() {
        FacesUtils.putAtributoFlash("currentUsuario", currentUsuario);
        
        return "/publico/usuario";
    }
    
    public void excluir() {
        usuarioDao.excluir(currentUsuario);
        listaUsuario.remove(currentUsuario);
        
        currentUsuario = new Usuario();
    }

    public List<Usuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public Usuario getCurrentUsuario() {
        return currentUsuario;
    }

    public void setCurrentUsuario(Usuario currentUsuario) {
        this.currentUsuario = currentUsuario;
    }

    public UsuarioDao getUsuarioDao() {
        return usuarioDao;
    }

    public void setUsuarioDao(UsuarioDao usuarioDao) {
        this.usuarioDao = usuarioDao;
    }
    
}
