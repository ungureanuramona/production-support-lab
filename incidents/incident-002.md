# Incident #002 - HTTP 404 Not Found

## Environment
- Ubuntu Linux (WSL2)
- Apache Tomcat 11
- Java web application packaged as WAR
- HTTP port: 8080

## Issue
A specific application URL returned HTTP 404 Not Found.

## Investigation
1. Requested /demo-app/missing-page using curl.
2. Received HTTP 404 Not Found.
3. Tested the main application URL /demo-app/.
4. Confirmed that the application returned HTTP 200 OK.
5. Inspected the WAR package using jar tf.
6. Confirmed that index.html was included, but the requested resource was not present.

## Root Cause
An intentionally invalid URL was requested during a simulated incident.

## Resolution
Identified the correct application URL: /demo-app/.
No application changes were required.

## Verification
The main application returned HTTP 200 OK and displayed the expected HTML content.

## Lessons Learned
- HTTP 404 does not necessarily indicate an application outage.
- Verify whether the issue affects one URL or the entire application.
- Inspect deployment packages when investigating missing resources.
- Check application context paths and URL mappings.

## Incident Type
Simulated incident in a local Production Support training lab.
