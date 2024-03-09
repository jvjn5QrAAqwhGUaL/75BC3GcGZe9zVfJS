@echo off
title Esun Library

pushd %~dp0

set JAVA_HOME=D:\jdk-17.0.7.7-hotspot

IF NOT EXIST "%userprofile%\esun_lib*" (
    echo missing database, running migration to build initial database...
    gradlew flywayMigrate -i
)

IF NOT EXIST "build\libs\esun_library-*.jar" (
    echo building jar
    gradlew bootJar
)

@REM always rebuild for dev
@REM start gradlew bootJar
@REM sleep 5

for %%F in ("build\libs\esun_library-*.jar") do (
    set "JAR=%%F"
)

%JAVA_HOME%\bin\java -version

%JAVA_HOME%\bin\java -jar %JAR%

pause
