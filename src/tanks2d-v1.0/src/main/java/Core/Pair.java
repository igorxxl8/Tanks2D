package Core;

public class Pair<Key, Value> {
    private final Key _key;
    private final Value _value;

    public Pair(Key key, Value value) {
        _key = key;
        _value = value;
    }

    public Value getValue(){
        return _value;
    }

    public Key getKey(){
        return _key;
    }
}
