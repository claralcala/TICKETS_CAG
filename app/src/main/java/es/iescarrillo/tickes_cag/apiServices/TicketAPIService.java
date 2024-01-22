package es.iescarrillo.tickes_cag.apiServices;

import java.util.List;

import es.iescarrillo.tickes_cag.models.Ticket;
import retrofit2.Call;
import retrofit2.http.GET;

public interface TicketAPIService {

    @GET("tickets/")
    Call<List<Ticket>> getTickets();




}
