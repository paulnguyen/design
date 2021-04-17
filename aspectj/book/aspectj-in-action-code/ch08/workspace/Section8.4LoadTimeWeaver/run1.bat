@rem Copyright 2009 Ramnivas Laddad
@rem 
@rem Licensed under the Apache License, Version 2.0 (the "License"); 
@rem you may not use this file except in compliance with the License. 
@rem You may obtain a copy of the License at 
@rem     http://www.apache.org/licenses/LICENSE-2.0 
@rem 
@rem Unless required by applicable law or agreed to in writing, software 
@rem distributed under the License is distributed on an "AS IS" BASIS, 
@rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
@rem See the License for the specific language governing permissions and 
@rem limitations under the License. 

set NEED_TOMCAT=Y
call ..\..\..\setHomes.bat
call ..\..\..\setEnvironment.bat

if "%CORRECT_HOMES%" == "n" goto end

@echo on
call cd ..\org.springframework.samples.jpetstore 
call mvn clean package
call copy target\org.springframework.samples.jpetstore-1.0.0-SNAPSHOT.war %CATALINA_HOME%\webapps\jpetstore.war
call start db\hsqldb\server.bat 
call %TOMCAT_HOME%\bin\startup.bat
echo #####################################################################
echo Visit the web page for the application, and buy a few pets. 
echo If everything goes fine, run the run2.bat.
echo ##################################################################### 

call cd ..\Section8.4LoadTimeWeaver 

:end