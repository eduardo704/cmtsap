/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import pojos.Role;
import pojos.Usuario;
import util.DAOGenerics;
import util.IGenerics;
import util.PersistFactory;

/**
 *
 * @author eduardo
 */
@ManagedBean(name="usuarioBean")
@ViewScoped
public class UsuarioBean implements Serializable {

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }
    
    private List<Role> listaRoleSelecionado;
    private List<Role> listaRole;
    private Usuario usuarioSelecionado;
    private Usuario usuario;
    private List<Usuario> listaUsuario;

    public List<Usuario> getListaUsuario() {
        listaUsuario= new DAOGenerics<Usuario>(Usuario.class).listar();
       
        return listaUsuario;
    }

    public List<Role> getListaRoleSelecionado() {
        return listaRoleSelecionado;
    }

    public void setListaRoleSelecionado(List<Role> listaRoleSelecionado) {
        this.listaRoleSelecionado = listaRoleSelecionado;
    }

    public List<Role> getListaRole() {
        return listaRole;
    }

    public void setListaRole(List<Role> listaRole) {
        this.listaRole = listaRole;
    }

    public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    public UsuarioBean() {
       usuario=new Usuario();
       listaUsuario= new DAOGenerics<Usuario>(Usuario.class).listar();
       listaRole= new DAOGenerics<Role>(Role.class).listar();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void novo(){
        usuario=new Usuario();
    } 


    public void salvar(){
        DAOGenerics dao=new DAOGenerics();
        usuario.setRoles(listaRoleSelecionado);
        dao.salvar(usuario);
        usuario=new Usuario();       
    }
     public void onRowSelect(SelectEvent event) {
         String teste=""; 
        usuario= (Usuario) event.getObject();
        RequestContext.getCurrentInstance().update(":frusuario");  
    }
 
    
    public void editar(SelectEvent event){
        String teste=""; 
        usuario= (Usuario) event.getObject();
        RequestContext.getCurrentInstance().update(":frusuario");        
    }
    
     public void onRowCancel(RowEditEvent event) {
   
    }
       
    
}
