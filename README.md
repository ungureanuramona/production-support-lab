# Production Support Lab

A hands-on technical lab focused on Linux troubleshooting, Java application deployment, and production incident investigation.

This project is part of my practical learning in Production Support, Site Reliability Engineering (SRE), and DevOps.

## Technologies

- Ubuntu Linux (WSL2)
- Apache Tomcat 11
- Java 21
- Apache Maven
- Bash
- Git

## Project Structure

- `demo-app/` - Java web application packaged as a WAR file
- `incidents/` - Documented troubleshooting exercises and simulated incidents

## What I've Implemented

### Java Application Deployment
- Built a Java web application using Maven
- Packaged the application as a WAR file
- Deployed it to Apache Tomcat 11
- Verified successful deployment through Tomcat logs
- Tested HTTP responses using curl

### Incident #001 - Application Unavailable
- Simulated a Tomcat service outage
- Investigated HTTP connection failures
- Checked service status using systemctl
- Reviewed shutdown logs
- Restored the service
- Verified application availability with HTTP 200 OK

See [Incident #001](incidents/incident-001.md) for the full investigation.

## Planned Exercises

- HTTP 404 and 500 troubleshooting
- Linux performance and resource monitoring
- SQL troubleshooting
- Bash automation
- Monitoring and alerting
- CI/CD pipelines
- SSL/TLS troubleshooting

## Note

All incidents are simulated in a local training environment. This repository is intended to demonstrate practical troubleshooting and operational skills.
