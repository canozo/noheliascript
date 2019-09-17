package tree;

import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public class Node<T> {

    T data;
    Node<T> parent;
    List<Node<T>> children;

    public Node(T data) {
        this.data = data;
        this.children = new LinkedList<>();
    }

    public Node<T> add(T child) {
        Node<T> childNode = new Node<>(child);
        childNode.parent = this;
        this.children.add(childNode);
        return childNode;
    }

    @Override
    public String toString() {
        if (children.size() > 0) {
            JSONArray arrChildren = new JSONArray();
            for (Object child : children) {
                arrChildren.add(child);
            }

            JSONObject res = new JSONObject();
            res.put(data.toString(), arrChildren);
            return res.toJSONString();
        } else {
            return data.toString();
        }
    }
}
