package Classes;

public class Classe {
    protected String nome;
    protected int baseVida;
    protected int baseAtaque;
    protected int baseDefesa;
    protected int baseMana;
    protected int baseStamina;

    public Classe(String nome, int baseVida, int baseAtaque, int baseDefesa, int baseMana, int baseStamina) {
        this.nome = nome;
        this.baseVida = baseVida;
        this.baseAtaque = baseAtaque;
        this.baseDefesa = baseDefesa;
        this.baseMana = baseMana;
        this.baseStamina = baseStamina;
    }

    public String getNome() { return nome; }
    public int getBaseVida() { return baseVida; }
    public int getBaseAtaque() { return baseAtaque; }
    public int getBaseDefesa() { return baseDefesa; }
    public int getBaseMana() { return baseMana; }
    public int getBaseStamina() { return baseStamina; }
}