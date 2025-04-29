package net.etfbl.sni.insurancesystem.model;

import lombok.*;


public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }
}
