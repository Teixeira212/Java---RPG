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
}
