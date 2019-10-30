package minipascal.util.types;

import minipascal.util.Globals;

import java.util.HashMap;

public class TRecord extends Type {

    public HashMap<String, Type> fields;

    public TRecord(String id) {
        super(id);
        fields = new HashMap<>();
    }

    public void addField(String id, String type) {
        Type resType = Globals.findType(type);
        if (resType == null) {
            return;
        }

        fields.put(id, resType);
    }

    public Type getField(String field) {
        return fields.get(field);
    }

    @Override
    public String toString() {
        StringBuilder strFields = new StringBuilder();
        boolean first = true;
        for (String id : fields.keySet()) {
            if (first) {
                strFields.append(String.format("(%s x %s)", id, fields.get(id)));
                first = false;
            } else {
                strFields.append(" x ");
                strFields.append(String.format("(%s x %s)", id, fields.get(id)));
            }
        }
        return String.format("%s: record(%s)", type, strFields);
    }
}
