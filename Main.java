import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class Main {
    public static void main(String[] args) {
        // Create a launcher discovery request for the PrescriptionTest class
        // This request specifies which tests to run, in this case, all tests in
        // PrescriptionTest.
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(PrescriptionTest.class)) // Selects the PrescriptionTest class for execution
                .build(); // Builds the discovery request

        // Create a launcher to execute the tests
        Launcher launcher = LauncherFactory.create(); // Factory method to create a test launcher

        // Create a listener to generate a summary of test results
        SummaryGeneratingListener listener = new SummaryGeneratingListener(); // Listens to the test execution results

        // Execute the test request with the listener to gather results
        launcher.execute(request, listener); // Launches the tests as per the request and uses the listener to collect
                                             // results

        // Get the summary of the test execution
        TestExecutionSummary summary = listener.getSummary(); // Retrieves the summary of executed tests

        // Check if any tests failed
        if (summary.getTestsFailedCount() > 0) {
            System.out.println("Some tests failed. Detailed report:");
            // Iterate through the failures to display detailed information
            summary.getFailures().forEach(failure -> {
                System.out.println("Test Case Failed: " + failure.getTestIdentifier().getDisplayName()); // Displays the
                                                                                                         // name of the
                                                                                                         // failed test
                System.out.println("Failure Reason: " + failure.getException().getMessage()); // Displays the reason for
                                                                                              // the failure
                System.out.println("Exception: " + failure.getException()); // Displays the exception that caused the
                                                                            // failure
                System.out.println("-----------------------------"); // Separator for readability
            });
        } else {
            // If no tests failed, print a success message
            System.out.println("All tests passed."); // Indicates successful execution of all tests
        }
    }
}
