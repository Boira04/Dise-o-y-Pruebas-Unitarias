package data.data;

import java.util.Objects;

public interface UserAccountInterface {

    // Getters
    public String getUserId();

    public String getUsername();

    public String getEmail();

    public String getPassword();

    public int getMonedero();

    public void setMonedero(int nou_saldo);

    // Mètode per verificar la contrasenya
    public boolean verifyPassword(String inputPassword);

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

    @Override
    public String toString();
}
