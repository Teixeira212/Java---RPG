public class Classe {
    private String nome;
    private int baseVida;
    private int baseAtaque;
    private int baseDefesa;
    private int baseMana;
    private int baseStamina;

    public Classe(String nome, int baseVida, int baseAtaque, int baseDefesa, int baseMana, int baseStamina) {
        this.nome = nome;
        this.baseVida = baseVida;
        this.baseAtaque = baseAtaque;
        this.baseDefesa = baseDefesa;
        this.baseMana = baseMana;
        this.baseStamina = baseStamina;
    }

    // Getters
    public String getNome() { return nome; }
    public int getBaseVida() { return baseVida; }
    public int getBaseAtaque() { return baseAtaque; }
    public int getBaseDefesa() { return baseDefesa; }
    public int getBaseMana() { return baseMana; }
    public int getBaseStamina() { return baseStamina; }
}
