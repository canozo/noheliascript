package tree;

public class FunctionProcedure extends node{
    public String id;
    public FunctionDeclaration fd;
    public FunctionProcedure fpb;
    public ProcedureDeclaration pd;

    public FunctionProcedure(FunctionDeclaration fd, FunctionProcedure fpb) {
        this.fpb = fpb;
        this.fd = fd;
    }

    public FunctionProcedure(ProcedureDeclaration pd, FunctionProcedure fpb, String str){
        this.fpb = fpb;
        this.pd = pd;
    }

    @Override
    public String getID() {
        return this.id;
    }

}
