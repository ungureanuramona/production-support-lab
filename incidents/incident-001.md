# Incident #001 - Application Unavailable

## Environment
- OS: Ubuntu Linux (WSL2)
- Application Server: Apache Tomcat 11
- Application: Java web application packaged as WAR
- Port: 8080

## Issue
The application was unavailable and users could not access its HTTP endpoint.

## Investigation
1. Tested the application endpoint using curl.
2. Received curl error 7: Could not connect to server.
3. Checked Tomcat service status using systemctl.
4. Confirmed that tomcat11.service was inactive (dead).
5. Reviewed service logs, which showed a clean shutdown.

## Root Cause
The Tomcat service was intentionally stopped as part of a simulated production incident.

## Resolution
Restarted the Tomcat service using systemctl.

## Verification
- Retested the application endpoint.
- Received HTTP 200 OK.
- Confirmed that the expected HTML content was returned.

## Lessons Learned
- A connection failure is different from an HTTP 500 error.
- Always verify service status and logs during troubleshooting.
- Service restoration must be followed by application-level verification.

## Incident Type
Simulated incident in a local Production Support training lab.
