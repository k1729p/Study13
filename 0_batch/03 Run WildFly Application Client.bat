@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-19
set JBOSS_HOME=c:\tools\wildfly-27.0.1.Final

del /F /Q %JBOSS_HOME%\appclient\log\*
del /F /Q %JBOSS_HOME%\appclient\tmp\*
call %JBOSS_HOME%\bin\appclient.bat ..\ear\target\Study13.ear#Study13_appclient.jar
pause