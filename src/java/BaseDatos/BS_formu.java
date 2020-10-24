
package BaseDatos;
import BaseDatos.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author zeus
 */
public class BS_formu {
    
    private Conexion conn;
    private Connection cn;
    private PreparedStatement prstmt = null;
    private ResultSet result = null;
                StringBuffer respuesta =  new StringBuffer();

    public BS_formu(){
        conn= new Conexion();
        cn=conn.conectar();
        
    }
    
    
    public String registro_estudiantes(String CARNE, String NOMBRE_Y_APELLIDO, String FECHA_DE_NACIMIENTO, String CORREO_ELEC, String TELEFONO ){
        String sql = "INSERT INTO DATOS_ESTUDIANTE( CARNE, NOMBRE_Y_APELLIDO, FECHA_DE_NACIMIENTO, CORREO_ELEC, TELEFONO)";
        sql += "VALUES(?,?,?,?,?)";
        System.out.println(CARNE);
        System.out.println(sql);
        
        try {                    
            prstmt = cn.prepareStatement(sql);
            prstmt.setString(1, CARNE);
            prstmt.setString(2, NOMBRE_Y_APELLIDO);
            prstmt.setString(3, FECHA_DE_NACIMIENTO);
            prstmt.setString(4, CORREO_ELEC);
            prstmt.setString(5, TELEFONO);
            
            int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                   return "1";
                }else{
                     return "0";
                }
        }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                //salida.append("ORA-00001");
                  return "ORA-00001";
            }else{
                //salida.append(error);
                  return "error al guardar";
            }
        }
    }
    public String registro_grado(String ID_GRADO, String DESCRIPCION_GRADO){
        String sql = "INSERT INTO DATOS_GRADO( ID_GRADO, DESCRIPCION_GRADO)";
        sql += "VALUES(?,?)";
        System.out.println(ID_GRADO);
        System.out.println(sql);
        
        try {                    
            prstmt = cn.prepareStatement(sql);
            prstmt.setString(1, ID_GRADO);
            prstmt.setString(2, DESCRIPCION_GRADO);
                        
            int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                   return "1";
                }else{
                     return "0";
                }
        }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                //salida.append("ORA-00001");
                  return "ORA-00001";
            }else{
                //salida.append(error);
                  return "error al guardar";
            }
        }   
        }
    
       public String registro_seccion(String ID_SECCION){
        String sql = "INSERT INTO DATOS_SECCION( ID_SECCION)";
        sql += "VALUES(?)";
        System.out.println(ID_SECCION);
        System.out.println(sql);
        
        try {                    
            prstmt = cn.prepareStatement(sql);
            prstmt.setString(1, ID_SECCION);
           
                        
            int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                   return "1";
                }else{
                     return "0";
                }
        }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                //salida.append("ORA-00001");
                  return "ORA-00001";
            }else{
                //salida.append(error);
                  return "error al guardar";
            }
        }   
        }
    
       
        public String registro_inscripcion(String ID_CARNE, String ID_GRADO, String ID_SECCION){
        String sql = "INSERT INTO DATOS_REGISTRO( ID_CARNE, ID_GRADO, ID_SECCION )";
        sql += "VALUES(?,?,?)";
        System.out.println(ID_CARNE);
        System.out.println(sql);
        
        try {                    
            prstmt = cn.prepareStatement(sql);
            prstmt.setString(1, ID_CARNE);
            prstmt.setString(2, ID_GRADO);
            prstmt.setString(3, ID_SECCION);            
            int resultado = prstmt.executeUpdate(); 
                if(resultado > 0){
                   return "1";
                }else{
                     return "0";
                }
        }catch(SQLException e){
            String error = e.getMessage();  
            if(error.indexOf("ORA-00001") != -1){
                //salida.append("ORA-00001");
                  return "ORA-00001";
            }else{
                //salida.append(error);
                  return "error al guardar";
            }
        }
        }
        public StringBuffer Tablas_Relacionadas()
    {
        String sql= "SELECT * FROM DATOS_REGISTRO INNER JOIN DATOS_ESTUDIANTE ON  DATOS_REGISTRO.ID_CARNE= datos_estudiante.carne";
                
            System.out.println(sql);
        try{
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();            
                while (result.next()){
                respuesta.append("<tr>");
                respuesta.append("<td >").append(result.getString("CARNE")).append("</td>");
                respuesta.append("<td >").append(result.getString("NOMBRE_Y_APELLIDO")).append("</td>");
                respuesta.append("<td >").append(result.getString("CORREO_ELEC")).append("</td>");
                respuesta.append("<td >").append(result.getString("TELEFONO")).append("</td>");
                respuesta.append("<td >").append(result.getString("ID_GRADO")).append("</td>");
                respuesta.append("<td >").append(result.getString("ID_SECCION")).append("</td>");
                respuesta.append("</tr>");}
        }catch(SQLException e){
       String error=e.getMessage();
       if(error.indexOf("ORA-00001")!=-1){
       return respuesta.append(error);
       }else{
       return respuesta.append(error);
       }
       }
        System.out.println(respuesta);
        return respuesta;
        } 
        
         public StringBuffer MostrarSeccion()
    {
        String sql= "SELECT * FROM DATOS_SECCION ";
        try{
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();            
                while (result.next()){
                respuesta.append("<option >").append(result.getString("ID_SECCION")).append("</option>");
                }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println(respuesta);
        return respuesta;
        }
         
         
            public StringBuffer MostrarGrado()
    {
        String sql= "SELECT * FROM DATOS_GRADO ";
        try{
        prstmt = cn.prepareStatement(sql);                        
        result = prstmt.executeQuery();            
                while (result.next()){
                respuesta.append("<option >").append(result.getString("ID_GRADO")).append("</option>");
                respuesta.append("<option >").append(result.getString("DESCRIPCION_GRADO")).append("</option>");
                }
        }
        catch(SQLException ex){
            ex.printStackTrace();
        }
        System.out.println(respuesta);
        return respuesta;
        }
        
      
        
        

        /* public StringBuffer MostrarSecciones(){
        prstmt=null;
        result=null;
        try{
        String sql = "SELECT * FROM DATOS_REGISTRO INNER JOIN DATOS_ESTUDIANTE "+"  ON  DATOS_REGISTRO.ID_CARNE= datos_estudiante.carne";
        prstmt=cn.prepareStatement(sql);
        result=prstmt.executeQuery();
        respuesta.append("<option> HOla Mundo</option>");
        while(result.next()){
       respuesta.append("<option> ").append(result.getString("ID_CARNE")).append(" </option>");
       respuesta.append("<option> ").append(result.getString("NOMBRE_Y_APELLIDO")).append(" </option>");
       respuesta.append("<option> ").append(result.getString("CORREO_ELEC")).append(" </option>");
       respuesta.append("<option> ").append(result.getString("TELEFONO")).append(" </option>");
       respuesta.append("<option> ").append(result.getString("ID_GRADO")).append(" </option>");
       respuesta.append("<option> ").append(result.getString("ID_SECCION")).append(" </option>");
       
       
        }
        
        
        }catch(SQLException e){
       String error=e.getMessage();
       if(error.indexOf("ORA-00001")!=-1){
       return respuesta.append(error);
       }else{
       return respuesta.append(error);
       }
       }
        return respuesta;
 
       } */      
    
        
}
            
        
        

    
        
        
        
         
               
    
           
