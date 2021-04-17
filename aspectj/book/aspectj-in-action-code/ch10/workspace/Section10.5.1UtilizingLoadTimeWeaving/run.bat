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

call ..\..\..\setHomes.bat
call ..\..\..\setEnvironment.bat
@echo on
setlocal
set MAVEN_OPTS=-javaagent:"%HOMEDRIVE%%HOMEPATH%\.m2\repository\org\aspectj\aspectjweaver\1.6.5\aspectjweaver-1.6.5.jar"
call mvn clean package
call mvn tomcat:run
endlocal
