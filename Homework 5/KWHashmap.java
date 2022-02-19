public interface KWHashmap <K,V>{
    public V get(Object key);
    public V put(K key, V value);
    public V remove(Object key);
    public boolean isEmpty();
    public int size();
}