package minipascal.util.types;

import java.util.ArrayList;
import java.util.List;

public class TRecord extends Type {

    public class Bucket {
        public String id;
        public Type type;

        public Bucket(String id, Type type) {
            this.id = id;
            this.type = type;
        }

        @Override
        public String toString() {
            return String.format("(%s x %s)", id, type);
        }
    }

    public List<Bucket> fields;

    public TRecord(String id) {
        super(id);
        fields = new ArrayList<>();
    }

    public void addField(String id, Type type) {
        fields.add(new Bucket(id, type));
    }

    @Override
    public String toString() {
        StringBuilder strFields = new StringBuilder();
        boolean first = true;
        for (Bucket field : fields) {
            if (first) {
                strFields.append(field);
                first = false;
            } else {
                strFields.append(" x ");
                strFields.append(field);
            }
        }
        return String.format("%s: record(%s)", type, strFields);
    }
}
