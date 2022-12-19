# egg.timer testing

This repository contains automated UI tests written in Java with Selenium for the [egg.timer](https://e.ggtimer.com/) and API test written in Postman for the [reqres](https://reqres.in/). Also here you can find test plan, bug report, theory questionnaire with answers.

## Selenium tests
1. Make sure you have [Java](https://www.java.com/en/), [Selenium](https://www.selenium.dev/downloads/) and [Maven](https://maven.apache.org/) installed on your system, if not follow the vendor instructions for installing them on your operating system. Add Maven dependecy in your IDE.
2. In order to run tests in CHROME browser make sure you have [chromedriver](https://chromedriver.chromium.org/).

### Running Tests
The following steps should get you set up for running Selenium tests locally on your machine:

1. Clone this repository to your local machine.
2. Clone selenium-config repository to the same folder (eg. myfolder/selenium-tests and myfolder/selenium-config)
Create symlinks for /selenium-config/config.xml and /selenium-config/captcha.txt (note the leading slash) that point to these files in your selenium-config repository.
Open up a terminal and navigate to the root directory of the repository.





## How to run API tests

1. Download [Postman](https://www.postman.com/downloads/)
2. Clone project
3. Import "reqres.postman_collection.json" in Postman. Next to your workspace name, click on "Import".
4. Click on collection name, in collection menu click on "Run" (play button in a square)

