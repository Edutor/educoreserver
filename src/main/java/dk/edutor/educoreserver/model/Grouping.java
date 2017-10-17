package dk.edutor.educoreserver.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 The purpose of Grouping is to...

 @author kasper
 */
@Entity
@Table( name = "Grouping" )
public class Grouping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    @Temporal( TemporalType.DATE )
    private Date start;
    
    @Temporal( TemporalType.DATE )
    private Date end;
    
    private String type;
    
    @OneToMany( mappedBy = "parent" )
    private Collection<Grouping> groupingCollection;
    
    @JoinColumn( name = "parent", referencedColumnName = "id" )
    @ManyToOne
    private Grouping parent;
    
    @OneToMany( mappedBy = "id.grouping" )
    private Collection<Enrollment> enrollments;

    public Grouping() {
    }

    public Grouping( Integer id ) {
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

    public Date getStart() {
        return start;
    }

    public void setStart( Date start ) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd( Date end ) {
        this.end = end;
    }

    public String getType() {
        return type;
    }

    public void setType( String type ) {
        this.type = type;
    }

    public Collection<Grouping> getGroupingCollection() {
        return groupingCollection;
    }

    public void setGroupingCollection( Collection<Grouping> groupingCollection ) {
        this.groupingCollection = groupingCollection;
    }

    public Grouping getParent() {
        return parent;
    }

    public void setParent( Grouping parent ) {
        this.parent = parent;
    }

    public Collection<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments( Collection<Enrollment> enrollments ) {
        this.enrollments = enrollments;
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
        if ( !( object instanceof Grouping ) ) {
            return false;
        }
        Grouping other = (Grouping) object;
        if ( ( this.id == null && other.id != null ) || ( this.id != null && !this.id.equals( other.id ) ) ) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grouping[ name=" + name + " ]";
    }

}
