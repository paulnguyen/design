@echo off
set CORRECT_HOMES=y
if "%ASPECTJ_HOME%" == "" goto noAspectJ
if not exist "%ASPECTJ_HOME%\bin\ajc.bat" goto invalidAspectJ
set ASPECTJ_CLASSPATH=%ASPECTJ_HOME%\lib\aspectjrt.jar

if "%JAVA_HOME%" == "" goto noJava
if not exist "%JAVA_HOME%\bin\java.exe" goto invalidJava
set JAVA_CLASSPATH=%JAVA_HOME%\jre\lib\rt.jar

if "%NEED_TOMCAT%" == "" goto skipTomcat
if "%TOMCAT_HOME%" == "" goto noTomcat
set CATALINA_HOME=%TOMCAT_HOME%
if not exist "%TOMCAT_HOME%\bin" goto invalidTomcat
:skipTomcat

if "%NEED_ANT%" == "" goto skipAnt
if "%ANT_HOME%" == "" goto noAnt
if not exist "%ANT_HOME%\bin" goto invalidAnt
:skipAnt

if "%MAVEN_HOME%" == "" goto noMaven
if not exist "%MAVEN_HOME%\bin" goto invalidMaven

set CLASSPATH=%ASPECTJ_CLASSPATH%;%JAVA_CLASSPATH%;%CLASSPATH%
set PATH=%ASPECTJ_HOME%\bin;%JAVA_HOME%\bin;%ANT_HOME%\bin;%PATH%;%MAVEN_HOME%\bin
goto end

:noAspectJ
echo Please set the ASPECTJ_HOME variable.
set CORRECT_HOMES=n
goto end

:invalidAspectJ
echo Invalid ASPECTJ_HOME. It must point to the parent of the bin and lib directory.
set CORRECT_HOMES=n
goto end

:noJava
echo Please set the JAVA_HOME variable.
set CORRECT_HOMES=n
goto end

:invalidJava
echo Invalid JAVA_HOME. It must point to the parent of the bin and lib directory.
set CORRECT_HOMES=n
goto end

:noTomcat
echo Please set the TOMCAT_HOME variable.
set CORRECT_HOMES=n
goto end

:invalidTomcat
echo Invalid TOMCAT_HOME. It must point to the parent of the bin directory.
set CORRECT_HOMES=n
goto end

:noAnt
echo Please set the ANT_HOME variable.
set CORRECT_HOMES=n
goto end

:invalidAnt
echo Invalid ANT_HOME. It must point to the parent of bin directory.
set CORRECT_HOMES=n
goto end

:noMaven
echo Please set the MAVEN_HOME variable.
set CORRECT_HOMES=n
goto end

:invalidMaven
echo Invalid MAVEN_HOME. It must point to the parent of bin directory.
set CORRECT_HOMES=n
goto end

:end
if "%CORRECT_HOMES%" == "n" echo Check setHomes.sh or set correct environment variables