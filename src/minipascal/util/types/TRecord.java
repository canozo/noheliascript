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
            res += fields.get(id).size;
            if (field.equals(id)) {
                break;
            }
        }
        return res;
    }

    public int getCompleteSize() {
        int res = 0;
        for (String id : fields.keySet()) {
            res += fields.get(id).size;
        }
        // redondear a multiplo de 4
        return 4 * (int) Math.ceil(res / 4.0);
    }

    public Type getFieldType(int offset) {
        int tempOffset = 0;
        for (String id : fields.keySet()) {
            tempOffset += fields.get(id).size;
            if (tempOffset == offset) {
                return fields.get(id);
            }
        }

        // posible error
        System.err.println("Error: No se encontro el field type con el offset.");
        return null;
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
