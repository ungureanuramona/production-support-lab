# Incident #003 - HTTP 500 Java Exception

## Environment
- Ubuntu 26.04 (WSL2)
- Apache Tomcat 11
- Java 21
- Maven
- Demo Java Servlet application

## Symptom
The endpoint `/demo-app/error` returned HTTP 500 Internal Server Error.

## Investigation
1. Confirmed successful Maven build and Tomcat deployment.
2. Reproduced HTTP 500 using curl.
3. Checked systemd journal, which showed deployment messages but not the exception.
4. Located the Java stack trace in the Tomcat localhost application log.
5. Correlated the exception with the HTTP 500 entry in the access log.

## Evidence
HTTP request:
GET /demo-app/error -> 500

Java exception:
java.lang.IllegalStateException: Simulated database connection failure
at com.supportlab.ErrorServlet.doGet(ErrorServlet.java:16)

## Root Cause
The ErrorServlet intentionally throws an IllegalStateException.
The database failure message is simulated; no real database connection was attempted.

## Resolution
Root cause identified. The intentional exception remains in place for demonstration purposes.

## Lessons Learned
- HTTP 500 indicates a server-side processing error.
- A successful deployment does not guarantee successful request processing.
- Tomcat application logs can contain exceptions absent from systemd journal.
- Access logs and application logs can be correlated using timestamps.
- Java stack traces help identify the failing class, method and source line.
