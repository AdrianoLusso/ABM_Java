package Modelo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class WebService {

	private static String ip = "192.9.200.190";
	private static String usuario = "usuario";
	private static String clave = "tithadpa";
	
	public WebService(){}
	
	public HashMap<String, Object> llamar(HashMap<String, Object> params) {
        HashMap<String, Object> respuesta = new HashMap<>();

        try {
            String url = "http://192.9.200.190/ws/request.php";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            // Configurar la conexión
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json");
            con.setDoOutput(true);

            // Crear el JSON de solicitud
            String jsonInputString = params.toString();

            // Enviar la solicitud
            try (OutputStream os = con.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Obtener la respuesta del WS
            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Procesar la respuesta JSON
                String jsonResponse = response.toString();
                // Puedes usar una biblioteca como Gson para analizar la respuesta JSON si es necesario.
                // Ejemplo: Gson gson = new Gson();
                // HashMap<String, Object> data = gson.fromJson(jsonResponse, HashMap.class);

                // Llenar la respuesta
                respuesta.put("respuesta", "Éxito");
                respuesta.put("mensaje", "Solicitud exitosa");
                respuesta.put("resultado", jsonResponse);
            } else {
                respuesta.put("respuesta", "Error");
                respuesta.put("mensaje", "Error en la solicitud");
            }

        } catch (Exception e) {
            respuesta.put("respuesta", "Error");
            respuesta.put("mensaje", e.getMessage());
        }

        return respuesta;
    }
}

