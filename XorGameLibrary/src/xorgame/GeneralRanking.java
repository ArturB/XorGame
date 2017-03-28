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
 * Entity class for general_ranking view - contains all users with data about its economy, military, research and total points. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "general_ranking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeneralRanking.findAll", query = "SELECT g FROM GeneralRanking g"),
    @NamedQuery(name = "GeneralRanking.findByNick", query = "SELECT g FROM GeneralRanking g WHERE g.nick = :nick"),
    @NamedQuery(name = "GeneralRanking.findByEconomyPoints", query = "SELECT g FROM GeneralRanking g WHERE g.economyPoints = :economyPoints"),
    @NamedQuery(name = "GeneralRanking.findByResearchPoints", query = "SELECT g FROM GeneralRanking g WHERE g.researchPoints = :researchPoints"),
    @NamedQuery(name = "GeneralRanking.findByMilitaryPoints", query = "SELECT g FROM GeneralRanking g WHERE g.militaryPoints = :militaryPoints")})
public class GeneralRanking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nick")
    private String nick;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EconomyPoints")
    private Double economyPoints;
    @Column(name = "ResearchPoints")
    private Float researchPoints;
    @Column(name = "MilitaryPoints")
    private Double militaryPoints;

    public GeneralRanking() {
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Double getEconomyPoints() {
        return economyPoints;
    }

    public void setEconomyPoints(Double economyPoints) {
        this.economyPoints = economyPoints;
    }

    public Float getResearchPoints() {
        return researchPoints;
    }

    public void setResearchPoints(Float researchPoints) {
        this.researchPoints = researchPoints;
    }

    public Double getMilitaryPoints() {
        return militaryPoints;
    }

    public void setMilitaryPoints(Double militaryPoints) {
        this.militaryPoints = militaryPoints;
    }
    
}
