package be.kanea.project.shoppinglist.client;

import be.kanea.project.shoppinglist.converter.JsonConverterFactory;
import be.kanea.project.shoppinglist.dto.UserDTO;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 *
 * @author cristiane
 */
public class WebServiceSeguridad {
    
    private String url;
    
    private SeguridadInterface seguridadInterface;
    
    public WebServiceSeguridad(String BASE_URL){
        
        this.url = BASE_URL;
        OkHttpClient client = new OkHttpClient();
        
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(this.url)
                .client(client)
                .addConverterFactory(JsonConverterFactory.create())
                .build();
        
        seguridadInterface = retrofit.create(SeguridadInterface.class);
        
    }
    
    public String getToken(JSONObject data){
        Call<JSONObject> retrofitCall = seguridadInterface.auth(RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), data.toString()));
        try{
            Response<JSONObject> respuesta = retrofitCall.execute();
            if(!respuesta.isSuccessful()){
                throw new IOException(respuesta.errorBody() != null
                        ? respuesta.errorBody().string() : "Error desconocido");
                
            }
            return respuesta.body().get("accessToken").toString();
        } catch(IOException e){
            return "El errore: "+e.getMessage();
        }        
    }
    
    public JSONObject getUsuario(String accessToken, String usuario){
        Call<JSONObject> retrofitCall = seguridadInterface.getUsuario(accessToken);
        try{
            Response<JSONObject> respuesta = retrofitCall.execute();
            if(!respuesta.isSuccessful()){
                throw new IOException(respuesta.errorBody() != null
                        ? respuesta.errorBody().string() : "Error desconocido");
                
            }
            return respuesta.body();
        } catch(IOException e){
            JSONObject data = new JSONObject();
            data.put("elerror", e.getMessage());
            return data;
        }        
    }
    
}
