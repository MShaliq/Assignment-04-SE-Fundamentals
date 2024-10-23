import java.util.*;
import java.io.*;

public class Prescription {

    private String firstName;
    private String lastName;
    private String address;
    private float sphere;
    private float cylinder;
    private float axis;
    private Date examinationDate;
    private String optometrist;
    private String[] remarkTypes = { "client", "optometrist" };
    private ArrayList<String> postRemarks = new ArrayList<>();

    public Prescription(String firstName, String lastName, String address, float sphere, float cylinder, float axis,
            Date examinationDate, String optometrist) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.sphere = sphere;
        this.cylinder = cylinder;
        this.axis = axis;
        this.examinationDate = examinationDate;
        this.optometrist = optometrist;
    }

    public boolean addPrescription() throws IOException {
        // Condition 1: Name validation
        if (firstName.length() < 4 || firstName.length() > 15 || lastName.length() < 4 || lastName.length() > 15) {
            return false;
        }

        // Condition 2: Address validation
        if (address.length() < 20) {
            return false;
        }

        // Condition 3: Prescription validation
        if (sphere < -20.00 || sphere > 20.00 || cylinder < -4.00 || cylinder > 4.00 || axis < 0 || axis > 180) {
            return false;
        }

        // Condition 4: Date is assumed valid (since it's a Date object)

        // Condition 5: Optometrist name validation
        if (optometrist.length() < 8 || optometrist.length() > 25) {
            return false;
        }

        // If all conditions are met, write to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter("presc.txt", true));
        writer.write(firstName + " " + lastName + ", " + address + ", Sphere: " + sphere + ", Cylinder: " + cylinder
                + ", Axis: " + axis + ", Date: " + examinationDate + ", Optometrist: " + optometrist);
        writer.newLine();
        writer.close();

        return true;
    }

    public boolean addRemark(String remark, String category) throws IOException {
        // Condition 1: Remark validation
        String[] words = remark.split(" ");
        if (words.length < 6 || words.length > 20 || !Character.isUpperCase(words[0].charAt(0))) {
            return false;
        }

        // Condition 2: Category validation
        if (!Arrays.asList(remarkTypes).contains(category)) {
            return false;
        }

        // Condition 3: Max remarks check
        if (postRemarks.size() >= 2) {
            return false;
        }

        // If all conditions are met, add remark
        postRemarks.add(remark);
        BufferedWriter writer = new BufferedWriter(new FileWriter("review.txt", true));
        writer.write(remark + " (" + category + ")");
        writer.newLine();
        writer.close();

        return true;
    }

}
