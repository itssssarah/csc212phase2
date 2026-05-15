

//start
/**
 * Represents a map that stores unique comparable keys with their values.
 * Supports searching, inserting, and deleting operations.
 *
 * @param <K> key type
 * @param <T> value type
 */
public class BSTMap<K extends Comparable<? super K>, T> implements Map<K, T> {

    class BSTMapNode<K extends Comparable<? super K>, T> {

        public K key;
        public T data;
        public BSTMapNode<K, T> left, right;

        public BSTMapNode(K key, T data) {
            this.key = key;
            this.data = data;
            left = null;
            right = null;
        }
    }

    // =================================================

    BSTMapNode<K, T> root;
    BSTMapNode<K, T> current;
    int count;

    public BSTMap() {
        root = null;
        current = null;
        count = 0;
    }

    /**
     * Returns number of elements in the map.
     */
    @Override
    public int size() {
        return count;
    }

    /**
     * Checks if the map is empty.
     * Runs in O(1).
     */
    @Override
    public boolean empty() {
        return root == null;
    }

    /**
     * Removes all elements from the map.
     */
    @Override
    public void clear() {
        root = null;
        current = null;
        count = 0;
    }

    /**
     * Inserts a new key-value pair if the key does not exist.
     *
     * @param key key to insert
     * @param data associated value
     * @return true if insertion succeeds, otherwise false
     */

 // Inserts a new key-value pair if the key does not already exist.
 
@Override
public boolean insert(K key, T data)
{
    if (root == null)
    {
        curr = root = new BSTMapNode<K, T>(key, data);
        count++;
        return true;
    }

    // search for insertion position
    BSTMapNode<K, T> pBstNode = root;
    BSTMapNode<K, T> qBSTNode = null;

    while (pBstNode != null)
    {
        int compareResult = key.compareTo(pBstNode.key);

        if (compareResult == 0)
            return false;
        else
        {
            qBSTNode = pBstNode;

            if (compareResult < 0)
                pBstNode = pBstNode.left;
            else
                pBstNode = pBstNode.right;
        }
    }

    BSTMapNode<K, T> newNode = new BSTMapNode<K, T>(key, data);

    if (key.compareTo(qBSTNode.key) < 0)
        qBSTNode.left = newNode;
    else
        qBSTNode.right = newNode;

    curr = newNode;
    count++;

    return true;
}



 // Returns the value associated with the given key.
 
@Override
public T get(K key)
{
    BSTMapNode<K, T> pBstNode = root;

    while (pBstNode != null)
    {
        if (key.compareTo(pBstNode.key) == 0)
            return pBstNode.data;

        else if (key.compareTo(pBstNode.key) < 0)
            pBstNode = pBstNode.left;

        else
            pBstNode = pBstNode.right;
    }

    return null;
}



 // Updates the value associated with the given key.

@Override
public boolean update(K key, T e)
{
    BSTMapNode<K, T> pBstNode = root;

    while (pBstNode != null)
    {
        if (key.compareTo(pBstNode.key) == 0)
        {
            pBstNode.data = e;
            return true;
        }

        else if (key.compareTo(pBstNode.key) < 0)
            pBstNode = pBstNode.left;

        else
            pBstNode = pBstNode.right;
    }

    return false;
}
