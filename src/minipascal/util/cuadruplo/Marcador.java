package minipascal.util.cuadruplo;

import minipascal.util.Globals;

import java.util.ArrayList;

public class Marcador {

    public static ArrayList<Marcador> marcadores = new ArrayList<>();

    public int sigCuad;
    public boolean res;

    public Marcador(boolean res) {
        this.res = res;
        sigCuad = Globals.cuadruplos.size() + 1;
        Marcador.marcadores.add(this);
    }

    public Marcador(boolean res, int sigCuad) {
        this.res = res;
        this.sigCuad = sigCuad;
        Marcador.marcadores.add(this);
    }

    @Override
    public String toString() {
        return String.format("_%d", sigCuad);
    }
}
