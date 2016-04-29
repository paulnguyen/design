
# Steps for a User Running ExampleGame (Kim)
# The steps a user, such as Kim, would take, are:

# Import the Certificates as Trusted Certificates

keytool -import -alias chris -file Chris.cer -keystore kim.keystore
keytool -import -alias terry -file Terry.cer -keystore kim.keystore

# Set Up a Policy File With the Required Permissions
# Here's the complete kim.policy policy file, as described in 
# A Sample Policy File.

# Run ExampleGame

# To set the high score:

java -Djava.security.manager -Djava.security.policy=kim.policy -classpath ./hs.jar;./terry.jar com.gamedev.games.ExampleGame set 456 

# To get the high score:

java -Djava.security.manager -Djava.security.policy=kim.policy -classpath ./hs.jar;./terry.jar com.gamedev.games.ExampleGame get


# Notes:

#    * If you don't specify -Djava.security.manager, the application will run unrestricted 
#      (policy files and permissions won't be checked).

#    * The -Djava.security.policy=kim.policy tells where the policy file is. 
#      Note: There are other ways of specifying the policy file. For example, you can add 
#      an entry in the security properties file that specifies the inclusion of kim.policy, 
#      as discussed at the end of the See the Policy File Effects lesson.

#    * -classpath hs.jar;terry.jar specifies the JAR files that contain the class files needed. 
#      For Windows, use a semicolon (";") to separate JAR files; for UNIX, use a colon (":").

#    * The policy file kim.policy specifies the keystore kim.keystore. Since it does not provide 
#      an absolute URL location for the keystore, the keystore is assumed to be in the same directory 
#      as the policy file.


