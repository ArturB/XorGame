/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity class for research_ranking view - containing all user ordered descending by their research points. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "research_ranking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ResearchRanking.findAll", query = "SELECT r FROM ResearchRanking r"),
    @NamedQuery(name = "ResearchRanking.findByNick", query = "SELECT r FROM ResearchRanking r WHERE r.nick = :nick"),
    @NamedQuery(name = "ResearchRanking.findByResearchPoints", query = "SELECT r FROM ResearchRanking r WHERE r.researchPoints = :researchPoints")})
public class ResearchRanking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nick")
    private String nick;
    @Basic(optional = false)
    @Column(name = "ResearchPoints")
    private float researchPoints;

    public ResearchRanking() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public float getResearchPoints() {
        return researchPoints;
    }

    public void setResearchPoints(float researchPoints) {
        this.researchPoints = researchPoints;
    }
    
}
