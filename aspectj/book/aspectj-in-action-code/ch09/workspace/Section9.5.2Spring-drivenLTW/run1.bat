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

@echo off
set NEED_TOMCAT=Y
call ..\..\..\setHomes.bat
call ..\..\..\setEnvironment.bat

if "%CORRECT_HOMES%" == "n" goto end

@echo on

call copy src\main\resources\WEB-INF\weaverContext.xml ..\org.springframework.samples.jpetstore\src\main\webapp\WEB-INF
call copy src\main\resources\META-INF\context.xml ..\org.springframework.samples.jpetstore\src\main\webapp\META-INF
@echo off
echo #####################################################
echo Modify ..\org.springframework.samples.jpetstore\src\main\webapp\WEB-INF\web.xml 
echo to add /WEB-INF/weaverContext.xml 
echo as param-value for contextConfigLocation as shown in Listing 9.10.

echo Also you may have to increase maximum memroy allocated to Tomcat by adding
echo set JAVA_OPTS=-Xmx512m to %TOMCAT_HOME%\bin\startup.bat
echo #####################################################
