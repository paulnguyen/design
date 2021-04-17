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

call mvn clean package
call copy target\Section8.4LoadTimeWeaver-1.0-SNAPSHOT.jar %TOMCAT_HOME%\webapps\jpetstore\WEB-INF\lib

echo ##########################################################
echo Now onto Step 3
echo ##########################################################
echo To enable LTW in Tomcat, first copy 
echo %TOMCAT_HOME%\bin\startup.bat to 
echo %TOMCAT_HOME%\bin\startup-aspectj-ltw.bat 
echo so you can easily switch echo between different ways to start the server. 
echo Next, modify the new file by adding the following snippet at the top,
echo to add the AspectJ LTW agent to the Java VM used by Tomcat:
echo set JAVA_OPTS=-javaagent:"%HOMEDRIVE%%HOMEPATH%\.m2\repository\org\aspectj\aspectjweaver\1.6.5\aspectjweaver-1.6.5.jar"
echo ##########################################################
echo Then run run3.bat
echo ##########################################################
