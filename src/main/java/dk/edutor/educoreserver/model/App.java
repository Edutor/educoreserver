package dk.edutor.educoreserver.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 The purpose of App is to...

 @author kasper
 */
@Entity
@Table( name = "App" )
public class App implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    @NotNull
    private Integer id;

    private String name;

    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription( String description ) {
        this.description = description;
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
        App other = (App) object;
        return this.id.equals( other.id ); 
    }

    @Override
    public String toString() {
        return "App[ name=" + name + " ]";
    }

}
