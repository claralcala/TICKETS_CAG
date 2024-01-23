package es.iescarrillo.tickes_cag.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DetailsTicket implements Serializable {

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("amount")
    @Expose
    private Double amount;

    @SerializedName("ticket")
    @Expose
    private Ticket ticket;

    @SerializedName("id")
    @Expose
    private Integer id;

    public DetailsTicket(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DetailsTicket{" +
                "description='" + description + '\'' +
                ", amount=" + amount +
                ", ticket=" + ticket +
                ", id=" + id +
                '}';
    }
}
