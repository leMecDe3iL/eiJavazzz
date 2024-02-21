@ECHO OFF
CHCP 1252 >NUL
PROMPT $G
CD /D "%~dp0"

:: Installe le JAR dans le dosier maven-repo du projet ~jfox

SET PARH_JAR=target\jfox-2023-03-EI.jar
SET PATH_REPO_EI=maven-repo

SET JAVA_HOME=C:\dev23\jdk-18
SET MAVEN_HOME=C:\dev23\utils\maven

PATH %JAVA_HOME%\bin;%PATH%
PATH %MAVEN_HOME%\bin;%PATH%

CALL MVN clean package
CALL MVN install:install-file -Dfile=%PARH_JAR% -DlocalRepositoryPath="%PATH_REPO_EI%"

ECHO. & PAUSE