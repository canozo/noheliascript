package tree;
import java.util.ArrayList;
public class Init {
    public ArrayList<Object> childrens = new ArrayList<>();
    public String id;
    public String program;

    public Init(String id, String program){
        this.id = id;
        this.program = program;
    }
    public Init(){}

    public void addChild(Object child){
        childrens.add(child);
        System.out.println("Added succ");
    }

    @Override
    public String toString() {
        return "Init{" +
                "id='" + id + '\'' +
                ", program='" + program + '\'' +
                '}';
    }
}
