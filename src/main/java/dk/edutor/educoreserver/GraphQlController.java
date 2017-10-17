package dk.edutor.educoreserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ExecutionResult;
import org.crygier.graphql.GraphQLExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
class GraphQlController {

    @Autowired
    private GraphQLExecutor graphQLExecutor;

    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(path = "/graphql", method = RequestMethod.POST)
    ExecutionResult graphQl(@RequestBody final GraphQLInputQuery query) throws IOException {
        Map<String, Object> variables = query.getVariables() != null ? objectMapper.readValue(query.getVariables(), Map.class) : null;

        return graphQLExecutor.execute(query.query, variables);
    }

    public static final class GraphQLInputQuery {
        private String query;
        private String variables;

        public GraphQLInputQuery() { }

        public GraphQLInputQuery(String query, String variables) {
            this.query = query;
            this.variables = variables;
        }

        public String getQuery() {
            return query;
        }

        public String getVariables() {
            return variables;
        }
    }

}
