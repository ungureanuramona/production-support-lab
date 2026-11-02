# Incident #004 — PostgreSQL Connection Failure

## Environment
- Ubuntu 26.04 (WSL2)
- Apache Tomcat 11
- Java 21 / Maven
- PostgreSQL 17 running in Docker
- PostgreSQL JDBC Driver 42.7.7

## Incident Summary
A Java web application deployed on Tomcat returned HTTP 503 when checking its PostgreSQL database connection.

## Symptoms
The `/demo-app/db-check` endpoint returned:

HTTP 503 — Database unavailable

Tomcat logs initially showed:

java.sql.SQLException: No suitable driver found

After resolving the JDBC driver loading issue, a second error appeared:

org.postgresql.util.PSQLException: Connection to 127.0.0.1:5433 refused

## Investigation
1. Verified that the PostgreSQL JDBC dependency was included in the Maven build.
2. Confirmed that the JDBC driver JAR and `org.postgresql.Driver` class were present.
3. Added explicit JDBC driver loading using `Class.forName("org.postgresql.Driver")`.
4. Rebuilt and redeployed the application.
5. Identified the new connection-refused error in Tomcat logs.
6. Checked Docker container status and found `support-postgres` stopped.

## Root Causes
Two separate issues were identified:

1. The JDBC driver was not being discovered automatically by DriverManager in the deployed application.
2. The PostgreSQL Docker container was stopped, leaving port 5433 unavailable.

## Resolution
- Explicitly loaded the PostgreSQL JDBC driver in the Java servlet.
- Rebuilt the WAR using Maven.
- Redeployed the application to Tomcat.
- Restarted the PostgreSQL Docker container.

## Validation
Performed a controlled database outage and recovery test:

| PostgreSQL State | HTTP Response |
|---|---|
| Running | 200 — Database connection OK |
| Stopped | 503 — Database unavailable |
| Restarted | 200 — Database connection OK |

The application successfully recovered after PostgreSQL was restarted.

## Lessons Learned
- An HTTP 503 response can have multiple underlying causes.
- Application logs and stack traces are essential for identifying the actual failure.
- A JDBC driver can exist in an application package without being automatically discovered.
- Dependency availability should be verified before investigating application code.
- Recovery must be validated after restoring a failed dependency.

## Scope
This incident was reproduced and resolved in a local training environment, not a production system.
