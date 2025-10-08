package websearch; 

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Perform "web search" (from a  file), notify the interested observers of each query.
 */
public class WebSearchModel {
    // Classe interna para agrupar o Observer com sua Strategy (QueryFilter)
    private static class ObserverEntry {
        final QueryObserver observer;
        final QueryFilter filter;

        ObserverEntry(QueryObserver observer, QueryFilter filter) {
            this.observer = observer;
            this.filter = filter;
        }
    }

    private final File sourceFile;
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

    public void addQueryObserver(QueryObserver queryObserver, QueryFilter filter) {
        observerEntries.add(new ObserverEntry(queryObserver, filter));
    }

    // ALTERADO: Implementa a lógica de filtragem (Strategy)
    private void notifyAllObservers(String line) {
        for (ObserverEntry entry : observerEntries) {
            // APLICANDO A ESTRATÉGIA: só notifica se o filtro permitir
            if (entry.filter.shouldNotify(line)) {
                entry.observer.onQuery(line);
            }
        }
    }
}