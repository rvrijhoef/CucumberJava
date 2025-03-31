package nl.squerist.cursus.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/resources/nl/squerist/cursus/cucumber/features/"},
        glue = {"StepDefs"},
        plugin = {"pretty"},
        publish = true
)
public class RunCucumberTest {

}
