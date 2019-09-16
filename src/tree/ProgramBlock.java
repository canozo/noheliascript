package tree;

public class ProgramBlock extends node{
    public String id;

    public ProgramBlock(String id){
        this.id = id;
    }
    @Override
    public String getID(){
        return this.id;
    }
}
