package tree;

public class Factor {
    public String id;
    public Factor fac;
    public ConstantValue cv;
    public String parIzq;
    public String parDer;
    public String not;

    public Factor(String id) {
        this.id = id;
    }

    public Factor(String not,Factor fac) {
        this.fac = fac;
        this.not = not;
    }
}
