package com.example.cafoma_app.entite;

public class Formation {
    private Integer formation_id;
    private Integer fk_user_id;
    private String libelle;
    private String acronyme;
    private String description;
    private String img;
    private String video;

    public Formation (Integer formation_id, Integer fk_user_id, String libelle, String acronyme, String description, String img, String video){
        this.formation_id = formation_id;
        this.fk_user_id = fk_user_id;
        this.libelle = libelle;
        this.acronyme = acronyme;
        this.description = description;
        this.img = img;
        this.video = video;
    }

    public Formation() {
        this.formation_id = formation_id;
        this.fk_user_id = fk_user_id;
        this.libelle = libelle;
        this.acronyme = acronyme;
        this.description = description;
        this.img = img;
        this.video = video;
    }
    public String getFormationStr(){
        return  libelle;
    }

    @Override
    public String toString() {
        return "Formation{" +
                "formation_id=" + formation_id +
                ", fk_user_id=" + fk_user_id +
                ", libelle='" + libelle + '\'' +
                ", acronyme='" + acronyme + '\'' +
                ", description='" + description + '\'' +
                ", img='" + img + '\'' +
                ", video='" + video + '\'' +
                '}';
    }

    public Integer getFormation_id() {
        return formation_id;
    }
    public void setFormation_id(Integer formation_id) {
        this.formation_id = formation_id;
    }

    public Integer getFk_user_id() {return fk_user_id;}
    public void setFk_user_id(Integer fk_user_id) {this.fk_user_id = fk_user_id;}

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAcronyme() {
        return acronyme;
    }
    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {this.libelle = img;}

    public String getVideo() {
        return video;
    }
    public void setVideo(String video) {
        this.video = video;
    }
    public String toStringShort() {
        return "F:{" +
                "formation_id='" + formation_id +'}';
    }
}
