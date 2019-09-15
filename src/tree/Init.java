package tree;
import java.util.ArrayList;
public class Init {
    public ArrayList<Object> childrens = new ArrayList<>();
    public String program;

    public Init(String program){
        this.program = program;
    }
    public Init(){}

    public void addChild(Object child){
        childrens.add(child);
        System.out.println("Added succ");
    }

}
