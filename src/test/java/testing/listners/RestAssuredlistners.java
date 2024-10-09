package testing.listners;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.logging.LogManager;

public class RestAssuredlistners implements Filter {

    private static final LogManager logger = LogManager.getLogManager();

    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        Response response = ctx.next(requestSpec, responseSpec);

        if (response.getStatusCode() != 200 & response.getStatusCode() != 201) {
//        logger.error("\n Method " + requestSpec.getMethod()
//        + "\n URI =>"+ requestSpec.getURI()
//        +"\n Request Body =>"+requestSpec.getBody() +
//                "\n Response Body =>"+ requestSpec.getBody().toString());
//
//    }

        }
        return response;
    }
}