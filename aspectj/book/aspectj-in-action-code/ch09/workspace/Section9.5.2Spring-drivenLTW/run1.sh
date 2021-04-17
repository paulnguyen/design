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

NEED_TOMCAT=Y
source ../../../setHomes.sh
source ../../../setEnvironment.sh

cp src/main/resources/WEB-INF/weaverContext.xml ../org.springframework.samples.jpetstore/src/main/webapp/WEB-INF
cp src/main/resources/META-INF/context.xml ../org.springframework.samples.jpetstore/src/main/webapp/META-INF
echo "Modify ../org.springframework.samples.jpetstore/src/main/webapp/WEB-INF/web.xml "
echo "to add /WEB-INF/weaverContext.xml" 
echo "as param-value for contextConfigLocation as shown in Listing 9.10."

echo "Also you may have to increase maximum memroy allocated to Tomcat by adding"
echo "JAVA_OPTS=-Xmx512m to $TOMCAT_HOME/bin/catalina.sh"
