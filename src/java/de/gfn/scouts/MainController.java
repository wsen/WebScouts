package de.gfn.scouts;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author tlubowiecki
 */
@ManagedBean
@SessionScoped
public class MainController {
    
    private Scout currentScout = new Scout();
    
    private Camp currentCamp = new Camp();
    
    private EntityManager em;
    
    {
        em = Persistence.createEntityManagerFactory("WebScoutsPU").createEntityManager();
    }
    
    public List<Scout> getScouts() {
        List<Scout> scouts = em.createQuery("SELECT s FROM Scout s").getResultList();
        return scouts;
    }
    
    public List<Camp> getCamps() {
        List<Camp> camps = em.createQuery("SELECT c FROM Camp c").getResultList();
        return camps;
    }
    
    public Scout getScout(long id) {
        return em.find(Scout.class, id);
    }
    
    public String saveScout(Scout scout) {
        
        if(scout instanceof Scout) {
            if(scout.getId() == null) {
                insertScout();
            }
            else {
                updateScout();
            }
            currentScout = new Scout();
        }
        
        return Pages.SCOUT_LIST;
    }
    
    public String saveCamp(Camp camp) {
        
        if(camp instanceof Camp) {
            if(camp.getId() == null) {
                insertCamp(camp);
            }
            else {
                updateCamp(camp);
            }
            currentCamp = new Camp();
        }
        
        return Pages.CAMP_LIST;
    }
    
    private void insertScout() {
        em.getTransaction().begin();
        em.persist(currentScout);
        em.getTransaction().commit();
    }
    
    private void insertCamp(Camp camp) {
        em.getTransaction().begin();
        em.persist(currentCamp);
        em.getTransaction().commit();
    }
    
    private void updateScout() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
    
    private void updateCamp(Camp camp) {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }
    
    public String editScout(Scout scout) {
        currentScout = scout;
        return Pages.SCOUT_EDIT;
    }
    
    public String editCamp(Camp camp) {
        currentCamp = camp;
        return Pages.CAMP_EDIT;
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
    
    public String deleteCamp(Camp camp) {
        if(camp instanceof Camp) {
            em.getTransaction().begin();
            em.remove(camp);
            em.getTransaction().commit();
            currentCamp = new Camp();
        }
        return Pages.CAMP_LIST;
    }
    
    public String addToCamp() {
        em.getTransaction().begin();
        Camp camp = em.find(Camp.class, currentScout.getCampId());
        camp.addScout(currentScout);
        em.getTransaction().commit();
        currentScout.setCampId(null);
        return Pages.SCOUT_SINGLE;
    }
    
    public String viewScout(Scout scout) {
        currentScout = scout;
        return Pages.SCOUT_SINGLE;
    }
    
    public String viewCamp(Camp camp) {
        currentCamp = camp;
        return Pages.CAMP_SINGLE;
    }

    public Scout getCurrentScout() {
        return currentScout;
    }

    public void setCurrentScout(Scout currentScout) {
        this.currentScout = currentScout;
    }

    public Camp getCurrentCamp() {
        return currentCamp;
    }

    public void setCurrentCamp(Camp currentCamp) {
        this.currentCamp = currentCamp;
    }
}
