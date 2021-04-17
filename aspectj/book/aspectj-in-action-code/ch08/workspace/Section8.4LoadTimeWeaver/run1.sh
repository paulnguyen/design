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
cd ../org.springframework.samples.jpetstore 
mvn clean package
cp target/org.springframework.samples.jpetstore-1.0.0-SNAPSHOT.war $TOMCAT_HOME/webapps/jpetstore.war
xterm -e sh db/hsqldb/server.sh &
xterm -e "sh $TOMCAT_HOME/bin/shutdown.sh; $TOMCAT_HOME/bin/catalina.sh run" &
echo "#####################################################################"
echo "Visit the web page for the application, and buy a few pets."
echo "If everything goes fine, run the run2.sh."
echo "#####################################################################"
