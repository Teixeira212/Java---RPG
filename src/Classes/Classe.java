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

    public String getNome() {
        return nome;
    }

    public int getBaseVida() {
        return baseVida;
    }

    public int getBaseAtaque() {
        return baseAtaque;
    }

    public int getBaseDefesa() {
        return baseDefesa;
    }

    public int getBaseMana() {
        return baseMana;
    }

    public int getBaseStamina() {
        return baseStamina;
    }

    @Override
    public String toString() {
        return nome + " {" +
                "Vida: " + baseVida +
                ", Ataque: " + baseAtaque +
                ", Defesa: " + baseDefesa +
                ", Mana: " + baseMana +
                ", Stamina: " + baseStamina +
                '}';
    }

    @Override
    public int hashCode() {
        int retorno = 1;
        if (nome != null) {
            retorno = 31 * retorno + nome.hashCode();
        }

        retorno = 31 * retorno + baseVida;
        retorno = 31 * retorno + baseAtaque;
        retorno = 31 * retorno + baseDefesa;
        retorno = 31 * retorno + baseMana;
        retorno = 31 * retorno + baseStamina;

        if (retorno < 0) retorno = -retorno;
        return retorno;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Classe c = (Classe) obj;

        if (this.nome.equals(c.nome)||
                this.baseVida != c.baseVida ||
                this.baseAtaque != c.baseAtaque ||
                this.baseDefesa != c.baseDefesa ||
                this.baseMana != c.baseMana ||
                this.baseStamina != c.baseStamina){

                return false;
        }

        return true;

    }
}