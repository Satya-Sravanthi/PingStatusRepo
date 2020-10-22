# PingStatusRepo

This repository contains changes related to ping status application where the client keeps sending the requests to the server.
If the client does not respond within the threshold limit the server identifies those clients and add the details to a log file 

# Assumptions:
1.	Every client makes at least one request to the server as soon as they come online
2.	The threshold limit is common across all the clients. For the demo purpose configured 10 seconds threshold
3.	The framework through which the client reaches the server is through the rest API
4.	Assuming persistent storage like Database for storing the incoming ping requests
5.	Assuming there will be a scheduler component to process the ping requests that have breached the idle threshold. For the demo purpose configured the scheduler to run for every 20 seconds
6.	Assuming the timeouts will be written in the log file. For simplicity added only one file but this can be broken down into multiple files based on size of the file. The format used to write the file is “Current date - clientId- idle time in milli seconds”
7.	Assuming there is a centralized lock mechanism which can be implemented using zookeeper configuration or database locking. The locking is used for the purpose of choosing one of the servers to act as master to print client idle timeouts periodically.
8.	For the demo purpose used Spring boot with h2 database. In real scenario the database can be replaced with the any other persistent store like MySQL/Oracle/DynamoDB etc.



# Key Components:
With the above assumptions below is the layering of the different services within Application.
1.	PingManager – Service to process the incoming ping request and updates in the database. This holds all the components 
2.	PingEndpoint - Rest API component which exposes ping endpoint call for the clients to interact with the server.
3.	DBEntityManager -  Component to update the incoming requests to the database
4.	PingDatabaseRepository / PingHistoryDatabaseRepository  / ConfigurationDatabaseRepository – Repository which connects to appropriate database and perform the CRUD operations on the ping  requests and configurations
5.	IdleTimeOutManager – Scheduler service component which runs periodically and pulls out the client requests that have breached the threshold limit ( It uses below LockManager component  to elect a master )
6.	LockManager – Component which provides the locking mechanism so that only one instance of IdleTimeOutManager from a given server processes the requests as the application is running in multiple servers for scalability. The lockManager can either use external zookeeper or database locking. 
7.	LogProcessor -   Processor to write the timeout entries to the log file. 
8.	ConfigurationManager – To fetch the threshold configuration 
9.	PingRequest/PingResponse – REST API POJO requests and response payloads
10.	PingEntity / PingHistoryEntity/ ConfigurationEntity – Internal representation to store the current ping request/ ping history details and configurations respectively

