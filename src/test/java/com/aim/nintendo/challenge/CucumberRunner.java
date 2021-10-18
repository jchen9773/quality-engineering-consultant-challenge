package com.aim.nintendo.challenge;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.CucumberOptions.SnippetType;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = {"src/test/resources"},
    plugin = {
      "com.aim.nintendo.challenge.util.CustomReportListener",
      "io.qameta.allure.cucumber6jvm.AllureCucumber6Jvm"
    },
    glue = {"com.aim.nintendo.challenge.stepDefinitions", "src/test/resources"},
    monochrome = true,
    snippets = SnippetType.UNDERSCORE)
public class CucumberRunner extends AbstractTestNGCucumberTests {
  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
