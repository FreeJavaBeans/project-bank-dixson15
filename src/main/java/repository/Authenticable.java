package repository;

import model_entity.BankUser;

public interface Authenticable {

    boolean register(BankUser bankUser);

    String login(String username,String password);

    void logout();
}
