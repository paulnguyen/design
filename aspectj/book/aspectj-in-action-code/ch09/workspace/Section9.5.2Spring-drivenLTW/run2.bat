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

mkdir ..\org.springframework.samples.jpetstore\src\main\java\ajia\monitoring
copy src\main\java\ajia\monitoring\Monitoring.java ..\org.springframework.samples.jpetstore\src\main\java\ajia\monitoring
mkdir ..\org.springframework.samples.jpetstore\src\main\resources\META-INF
copy src\main\resources\META-INF\aop.xml ..\org.springframework.samples.jpetstore\src\main\resources\META-INF

call cd ..\org.springframework.samples.jpetstore

call mvn clean package

@echo on
rmdir /s /q %TOMCAT_HOME%\webapps\jpetstore
call copy "%HOMEDRIVE%\%HOMEPATH%\.m2\repository\org\springframework\spring-instrument-classloading\%SPRING_VERSION%\spring-instrument-classloading-%SPRING_VERSION%.jar" %TOMCAT_HOME%\lib 
call copy target\org.springframework.samples.jpetstore-1.0.0-SNAPSHOT.war %TOMCAT_HOME%\webapps\jpetstore.war

call start db\hsqldb\server.bat
call %TOMCAT_HOME%\bin\startup.bat

call cd ..\Section9.5.2Spring-drivenLTW
