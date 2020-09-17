package runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;

@RunWith(io.cucumber.junit.Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"}, glue = { "stepdefinitions" })
public class TestRunner {
}