import java.util.*;
public class NameAgeExample {
  public static void main(String[] args) {
      MyHashMap<String, Integer> nameAgeMap = new MyHashMap<String, Integer>();
      nameAgeMap.put("Siddharth", 25);
      nameAgeMap.put("Chethan", 55);
      System.out.println(nameAgeMap.get("Chethan"));
      if(nameAgeMap.get("Siddharth") != 25) {
          throw new Error("Siddharth's age does not match");
      }
      nameAgeMap.delete("Chethan");
      System.out.println(nameAgeMap.get("Chethan"));
      if(nameAgeMap.get("Chethan") == null) {
          throw new Error("Chethan's name is not null after deleting it");
      }
      
  }
}

class KeyValuePair<K, V> { //generics:No data type assigned, to be associated with a suitable type later.
    K key; //K type key
    V value; //V type value

    public KeyValuePair(K key, V value) {
        this.key = key;  //a linked list with key and value pointed to.
        this.value = value;
    }
}

class MyHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 4;  
    // The max capacity of the map.
    private List<List<KeyValuePair<K, V>>> buckets; 
    /* The hashmap we create is a list of linkedlists, we defined here buckets, 
        which are of datatype list of linked lists containing key value pairs.*/
    private int capacity; 

    public MyHashMap() {        
        this.capacity = DEFAULT_CAPACITY;
        this.buckets = new ArrayList<>(capacity); 
        for (int i = 0; i < capacity; i++) {
            buckets.add(null); 
            //creating empty arraylist in the buckets(list of lists), to store KeyValuePairs
        }
    }

    private int hash(K key) {    
        return Math.abs(key.hashCode() % capacity); 
        /*this method calculates index of bucket or mana list of key 
        value pair using the hashcode of the key*/ 
    }

    public void put(K key, V value) {
        int index = hash(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(index); //A temporary space to store each KeyValuePair

        if (bucket == null) { //If bucket is null,new ArrayList is created to use the bucket,
            bucket = new ArrayList<>();
            buckets.set(index, bucket); //the KeyValuePair at the specified index is assigned to bucket.
        }

        for (KeyValuePair<K, V> pair : bucket) {
            if (pair.key.equals(key)) {       // if the KeyValuePair is already present in the bucket
                pair.value = value;            //  then we check, if key is same, if true, we update
                return;                         //    the value.
            }
        }

        bucket.add(new KeyValuePair<>(key, value)); 
    }

    public V get(K key) {
        int index = hash(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(index);//store the key value pair of the key input
                                                             //,into a temp.

        if (bucket != null) {    // if bucket not null search for key, returns value.
            for (KeyValuePair<K, V> pair : bucket) {
                if (pair.key.equals(key)) {
                    return pair.value;
                }
            }
        }

        return null; 
    }

    public void delete(K key) {
        int index = hash(key);
        List<KeyValuePair<K, V>> bucket = buckets.get(index);

        if (bucket != null) {
            for (KeyValuePair<K, V> pair : bucket) {
                if (pair.key.equals(key)) {
                    bucket.remove(pair);
                    return;
                }
            }
        }
    }
}




