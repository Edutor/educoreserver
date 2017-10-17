package dk.edutor.educoreserver.model;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class EnrollmentId implements Serializable {
//        @Column(name = "User_id")
//        private int user;
//        @Column(name = "Group_id")
//        private int grouping;

    @ManyToOne( fetch = FetchType.EAGER )
//        /@JoinColumn( name = "User_id", nullable = false, insertable = false, updatable = false )
    @JoinColumn( name = "User_id", nullable = false )
    private User user;

    @ManyToOne( fetch = FetchType.EAGER )
//        @JoinColumn( name = "Group_id", nullable = false, insertable = false, updatable = false )
    @JoinColumn( name = "Group_id", nullable = false )
    private Grouping grouping;

    @Override
    public int hashCode() {
//            return Integer.hashCode( getUser().getId() ) + Integer.hashCode( getGrouping().getId() );
        return user.hashCode()+grouping.hashCode();
    }

    @Override
    public boolean equals( Object obj ) {
        if ( obj == null ) {
            return false;
        }
        if ( !( obj instanceof EnrollmentId) ) {
            return false;
        }
        final EnrollmentId other = (EnrollmentId) obj;
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
