package com.example.cafoma_app.entite;

import java.util.List;

public class User {
    private Integer user_id;
    private String login;
    private String mdp;
    private String email;
    private String img;
    private String role;
    private String valid;
    private List<Formation> formationInscrit;

    public User(Integer user_id,String login, String mdp, String email, String img, String role, String valid){
        this.user_id = user_id;
        this.login = login;
        this.mdp = mdp;
        this.email = email;
        this.img = img;
        this.role = role;
        this.valid = valid;
    }
    public User(String login, String valid) {
        this.login = login;
        this.valid = valid;
    }

    public User(String login, String verif, List<Formation> formationInscrit) {
        this.valid = verif;
        this.login = login;
        this.formationInscrit=formationInscrit;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", valid='" + valid +
                ", Formations='" + afficheShortFormation(formationInscrit) +
                '}';
    }

    public Integer getUser_id() {
        return user_id;
    }
    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }

    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getValid() {
        return valid;
    }
    public void setValid(String valid) {
        this.valid = valid;
    }
    public List<Formation> getFormationList(){
        return formationInscrit;
    }

    public String afficheShortFormation (List<Formation> formations){
        String affiche = "";
        if (formations != null){
            for(Formation f : formations){
                affiche = affiche+f.toStringShort();
            }
        }
        return affiche;
    }
}
