@ECHO OFF
CHCP 1252 >NUL
PROMPT $G
CD /D "%~dp0"

IF NOT "%Console-Dev-Java%"=="" GOTO :EOF
SET Console-Dev-Java=OK

SET JAVA_HOME=C:\dev23\jdk-18
SET ANT_HOME=C:\dev23\utils\ant
SET MAVEN_HOME=C:\dev23\utils\maven
SET GRADLE_HOME=C:\dev23\utils\gradle
SET IJ_HOME=C:\dev23\utils\ij

SET ANT_OPTS=-Dpolyglot.engine.WarnInterpreterOnly=false -Djava.security.manager=allow

SET GRADLE_USER_HOME=C:\TEMP\gradle-data

PATH %JAVA_HOME%\bin;%PATH%
PATH %ANT_HOME%\bin;%PATH%
PATH %MAVEN_HOME%\bin;%PATH%
PATH %GRADLE_HOME%\bin;%PATH%
PATH %IJ_HOME%\bin;%PATH%

CMD
