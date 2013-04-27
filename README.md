lms: Lighting &amp; Motion Services
===
Lighting &amp; Motion Services

For a different view on the issues, check this out: 
http://huboard.com/HPGurre/lms/board

Requirements:
-----------
  * Tomcat server to deploy it on (works on Tomcat 7) 
  * MySQL: database management system(works with 5.5)

Eclipse:
-----------
We all use Eclipse, hence here is the plugins you need to add in order to work with lms:
* Ivy(Dependency management)
* EGit

Interacting with lms:
-----------
When it is running you can find rest services on the URL:
* http://localhost:8080/lms/rest + the service you need. 
* e.g.http://localhost:8080/lms/rest/building

Package structure
---------------------
**src/main/java**
* dk.itu.gsd.lms.dao -                    Layer to hold Data Access objects. Encapsulates data source access.
* dk.itu.gsd.lms.integration.consumed -   Layer to hold Adapter objects. Encapsulates Building simulator access.
* dk.itu.gsd.lms.integration.exposed -    Layer to hold REST webservices. Encapsulates exposed data.
* dk.itu.gsd.lms.model -                  Layer to hold model objects. Encapsulates the data model.
* dk.itu.gsd.lms.services -               Layer to hold services. Encapsulates business logic.
* dk.itu.gsd.lms.ui -                     Layer to hold UI objects. Encapsulates the front end.

**src/main/resources**
* no package structure -               	  Holds resources. E.g. Datasource config files, Application context.

**src/test/java**
* dk.itu.gsd.lms -                        Holds test classes.

**src/test/resources**
* no package structure -                  Holds test resources.



