package MainMethod;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
  //format = { "pretty", "html:report/test-report" },
  features = { "src/test/java/Feature/amazon.feature" },
  glue = { "src/test/java/StepDefinition" }
)

public class TestRunner {

	
}
