# Copyright 2009 Ramnivas Laddad
# 
# Licensed under the Apache License, Version 2.0 (the "License"); 
# you may not use this file except in compliance with the License. 
# You may obtain a copy of the License at 
#     http://www.apache.org/licenses/LICENSE-2.0 
# 
# Unless required by applicable law or agreed to in writing, software 
# distributed under the License is distributed on an "AS IS" BASIS, 
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
# See the License for the specific language governing permissions and 
# limitations under the License. 

source ../../../setHomes.sh
source ../../../setEnvironment.sh
cd src/main/java
mkdir -p classes
mkdir -p aspects
javac -d classes ajia/messaging/MessageCommunicator.java ajia/main/Main.java ajia/security/AuthenticationException.java ajia/security/Authenticator.java
javac -d aspects ajia/security/SecurityAspect.java
# Adding ../resources to the classpath, since META-INF/aop.xml is in src/main/resources 
java -classpath classes:aspects:../resources -javaagent:"$HOME/.m2/repository/org/aspectj/aspectjweaver/1.6.5/aspectjweaver-1.6.5.jar" ajia.main.Main
