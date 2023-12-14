package com.example.quanly;

public class Oto {
    private String bks;
    private String tenxe;
    private String tenchuxe;
    private String loaixe;
    private String hangxe;
    private int namsanxuat;

    public Oto() {

    }

    public Oto(String bks, String tenxe, String tenchuxe, String loaixe, String hangxe, int namsanxuat) {
        this.bks = bks;
        this.tenxe = tenxe;
        this.tenchuxe = tenchuxe;
        this.loaixe = loaixe;
        this.hangxe = hangxe;
        this.namsanxuat = namsanxuat;
    }

    public String getBks() {
        return bks;
    }

    public void setBks(String bks) {
        this.bks = bks;
    }

    public String getTenxe() {
        return tenxe;
    }

    public void setTenxe(String tenxe) {
        this.tenxe = tenxe;
    }

    public String getTenchuxe() {
        return tenchuxe;
    }

    public void setTenchuxe(String tenchuxe) {
        this.tenchuxe = tenchuxe;
    }

    public String getLoaixe() {
        return loaixe;
    }

    public void setLoaixe(String loaixe) {
        this.loaixe = loaixe;
    }

    public String getHangxe() {
        return hangxe;
    }

    public void setHangxe(String hangxe) {
        this.hangxe = hangxe;
    }

    public int getNamsanxuat() {
        return namsanxuat;
    }

    public void setNamsanxuat(int namsanxuat) {
        this.namsanxuat = namsanxuat;
    }
}
