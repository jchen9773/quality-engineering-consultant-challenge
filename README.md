# Quality Engineering Consultant Challenge

Automation framework for running SKUCrud api test cases

## Basic usage

To run a full build that runs all feature files run the following command in the root directory:

```bash
$./gradlew build
```

To generate reports run:

```bash
$ ./gradlew allureReport
```

allure reports will be generated in `/build/reports/allure-report`
extent reports are in `/build/extentReports`
cucumber reports are in `/build/reports/tests`

## Requirements

Gradle 5.0+ JDK 11

## Prerequisites

Copy keys from `gradle.properties` at root directory and add them to
local `~/.gradle/gradle.properties`. Alternatively add properties via `-D` for every gradle build
command.

### Note: for this example base URI is saved to gradle.properties

### Intellij Users

In order to run feature files through Intellij, in the Run/Debug
Configurations, `Glue: com.aim.nintendo.challenge.stepDefinitions` and system properties
from `gradle.properties` must be added to `VM options:`.

## Contributing

josh.chen.jc@gmail.com
