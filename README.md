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


# 20. http://localhost:8080//account-and-user/verbose/1 and no debug shoud come

# 31. http://localhost:8080//account-and-user/verbose/1?verbose=true
