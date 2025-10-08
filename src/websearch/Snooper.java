package websearch;

/**
 * Watches the search queries e configura os novos observadores/filtros.
 */
public class Snooper {
    private final WebSearchModel model;

    public Snooper(WebSearchModel model) {
        this.model = model;

        // REMOVIDO: O observador original foi removido, pois a assinatura do addQueryObserver mudou.

        // NOVO: 1. Observador para consultas que contêm 'friend' (case-insensitive)
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
        
        // Registra o observador e seu filtro usando a nova API
        model.addQueryObserver(friendObserver, friendFilter);

        // O segundo observador será adicionado no Passo 6
    }
}