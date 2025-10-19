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


    // Classes pré-definidas
    public static Classe GUERREIRO() {
        return new Classe("Guerreiro", 120, 15, 10, 0, 20);
    }

    public static Classe MAGO() {
        return new Classe("Mago", 80, 5, 4, 25, 10);
    }

    public static Classe ARQUEIRO() {
        return new Classe("Arqueiro", 90, 12, 6, 5, 25);
    }

    public static Classe LADINO() {
        return new Classe("Ladino", 85, 10, 5, 5, 30);
    }

    public static Classe PALADINO() {
        return new Classe("Paladino", 110, 12, 12, 10, 15);
    }
}