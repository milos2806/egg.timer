# egg.timer testing

This repository contains automated UI tests written in Java with Selenium for the [egg.timer](https://e.ggtimer.com/) and API test written in Postman for the [reqres](https://reqres.in/). Also here you can find test plan, bug report, theory questionnaire with answers.

## Selenium tests
1. Make sure you have [JDK 1.8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html), [Selenium 3.141](https://www.selenium.dev/downloads/) and [Maven 3.8.1](https://maven.apache.org/) installed on your system, if not follow the vendor instructions for installing them on your operating system. Add Maven dependecy in your IDE (my recommendation is Intellij).
2. In order to run tests in CHROME browser make sure you have [chromedriver](https://chromedriver.chromium.org/) and you need to set chromedriver path in class "EggTimerHomework.java" line 32 (SetUp method) to match the path on your computer.   

### Running Selenium tests
The following steps should get you set up for running Selenium tests locally on your machine:

1. Clone this repository to your local machine
2. Open the egg.timer_maven project in your IDE
3. Wait for Maven to install all dependecies
4. Adjust chromedriver path (line 32 class "EggTimerHomework.java")
5. If you want to run all tests run EggTimerHomeWork or you can run specific tests one by one 
  


## How to run API tests

1. Download [Postman](https://www.postman.com/downloads/)
2. Clone project
3. Import "reqres.postman_collection.json" in Postman.
4. Run collection, or you can performe requests one by one

