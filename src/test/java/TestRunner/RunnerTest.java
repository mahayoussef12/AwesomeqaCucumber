package TestRunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
@CucumberOptions(
        features = "src/test/java/Features/AddToWishList.feature",
        glue = "StepDefinitions",
        plugin = {
                "pretty",
                "html:target/cucumber/rapport1.html",
                "json:target/cucumber/report.json"
        }
)
public class RunnerTest extends AbstractTestNGCucumberTests {
}
