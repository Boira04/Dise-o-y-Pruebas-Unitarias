package data.data;

import java.util.Objects;

/**
 * Classe que representa un compte d'usuari.
 */
public class UserAccount {
    private final String username;
    private final String email;
    private final String password;
    private int monedero;

    public UserAccount(String username, String email, String password, int monedero) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("El nom d'usuari no pot ser null o buit.");
        }
        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException("Adreça de correu electrònic no vàlida.");
        }
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("La contrasenya ha de tenir almenys 6 caràcters.");
        }
        this.username = username;
        this.email = email;
        this.password = password;
        this.monedero = monedero;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getMonedero() {
        return monedero;
    }

    public void setMonedero(int nou_saldo) {
        this.monedero = nou_saldo;
    }

    // Mètode per verificar la contrasenya
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return username.equals(that.username) &&
                email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString() {
        return "data.data.UserAccount{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
