@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-25
set JBOSS_HOME=d:\tools\wildfly-39.0.0.Final

del /F /Q %JBOSS_HOME%\standalone\log\*
del /F /Q %JBOSS_HOME%\standalone\tmp\*
call %JBOSS_HOME%\bin\standalone.bat --server-config=standalone-full.xml