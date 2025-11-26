package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features/AddNewAdressBook.feature" ,
        glue = "StepDefinitions",

        plugin = {"pretty","html:target\\cucumber\\rapport.html"}
)

public class Runner {


}
