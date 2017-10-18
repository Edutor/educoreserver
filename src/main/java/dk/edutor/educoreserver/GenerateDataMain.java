package dk.edutor.educoreserver;

import static dk.edutor.educoreserver.RandomData.*;
import dk.edutor.educoreserver.model.*;
import java.util.List;
import javax.persistence.*;

public class GenerateDataMain {

    public static void main (String...args) throws InterruptedException {

        insertAll( );
    }

    private static void generateAll() {
        List<User> users = generateUsers( 300 );
        List<Grouping> groupings = generateGroups( 15 );
        List<App> apps = generateApps();
        List<Enrollment> enrollments = enroll(groupings, users);
        setAppmins( apps, users );
        
        for(User u : users){
            System.out.println( u );
        }
        for(Enrollment e : enrollments){
            System.out.println( e );
        }
    }

    private static void insertAll() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("educore");
        EntityManager em = emf.createEntityManager();
        
        List<User> users = generateUsers( 300 );
        List<Grouping> groupings = generateGroups( 15 );
        List<App> apps = generateApps();
        List<Enrollment> enrollments = enroll(groupings, users);
        setAppmins( apps, users );

        em.getTransaction().begin();
        for(User user: users) em.persist( user);
        for(Grouping grp: groupings) em.persist( grp);
        for(App app: apps) em.persist( app);
        for(Enrollment enroll: enrollments) em.persist( enroll);
        
        em.getTransaction().commit();
        emf.close();
    }
}
