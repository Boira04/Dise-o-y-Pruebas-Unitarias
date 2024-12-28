package data.data;

public class UserAccountDoble implements UserAccountInterface{
    @Override
    public String getUserId() {
        return "44";
    }

    @Override
    public String getUsername() {
        return "Raul";
    }

    @Override
    public String getEmail() {
        return "raul342@gmail.com";
    }

    @Override
    public String getPassword() {
        return "Lleida123";
    }

    @Override
    public int getMonedero() {
        return 0;
    }

    @Override
    public void setMonedero(int nou_saldo) {

    }

    @Override
    public boolean verifyPassword(String inputPassword) {
        return true;
    }
}
