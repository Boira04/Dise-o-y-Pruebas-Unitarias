package data;

/**
 * Interfície per definir les operacions essencials d'un punt geogràfic.
 */
public interface GeographicPointInterface {

    /**
     * Retorna la latitud del punt geogràfic.
     *
     * @return la latitud com un float.
     */
    float getLatitude();

    /**
     * Retorna la longitud del punt geogràfic.
     *
     * @return la longitud com un float.
     */
    float getLongitude();

    /**
     * Compara aquest objecte amb un altre per veure si són iguals.
     *
     * @param o Objecte a comparar.
     * @return true si són iguals, false en cas contrari.
     */
    @Override
    boolean equals(Object o);

    /**
     * Genera un codi hash per a aquest objecte.
     *
     * @return el codi hash com un enter.
     */
    @Override
    int hashCode();

    /**
     * Retorna una representació textual del punt geogràfic.
     *
     * @return una cadena que representa el punt geogràfic.
     */
    @Override
    String toString();
}
