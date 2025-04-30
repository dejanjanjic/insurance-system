package net.etfbl.sni.insurancesystem.dtos;

public class VerificationCodeDTO {
    String mail;
    String verificationCode;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
