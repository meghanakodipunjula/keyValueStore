package constants;

public class Constants {
    public static final String defaultDataStoreLoc = "/Users/nandinikodipunzula/Documents/proj";
    public static final int MILLISECONDS = 1000;
    public static final int MAX_KEY_LENGTH = 32;
    public static final long MAX_FILE_SIZE = 1024 * 1024 * 1000;

    // Messages
    public static final String FAILURE_KEY_LENGTH_EXCEEDED = " Key length exceeded the limit(32chars)";
    public static final String FAILURE_FILE_LENGTH_EXCEEDED = "File length exceeded the limit(1gb)";

    public static final String FAILURE_KEY_ALREADY_AVAILABLE = " Operation failed due to key already available";
    public static final String FAILURE_READ = " Read failed due to unknown error, please try again later";
    public static final String DELETION_SUCCESSFUL = " deletion successful";
    public static final String DELETION_FAILED = " deletion failed";
    public static final String CREATE_SUCCESSFUL = " Create successful";
    public static final String CREATE_FAILED = " Create failed";
    public static final String FAILURE_KEY_NOT_AVAILABLE = " Operation failed due to key not available";


}
