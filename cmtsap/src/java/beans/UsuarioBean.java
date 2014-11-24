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
    

    
    private Usuario usuario;
    private List<Usuario> listaUsuario;

    public List<Usuario> getListaUsuario() {
        listaUsuario= new DAOGenerics<Usuario>().listar();
        return listaUsuario;
    }

    public void setListaUsuario(ArrayList<Usuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
    public UsuarioBean() {
       usuario=new Usuario();
       listaUsuario= new DAOGenerics<Usuario>().listar();
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
        dao.salvar(usuario);
        //new PersistFactory<Usuario>().persistGeneric(this.usuario);
       // listaUsuario.add(usuario);
        
       // System.out.println("usuario salvo "+usuario.getNome());
        usuario=new Usuario();
       
    }
       
    
}
