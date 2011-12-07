#!/bin/bash
JREBEL_DIR=~/bin/jrebel
java -XL:MaxPermSize=786m -Xmx712M -Xss2M -XX:+CMSClassUnloadingEnabled -noverify -javaagent:$JREBEL_DIR/jrebel.jar -jar `dirname $0`/sbt-launcher.jar "$@"
