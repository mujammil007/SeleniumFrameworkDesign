package Cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		
		features="src/test/java/Cucumber", 
		glue="SeleniumFrameworkDesign.stepDefinitions", 
		monochrome=true, 
		plugin= {"html:target/cucumber.html"},
		tags="@Regression"
)

public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
