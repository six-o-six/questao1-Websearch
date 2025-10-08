package websearch;

/**
 * Watches the search queries e configura os novos observadores/filtros.
 */
public class Snooper {
    private final WebSearchModel model;

    public Snooper(WebSearchModel model) {
        this.model = model;

        // 1. Observador para consultas que contêm 'friend' (case-insensitive)
        WebSearchModel.QueryObserver friendObserver = new WebSearchModel.QueryObserver() {
            @Override
            public void onQuery(String query) {
                System.out.println("Oh Yes! " + query);
            }
        };

        QueryFilter friendFilter = new QueryFilter() {
            @Override
            public boolean shouldNotify(String query) {
                // Estratégia 1: Contém 'friend' (ignora case)
                return query.toLowerCase().contains("friend");
            }
        };
        
        model.addQueryObserver(friendObserver, friendFilter);


        // NOVO: 2. Observador para consultas com mais de 60 caracteres
        WebSearchModel.QueryObserver longQueryObserver = new WebSearchModel.QueryObserver() {
            @Override
            public void onQuery(String query) {
                System.out.println("So long " + query);
            }
        };

        QueryFilter longQueryFilter = new QueryFilter() {
            @Override
            public boolean shouldNotify(String query) {
                // Estratégia 2: Comprimento maior que 60 caracteres
                return query.length() > 60;
            }
        };

        model.addQueryObserver(longQueryObserver, longQueryFilter);
    }
}