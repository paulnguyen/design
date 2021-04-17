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

del ajia\*.class
del ajia\auth\*.class

call ajc -source 5 ajia\MessageCommunicator.java ajia\SecurityAspect.java ajia\Main.java ajia\auth\*.java
@echo on
call java ajia.Main
