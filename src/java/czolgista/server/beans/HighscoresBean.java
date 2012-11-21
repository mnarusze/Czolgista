/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package czolgista.server.beans;

import czolgista.server.entity.Highscores;
import czolgista.server.entity.Users;
import java.lang.reflect.Array;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class HighscoresBean {
    @Id @GeneratedValue 
    private Highscores highscore;
    private EntityManagerFactory emf;
    private List<Highscores> highscores;
    
    private EntityManager getEntityManager() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("CzolgistaPU");
        }
        return emf.createEntityManager();
    }
    
    public HighscoresBean() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q = em.createQuery("select h from Highscores as h");
            highscores = q.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Highscores> getHighscores() {
        return highscores;
    }

    public Highscores getHighscore() {
        return highscore;
    }
    
    public String getUsername(Highscores highscore) {
        EntityManager em = getEntityManager();
        try {
            Query query = em.createQuery(
                "SELECT u FROM Users u WHERE u.id = '" + highscore.getUserId() + "'");
            List<Users> users = query.getResultList();
            return users.get(0).getUsername();
        } finally {
            em.close();
        }
    }

    public void setHighscore(Highscores highscore) {
        this.highscore = highscore;
    }
    
}
