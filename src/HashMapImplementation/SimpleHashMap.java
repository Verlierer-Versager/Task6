package HashMapImplementation;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;

public class SimpleHashMap<K, V> {

    private class EntryListItem<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private EntryListItem<K, V> next;

        public EntryListItem(K key, V value, EntryListItem<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }
    }

    protected EntryListItem<K, V>[] table;
    protected int size = 0;
    protected int capacity;
    private int occupancy = 0;
    private int threshold = 0;
    private double loadFactor = 0.75;


    public SimpleHashMap(int capacity, double loadFactor) {
        table = (EntryListItem<K, V>[]) Array.newInstance(EntryListItem.class, capacity);
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        threshold = (int) (capacity * loadFactor);
    }

    public SimpleHashMap() {
        capacity = 0;
        table = (EntryListItem<K, V>[]) Array.newInstance(EntryListItem.class, capacity);
    }

    private int getIndex(Object key) {
        int index = key.hashCode() % capacity;
        if (index < 0) {
            index += table.length;
        }
        return index;
    }

    private EntryListItem<K, V> getEntry(Object key, int index) {
        if (index < 0) {
            index = getIndex(key);
        }
        for (EntryListItem<K, V> curr = table[index]; curr != null; curr = curr.next) {
            if (key.equals(curr.key)) {
                return curr;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() <= 0;
    }

    public boolean containsKey(Object key) {
        return getEntry(key, -1) != null;
    }

    public V get(Object key) {
        EntryListItem<K, V> item = getEntry(key, -1);
        return (item == null) ? null : item.value;
    }

    public V put(K key, V value) {
        if(occupancy>threshold){
            increase();
        }
        int index = getIndex(key);
        EntryListItem<K, V> item = getEntry(key, index);
        if(item==null){
            occupancy++;
        }
        if (item != null) {
            V oldValue = item.value;
            item.value = value;
            return oldValue;
        }
        table[index] = new EntryListItem<K, V>(key, value, table[index]);
        size++;
        return null;
    }

    public V remove(Object key) {
        int index = getIndex(key);
        EntryListItem<K, V> parent = null;
        for (EntryListItem<K, V> curr = table[index]; curr != null; curr = curr.next) {
            if (key.equals(curr.key)) {
                if (parent == null) {
                    table[index] = curr.next;
                } else {
                    parent.next = curr.next;
                }
                size--;
                occupancy--;
                return curr.value;
            }
            parent = curr;
        }
        return null;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    private void increase() {
        if (capacity == Integer.MAX_VALUE) return;
        capacity *= 2;
        EntryListItem<K, V>[] newTable = (EntryListItem<K, V>[]) Array.newInstance(EntryListItem.class, capacity);
        for (EntryListItem<K, V> element : table) {
            if (element != null) {
                int newIndex = getIndex(element.getKey());
                newTable[getIndex(element.key)] = element;
            }
        }
        threshold = capacity == Integer.MAX_VALUE ? capacity : (int) (capacity * loadFactor);
        this.table = newTable;
    }

    /**
     * Реализация Iterable&lt;Map.Entry&lt;K, V&gt;&gt;
     *
     * @return Итератор
     */
    /*чpublic Set<Map.Entry<K, V>> entrySet() {
        return new DefaultNotSupportedSet<Map.Entry<K, V>>() {
            @Override
            public int size() {
                return SimpleHashMap.this.size();
            }

            @Override
            public Iterator<Map.Entry<K, V>> iterator() {
                return new Iterator<Map.Entry<K, V>>() {
                    int tableIndex = -1;
                    EntryListItem curr = null;

                    {
                        findNext();
                    }

                    private void findNext() {
                        if (tableIndex >= table.length) {
                            return;
                        }
                        if (curr != null) {
                            curr = curr.next;
                        }
                        if (curr == null) {
                            for (tableIndex = tableIndex + 1; tableIndex < table.length; tableIndex++) {
                                curr = table[tableIndex];
                                if (curr != null) {
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    public boolean hasNext() {
                        return curr != null;
                    }

                    @Override
                    public Map.Entry<K, V> next() {
                        Map.Entry<K, V> temp = curr;
                        findNext();
                        return temp;
                    }
                };
            }
        };
    }*/
}

