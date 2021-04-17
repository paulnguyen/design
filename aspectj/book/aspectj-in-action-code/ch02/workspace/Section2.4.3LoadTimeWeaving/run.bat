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
@echo off
cd src\main\java
mkdir classes
mkdir aspects
@echo on
javac -d classes ajia\messaging\MessageCommunicator.java ajia\main\Main.java ajia\security\AuthenticationException.java ajia\security\Authenticator.java
call javac -d aspects ajia\security\SecurityAspect.java
@rem Adding ..\resources to the classpath, since META-INF/aop.xml is in src/main/resources 
call java -classpath classes;aspects;..\resources -javaagent:"%HOMEDRIVE%%HOMEPATH%\.m2\repository\org\aspectj\aspectjweaver\1.6.5\aspectjweaver-1.6.5.jar" ajia.main.Main
@echo off
cd ..\..\..\
@echo on
