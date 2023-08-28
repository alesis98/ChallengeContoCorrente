package it.corso.ContoCorrente.dto;

import java.util.Objects;

public class ContoCorrente {

    private long numeroConto;
    private String CIN;
    private int ABI;
    private int CAB;
    private String nomeTitolare;
    private String cognomeTitolare;
    private String codiceFiscaleTitolare;

    public ContoCorrente(long numeroConto, String CIN, int ABI, int CAB, String nomeTitolare, String cognomeTitolare, String codiceFiscaleTitolare) {
        this.numeroConto = numeroConto;
        this.CIN = CIN;
        this.ABI = ABI;
        this.CAB = CAB;
        this.nomeTitolare = nomeTitolare;
        this.cognomeTitolare = cognomeTitolare;
        this.codiceFiscaleTitolare = codiceFiscaleTitolare;
    }

    public ContoCorrente() {
    }

    public long getNumeroConto() {
        return numeroConto;
    }

    public void setNumeroConto(long numeroConto) {
        this.numeroConto = numeroConto;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public int getABI() {
        return ABI;
    }

    public void setABI(int ABI) {
        this.ABI = ABI;
    }

    public int getCAB() {
        return CAB;
    }

    public void setCAB(int CAB) {
        this.CAB = CAB;
    }

    public String getNomeTitolare() {
        return nomeTitolare;
    }

    public void setNomeTitolare(String nomeTitolare) {
        this.nomeTitolare = nomeTitolare;
    }

    public String getCognomeTitolare() {
        return cognomeTitolare;
    }

    public void setCognomeTitolare(String cognomeTitolare) {
        this.cognomeTitolare = cognomeTitolare;
    }

    public String getCodiceFiscaleTitolare() {
        return codiceFiscaleTitolare;
    }

    public void setCodiceFiscaleTitolare(String codiceFiscaleTitolare) {
        this.codiceFiscaleTitolare = codiceFiscaleTitolare;
    }

    @Override
    public String toString() {
        return "ContoCorrente{" +
                "numeroConto=" + numeroConto +
                ", CIN='" + CIN + '\'' +
                ", ABI=" + ABI +
                ", CAB=" + CAB +
                ", nomeTitolare='" + nomeTitolare + '\'' +
                ", cognomeTitolare='" + cognomeTitolare + '\'' +
                ", codiceFiscaleTitolare='" + codiceFiscaleTitolare + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if(this==o)
            return true;
        if(!(o instanceof ContoCorrente))
            return false;
        ContoCorrente contoCorrente=(ContoCorrente)o;
        return Objects.equals(numeroConto,contoCorrente.numeroConto) && Objects.equals(CIN,contoCorrente.CIN) && Objects.equals(CAB,contoCorrente.CAB) && Objects.equals(nomeTitolare,contoCorrente.nomeTitolare) && Objects.equals(cognomeTitolare,contoCorrente.cognomeTitolare) && Objects.equals(codiceFiscaleTitolare,contoCorrente.codiceFiscaleTitolare);
    }
}
