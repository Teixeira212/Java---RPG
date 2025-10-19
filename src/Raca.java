public class Raca {
    private String nome;
    private int bonusVida;
    private int bonusAtaque;
    private int bonusDefesa;
    private int bonusMana;
    private int bonusStamina;

    public Raca(String nome, int bonusVida, int bonusAtaque, int bonusDefesa, int bonusMana, int bonusStamina) {
        this.nome = nome;
        this.bonusVida = bonusVida;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.bonusMana = bonusMana;
        this.bonusStamina = bonusStamina;
    }

    // Getters
    public String getNome() { return nome; }
    public int getBonusVida() { return bonusVida; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusDefesa() { return bonusDefesa; }
    public int getBonusMana() { return bonusMana; }
    public int getBonusStamina() { return bonusStamina; }

    // Raças pré-definidas
    public static Raca HUMANO() {
        return new Raca("Humano", 5, 2, 2, 5, 5);
    }

    public static Raca ELFO() {
        return new Raca("Elfo", 3, 3, 1, 10, 4);
    }

    public static Raca ORC() {
        return new Raca("Orc", 8, 5, 3, 0, 6);
    }

    public static Raca ANAO() {
        return new Raca("Anão", 6, 3, 6, 0, 4);
    }
}