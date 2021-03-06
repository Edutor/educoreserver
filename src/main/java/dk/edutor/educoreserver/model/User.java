package dk.edutor.educoreserver.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The purpose of User is to...
 * @author kasper
 */
@Entity
@Table( name = "User" )
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column( name = "fullname" )
    private String fullName;
    
    @Column( name = "callname" )
    private String callName;
    
    @Column( name = "cph_email" )
    private String cphEmail;
    
    @Column( name = "pref_email" )
    private String prefEmail;
    
    @Column( name = "pw_hash" )
    private String pwHash;
    
    private String role;
    
    @ManyToMany( mappedBy = "users" )
    private Collection<App> apps;
    
    @OneToMany(mappedBy = "id.user")
    private Collection<Enrollment> enrollments;

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



    @Override
    public int hashCode() {
        int hash = 0;
        hash += ( id != null ? id.hashCode() : 0 );
        return hash;
    }

    @Override
    public boolean equals( Object object ) {
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
        return "User[ name = " + fullName + " callName = " + callName + " email=" + prefEmail + " ]";
    }

    public Collection<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments( Collection<Enrollment> enrollments ) {
        this.enrollments = enrollments;
    }

}
