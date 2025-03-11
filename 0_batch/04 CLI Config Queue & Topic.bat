@echo off
set JAVA_HOME=C:\PROGRA~1\JAVA\JDK-23
set JBOSS_HOME=c:\tools\wildfly-35.0.1.Final
set CLI_BAT=%JBOSS_HOME%\bin\jboss-cli.bat --connect --echo-command
set NOPAUSE=true

::GOTO QUEUE_TOPIC_ADD
::GOTO QUEUE_TOPIC_REMOVE
GOTO QUEUE_TOPIC_SHOW

:QUEUE_TOPIC_ADD
set COMMANDS=cd /subsystem=messaging-activemq/server=default/,^
./jms-queue=StudyQueueSync:add(entries=["jms/StudyQueueSync"]),^
./jms-queue=StudyQueueAsync:add(entries=["jms/StudyQueueAsync"]),^
./jms-topic=StudyTopicSync:add(entries=["jms/StudyTopicSync"]),^
./jms-topic=StudyTopicAsync:add(entries=["jms/StudyTopicAsync"]),^
./jms-queue=StudyReplyQueue:add(entries=["jms/StudyReplyQueue"])
GOTO EXEC_CLI

:QUEUE_TOPIC_REMOVE
set COMMANDS=cd /subsystem=messaging-activemq/server=default/,^
./jms-queue=StudyQueueSync:remove,^
./jms-queue=StudyQueueAsync:remove,^
./jms-topic=StudyTopicSync:remove,^
./jms-topic=StudyTopicAsync:remove,^
./jms-queue=StudyReplyQueue:remove
GOTO EXEC_CLI

:QUEUE_TOPIC_SHOW
set COMMANDS=cd /subsystem=messaging-activemq/server=default/,^
ls jms-queue,^
ls jms-topic,^
./jms-queue=StudyQueueSync:read-resource,^
./jms-queue=StudyQueueAsync:read-resource,^
./jms-topic=StudyTopicSync:read-resource,^
./jms-topic=StudyTopicAsync:read-resource,^
./jms-queue=StudyReplyQueue:read-resource
GOTO EXEC_CLI

:EXEC_CLI
call %CLI_BAT% --commands="%COMMANDS%"
pause