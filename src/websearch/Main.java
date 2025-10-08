package websearch; // Adicionado

import java.io.File;

/**
 * Launch the web-search example
 */
public class Main {
    public static void main(String[] args) {
        // O caminho foi ajustado para refletir a localização de Hamlet.txt na raiz da pasta src
        File inputTextFile = new File("src/Hamlet.txt"); 

        // Build object graph
        WebSearchModel model = new WebSearchModel(inputTextFile);
        Snooper snoop = new Snooper(model);

        // Execute
        model.pretendToSearch();
    }
}