# Outage Bot

<br>
This application is to provide an interactive shell environment to record and search about outage of a system.

### Application Requirements:
- Java 1.8
- Spring Boot (Starter)
- Spring Shell

### Building and running the app standalone
```
Open terminal and goto the project folder.

-./gradlew build
-java -jar build/libs/OutageBot-0.0.1-SNAPSHOT.jar

```
### Available Commands


Built-In Commands
        clear: Clear the shell screen.
        exit, quit: Exit the shell.
        help: Display help about available commands.

Outage Commands
        search: Search an incident
        start: Create an incident, this will create a start event
        status: Report status an incident
        stop: End an incident


### Example usage of commands

1) start

Usage:
outage-bot:>start "Outage 1" "dev 1" "outstage started"

Description:
Start or report an incident for the first time.

Response:
Incident was created successfully and your incident number is:  1001

2) status

Usage:
outage-bot:>status 1001 "dev 2" "outage 1 status update"

Description:
Update status of the outage using the outage number. If the outage is already closed, error message is displayed to user.

Response:
Successfully recorded

3) search

Usage:
outage-bot:>search 1001

Description:
Search an incident/outage that was created. It prints all the status of incident. Displays incident not found message, if the incident is not found.

Response:

SystemName : Outage 1 OutageNumber : 1001 Message :dev 1
----------------------------------------------------------------------------
Received message : Outage started Reported by :outstage started Eventtime : 2018-04-29T09:36:36.530 Reported at :2018-04-29T09:36:36.530
Received message : outage 1 status update Reported by :dev 2 Eventtime : 2018-04-29T09:39:06.872 Reported at :2018-04-29T09:39:06.872



4) stop

Usage:
outage-bot:>stop 1001 "dev 2" "fixed outage 1"

Description:
If the incident or outage is fixed. Use the stop to provide the resolution and close the incident. Once the incident is closed, the outage cannot be updated later. This prints all the outage details.

Response:

SystemName : Outage 1 OutageNumber : 1001 Message :dev 1
----------------------------------------------------------------------------
Received message : Outage started Reported by :outstage started Eventtime : 2018-04-29T09:36:36.530 Reported at :2018-04-29T09:36:36.530
Received message : outage 1 status update Reported by :dev 2 Eventtime : 2018-04-29T09:39:06.872 Reported at :2018-04-29T09:39:06.872
Received message : fixed outage 1 Reported by :dev 2 Eventtime : 2018-04-29T09:41:19.376 Reported at :2018-04-29T09:41:19.376
Received message : End of Outage Reported by :SYSTEM Eventtime : 2018-04-29T09:41:19.376 Reported at :2018-04-29T09:41:19.376



5) help

Usage:
outage-bot:>help search

Description:
Used to display description of every command. 

Response:
NAME
	search - Search an incident

SYNOPSYS
	search [--outage-number] int  

OPTIONS
	--outage-number  int
		Outage number
		[Mandatory]

### Demo Screenshot

![Demo](Demo.png) 

### Error Handling

1) Incident or Outage not Found  

Message : Incident not found. Please provide the correct incident number.

2) Outage closed and user trying to update or add status

Message : Incident already ended. Status cannot be updated.

