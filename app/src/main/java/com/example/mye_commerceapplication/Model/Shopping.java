package com.example.mye_commerceapplication.Model;

public class Shopping {

    private String date,idLignCommande,productId,quantity,status,telNumberClient;

    public Shopping(String date, String idLignCommande, String productId, String quantity, String status, String telNumberClient) {
        this.date = date;
        this.idLignCommande = idLignCommande;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
        this.telNumberClient = telNumberClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIdLignCommande() {
        return idLignCommande;
    }

    public void setIdLignCommande(String idLignCommande) {
        this.idLignCommande = idLignCommande;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTelNumberClient() {
        return telNumberClient;
    }

    public void setTelNumberClient(String telNumberClient) {
        this.telNumberClient = telNumberClient;
    }

    @Override
    public String toString() {
        return "Shopping{" +
                "date='" + date + '\'' +
                ", idLignCommande='" + idLignCommande + '\'' +
                ", productId='" + productId + '\'' +
                ", quantity='" + quantity + '\'' +
                ", status='" + status + '\'' +
                ", telNumberClient='" + telNumberClient + '\'' +
                '}';
    }

}
