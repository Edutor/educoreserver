package dk.edutor.educoreserver.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 The purpose of Enrollment is to...

 @author kasper
 */
@Entity
@Table( name = "Enrollment" )
@NamedQueries( {
    @NamedQuery( name = "Enrollment.findAll", query = "SELECT e FROM Enrollment e" ) } )
public class Enrollment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic( optional = false )
    @NotNull
    @Column( name = "User_id" )
    private Integer userid;

    @Size( max = 45 )
    private String role;

    private Boolean active;

    @JoinColumn( name = "Group_id", referencedColumnName = "id" )
    @ManyToOne( optional = false )
    private Grouping grouping;

    @JoinColumn( name = "User_id", referencedColumnName = "id", insertable = false, updatable = false )
    @OneToOne( optional = false )
    private User user;

    public Enrollment() {
    }

    public Enrollment( Integer userid ) {
        this.userid = userid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid( Integer userid ) {
        this.userid = userid;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive( Boolean active ) {
        this.active = active;
    }

    public Grouping getGrouping() {
        return grouping;
    }

    public void setGrouping( Grouping grouping ) {
        this.grouping = grouping;
    }

    public User getUser() {
        return user;
    }

    public void setUser( User user ) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( userid != null ? userid.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof Enrollment ) ) {
            return false;
        }
        Enrollment other = (Enrollment) object;
        if ( ( this.userid == null && other.userid != null ) || ( this.userid != null && !this.userid.equals( other.userid ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.edutor.educoreserver.model.Enrollment[ userid=" + userid + " ]";
    }

}
