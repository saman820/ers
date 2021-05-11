***************************************************
* Project: 		Expense Reimbursement System 
* Last Updated: May 1, 2021
***************************************************
* Contents:	
*	- Technologies Used
*	- Features
*	-	Getting Started
*	- Usage
* - License
***************************************************

1.  Technologies Used: 

	1.1 Java
  1.2 Javascript 
  1.3 PostgreSQL 
  1.4 AWS RDS
  1.5 JDBC
  1.6 JUnit
  1.7 Mockito
  1.8 Apache Tomcat
  1.9 Apache Log4j
  1.10 Servlets
	

2. 	Features
  
  2.1 Asynchronously fetching resources from the backend
  2.2 Login validation with Sessions
  2.3 having Users able to upload images.
  2.4 Storing and retrieving images from the databse.
  2.5 One way password hashing with salt
  2.6 Capability to send emails


3.  Getting Started
  
  First execute the following command in git to import the project locally.
  - git pull https://github.com/saman820/ers.git
  Then import the project as a "Maven" project in Eclypse or spring suite tool 4.
  In the projects properties section, add Apache Tomcat v9.0 or above to "Targeted Runtimes"
  Add the Tomcat server you set up in the previous step to the list of active servers and start it.
  The default port for applications served by Tomcat is 8080 and the project can be accessed at : http://localhost:8080/ers/home.ers
  

4.	Usage
   
  Employee Reimbursement System manages the process of reimbursing employees for expenses incurred while on company time. 
  All employees can create an account, login, view their past tickets, and create new reimbursement tickets. 
  Employees also can update the tickets they have posted previously or delete them.
  Finance managers can log in and view all reimbursement requests and past history for all employees in the company. 
  Finance managers can filter requests based on different criteria such as amount range, type, author; and approve or reject tickets.
 

5.	Licenses

