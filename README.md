# articleRepo
book articles

# Getting Started

Steps to follow:
1. goto project directory
2. run - gradle clean build
3. if you find "BUILD SUCCESSFUL", then run BookApplication.java class
4. Tomcat server will run at port 8888.
5. sample CURL -
curl --location --request GET 'localhost:8888/api/articles' \
--header 'Cookie: JSESSIONID.43bdf579=node01f7ilk3krjhb4p1tvothr6hwr2.node0; JSESSIONID.c8dfb96e=node01el50ylmtkuf417idqf9uicdoe4.node0'
6. import collection from src.main.postman to Postman tool.

7. H2 database URL : http://localhost:8888/h2/
    username : sa
    password : <blank>(no password)
8. Use swagger html doc for reference- http://localhost:8888/swagger-ui.html#/

