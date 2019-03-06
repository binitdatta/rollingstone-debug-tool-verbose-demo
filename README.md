# rollingstone-debug-tool-verbose-demo

# This multiple applications in this repository is added to demonstrate a debug aid called verbose. 

# Purpose : Debug Downstream Third party APIs instantly.

# Scenario : A number of applications have to call other downstream third party internal / external APIs to get data from legacy systems. 
## A very typical pattern can be seen in client JavaScript applications, that has a proxy / API gateway type of backend. This backend talks to the numerous other third party internal / external APIs, on behalf of the front end application. As the front end application only knows this API gateway backend, debugging this pair app of front end / API gateway backend sometimes becomes very time consuming (often with # financial impacts) and cumbersome without special tools. 

## Following is a typical scenario when one or more production issues are reported for these pair of  front end / API gateway backend applications

## 1. Customers complain and call the customer Support

## 2. Customer Support log support request with 24/7 Tech Support

## 3. Tech Support fails to resolve the issue

## 4. Tech support reach out to Dev Team and create a ticket in the Ticketing system i.e. Sericenow

## 5. Dev Team Manager gets notified by the Tech Support through Email / Self checks.

## 6. Dev Team manager owns ticket and assigns the ticket to an Engineer

## 7. The Engineer looks at the request and searches either Splunk Logs / ELK Logs to retreive past log data, hoping the incident took
## place within the last so many days so logs are available

# 8. Logs do not help much as production logs do not contain debug messages and the Developer starts to debug manually within his/her IDE using user data.

# 9. Step number 9 takes time......, goes in a loop, almost all downstream system leads claim through email / comments in ServiceNow that the issue certainly does not belong to them at all (trust ne, speaking from personal experience :), email chains starts to make rounds between different systems / applications, onshore / offshore.

## 10. If this happens with a critical API where the system / company is actually losing money by the hour, developers spend hours / days to fix and the company still looses huge sums. The immediate impact is that engineers are speding time not by adding new features but losing sleep trying to fix a non-existing issue. That is a double loss of money and development hours that could have been used productively adding features customers want, features competitors do not have etc. Losing time like these is thus a strict no-no for smart teams.

# Alternative

## 1. During design / development think about this kind of situations and add tools / aids that might instantly help pin point  the error and if that error is coming from a downsteam system, fixing effort can quickly be routed to that system.

## 2. How this works is by adding an optional verbose parameter to critical REST APIs. With that verbose parameter present, APIs will respond normally  but include raw responses from other APIs they called helping instant debugging of which system is responsible for the error. This repository demonstrates that situation with a few APIs.

# Prequisitites

# 1. You should have your MySQL like I have My own MySQL.
# 2. You should have Maven installed and in path
# 3. You should have gradle installed and in path
# 4. You should have a MySQ database called `verbose_demo_db`

# Functional Description : We have two APIs here. 

  # A. rolligstone-debug-tool-verbose-demo-department-employee-rest-api - No surprise Spring Boot REST API
  # B. rollingstone-debug-tool-verbose-demo-department-employee-client-api - Client REST API Calling the REST Service and equiped with the verbose feature.
  # C. The Client API makes two calls to the Service
    ## 1. Get a Department By ID
    ## 2. Gets all the employees who works for that Department
   
# Process

# 1. git clone https://github.com/binitdatta/rollingstone-debug-tool-verbose-demo.git

# 2. cd rollingstone-debug-tool-verbose-demo

# 3. cd rolligstone-debug-tool-verbose-demo-department-employee-rest-api

# 4. mvn clean install

# 4. java -jar target/verbose-demo-rest-api.jar

# 5. Open another comman prompt

# 6. cd rollingstone-debug-tool-verbose-demo

# 7. cd rollingstone-debug-tool-verbose-demo-department-employee-client-api

# 8. mvn clean install

# 9. java -jar target/debugclientdemo-0.0.1-SNAPSHOT.jar

# 10. When we try the URL without verbose request paramater

http://localhost:8094/rollingstone-verbose-demo/client/department/1/employees

![alt text](https://github.com/binitdatta/rollingstone-debug-tool-verbose-demo/blob/master/verbose_demo_false_1.png)

# 11. We get

![alt text](https://github.com/binitdatta/rollingstone-debug-tool-verbose-demo/blob/master/verbose_demo_false_2.png)

# 12. When we try the URL with verbose request paramater

http://localhost:8094/rollingstone-verbose-demo/client/department/1/employees?verbose=true

![alt text](https://github.com/binitdatta/rollingstone-debug-tool-verbose-demo/blob/master/verbose_demo_true_1.png)

![alt text](https://github.com/binitdatta/rollingstone-debug-tool-verbose-demo/blob/master/verbose_demo_true_2.png)

![alt text](https://github.com/binitdatta/rollingstone-debug-tool-verbose-demo/blob/master/verbose_demo_true_3.png)

# 13 As we can see in a debugging situation, without touching source code or even opening our IDE, not calling the engineering responsibles for the application we get significant details that can help pinpoint the root cause of the bug / issue. This accurate identification of the root cause can save revenue loss, save engineers and everybody else lengthy nighttime calls etc.

# 14. Now some technical staff. I have used Spring Boot Aspect Oriented Programming and Spring Boot RESTTemplate Request Interceptor to make it easy for the average engineer to integrate this without a lot of addition programming. Some special classes can be reviewed for better understanding

  # A. com.rollingstone.debugclientdemo.verbose.aspects.VerboseAspect.java - This class leaves the Spring REST Controller free from having to deal with verbose related code. It has @Before annotation and @AfterReturning annotation to start collecting metadata and stop collecting metadata for a request.
  
  # B. com.rollingstone.debugclientdemo.model.verbose.RequestInsightCollector.java - This class has all the methods to implement the verbose functionality and it uses ThreadLocal to do so.
  
  # C. com.rollingstone.debugclientdemo.model.verbose.RequestInsight.java  - The Model class for verbose feature

