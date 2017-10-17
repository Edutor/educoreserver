package dk.edutor.educoreserver.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "Enrollment" )
public class Enrollment implements Serializable {

    @Embeddable
    public static class EnrollmentId implements Serializable {

        @ManyToOne( fetch = FetchType.EAGER )
        @JoinColumn( name = "User_id", nullable = false )
        private User user;

        @ManyToOne( fetch = FetchType.EAGER )
        @JoinColumn( name = "Group_id", nullable = false )
        private Grouping grouping;

        @Override
        public int hashCode() {
            return user.hashCode()+grouping.hashCode();
        }

        @Override
        public boolean equals( Object obj ) {
            if ( obj == null ) {
                return false;
            }
            if ( !( obj instanceof EnrollmentId ) ) {
                return false;
            }
            EnrollmentId other = (EnrollmentId) obj;
            return user.getId() == other.user.getId() && grouping.getId() == other.grouping.getId();
        }

        public User getUser() {
            return user;
        }

        public void setUser( User user ) {
            this.user = user;
        }

        public Grouping getGrouping() {
            return grouping;
        }

        public void setGrouping( Grouping grouping ) {
            this.grouping = grouping;
        }
    }

    @EmbeddedId
    private EnrollmentId id;

    @Column( name = "role" )
    private String role;

    @Column( name = "active" )
    private Boolean active;

    public Enrollment() {
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
        return getId().grouping;
    }

    public void setGrouping( Grouping grouping ) {
        id.grouping = grouping;
    }

    public User getUser() {
        return getId().user;
    }

    public void setUser( User user ) {
        id.user = user;
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
        return id.hashCode(); 
    }
    
}
