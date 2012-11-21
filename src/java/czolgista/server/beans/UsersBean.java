/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package czolgista.server.beans;

import czolgista.server.entity.Users;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author mnarusze
 */
@ManagedBean
@RequestScoped
public class UsersBean {
    @Id @GeneratedValue
    
    Users user;
    private EntityManagerFactory emf;
    
    public UsersBean() {
        user = new Users();
    }
    
    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("CzolgistaPU");
        }
        return emf.createEntityManager();
    }
    
    public String addUser(Users users) {
        EntityManager em = getEntityManager();
        try{
            em.getTransaction().begin();
            Query query = em.createQuery(
                "SELECT u FROM Users u WHERE u.username = '" + users.getUsername() + "'");
            if (!query.getResultList().isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Błąd: podana nazwa użytkownika już istnieje!", null));
                    return "failure";
            }
            em.persist(users);
            em.getTransaction().commit();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Nowy użytkownik został dołączony do bazy", null));
            return "success";
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Błąd: nie udało się stworzyć użytkownika", null));
            ex.printStackTrace();
            return "failure";
        }
            finally {
                em.close();
        }
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
    
}
