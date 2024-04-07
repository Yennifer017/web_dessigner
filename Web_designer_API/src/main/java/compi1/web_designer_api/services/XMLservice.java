package compi1.web_designer_api.services;

import compi1.web_designer_api.htmltraductor.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

/**
 *
 * @author yennifer
 */
public class XMLservice {

    public void executeXML(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String textUser = getRequestContent(request);
        StringReader reader = new StringReader(textUser);
        XMLlexer lexer = new XMLlexer(reader);
        XMLparser parser = new XMLparser(lexer);

        try {
            /*while (!lexer.yyatEOF()) {                
                lexer.next_token();
            }
            showTkns(lexer);
            if(!lexer.getErrors().isEmpty()){
                String content = "\n Erorres encontrados: ";
                content += showErrors("ERRORES LEXICOS", lexer.getErrors());
                System.out.println(content);
            }*/
            
            parser.parse();
            if (lexer.getErrors().isEmpty() && parser.getSyntaxErrors().isEmpty()) {
                System.out.println("Ha pasado la prueba");
            } else {
                String content = "\n Erorres encontrados: ";
                content += showErrors("ERRORES LEXICOS", lexer.getErrors());
                content += showErrors("ERRORES SINTACTICOS", parser.getSyntaxErrors());
                System.out.println(content);
                showTkns(lexer);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            System.out.println("manejo de exception");
        }
    }

    private String getRequestContent(HttpServletRequest request) throws IOException {
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        String requestBody = sb.toString();
        return requestBody;
    }

    private String showErrors(String mss, List<String> errors) {
        String content = "--------------------------------------------\n" + mss + "\n";
        if (errors.isEmpty()) {
            content += "     __ningun_error__\n";
        } else {
            for (int i = 0; i < errors.size(); i++) {
                content += errors.get(i);
                content += "\n";
            }
        }
        return content;
    }
    
    private void showTkns(XMLlexer lexer){
        for (int i = 0; i < lexer.getTokens().size(); i++) {
            System.out.print(lexer.getTokens().get(i).getType());
            if(lexer.getTokens().get(i).getLexem() != null){
                System.out.println(lexer.getTokens().get(i).getLexem().toString());
            }else{
                System.out.println();
            }
        }
    }
}
