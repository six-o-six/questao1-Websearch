package websearch;

/**
 * Interface de estratégia para filtrar consultas.
 */
public interface QueryFilter {
    /**
     * Verifica se uma consulta deve ser notificada ao observador.
     * @param query A string de consulta.
     * @return true se o observador deve ser notificada, false caso contrário.
     */
    boolean shouldNotify(String query);
}