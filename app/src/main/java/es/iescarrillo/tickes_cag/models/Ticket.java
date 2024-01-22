package es.iescarrillo.tickes_cag.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Ticket implements Serializable {

    @SerializedName("creationDate")
    @Expose
    private String creationDate;

    @SerializedName("totalAmount")
    @Expose
    private Double totalAmount;

    @SerializedName("id")
    @Expose
    private Integer id;

    public Ticket(){

    }


    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "creationDate='" + creationDate + '\'' +
                ", totalAmount=" + totalAmount +
                ", id=" + id +
                '}';
    }
}
