eHow to execute script for debugging the prod issue:
1. mvn clean install (if you have maven) or
2. mvn clean test -DsuiteXmlFile=testng.xml
3. Go to src/test/java/ProdTest.java and execute the ProdIssue method. You can do it with TestNG

Note: I have removed the other API tests from the TestNG file so only the test for prod issue can be executed. 
The default environment has been set as starkindstries-dev for debugging the issue for now.(It will be changed later) In order to change the environment, please execute the tests with a parameter.
For e.g. 
mvn -Droot_env="1k-dev" -Dclient_env="1k-qa" clean install
