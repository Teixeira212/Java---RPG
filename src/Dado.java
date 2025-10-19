import java.util.Random;
public class Dado {
    private Random rolagem;

    public Dado() {
        this.rolagem = new Random();
    }

    public int rolar() {
        return rolagem.nextInt(20) + 1;
    }
}
