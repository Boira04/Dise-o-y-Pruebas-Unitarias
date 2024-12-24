package data.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAccountTest {

    private UserAccount userAccount;

    @BeforeEach
    void setUp() {
        userAccount = new UserAccount("123", "testUser", "test@example.com", "password123", 100);
    }

    @Test
    void testConstructor_ValidParameters() {
        assertNotNull(userAccount);
        assertEquals("123", userAccount.getUserId());
        assertEquals("testUser", userAccount.getUsername());
        assertEquals("test@example.com", userAccount.getEmail());
        assertEquals("password123", userAccount.getPassword());
        assertEquals(100, userAccount.getMonedero());
    }

    @Test
    void testConstructor_InvalidUserId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new UserAccount(null, "testUser", "test@example.com", "password123", 100));
        assertEquals("L'identificador d'usuari no pot ser null o buit.", exception.getMessage());
    }

    @Test
    void testConstructor_InvalidUsername() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new UserAccount("123", "", "test@example.com", "password123", 100));
        assertEquals("El nom d'usuari no pot ser null o buit.", exception.getMessage());
    }

    @Test
    void testConstructor_InvalidEmail() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new UserAccount("123", "testUser", "invalid-email", "password123", 100));
        assertEquals("Adreça de correu electrònic no vàlida.", exception.getMessage());
    }

    @Test
    void testConstructor_InvalidPassword() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new UserAccount("123", "testUser", "test@example.com", "123", 100));
        assertEquals("La contrasenya ha de tenir almenys 6 caràcters.", exception.getMessage());
    }

    @Test
    void testGetters() {
        assertEquals("123", userAccount.getUserId());
        assertEquals("testUser", userAccount.getUsername());
        assertEquals("test@example.com", userAccount.getEmail());
        assertEquals("password123", userAccount.getPassword());
        assertEquals(100, userAccount.getMonedero());
    }

    @Test
    void testSetMonedero() {
        userAccount.setMonedero(200);
        assertEquals(200, userAccount.getMonedero());
    }

    @Test
    void testVerifyPassword_CorrectPassword() {
        assertTrue(userAccount.verifyPassword("password123"));
    }

    @Test
    void testVerifyPassword_IncorrectPassword() {
        assertFalse(userAccount.verifyPassword("wrongPassword"));
    }

    @Test
    void testEquals_SameObject() {
        assertTrue(userAccount.equals(userAccount));
    }

    @Test
    void testEquals_DifferentObject_SameValues() {
        UserAccount otherAccount = new UserAccount("123", "testUser", "test@example.com", "password123", 200);
        assertTrue(userAccount.equals(otherAccount));
    }

    @Test
    void testEquals_DifferentObject_DifferentValues() {
        UserAccount otherAccount = new UserAccount("456", "anotherUser", "another@example.com", "password456", 200);
        assertFalse(userAccount.equals(otherAccount));
    }

    @Test
    void testHashCode() {
        UserAccount otherAccount = new UserAccount("123", "testUser", "test@example.com", "password123", 200);
        assertEquals(userAccount.hashCode(), otherAccount.hashCode());
    }

    @Test
    void testToString() {
        String expected = "data.data.UserAccount{userId='123', username='testUser', email='test@example.com'}";
        assertEquals(expected, userAccount.toString());
    }
}
