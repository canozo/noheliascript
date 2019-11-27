package minipascal.util.types;

import minipascal.util.Globals;

import java.util.TreeMap;

public class TRecord extends Type {

    public TreeMap<String, Type> fields;

    public TRecord(String id) {
        super(id);
        fields = new TreeMap<>();
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

    public int getFieldSize(String field) {
        int res = 0;
        for (String id : fields.keySet()) {
            if (field.equals(id)) {
                break;
            }
            res += fields.get(id).size;
        }
        return res;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof TRecord)) {
            return false;
        }

        TRecord other = (TRecord) obj;
        return type.equals(other.type) && fields.equals(other.fields);
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
