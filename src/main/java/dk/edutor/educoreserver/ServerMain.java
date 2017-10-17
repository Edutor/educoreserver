package dk.edutor.educoreserver;

import org.crygier.graphql.GraphQLExecutor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
@EnableAutoConfiguration
@EntityScan
class ServerMain {

    private static EntityManager em;

    public static void main(String[] args) {
        ApplicationContext ac = SpringApplication.run(ServerMain.class, args);
    }

    @Bean
    public GraphQLExecutor graphQLExecutor() {
        System.out.println("Kommer vi hertil?");
        return new GraphQLExecutor(getEntityManager());
    }

    @Bean
    public GraphQlController graphQlController() {
        System.out.println("Kommer vi hertil2?");
        return new GraphQlController();
    }

    @Bean
    public EntityManager entityManager() {
        return getEntityManager();
    }

    private synchronized static EntityManager getEntityManager() {
        if (em == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("educore");
            em = emf.createEntityManager();
        }
        return em;

    }

}

