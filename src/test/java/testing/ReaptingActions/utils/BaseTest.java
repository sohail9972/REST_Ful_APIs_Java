package testing.ReaptingActions.utils;

import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.LogManager;

public class BaseTest {

    private static final LogManager logger = LogManager.getLogManager();

    @BeforeMethod
    public void beforeMethod() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
        @AfterMethod
        public void afterMethod(ITestResult result){

            if (result.getStatus()==ITestResult.FAILURE){
                Throwable t = result.getThrowable();

                StringWriter error = new StringWriter();
                t.printStackTrace(new PrintWriter(error));

//                logger.info();
            }

        }
    }


