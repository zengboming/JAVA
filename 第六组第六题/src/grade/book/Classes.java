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
@Table(name = "classes", catalog = "gradeBook", schema = "")
@NamedQueries({
    @NamedQuery(name = "Classes.findAll", query = "SELECT c FROM Classes c"),
    @NamedQuery(name = "Classes.findByCid", query = "SELECT c FROM Classes c WHERE c.cid = :cid"),
    @NamedQuery(name = "Classes.findByIid", query = "SELECT c FROM Classes c WHERE c.iid = :iid"),
    @NamedQuery(name = "Classes.findByYear", query = "SELECT c FROM Classes c WHERE c.year = :year"),
    @NamedQuery(name = "Classes.findByQuarter", query = "SELECT c FROM Classes c WHERE c.quarter = :quarter"),
    @NamedQuery(name = "Classes.findByCname", query = "SELECT c FROM Classes c WHERE c.cname = :cname"),
    @NamedQuery(name = "Classes.findBySection", query = "SELECT c FROM Classes c WHERE c.section = :section")})
public class Classes implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CID")
    private Integer cid;
    @Basic(optional = false)
    @Column(name = "IID")
    private int iid;
    @Basic(optional = false)
    @Column(name = "YEAR")
    private int year;
    @Basic(optional = false)
    @Column(name = "QUARTER")
    private String quarter;
    @Basic(optional = false)
    @Column(name = "CNAME")
    private String cname;
    @Basic(optional = false)
    @Column(name = "SECTION")
    private int section;

    public Classes() {
    }

    public Classes(Integer cid) {
        this.cid = cid;
    }

    public Classes(Integer cid, int iid, int year, String quarter, String cname, int section) {
        this.cid = cid;
        this.iid = iid;
        this.year = year;
        this.quarter = quarter;
        this.cname = cname;
        this.section = section;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        Integer oldCid = this.cid;
        this.cid = cid;
        changeSupport.firePropertyChange("cid", oldCid, cid);
    }

    public int getIid() {
        return iid;
    }

    public void setIid(int iid) {
        int oldIid = this.iid;
        this.iid = iid;
        changeSupport.firePropertyChange("iid", oldIid, iid);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        int oldYear = this.year;
        this.year = year;
        changeSupport.firePropertyChange("year", oldYear, year);
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        String oldQuarter = this.quarter;
        this.quarter = quarter;
        changeSupport.firePropertyChange("quarter", oldQuarter, quarter);
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        String oldCname = this.cname;
        this.cname = cname;
        changeSupport.firePropertyChange("cname", oldCname, cname);
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        int oldSection = this.section;
        this.section = section;
        changeSupport.firePropertyChange("section", oldSection, section);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classes)) {
            return false;
        }
        Classes other = (Classes) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "grade.book.Classes[ cid=" + cid + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
