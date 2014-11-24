package util;


import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.ScheduleEvent;

public class PersistFactory<T> {

    public void persistGeneric(IGenerics generico) {
        DAOGenerics<T> dao = new DAOGenerics<T>();
        FacesContext context = FacesContext.getCurrentInstance();

        if (dao.salvar(generico)) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Salvo com Sucesso!", ""));
        } else {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problema ao realizar a operação!", ""));
        }
    }
    public void removeGeneric(IGenerics generico) {
        DAOGenerics<T> dao = new DAOGenerics<T>();
        FacesContext context = FacesContext.getCurrentInstance();
        if (dao.excluir(generico)) {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Registro removido com sucesso!", ""));
        } else {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Problema ao remover o registro!", ""));
        }
    }
}
