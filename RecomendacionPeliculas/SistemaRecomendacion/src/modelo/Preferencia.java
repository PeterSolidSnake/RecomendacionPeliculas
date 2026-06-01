package modelo;

public class Preferencia {

    private Genero generoPreferido;

    public Preferencia(Genero generoPreferido) {
        this.generoPreferido = generoPreferido;
    }

    public Genero getGeneroPreferido() {
        return generoPreferido;
    }

    public void setGeneroPreferido(Genero generoPreferido) {
        this.generoPreferido = generoPreferido;
    }
}
