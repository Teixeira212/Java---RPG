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

    public static Raca DRAGAO() {return new Raca("Dragão", 8, 4, 6, 6, 7);}

    @Override
    public String toString() {
        return "Raça: " + nome +
                " | Vida: " + bonusVida +
                " | Ataque: " + bonusAtaque +
                " | Defesa: " + bonusDefesa +
                " | Mana: " + bonusMana +
                " | Stamina: " + bonusStamina;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Raca r = (Raca) obj;

        if (!this.nome.equals(r.nome) ||
                this.bonusVida != r.bonusVida ||
                this.bonusAtaque != r.bonusAtaque ||
                this.bonusDefesa != r.bonusDefesa ||
                this.bonusMana != r.bonusMana ||
                this.bonusStamina != r.bonusStamina)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int retorno = 7;

        if (this.nome != null)
            retorno = retorno * 7 + this.nome.hashCode();

        retorno = retorno * 7 + ((Integer)this.bonusVida).hashCode();
        retorno = retorno * 7 + ((Integer)this.bonusAtaque).hashCode();
        retorno = retorno * 7 + ((Integer)this.bonusDefesa).hashCode();
        retorno = retorno * 7 + ((Integer)this.bonusMana).hashCode();
        retorno = retorno * 7 + ((Integer)this.bonusStamina).hashCode();

        if (retorno < 0)
            retorno =-retorno;

        return retorno;
    }
}