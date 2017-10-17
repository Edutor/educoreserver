package dk.edutor.educoreserver;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.crygier.graphql.GraphQLSchemaBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SchemaMain  {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("educore");
        EntityManager entityManager = emf.createEntityManager();

        GraphQLSchema graphQLSchema = new GraphQLSchemaBuilder(entityManager).getGraphQLSchema();
        System.out.println(graphQLSchema.getDirectives().get(0));
    }
}
