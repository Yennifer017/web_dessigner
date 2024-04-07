echo "STARTING JFLEX COMPILING"

java -jar "/home/yennifer/Documents/compilator_java_resources/jflex.jar" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/analysis_resources/XML_Lexer.jflex"

mv "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/analysis_resources/XMLlexer.java" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/compi1/web_designer_api/htmltraductor"


echo "STARTING CUP COMPILING"
java -jar "/home/yennifer/Documents/compilator_java_resources/java-cup-11b.jar" -parser XMLparser "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/analysis_resources/XML_parser.cup"

mv "./XMLparser.java" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/compi1/web_designer_api/htmltraductor"
mv "./sym.java" "/home/yennifer/NetBeansProjects/WEB_DESSINGER/Web_designer_API/src/main/java/compi1/web_designer_api/htmltraductor"  
