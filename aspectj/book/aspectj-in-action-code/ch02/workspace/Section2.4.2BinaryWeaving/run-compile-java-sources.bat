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
cd src\main\java
mkdir classes
@echo on
call javac -d classes ajia\messaging\MessageCommunicator.java ajia\security\Authenticator.java ajia\security\AuthenticationException.java ajia\main\Main.java
@echo on
call java -classpath classes ajia.main.Main
@echo off
cd ..\..\..\
@echo on