import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Ex2 {
	public static void main (String... args) throws IOException {
		//sacamos el string con la codificacion
		String charset = "UTF-8";
		//y otro con la info que mandamos a la pagina
		String data = URLEncoder.encode("entry.835030737", charset) + "=" + URLEncoder.encode("MLC", charset);
	    data += "&" + URLEncoder.encode("entry.1616686619", charset) + "=" +
		//y la busqueda al boton no para relllenar:
	    URLEncoder.encode("No", charset);
	    
	    //y ahroa tiramos en bucle las peticiones a la pagina:
	    for (int i = 0; i < 20; i++) {
	      URL url = new URL(
	          "https://docs.google.com/forms/d/e/1FAIpQLScE6sxLFb3BmCmT2TKHQH5ormS0qvjHwO-uTAR8MXaagBvSSQ/formResponse");
	      //abircmos la conexion URL a la direccion cada vez:
	      URLConnection con = url.openConnection();
	      //y las transformamos a HttpURLConecction
	      HttpURLConnection huc = (HttpURLConnection) con;
	      //configuramos la conexion forma post, que sea de saliada, codificacion UTF-8 en nuestro caso
	      huc.setRequestMethod("POST");
	      huc.setDoOutput(true);
	      huc.setRequestProperty("Accept-Charset", charset);
	      huc.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
	      //creamos un stream de salida de datos:
	      DataOutputStream dos = new DataOutputStream(huc.getOutputStream());
	      //y que nos lo escriba:
	      dos.write(data.getBytes(charset));
	      dos.flush();
	      dos.close();
	      System.out.println(huc.getHeaderField("entry.835030737"));
	      huc.disconnect();
	      System.out.println(i);
	    }
	    System.out.println("Rellenado");
	}
}