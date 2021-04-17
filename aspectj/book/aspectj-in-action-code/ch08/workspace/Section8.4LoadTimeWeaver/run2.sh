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
mvn clean package
cp target/Section8.4LoadTimeWeaver-1.0-SNAPSHOT.jar $TOMCAT_HOME/webapps/jpetstore/WEB-INF/lib

echo "##########################################################"
echo "Now onto Step 3"
echo "##########################################################"
echo "To enable LTW in Tomcat, first copy "
echo "$TOMCAT_HOME/bin/catalina.sh to "
echo "$TOMCAT_HOME/bin/catalina-aspectj-ltw.sh "
echo "so you can easily switch echo between different ways to start the server. "
echo "Next, modify the new file by adding the following snippet at the top,"
echo "to add the AspectJ LTW agent to the Java VM used by Tomcat:"
echo "JAVA_OPTS=-javaagent:$HOME/.m2/repository/org/aspectj/aspectjweaver/1.6.5/aspectjweaver-1.6.5.jar"
echo "##########################################################"
echo "Then run run3.sh"
echo "##########################################################"
