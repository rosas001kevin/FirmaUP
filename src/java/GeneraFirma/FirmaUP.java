/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GeneraFirma;

//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lelguea
 */
@WebServlet(name = "Firma", urlPatterns = {"/Firma"})
public class FirmaUP extends HttpServlet {
    

    
    
  

    int getParamEntero(HttpServletRequest request,String pNombre, int pDefecto) {
        
        String param = request.getParameter(pNombre);

        if (param == null || param.compareTo("") == 0) {
            return pDefecto;
        }

        return Integer.parseInt(param);
    }

    String getParamCadena(HttpServletRequest request,String pNombre, String pDefecto) {
        
        String param = request.getParameter(pNombre);

        if (param == null || param.compareTo("") == 0) {
            return pDefecto;
        }

        return param;
    }

    
 
    /** Handles the HTTP GET method.
    * @param request servlet request
    * @param response servlet response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
           
        response.setContentType( "image/png" ) ;

       try {
            
            String Nombre;
            String Area;
            String Extension;
            String Numero;
            String Directo;

            Nombre=getParamCadena(request,"Nombre","");
            Nombre=Nombre.replaceAll("ó", "&oacute;");
            Area=getParamCadena(request,"Area","UP");
            Extension=getParamCadena(request,"Extension","Sin Ext");
            Directo=getParamCadena(request,"Directo","");
            Numero=getParamCadena(request,"Directo","5482 1600");
            
            //BufferedImage img = new BufferedImage(422, 113,
            //         BufferedImage.TYPE_INT_RGB);
            
            BufferedImage img= ImageIO.read(new File("c:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\fondo.png"));
            //BufferedImage img= ImageIO.read(new File("c:\\fondo.png"));
            
            int r = 125;
            int g = 0;
            int b = 0;
            int casirojo = (r << 16) | (g << 8) | b;
            
            
            r = 245;
            g = 245;
            b = 245;
            int casiblanco = (r << 16) | (g << 8) | b;
            
            
            Graphics gr = img.getGraphics();
            gr.setColor(Color.RED);
            
            gr.setColor(new Color(casirojo));
            gr.setFont(new Font("Arial", Font.PLAIN, 14));
            Font font = new Font("Arial", Font.PLAIN, 14);
            gr.setFont(font);
            FontMetrics metrics = gr.getFontMetrics( font );
            
            
            gr.setColor(Color.WHITE);
            //gr.fillRect(0, 0, 422, 113);
            
            gr.setColor(Color.BLUE);
            gr.drawString(Nombre, Alinea(Nombre,metrics), 25);
            gr.setColor(Color.BLACK);
            gr.drawString(Area,Alinea(Area, metrics), 45);
            
            gr.drawString(Numero, Alinea(Numero+" Ext. "+Extension, metrics), 65);
            gr.setColor(Color.BLUE);
            gr.drawString("Ext.",Alinea("Ext. "+Extension, metrics), 65);
            gr.setColor(Color.BLACK);
            gr.drawString(Extension, Alinea(Extension, metrics), 65);
            
            
            gr.setColor(Color.BLUE);
            gr.drawString("Directo", Alinea("Directo "+Directo, metrics), 85);
            
            gr.setColor(Color.BLACK);
            gr.drawString(Directo,Alinea(Directo, metrics), 85);
            
            
                    // send back image 
            ServletOutputStream sos = response.getOutputStream() ;
            //JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder( sos ) ;
            //encoder.encode( img ) ;
            ImageIO.write(img,"PNG", sos);

            
       } catch (Exception ex) { 
            System.err.println(ex.toString()+" "+System.getProperty("user.dir"));
       }

    }
    
    public int Alinea(String cadena, FontMetrics m) {
        int width = m.stringWidth( cadena );
        return 340-width;
    }
    
    
 

}