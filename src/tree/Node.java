package tree;

import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public abstract class Node<T> {

    public T data;
    public Node<T> parent;
    public List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.children = new LinkedList<>();
    }

    public void join(Node<T> other) {
        this.children.addAll(other.children);
    }

    public void add(Node<T> child) {
        child.parent = this;
        this.children.add(child);
    }

    public abstract void visit();

    @Override
    public String toString() {
        if (children.size() == 0) {
            return data.toString();
        } else if (children.size() == 1) {
            JSONObject res = new JSONObject();
            res.put(data.toString(), children.get(0));
            return res.toJSONString();
        } else {
            JSONArray arrChildren = new JSONArray();
            arrChildren.addAll(children);
//            for (Object child : children) {
//                arrChildren.add(child);
//            }

            JSONObject res = new JSONObject();
            res.put(data.toString(), arrChildren);
            return res.toJSONString();
        }
    }
}
