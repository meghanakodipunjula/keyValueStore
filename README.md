# keyValuestore
 

## How to consume the keyvaluestore library ?

1.Clone the project

2.Import project as maven project
The key valuestore is available as jar file which is built via artifacts

3.Initialise the keyvaluestore object

     KeyValueStore keyValuestore = new KeyValueStore("/Users/meghanakodipunjula/Documents/proj");
     
4.call helper methods

    keyValuestore.create(String key, JSONObject value);

For more detilas please check my unittests class `## KeyValueStoreTest ##`
