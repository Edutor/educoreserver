package dk.edutor.educoreserver.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The purpose of User is to...
 * @author kasper
 */
@Entity
@Table( name = "User" )
@NamedQueries( { 
    @NamedQuery( name = "User.findAll", query = "SELECT u FROM User u" ) } )
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic( optional = false )
    @NotNull
    private Integer id;
    
    @Size( max = 100 )
    @Column( name = "fullname" )
    private String fullName;
    
    @Size( max = 45 )
    @Column( name = "callname" )
    private String callName;
    
    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 100 )
    @Column( name = "cph_email" )
    private String cphEmail;
    
    @Size( max = 100 )
    @Column( name = "pref_email" )
    private String prefEmail;
    
    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 256 )
    @Column( name = "pw_hash" )
    private String pwHash;
    
    @Basic( optional = false )
    @NotNull
    @Size( min = 1, max = 45 )
    private String role;
    
    @ManyToMany( mappedBy = "users" )
    private Collection<App> apps;
    
    @OneToOne( cascade = CascadeType.ALL, mappedBy = "user" )
    private Enrollment enrollment;

    public User() {
    }

    public User( Integer id ) {
        this.id = id;
    }

    public User( Integer id, String cphEmail, String pwHash, String role ) {
        this.id = id;
        this.cphEmail = cphEmail;
        this.pwHash = pwHash;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName( String fullName ) {
        this.fullName = fullName;
    }

    public String getCallName() {
        return callName;
    }

    public void setCallName( String callName ) {
        this.callName = callName;
    }

    public String getCphEmail() {
        return cphEmail;
    }

    public void setCphEmail( String cphEmail ) {
        this.cphEmail = cphEmail;
    }

    public String getPrefEmail() {
        return prefEmail;
    }

    public void setPrefEmail( String prefEmail ) {
        this.prefEmail = prefEmail;
    }

    public String getPwHash() {
        return pwHash;
    }

    public void setPwHash( String pwHash ) {
        this.pwHash = pwHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public Collection<App> getApps() {
        return apps;
    }

    public void setApps( Collection<App> apps ) {
        this.apps = apps;
    }

    public Enrollment getEnrollment() {
        return enrollment;
    }

    public void setEnrollment( Enrollment enrollment ) {
        this.enrollment = enrollment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if ( !( object instanceof User ) ) {
            return false;
        }
        User other = (User) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.edutor.educoreserver.model.User[ id=" + id + " ]";
    }

}
