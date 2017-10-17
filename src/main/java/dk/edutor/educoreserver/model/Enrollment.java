package dk.edutor.educoreserver.model;

import org.crygier.graphql.annotation.GraphQLIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table( name = "Enrollment" )
public class Enrollment implements Serializable {

    @GraphQLIgnore
    @EmbeddedId
    private EnrollmentId id;

    @Column( name = "role" )
    private String role;

    @Column( name = "active" )
    private Boolean active;

    @MapsId( value = "id.User_id")
    @ManyToOne
    User user;



//    @ManyToOne
//    @JoinColumn( name = "Group_id", referencedColumnName = "id",
//            updatable = false, insertable = false )
//    private Grouping grouping;
//
//    @ManyToOne
//    @JoinColumn( name = "User_id", referencedColumnName = "id",
//            updatable = false, insertable = false )
//    private User user;
    public Enrollment() {
    }

//    public Enrollment( Integer userid ) {
//        this.userid = userid;
//    }
//    public Integer getUserid() {
//        return userid;
//    }
//
//    public void setUserid( Integer userid ) {
//        this.userid = userid;
//    }
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
        return getId().getGrouping();
    }

    public void setGrouping( Grouping grouping ) {
        id.setGrouping(grouping);
    }

    public User getUser() {
        return getId().getUser();
    }

    public void setUser( User user ) {
        id.setUser(user);
    }


    public EnrollmentId getId() {
        return id;
    }

    public void setId( EnrollmentId id ) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Enrollment[ user=" + getUser().getFullName()
                + " grouping=" + getGrouping().getName() + " ]";
    }

    @Override
    public int hashCode() {
        return id.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
