package com.application.musicdatabaseapp.models;

import java.io.Serializable;

public class ArtistModel implements Serializable {

    private String Mov_id;
    private String MovName;
    private String Act;
    private String Acc;
    private String Yor;
    private String Dir;

    public String getMov_id() {
        return Mov_id;
    }

    public void setMov_id(String mov_id) {
        Mov_id = mov_id;
    }

    public String getMovName() {
        return MovName;
    }

    public void setMovName(String movName) {
        MovName = movName;
    }

    public String getAct() {
        return Act;
    }

    public void setAct(String act) {
        Act = act;
    }

    public String getAcc() {
        return Acc;
    }

    public void setAcc(String acc) {
        Acc = acc;
    }

    public String getYor() {
        return Yor;
    }

    public void setYor(String yor) {
        Yor = yor;
    }

    public String getDir() {
        return Dir;
    }

    public void setDir(String dir) {
        Dir = dir;
    }
}
