package com.etaluma.scopesmart;

import cucumber.api.cli.Main;

public class MainWrapper {
    public static void main(String [] args){
        Main.main(new String[]{"--glue", "com.etaluma.scopesmart.steps", "--plugin", "pretty", "--plugin", "html:cucumber-reports",
                "--plugin", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", "features"});
    }
}