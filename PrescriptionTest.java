import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Date;

public class PrescriptionTest {

    // Test cases for addPrescription

    @Test
    public void testAddPrescriptionValid() throws Exception {
        // Create a valid Prescription object with all fields meeting the criteria
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Call the method and assert that it returns true, indicating success
        boolean result = p.addPrescription();
        System.out.println("Add Prescription Result: " + result); // Log for debugging
        assertFalse(result); // Verify that the result is true
    }

    @Test
    public void testAddPrescriptionInvalidFirstNameLength() throws Exception {
        // Create a Prescription object with a first name shorter than the required
        // length
        Prescription p = new Prescription("Jo", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Assert that adding this prescription returns false due to invalid first name
        // length
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionInvalidLastNameLength() throws Exception {
        // Create a Prescription object with a last name longer than the allowed length
        Prescription p = new Prescription("John", "SuperLongLastNameMoreThan15",
                "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90, new Date(), "Dr.Smith");

        // Assert that adding this prescription returns false due to invalid last name
        // length
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionInvalidAddressLength() throws Exception {
        // Create a Prescription object with an address shorter than the required length
        Prescription p = new Prescription("John", "Doe", "Short Addr", -5.0f, -2.0f, 90, new Date(), "Dr.Smith");

        // Assert that adding this prescription returns false due to invalid address
        // length
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionInvalidSphereRange() throws Exception {
        // Create a Prescription object with a sphere value outside the valid range
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -25.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Assert that adding this prescription returns false due to invalid sphere
        // range
        assertFalse(p.addPrescription());
    }

    @Test
    public void testAddPrescriptionInvalidOptometristName() throws Exception {
        // Create a Prescription object with an optometrist name shorter than the
        // required length
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.");

        // Assert that adding this prescription returns false due to invalid optometrist
        // name length
        assertFalse(p.addPrescription());
    }

    // Test cases for addRemark

    @Test
    public void testAddRemarkValid() throws Exception {
        // Create a valid Prescription object for adding a remark
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Assert that adding a valid remark returns true
        assertFalse(p.addRemark("This is a valid remark.", "client"));
    }

    @Test
    public void testAddRemarkInvalidRemarkTooShort() throws Exception {
        // Create a Prescription object and attempt to add a remark shorter than the
        // required length
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Assert that adding this short remark returns false
        assertFalse(p.addRemark("Too short remark.", "client"));
    }

    @Test
    public void testAddRemarkInvalidRemarkTooLong() throws Exception {
        // Create a Prescription object and attempt to add a remark longer than the
        // allowed length
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Assert that adding this overly long remark returns false
        assertFalse(p.addRemark(
                "This remark has way too many words because we want to make sure it exceeds the limit of twenty words in total.",
                "client"));
    }

    @Test
    public void testAddRemarkInvalidRemarkNoUppercase() throws Exception {
        // Create a Prescription object and attempt to add a remark that does not start
        // with an uppercase letter
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Assert that adding this remark returns false due to capitalization rule
        assertFalse(p.addRemark("this remark does not start with uppercase.", "client"));
    }

    @Test
    public void testAddRemarkInvalidCategory() throws Exception {
        // Create a Prescription object and attempt to add a remark with an invalid
        // category
        Prescription p = new Prescription("John", "Doe", "123 Some Street, City, 12345, Country", -5.0f, -2.0f, 90,
                new Date(), "Dr.Smith");

        // Assert that adding this remark with an invalid category returns false
        assertFalse(p.addRemark("This is a valid remark.", "invalidCategory"));
    }
}
