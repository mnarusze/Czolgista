/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package czolgista.server.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
            logout();
        }
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        try {
            request.login(username, password);
            setUsername(username);
            return "success";
        } catch (ServletException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Błąd: nie udało się zalogować", null));
            ex.printStackTrace();
        }
        return "failure";
    }
    
    public void logout() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (session != null) {
            try {
                request.logout();
                session.invalidate();
                FacesContext.getCurrentInstance().getExternalContext().redirect("/Czolgista");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        //FacesContext.getCurrentInstance().getApplication().getNavigationHandler().
                //handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
    }
    
    public boolean isLogged() {
        if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null)
        {
            return true;
        }
        return false;
    }
    
    public LoginBean() {
        logout();
    }
}
