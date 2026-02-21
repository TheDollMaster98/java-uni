public class Cioccolatino extends Sorpresa {
    private final double cacao;

    public Cioccolatino(String nome, double cacao) {
        super(nome, cacao * 0.1);
        this.cacao = cacao;
    }

    public double getCacao() {
        return cacao;
    }

    @Override
    public String toString() {
        return String.format("Cioccolatino %s con %.2f%% di cacao e costo %.2f", getNome(), getCacao(), getCosto());
    }
}