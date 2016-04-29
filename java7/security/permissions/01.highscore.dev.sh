#
# Steps for the HighScore Developer (Chris)
#

# Compile the Classes

javac HighScore*.java -d .

# Place the class files in a JAR File

jar cvf hs.jar com/scoredev/scores/HighScore*.class

# Create a Keystore and Keys for Signing

keytool -genkey -keystore chris.keystore -alias signJars

# Specify whatever you want for the passwords and distinguished name information.
# Sign the JAR File

jarsigner -keystore chris.keystore hs.jar signJars

# Export the Public Key Certificate

keytool -export -keystore chris.keystore -alias signJars -file Chris.cer

# Supply Files and Information Needed by Game Developers and Users
# That is, supply them
#
#    * the signed JAR File hs.jar,
#    * the public key certificate file Chris.cer, and
#    * information as to the permissions the HighScore and HighScorePermission 
#      classes must be granted in a policy file in order to work. For this, Chris 
#      could supply the exact grant entry needed.



