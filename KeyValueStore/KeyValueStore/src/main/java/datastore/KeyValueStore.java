package datastore;

import constants.Constants;
import helper.CRDHelper;
import model.Data;
import org.json.simple.JSONObject;

import java.io.File;
import java.util.Date;

public class KeyValueStore {

    private String dataStoreLoc = "";
    private String dataStoreName = "";

    /**
     * Constructor to initialize the KeyValueStore with default location
     */
    public KeyValueStore() {
        try {
            dataStoreLoc = Constants.defaultDataStoreLoc;
            dataStoreName = "datastore-" + CRDHelper.getProcessName();
        } catch (Exception exception) {
          //log the exception
        }
    }

    /**
     * Constructor to initialize the KeyValueStore with given location
     *
     * @param filePath
     *            the storage location path
     */
    public KeyValueStore(String filePath) {
        try {
            dataStoreLoc = filePath;
            dataStoreName = "datastore-" + CRDHelper.getProcessName();
        } catch (Exception exception) {
         //log the exception
        }

    }

    // Operations

    /**
     *
     * Method to create an element in the DataStore
     *
     * @param key
     *            The key of the element
     * @param value
     *            The value of the element
     * @return status of the operation
     */
    public synchronized String create(String key, JSONObject value) {
        try {
            return create(key, value, -1);
        } catch (Exception exception) {
            return Constants.CREATE_FAILED;
        }
    }

    /**
     * Method to create an element in the DataStore
     *
     * @param key
     *            The key of the element
     * @param value
     *            The value of the element
     * @param timeToLive
     *            Number of seconds after which the element is destroyed
     * @return status of the operation
     */
    public synchronized String create(String key, JSONObject value,
                                      int timeToLive) {
        try {
            String filePath = dataStoreLoc + "/" + dataStoreName;
            //check for the file size before writing data
            if(CRDHelper.doesFileSizeLimitExceed(new File(filePath))){
                return Constants.FAILURE_FILE_LENGTH_EXCEEDED;
            }
            // validate the key
            if (!CRDHelper.isKeyNameValid(key)) {
                return Constants.FAILURE_KEY_LENGTH_EXCEEDED;
            }
            if (CRDHelper.isKeyExists(key, filePath)) {
               return Constants.FAILURE_KEY_ALREADY_AVAILABLE;
            }

            Data data = new Data();
            data.setKey(key);
            if (timeToLive > 0) {
                data.setTimeToLive(timeToLive);
            } else {
                data.setTimeToLive(-1);
            }
            data.setValue(value);
            data.setCreationDateTimeMillis(new Date().getTime());

            if (CRDHelper.writeData(data, filePath)) {
                return Constants.CREATE_SUCCESSFUL;
            } else {
                return Constants.CREATE_FAILED;
            }
        } catch (Exception exception) {
            return Constants.CREATE_FAILED;
        }
    }

    /**
     * Method to read an element from the DataStore
     *
     * @param key
     *            The key of the element to read the element
     * @return The value as type of JSONObject
     */
    public synchronized Object read(String key) {
        try {
            String filePath = dataStoreLoc + "/" + dataStoreName;

            // validate the key
            if (!CRDHelper.isKeyNameValid(key)) {
                return Constants.FAILURE_KEY_LENGTH_EXCEEDED;
            }
            if (!CRDHelper.isKeyExists(key, filePath)) {
                return Constants.FAILURE_KEY_NOT_AVAILABLE;
            }
            // success flow

            Data data = CRDHelper.readData(key, filePath);
            if (null != data) {
                return data.getValue();
            }
            return Constants.FAILURE_READ;
        } catch (Exception exception) {
            exception.printStackTrace();
            return Constants.FAILURE_READ;
        }
    }

    /**
     * Method to delete an element from the DataStore
     *
     * @param key
     *            The key of the element to read the element
     * @return The status of the delete operation
     */
    public synchronized Object delete(String key) {
        try {
            String filePath = dataStoreLoc + "/" + dataStoreName;
            // validate the key
            if (!CRDHelper.isKeyNameValid(key)) {
                return Constants.FAILURE_KEY_LENGTH_EXCEEDED;
            }
            if (!CRDHelper.isKeyExists(key, filePath)) {
                return Constants.FAILURE_KEY_NOT_AVAILABLE;
            }
            // success flow

            if (CRDHelper.deleteData(key, filePath)) {
                return Constants.DELETION_SUCCESSFUL;
            }
            return Constants.DELETION_FAILED;
        } catch (Exception exception) {
            exception.printStackTrace();
            return Constants.DELETION_FAILED;
        }
    }
}
