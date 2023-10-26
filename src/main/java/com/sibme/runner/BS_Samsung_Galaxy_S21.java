package com.sibme.runner;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@io.cucumber.testng.CucumberOptions(
		features="src/resources/features",
		glue= {"com.sibme.StepDefinitions"},
		tags = "@CF",
		monochrome=true,
		plugin= {"pretty",
				"html:target/cucumber-html-report.html",
				"json:target/cucumber-json-report.json",
//				"junit:target/cucumber-junit-xml-report.xml",
//				"rerun:target/cucumber-reports/rerun.txt",
		},
		dryRun = false
)
public class BS_Samsung_Galaxy_S21 extends AbstractTestNGCucumberTests {

}