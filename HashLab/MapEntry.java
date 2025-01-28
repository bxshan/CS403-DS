import java.util.Map.Entry;

/**
 * 
 * @author joelmanning
 * @version 1.0
 * @param <K>
 *            the type of key to hold
 * @param <V>
 *            the type of value to hold
 */
public class MapEntry<K, V> implements Entry<K, V>
{
    private K key;
    private V value;

    /**
     * @param key
     *            the initial key for the entry
     * @param value
     *            the initial value for the entry
     */
    public MapEntry(K key, V value)
    {
        super();
        this.key = key;
        this.value = value;
    }

    /**
     * @return the key in the entry
     */
    public K getKey()
    {
        return key;
    }

    /**
     * @param key
     *            the key to set this entry's key to
     */
    public void setKey(K key)
    {
        this.key = key;
    }

    /**
     * @return the value of the entry
     */
    public V getValue()
    {
        return value;
    }

    /**
     * @return the previous value in the entry
     * @param val
     *            the value to set this entry's value to
     */
    public V setValue(V val)
    {
        V past = value;
        value = val;
        return past;
    }

    /**
     * @param me
     *            the MapEntry to check if this is equal to
     * @return whether it is equal to that MapEntry
     */
    public boolean equals(MapEntry me)
    {
        return key.equals(me.getKey());
    }
}
