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
cd ..\Section2.2.3InterTypeDeclaration\src\main\java
call ajdoc -d ..\..\..\..\Section2.7.2AspectJDocumentationTool\doc -sourcepath src\main\java -source 5 ajia\messaging\*.java ajia\main\*.java ajia\security\*.aj ajia\security\*.java ajia\track\*.aj ajia\profile\*.aj
del /s/q ajdocworkingdir
cd %~dp0%
@echo on
