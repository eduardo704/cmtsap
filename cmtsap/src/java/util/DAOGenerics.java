/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author eduardo
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class DAOGenerics<T> {

    protected Class<T> classe;

    public DAOGenerics() {
    }

    public DAOGenerics(Class<T> classe) {
        this.classe = classe;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public boolean salvar(IGenerics generics) {
        Session sessao = null;
        Transaction transacao = null;

        boolean ok = false;
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            if (generics.getId() == 0) {
                sessao.save(generics);
            } else {
                sessao.update(generics);
            }

            transacao.commit();
            ok = true;
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        } 
        return ok;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public boolean excluir(IGenerics generics) {
        Session sessao = null;
        Transaction transacao = null;
        boolean ok = true;
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            sessao.delete(generics);
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
            ok = false;
        } finally {
            HibernateUtil.closeSessionFactory();
        } 
        return ok;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public boolean verificarDependencia(String campo, IGenerics valor) {
        Session sessao = null;
        Transaction transacao = null;
        boolean ok = false;
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();
            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq(campo, valor));
            
            if (criteria.list().size() > 0)
                ok = true;
            
            transacao.commit();
        } catch (Exception e) {
            transacao.rollback();
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        } 
        return ok;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listar() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.addOrder(Order.asc("id"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarDistinto() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.setProjection(Projections.distinct(Projections.property("carro")));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarDistintoUf() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.setProjection(Projections.distinct(Projections.property("uf")));
            criteria.addOrder(Order.asc("uf"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
   
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarOr() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.addOrder(Order.asc("nome"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarOrdenadoStatusData(){
        Session sessao = null;
        Transaction transacao = null;
        List<T> lista = new ArrayList<T>();
        
        try{
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();
            
            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.addOrder(Order.asc("status"));
            criteria.addOrder(Order.asc("dtCriacao"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return lista;
    }
    
     @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarOrDescricao() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.addOrder(Order.asc("descricao"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarOrPorDescricao() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.addOrder(Order.asc("descricao"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarOrC() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.addOrder(Order.asc("obj_contrato"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarOrData() {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.addOrder(Order.asc("data_inicio"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarOrDataCampo(String campo, Object valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq(campo, valor));
            criteria.addOrder(Order.desc("data_inicio"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
    
     @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorAtivo() {
         Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            
            criteria.addOrder(Order.asc("nome"));
           criteria.add(Restrictions.eq("indAtivo", true));
           // criteria.add(Restrictions.)
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
           // criteria.add(Restrictions.eq("indAtivo", 1));
            //criteria.add(Restrictions.ge(campo, valor));
           
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorCampo(String campo, Object valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq(campo, valor));
            //criteria.add(Restrictions.ge(campo, valor));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            //lista=criteria.list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorCampoDatas(String campo1, Date valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            //criteria.add(Restrictions.eq(campo, valor));
            
            //criteria.add(Restrictions.le(campo1, valor));
            //criteria.add(Restrictions.eq(campo1, null));
            Criterion a = Restrictions.le(campo1, valor);
            Criterion b = Restrictions.eq(campo1, null);
            criteria.add(Restrictions.or(a, b));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public long listarCount() {
        Session sessao = null;
        Transaction transacao = null;

       long registros = 0;
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            //criteria.add(Restrictions.eq(campo, valor));            
            registros = (Long) criteria.setProjection(Projections.rowCount()).uniqueResult();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return registros;
    }
    
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorCriador(String campo, Object valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq(campo, valor));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return lista;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorCampoOr(String campo, Object valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);            
            criteria.add(Restrictions.eq(campo, valor));
            criteria.addOrder(Order.asc("nome"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return lista;
    }
    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorCampoOrContrato(String campo, Object valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);            
            criteria.add(Restrictions.eq(campo, valor));
            criteria.addOrder(Order.asc("obj_contrato"));
            lista = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return lista;
    }
    
    
    
    @SuppressWarnings("CallToThreadDumpStack")
    public T buscarPorCampo(String campo, Object valor) {
        Session sessao = null;
        Transaction transacao = null;
        T resultado = null;
        
          
        

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq(campo, valor));
            resultado = (T) criteria.uniqueResult();
            transacao.commit();
        } catch (Exception e) { 
            
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return resultado;
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    public T buscarPorCampoUnico(String campo, Object valor) {
        Session sessao = null;
        Transaction transacao = null;
        T resultado = null;
        
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq(campo, valor));
            resultado = (T) criteria.uniqueResult();
            transacao.commit();
        } catch (Exception e) { 
            
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return resultado;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public T buscarPorId(int id) {
        Session sessao = null;
        Transaction transacao = null;
        T resultado = null;

        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();
            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq("id", id));
            resultado = (T) criteria.uniqueResult();
            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return resultado;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public boolean verificarExistente(String campo, String valor) {

        Session sessao = null;
        Transaction transacao = null;

        boolean existe = false;
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();
            Criteria criteria = sessao.createCriteria(this.classe);
            criteria.add(Restrictions.eq(campo, valor).ignoreCase());

            if (criteria.uniqueResult() != null) {
                existe = true;
            }

            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return existe;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorCampo(String tabela, String campo, IGenerics valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();
            lista = sessao.createQuery("from " + tabela + " where " + campo + " = " + valor).list();

            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return lista;
    }

    @SuppressWarnings("CallToThreadDumpStack")
    public List<T> listarPorCampo(String tabela, String campo, int valor) {
        Session sessao = null;
        Transaction transacao = null;

        List<T> lista = new ArrayList<T>();
        try {
            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();

            sessao = HibernateUtil.getSessionFactory().getCurrentSession();
            transacao = sessao.beginTransaction();
            lista = sessao.createQuery("from " + tabela + " where " + campo + " = " + valor).list();

            transacao.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.closeSessionFactory();
        }
        return lista;
    }
}
