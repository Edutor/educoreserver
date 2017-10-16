package dk.edutor.educoreserver.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 The purpose of Grouping is to...

 @author kasper
 */
@Entity
@Table( name = "Group" )
@NamedQueries( {
    @NamedQuery( name = "Grouping.findAll", query = "SELECT g FROM Grouping g" ) } )
public class Grouping implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic( optional = false )
    @NotNull
    private Integer id;
    
    @Size( max = 45 )
    private String name;
    
    @Temporal( TemporalType.DATE )
    private Date start;
    
    @Temporal( TemporalType.DATE )
    private Date end;
    
    @Size( max = 45 )
    private String type;
    
    @OneToMany( mappedBy = "parent" )
    private Collection<Grouping> groupingCollection;
    
    @JoinColumn( name = "parent", referencedColumnName = "id" )
    @ManyToOne
    private Grouping parent;
    
    @OneToMany( cascade = CascadeType.ALL, mappedBy = "grouping" )
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
        return "dk.edutor.educoreserver.model.Grouping[ id=" + id + " ]";
    }

}