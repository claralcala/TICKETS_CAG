package es.iescarrillo.tickes_cag.apiServices;

import java.util.List;

import es.iescarrillo.tickes_cag.models.DetailsTicket;
import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TicketAPIService {

    @GET("api/tickets/")
    Call<List<Ticket>> getTickets();



    @GET("api2/detailTickets/{id}")
    Call<List<DetailsTicket>> getDetails(@Path("id")Integer id);


    /**
     * Publica un nuevo ticket en el servidor.
     *
     * @param ticket El objeto Ticket que se enviará en el cuerpo de la solicitud, debe ir con el ID a 0.
     * @return Una llamada de Retrofit que contiene el Ticket creado, con el ID autoasignado.
     */
    @POST("api/ticket/")
    Call<Ticket> postTicket(@Body Ticket ticket);



}
