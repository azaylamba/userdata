# userdata
User data microservice

Follow below steps to set up the application:
1. Install mongoDB on localhost
   https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/
   Start MongoDB on Windows - https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/#std-label-run-mongodb-from-cmd
   Open CMD in admin mode and run command - "C:\Program Files\MongoDB\Server\5.0\bin\mongod.exe" --dbpath="c:\data\db"
   Create database 'userdata' on the localhost instance of mongoDB
2. Clone the project from the git repo - https://github.com/azaylamba/userdata
3. Import the project using maven on local
4. Run the application (UserdataApplication)
5. Access the REST end points using POSTMAN tool


Netflix Zuul Proxy has been discontinued, so I have used Spring Cloud Gateway instead.

Steps are as below:
1. Run UserdataApplication microservice (configured to run on port 8080)
2. Run CloudgatewayApplication microservice which is configured to run on port 8085. Repo link - https://github.com/azaylamba/cloudgateway
   This will route all the requests having URLs matching to /user/** coming to 8085 to 8080 and return the results

