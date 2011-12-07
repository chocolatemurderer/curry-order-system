set SCRIPT_DIR=%~dp0
set JREBEL_DIR=C:\JRebel\
java -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=786m -Xmx712M -Xss2M -noverify -javaagent:"%JREBEL_DIR%\jrebel.jar" -jar "%SCRIPT_DIR%\sbt-launcher.jar" %*
