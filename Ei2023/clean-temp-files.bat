@ECHO OFF
SetLocal EnableExtensions EnableDelayedExpansion
CHCP 1252 >NUL
PROMPT $G
CD /D "%~dp0"

CALL :display-size

CALL :clean-metadata
FOR /D %%d IN (*) DO (
  CD /D "%~dp0\%%d" 
  CALL :remove-build-dirs
  CALL :clean-metadata %d
  FOR /D %%e IN (*) DO (
    CD /D "%~dp0\%%d\%%e" 
    CALL :remove-build-dirs
	IF EXIST "%~dp0\%%d\.metadata" (
       FOR /D %%g IN (*) DO (
         CD /D "%~dp0\%%d\%%e\%%g" 
         CALL :remove-build-dirs
       )
    )	
  )
)
CD /D "%~dp0"

ECHO. & ECHO Traitement effectué
CALL :display-size
ECHO. & PAUSE
GOTO :EOF

:remove-build-dirs
CALL :remove-dir build
CALL :remove-dir bin
CALL :remove-dir target
GOTO :EOF


:clean-metadata
CALL :remove-file .metadata\.log
CALL :remove-file .metadata\*.log
CALL :remove-file .metadata\.plugins\org.eclipse.jdt.core\*.index
CALL :remove-file .metadata\.plugins\org.eclipse.jdt.core\savedIndexNames.txt
CALL :remove-file .metadata\.plugins\org.eclipse.xtext.builder\builder.state
CALL :remove-dir  .metadata\.plugins\org.eclipse.epp.logging.aeri.ui
CALL :remove-dir  .metadata\.plugins\org.eclipse.core.resources\.history
CALL :remove-dir  .metadata\.plugins\org.eclipse.datatools.sqltools.result
CALL :remove-dir  .metadata\.plugins\org.eclipse.epp.logging.aeri.ide

CALL :remove-dir  .metadata\.plugins\org.jboss.tools.common.projecttemplates
CALL :remove-dir  .metadata\.plugins\org.jboss.tools.foundation.core
CALL :remove-dir  .recommenders

FOR /D %%d IN ( .metadata\.plugins\org.eclipse.core.resources\.projects\* ) DO (
  CALL :remove-dir %%d\org.eclipse.jdt.core
)
GOTO :EOF


:remove-file
IF EXIST %1  DEL /Q %1
GOTO :EOF

:remove-dir
IF EXIST %1  DEL /Q /S %1 >NUL
IF EXIST %1  RMDIR /Q /S %1
GOTO :EOF

:display-size
SET size=0
call:add-size-metadata
FOR /D  %%d IN (*) DO (
  CD /D "%~dp0\%%d" 
  call :add-size-build-dirs
  call:add-size-metadata
  FOR /D %%e IN (*) DO (
    CD /D "%~dp0\%%d\%%e" 
    call :add-size-build-dirs
	IF EXIST "%~dp0\%%d\.metadata" (
      FOR /D %%g IN (*) DO (
        CD /D "%~dp0\%%d\%%e\%%g" 
        call :add-size-build-dirs
      )
	)
  )
)
CD /D "%~dp0"
SET /a size/=1024
call :format size %size% 
EcHO. & ECHO Taille :  %size% Ko


GOTO :EOF

:add-size-metadata
FOR /R .metadata %%f IN (*) DO (
  SET /a size+=%%~zf
)
GOTO :EOF

:add-size-build-dirs
FOR /R bin %%f IN (*) DO (
  SET /a size+=%%~zf
)
FOR /R build %%f IN (*) DO (
  SET /a size+=%%~zf
)
FOR /R target %%f IN (*) DO (
  SET /a size+=%%~zf
)
GOTO :EOF


:format

set "var1=%2"
set "var2="
set "sign="
if "%var1:~0,1%" equ "-" set "sign=-" & set "var1=%var1:~1%"

for /L %%i in (1,1,4) do if defined var1 (
   set "var2= !var1:~-3!!var2!"
   set "var1=!var1:~0,-3!"
)
set "var2=%sign%%var2:~1%

rem echo Output: %var2%
set %1=%var2%

GOTO :EOF
