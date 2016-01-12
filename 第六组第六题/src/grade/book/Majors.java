/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grade.book;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Cogallen
 */
@Entity
@Table(name = "majors", catalog = "gradeBook", schema = "")
@NamedQueries({
    @NamedQuery(name = "Majors.findAll", query = "SELECT m FROM Majors m"),
    @NamedQuery(name = "Majors.findByCode", query = "SELECT m FROM Majors m WHERE m.code = :code"),
    @NamedQuery(name = "Majors.findByMajor", query = "SELECT m FROM Majors m WHERE m.major = :major"),
    @NamedQuery(name = "Majors.findByShortName", query = "SELECT m FROM Majors m WHERE m.shortName = :shortName")})
public class Majors implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Code")
    private Integer code;
    @Column(name = "Major")
    private String major;
    @Column(name = "shortName")
    private String shortName;

    public Majors() {
    }

    public Majors(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        Integer oldCode = this.code;
        this.code = code;
        changeSupport.firePropertyChange("code", oldCode, code);
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        String oldMajor = this.major;
        this.major = major;
        changeSupport.firePropertyChange("major", oldMajor, major);
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        String oldShortName = this.shortName;
        this.shortName = shortName;
        changeSupport.firePropertyChange("shortName", oldShortName, shortName);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (code != null ? code.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Majors)) {
            return false;
        }
        Majors other = (Majors) object;
        if ((this.code == null && other.code != null) || (this.code != null && !this.code.equals(other.code))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grade.book.Majors[ code=" + code + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
