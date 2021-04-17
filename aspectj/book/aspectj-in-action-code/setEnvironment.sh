CHECK_HOME_MSG="Check setHomes.sh or set correct environment variables"

if [ "$ASPECTJ_HOME" == "" ] ; then
    echo "Please set the ASPECTJ_HOME variable. $CHECK_HOME_MSG"
    exit 1
fi
if [ ! -f "$ASPECTJ_HOME/bin/ajc" ] ; then
    echo "Invalid ASPECTJ_HOME. "
	echo "    It must point to the parent of the bin and lib directory. $CHECK_HOME_MSG"
    exit 1
fi

CLASSPATH=$ASPECTJ_HOME/lib/aspectjrt.jar:$CLASSPATH
PATH=$ASPECTJ_HOME/bin:$PATH

if [ "$NEED_TOMCAT" != "" ] ; then 
    if [ "$TOMCAT_HOME" == "" ] ; then 
		echo "Please set the TOMCAT_HOME variable. $CHECK_HOME_MSG"
		exit 1
    fi
    if [ ! -d "$TOMCAT_HOME/bin" ] ; then
		echo "Invalid TOMCAT_HOME. "
		echo "It must point to the parent of the bin directory. $CHECK_HOME_MSG"
        exit 1
    fi
    CATALINA_HOME=$TOMCAT_HOME
fi

if [ "$NEED_ANT" != "" ] ; then 
	if [ "$ANT_HOME" == "" ] ; then 
		echo "Please set the ANT_HOME variable. $CHECK_HOME_MSG"
		exit 1
	fi
	if [ ! -f "$ANT_HOME/bin/ant" ] ; then
		echo "Invalid ANT_HOME. "
		echo "It must point to the parent of bin directory. $CHECK_HOME_MSG"
		exit 1
	fi
fi
PATH=$ANT_HOME/bin:$PATH

if [ "$MAVEN_HOME" == "" ] ; then 
    echo "Please set the MAVEN_HOME variable. $CHECK_HOME_MSG"
    exit 1
fi
if [ ! -f "$MAVEN_HOME/bin/mvn" ] ; then
    echo "Invalid MAVEN_HOME. "
	echo "It must point to the parent of bin directory. $CHECK_HOME_MSG"
    exit 1
fi
PATH=$MAVEN_HOME/bin:$PATH

export CLASSPATH
export PATH

