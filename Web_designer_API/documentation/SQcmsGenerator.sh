echo "STARTING JFLEX COMPILING"

java -jar "/home/yennifer/Documents/compilator_java_resources/jflex.jar" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/analysis_resources/SQcms_lexer.jflex"

mv "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/analysis_resources/SQcmsLexer.java" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/compi1/web_designer_api/sqcmstraductor"


echo "STARTING CUP COMPILING"
java -jar "/home/yennifer/Documents/compilator_java_resources/java-cup-11b.jar" -parser SQcmsParser "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/analysis_resources/SQcms_parser.cup"

mv "./SQcmsParser.java" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/compi1/web_designer_api/htmltraductor"
mv "./sym.java" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/compi1/web_designer_api/htmltraductor" 
