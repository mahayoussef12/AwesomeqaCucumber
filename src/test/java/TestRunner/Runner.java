package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/Features/Login.feature",
        glue = "StepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber-report/rapport.html",
                "json:target/cucumber-report/cucumber.json"
        }
)
public class Runner {

}
