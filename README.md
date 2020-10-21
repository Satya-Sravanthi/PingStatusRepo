# PingStatusRepo

This repository contains changes related to ping status application where the client keeps sending the requests to the server.
If the client does not respond within the threshold limit the server identifies those clients and add the details to a log file 

# Assumptions:
1.	Every client makes at least one request to the server as soon as they come online
2.	The threshold limit is common across all the clients 
3.	The framework through which the client reaches the server is through the rest API
4.	Assuming persistent storage like Database for storing the incoming ping requests
5.	Assuming scheduler component to process the ping requests that have breached the threshold. 
6.	Assuming the timeouts will be written in the log file. For simplicity added only one file but this can be broken down into multiple files based on size of the file
7.	Assuming there is a centralized lock mechanism which can be implemented using zookeeper configuration or database locking 
8.	For the demo purpose used Spring boot with h2 database. In real scenario the database can be replaced with the oracle database.
9.	Assuming the timeout files will be written locally and moved to a centralized location. For the demo purpose used the filename where the application is hosted 


# Key Components:
With the above assumptions below is the layering of the different services within Application.
1.	PingService – Service to process the incoming  ping  request and updates in the database
2.	PingEndpoint - Rest API component which exposes ping endpoint call for the clients to interact with the server.
3.	DBEntityManager -  Component to update the incoming requests to the database
4.	DatabaseRepository – Repository which connects to appropriate database and perform the CRUD operations on the client requests 
5.	IdleTimeOutManager – Scheduler service component which runs periodically and pulls out the client requests that have breached the threshold limit ( It uses LockManager component to elect a master )
6.	LockManager – Component which provides the locking mechanism so that only one component of IdleTimeOutManager processes the requests when the application is in multiple servers. The lockManager can either use external zookeeper configuration or database optimistic locking 
7.	FileManager -  To write the timeout files to the log file
8.	ConfigurationManager – To fetch the threshold configuration 
9.	PingRequest/PingResponse – REST API POJO requests and response payloads
