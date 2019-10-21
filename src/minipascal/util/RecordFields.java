package minipascal.util;

import java.util.LinkedList;
import java.util.List;

public class RecordFields {

    public String type;
    public List<String> vars;

    public RecordFields(String type) {
        this.type = type;
        this.vars = new LinkedList<>();
    }

    public void addField(String field) {
        vars.add(field);
    }
}
