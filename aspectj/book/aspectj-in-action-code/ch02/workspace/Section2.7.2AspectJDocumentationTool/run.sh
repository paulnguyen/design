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
cd ../Section2.2.3InterTypeDeclaration/src/main/java
ajdoc -d ../../../../Section2.7.2AspectJDocumentationTool/doc -sourcepath src/main/java -source 5 ajia/messaging/*.java ajia/main/*.java ajia/security/*.aj ajia/security/*.java ajia/track/*.aj ajia/profile/*.aj
rm -rf ajdocworkingdir
