/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package czolgista.server.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mnarusze
 */
@Entity
@Table(name = "highscores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Highscores.findAll", query = "SELECT h FROM Highscores h"),
    @NamedQuery(name = "Highscores.findById", query = "SELECT h FROM Highscores h WHERE h.id = :id"),
    @NamedQuery(name = "Highscores.findByUserId", query = "SELECT h FROM Highscores h WHERE h.userId = :userId"),
    @NamedQuery(name = "Highscores.findByScore", query = "SELECT h FROM Highscores h WHERE h.score = :score"),
    @NamedQuery(name = "Highscores.findByMadeOn", query = "SELECT h FROM Highscores h WHERE h.madeOn = :madeOn")})
public class Highscores implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Short id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private short userId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score")
    private int score;
    @Basic(optional = false)
    @NotNull
    @Column(name = "made_on")
    @Temporal(TemporalType.TIMESTAMP)
    private Date madeOn;

    public Highscores() {
    }

    public Highscores(Short id) {
        this.id = id;
    }

    public Highscores(Short id, short userId, int score, Date madeOn) {
        this.id = id;
        this.userId = userId;
        this.score = score;
        this.madeOn = madeOn;
    }

    public Short getId() {
        return id;
    }

    public void setId(Short id) {
        this.id = id;
    }

    public short getUserId() {
        return userId;
    }

    public void setUserId(short userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getMadeOn() {
        return madeOn;
    }

    public void setMadeOn(Date madeOn) {
        this.madeOn = madeOn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Highscores)) {
            return false;
        }
        Highscores other = (Highscores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "czolgista.server.entity.Highscores[ id=" + id + " ]";
    }
    
}
