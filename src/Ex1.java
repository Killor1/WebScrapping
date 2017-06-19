import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex1 {
	public static void main (String... args) throws IOException {
	    String tag;
	    
	    //Primero vemos que haya dos argumentos en la entrada, y si no lo hay, que cierre:
	    if(args.length !=2 ) {
	      System.out.println("Error en los argumentos : getTag2Html URL tag");
	      System.exit(0);
	    }
	    //si no.....
	    else {
	      //cogemos los parametros:
	      URL url = new URL(args[0]);
	      tag = args[1];

	      // y creamos el patron de busqueda;
	      String patron = "<" + tag + ".*\\/?>";
	      Pattern p = Pattern.compile(patron);

	      System.out.println("Estamos buscando en: " + url.toString() + " la etiqueta: " + tag);
	      //iniciamos la conexion:
	      HttpURLConnection huc = (HttpURLConnection) url.openConnection();
	      BufferedReader br = new BufferedReader( new InputStreamReader(huc.getInputStream()) );
	      String line;
	      while( (line = br.readLine()) != null) {
	        Matcher m = p.matcher(line);
	        //y con el matcher buscamos linea a linea la coincidencia con el patron y la sacamos por pantalla:
	        if(m.find()) System.out.println(line);
	      }
	      br.close();	      
	    }
	  }
}
