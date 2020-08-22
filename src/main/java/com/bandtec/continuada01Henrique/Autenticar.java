package com.bandtec.continuada01Henrique;

public interface Autenticar {

    public boolean isAuth();

    public boolean isAdmin();

    public String loginAdmin(String login, String senha);

    public String loginClient(String login, String senha);
}
