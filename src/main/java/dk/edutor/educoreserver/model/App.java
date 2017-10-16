package dk.edutor.educoreserver.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The purpose of App is to...
 * @author kasper
 */
@Entity
@Table( name = "App" )
@NamedQueries( { 
    @NamedQuery( name = "App.findAll", query = "SELECT a FROM App a" ) } )
public class App implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic( optional = false )
    @NotNull
    private Integer id;
    
    @Size( max = 45 )
    private String name;
    
    @Size( max = 45 )
    private String desc;
    
    @JoinTable( name = "Appmin", joinColumns = { 
        @JoinColumn( name = "App_id", referencedColumnName = "id" ) }, inverseJoinColumns = { 
        @JoinColumn( name = "User_id", referencedColumnName = "id" ) } )
    @ManyToMany
    private Collection<User> users;

    public App() {
    }

    public App( Integer id ) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId( Integer id ) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc( String desc ) {
        this.desc = desc;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers( Collection<User> users ) {
        this.users = users;
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
        if ( !( object instanceof App ) ) {
            return false;
        }
        App other = (App) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dk.edutor.educoreserver.model.App[ id=" + id + " ]";
    }

}
