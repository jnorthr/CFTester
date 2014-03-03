README.txt
tues.20 aug.2013
// read: https://weblogs.java.net/blog/manningpubs/archive/2013/03/18/building-java-web-application-gradle

// blitz passwords in *.gradle before deploying !!!!!

This dir was a set of gradle tests like

cd /Volumes/DURACELL/test 
$ gradlew -b buildcf.gradle  --info   jettyRun

or 
gradlew -b buildcf.gradle  --info   clean build runS
// to test java code to talk to cloud foundry target for source at
/Volumes/DURACELL/test/src/java/JavaSample2.java - may need to look up other methods on c/f client to add more functions to java test


The cf plugin does not work even with same target address https://api.run.pivotal.io so there is a glitch with cf 0.2.0 ? maybe needs later version but Scott has not uploaded it yet

kinda found a CF plugin with:
mavenRepo url :'http://repo.springsource.org/milestone'


also tried to make a .jar to go into local ~/.m2 maven repo
and added dpendsOn :
// puts compiled .jar into ~/.m2 maven local
install.dependsOn ':build'


also tried jettyRun but looks like folder structure not right for either the 'war' or 'jetty' plugins


// following parms feed the gradle plugin - the same parms feed the java sample below which runs fine with the same credentials
// this plugin reports bad atrget address - so maybe issue w/ CF 0.2.0

// a new task def allows several args - one per line - to feed the java main method
