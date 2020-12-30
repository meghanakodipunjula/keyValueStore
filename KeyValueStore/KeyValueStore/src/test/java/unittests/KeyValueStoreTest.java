package unittests;

import constants.Constants;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import datastore.KeyValueStore;

public class KeyValueStoreTest {

    //Replace your file name to test run
    KeyValueStore myDataStore = new KeyValueStore(
            "/Users/nandinikodipunzula/Documents/proj");
    @Test
    public void createDataTest() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("firstName", "Meghana");
        jsonObject.put("lastName", "KodipunJula");
        jsonObject.put("address", "Tirupathi,piler");

        Assert.assertEquals(Constants.CREATE_SUCCESSFUL,myDataStore.create("1", jsonObject, 3));// success
        Assert.assertEquals(Constants.FAILURE_KEY_ALREADY_AVAILABLE,myDataStore.create("1", jsonObject));// failure
        Assert.assertEquals(Constants.FAILURE_KEY_ALREADY_AVAILABLE,myDataStore.create("1", jsonObject, 3));// failure
        Assert.assertEquals(Constants.CREATE_SUCCESSFUL,myDataStore.create("2", jsonObject));// success
        Assert.assertEquals(Constants.FAILURE_KEY_LENGTH_EXCEEDED,myDataStore.create(
                "CONDITION FOR CHECKING - LONGEST KEY-MORE THAN -THIRTY(30) -CHARACTERS-LENGTH", new JSONObject()));// failure
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }

        jsonObject.put("age", "25");
        Assert.assertEquals(Constants.CREATE_SUCCESSFUL,myDataStore.create("1", jsonObject, 10));// success
        Assert.assertEquals(Constants.FAILURE_KEY_ALREADY_AVAILABLE,myDataStore.create("1", jsonObject));// failure
        Assert.assertEquals(Constants.FAILURE_KEY_ALREADY_AVAILABLE,myDataStore.create("1", jsonObject, 10));// failure
        Assert.assertEquals(Constants.FAILURE_KEY_ALREADY_AVAILABLE,myDataStore.create("2", jsonObject));// success
    }

    @Test
    public void readDataTest() {
        Assert.assertNotNull(myDataStore.read("1"));// success
        Assert.assertNotNull(myDataStore.read("2"));// success
        Assert.assertEquals(Constants.FAILURE_KEY_NOT_AVAILABLE,myDataStore.read("3"));// failure
        Assert.assertEquals(Constants.FAILURE_KEY_LENGTH_EXCEEDED, myDataStore.read("CONDITION FOR CHECKING - LONGEST KEY-MORE THAN -THIRTY(30) -CHARACTERS-LENGTH"));// failure
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {

        }
        Assert.assertNotNull(myDataStore.read("1"));// failure
        Assert.assertNotNull(myDataStore.read("2"));// success
    }

    @Test
    public void deleteDataTest() {
        Assert.assertEquals(Constants.FAILURE_KEY_NOT_AVAILABLE,myDataStore.delete("11"));
        Assert.assertEquals(Constants.FAILURE_KEY_NOT_AVAILABLE,myDataStore.delete("21"));
    }
}
