/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package czolgista.server.beans;

import czolgista.server.entity.Users;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Persistence;

/**
 *
 * @author mnarusze
 */
@ManagedBean
@RequestScoped
public class UsersBean {
    @Id @GeneratedValue
    
    public Users user;
    private EntityManager em;
    
    public UsersBean() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PersistenceUsers");
        em = emf.createEntityManager();
    }
    
    public void createUser() {
        em.getTransaction().begin();
        em.persist(user);
        em.close();
    }
}
