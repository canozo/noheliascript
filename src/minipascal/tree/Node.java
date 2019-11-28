package minipascal.tree;

import java.util.LinkedList;
import java.util.List;

import minipascal.util.cuadruplo.Marcador;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;

public abstract class Node<T> {

    public T data;
    public String place;
    public List<Marcador> listaV;
    public List<Marcador> listaF;
    public List<Marcador> listaSig;
    public Node<T> parent;
    public List<Node<T>> children;

    public Node(T data) {
        this.place = null;
        this.listaV = null;
        this.listaF = null;
        this.listaSig = null;
        this.data = data;
        this.children = new LinkedList<>();
    }

    public void join(Node<T> other) {
        this.children.addAll(other.children);
    }

    public void add(Node<T> child) {
        if (child != null) {
            child.parent = this;
        }
        this.children.add(child);
    }

    public abstract void visit();
    public abstract void compile();
    public abstract String rebuild();

    @Override
    public String toString() {
        if (children.size() == 0) {
            return data.toString();
        } else if (children.size() == 1) {
            JSONObject res = new JSONObject();
            res.put(data, children.get(0));
            return res.toJSONString();
        } else {
            JSONArray arrChildren = new JSONArray();
            arrChildren.addAll(children);
            JSONObject res = new JSONObject();
            res.put(data, arrChildren);
            return res.toJSONString();
        }
    }
}
