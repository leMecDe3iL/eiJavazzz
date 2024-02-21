@ECHO OFF
CHCP 1252 >NUL
PROMPT $G
CD /D "%~dp0"
CD ..

SET        PATH_ROOT=C:\dev23

SET      NOM_EI=EI Outils Java - Rattrapage
SET    URL_VISU=https://acloud10.zaclys.com/index.php/s/LrdfYdBXASxJnL5
SET TOKEN_DEPOT=a6qWkRQYfWkK22T
SET   URL_DEPOT=https://acloud10.zaclys.com

SET PATH_EXE_7Z=%PATH_ROOT%\utils\7-Zip\7z.exe


:: Affiche le nom de l'EI
ECHO.
ECHO.%NOM_EI%
ECHO.

:saisie
::Saisie du nom + pr�nom
ECHO.
SET /P NAME_ARCHIVE=Indiquez votre nom + pr�nom : 
IF "%NAME_ARCHIVE%"=="" GOTO :saisie

:: Mise ne majuscule du nom de l'archive
CALL :toUpper NAME_ARCHIVE "%NAME_ARCHIVE%"

:: Nom et chemin du fichier archive
SET FILE_ARCHIVE=%NAME_ARCHIVE%.zip
SET PATH_ARCHIVE=..\%FILE_ARCHIVE%

:: Param�tres de 7-Zip
SET ARGS_7Z=a "%PATH_ARCHIVE%"
SET ARGS_7Z=%ARGS_7Z% .
SET ARGS_7Z=%ARGS_7Z% -x!".metadata"
SET ARGS_7Z=%ARGS_7Z% -x!"~*"
SET ARGS_7Z=%ARGS_7Z% -x!"_*"
SET ARGS_7Z=%ARGS_7Z% -x!"script-envoi"
SET ARGS_7Z=%ARGS_7Z% -x!"RemoteSystemsTempFiles"
SET ARGS_7Z=%ARGS_7Z% -x!"maven-settings.xml"
SET ARGS_7Z=%ARGS_7Z% -x!"*.bat"
SET ARGS_7Z=%ARGS_7Z% -x!"*\bin"
SET ARGS_7Z=%ARGS_7Z% -x!"*\target"
SET ARGS_7Z=%ARGS_7Z% -mx
 
:: Cr�e l'archive
IF EXIST "%PATH_ARCHIVE%" DEL "%PATH_ARCHIVE%"
"%PATH_EXE_7Z%" %ARGS_7Z%

:: Envoie le fichier
CALL :formaURL URL_DEST "%URL_DEPOT%/public.php/webdav/%FILE_ARCHIVE%"
curl -T "%PATH_ARCHIVE%" -u "%TOKEN_DEPOT%":"" "%URL_DEST%"

:: Ouvre la page de v�rification visuelle
START "" "%URL_VISU%"

:: Ouvre l'explorateur de fichiers de Windows
::ping localhost -n 3 > NUL 
::explorer /n,..

:: Fin du traitement
::ECHO. & PAUSE
GOTO :EOF

:: Proc�dure de mise en majuscule
:toUpper <return_var> <str>
FOR /f "usebackq delims=" %%I IN (`powershell "\"%~2\".toUpper()"`) DO SET "%~1=%%~I"
GOTO :EOF

:: Proc�dure de normalisaton d'url
:formaURL <return_var> <str>
FOR /f "usebackq delims=" %%I IN (`powershell "\"%~2\".replace(' ', '%%20' )"`) DO SET "%~1=%%~I"
GOTO :EOF
