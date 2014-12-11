package client.costarica.main;

public class Main
{
    public static void main( String[] args )
    {
        String pronostico = pronosticoPorCiudad();
        System.out.format( "Pronóstico: %s%n", pronostico );
    }

    private static String pronosticoPorCiudad()
    {
        client.costarica.WSMeteorologico service = new client.costarica.WSMeteorologico();
        client.costarica.WSMeteorologicoSoap port = service.getWSMeteorologicoSoap();
        return port.pronosticoPorCiudad();
    }
}
