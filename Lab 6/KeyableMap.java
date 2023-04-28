
import java.util.Optional;
import java.util.Map;

class KeyableMap<T extends Keyable> implements Keyable {

    private final String key;
    private final ImmutableMap<String, T> map;

    public KeyableMap (String key) {
        this.key = key;
        this.map = new ImmutableMap<>();
    }

    public KeyableMap(String key, ImmutableMap<String, T> map) {
        this.key = key;
        this.map = map;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public Optional<T> get(String key) {
        return map.get(key);
    }
    

    public KeyableMap<T> put(T item) {
        return new KeyableMap<T>(item.getKey(), map.put(item.getKey(), item));
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(": {");
        for (Map.Entry<String, T> e : map) {
            sb.append(e.getValue().toString() + ", ");
        }
        if (!map.isEmpty()) {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("}"); 
        return sb.toString();
    }
}
    
    

