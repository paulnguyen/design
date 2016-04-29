#
# Steps for the ExampleGame Developer (Terry)
#

# The steps Terry would take, after creating a game (ExampleGame ) that calls the 
# HighScore getHighScore and setHighScore methods to get and set, respectively, 
# the user's high scores, are:

# Compile the Game Class

javac ExampleGame.java -classpath hs.jar -d .

# Place its class file in a JAR File

jar cvf terry.jar com/gamedev/games/ExampleGame.class

# Create a Keystore and Keys for Signing

keytool -genkey -keystore terry.keystore -alias signTJars

# Specify whatever you want for the passwords and distinguished name information.
# Sign the JAR File

jarsigner -keystore terry.keystore terry.jar signTJars

# Export the Public Key Certificate

keytool -export -keystore terry.keystore -alias signTJars -file Terry.cer

# Supply Files and Information Needed by Users
# That is, supply them
#    * the signed JAR File terry.jar,
#    * the public key certificate file Terry.cer, and
#    * information as to the permissions the ExampleGame class needs. 
#      For this, Terry could supply the exact grant entry needed.

# Game users also need files and information from Chris. For their convenience, 
# Terry may forward this information to them:
#    * the signed JAR File hs.jar,
#    * the public key certificate file Chris.cer, and
#    * information as to the permissions the HighScore and HighScorePermission 
#      classes must be granted in a policy file in order to work. This could be 
#      the exact grant entry needed.


