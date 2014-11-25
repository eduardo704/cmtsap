/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GeneratorType;
import util.IGenerics;

/**
 *
 * @author eduardo
 */
@Entity
@Table(name="usuario")
public class Usuario implements Serializable, IGenerics {
    private static final long serialVersionUID = 1L;
    private String nome,senha;
    
    
    
    @Id
    @GeneratedValue
    private int id;
    
     @ManyToMany(fetch= FetchType.EAGER,cascade= CascadeType.PERSIST)
    @JoinTable(name="usuario_role", joinColumns={@JoinColumn(name="usuario_id")}, 
            inverseJoinColumns={@JoinColumn(name="role_id")})
    private List<Role> roles;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario() {
    }

  
    @Override
    public String toString(){
        if(nome==null){
            return "";
        }
        String s= "nome: "+nome;
        s+="senha: "+senha;
        
        return s;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }



  
}
