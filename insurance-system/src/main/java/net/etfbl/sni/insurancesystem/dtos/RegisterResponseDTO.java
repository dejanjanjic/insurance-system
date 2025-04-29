package net.etfbl.sni.insurancesystem.dtos;

import net.etfbl.sni.insurancesystem.model.User;

public class RegisterResponseDTO {
    private String username;
    private String mail;

    public RegisterResponseDTO(User user){
        this.username = user.getUsername();
        this.mail = user.getMail();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
