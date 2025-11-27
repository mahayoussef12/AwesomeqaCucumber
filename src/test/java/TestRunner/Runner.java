package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/Features/Logout.feature" ,
        glue = "StepDefinitions",

        plugin = {"pretty","html:C:/Users/PCS/Desktop/BootCamp QA Engineering/awesomeqa/target/cucumber/rapport.html"}
)

public class Runner {


}
