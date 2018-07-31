package de.gfn.scouts;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author tlubowiecki
 */
@ManagedBean
public class MainController {
    
    private Scout currentScout = new Scout();
    
    private EntityManager em;
    
    {
        em = Persistence.createEntityManagerFactory("WebScoutsPU").createEntityManager();
    }
    
    public List<Scout> getScouts() {
        List<Scout> scouts = em.createQuery("SELECT s FROM Scout s").getResultList();
        return scouts;
    }
    
    public Scout getScout(long id) {
        return em.find(Scout.class, id);
    }
    
    public String saveScout(Scout scout) {
        
        return Pages.SCOUT_LIST;
    }
    
    public String editScout(Scout scout) {
        
        return Pages.SCOUT_EDIT;
    }
    
    public String deleteScout(Scout scout) {
        if(scout instanceof Scout) {
            em.getTransaction().begin();
            em.remove(scout);
            em.getTransaction().commit();
            currentScout = new Scout();
        }
        return Pages.SCOUT_LIST;
    }
    
    public String viewScout(Scout scout) {
        currentScout = scout;
        return Pages.SCOUT_SINGLE;
    }

    public Scout getCurrentScout() {
        return currentScout;
    }

    public void setCurrentScout(Scout currentScout) {
        this.currentScout = currentScout;
    }
}
