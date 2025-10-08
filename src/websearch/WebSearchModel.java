package websearch; 

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Perform "web search" (from a  file), notify the interested observers of each query.
 */
public class WebSearchModel {
    // NOVO: Classe interna para agrupar o Observer com sua Strategy (QueryFilter)
    private static class ObserverEntry {
        final QueryObserver observer;
        final QueryFilter filter;

        ObserverEntry(QueryObserver observer, QueryFilter filter) {
            this.observer = observer;
            this.filter = filter;
        }
    }
    
    private final File sourceFile;
    // ALTERADO: A lista de observadores é agora uma lista de ObserverEntry
    private final List<ObserverEntry> observerEntries = new ArrayList<>();

    public interface QueryObserver {
        void onQuery(String query);
    }

    public WebSearchModel(File sourceFile) {
        this.sourceFile = sourceFile;
    }

    public void pretendToSearch() {
        try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
            while ( true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                notifyAllObservers(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // MANUTENÇÃO TEMPORÁRIA: Este método será corrigido no Passo 3.
    // O método original não é mais funcional com a nova estrutura interna.
    public void addQueryObserver(QueryObserver queryObserver) {
        // ... (Este método não pode mais funcionar. Será reescrito no Passo 3.)
    }
    
    // MANUTENÇÃO TEMPORÁRIA: Este método será corrigido no Passo 4.
    private void notifyAllObservers(String line) {
        // Por enquanto, apenas iteramos sobre a nova estrutura, sem lógica de filtro.
        // for (ObserverEntry entry : observerEntries) { entry.observer.onQuery(line); }
    }
}