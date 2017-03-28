/* This software is licensed under XorGame 1.0 license. License does not expire.
 * Can be used for creating unlimited applications.
 * Can be distributed in binary or object form only.
 * Non-commercial use only.
 * Cannot modify source-code for any purpose (cannot create derivative works).
 */

package xorgame;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.persistence.Id;

/**
 * Entity class for economy_ranking view - contains all users ordered descending by its economy points. 
 * @author Artur M. Brodzki
 */
@Entity
@Table(name = "economy_ranking")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EconomyRanking.findAll", query = "SELECT e FROM EconomyRanking e"),
    @NamedQuery(name = "EconomyRanking.findByNick", query = "SELECT e FROM EconomyRanking e WHERE e.nick = :nick"),
    @NamedQuery(name = "EconomyRanking.findByEconomyPoints", query = "SELECT e FROM EconomyRanking e WHERE e.economyPoints = :economyPoints")})
public class EconomyRanking implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "Nick")
    private String nick;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EconomyPoints")
    private Double economyPoints;

    public EconomyRanking() {
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
    
}
