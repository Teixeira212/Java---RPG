import Classes.Classe;
import Classes.Guerreiro;
import Classes.Mago;
import Classes.Arqueiro;
import Classes.Ladino;
import Classes.Paladino;

public class SubClasseInimigo extends Personagem {

    private int experienciaDrop;

    public SubClasseInimigo(String nome,Raca raca, Classe classe, int nivel, int experienciaDrop ) throws Exception {
        super(nome,raca,classe);

        super.inventario = new Inventario();
        this.nivel = nivel;
        this.experienciaDrop = nivel * nivel * experienciaDrop;

        // Atributos baseados na raça e classe
        this.vidaMaxima = (classe.getBaseVida() + raca.getBonusVida()) * nivel;
        this.ataque = (classe.getBaseAtaque() + raca.getBonusAtaque()) * nivel;
        this.defesa = (classe.getBaseDefesa() + raca.getBonusDefesa()) * nivel;
        this.manaAtual = (classe.getBaseMana() + raca.getBonusMana()) * nivel;
        this.staminaAtual = (classe.getBaseStamina() + raca.getBonusStamina()) * nivel;

        this.vidaAtual = vidaMaxima;

        this.nivel = nivel;
        this.experienciaDrop = nivel * nivel * experienciaDrop;

    }

    //@Override
    public void receberDano(int dano, Personagem atacante) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) this.vidaAtual = 0;

        if (!estaVivo()) {
            System.out.println("\n===============================================");
            System.out.println(nome + " foi derrotado e dropou " + experienciaDrop + " de experiência!");
            System.out.println("===============================================\n");

            atacante.ganharExperiencia(experienciaDrop);
        }
    }

    public static SubClasseInimigo GOBLIN(int nivel) throws Exception {
        return new SubClasseInimigo("Goblin", Raca.ORC(), new Ladino(), nivel, 100);
    }

    public static SubClasseInimigo ESQUELETO(int nivel) throws Exception {
        return new SubClasseInimigo("Esqueleto", Raca.HUMANO(), new Guerreiro(), nivel, 40);
    }

    public static SubClasseInimigo MAGO_NEGRO(int nivel) throws Exception {
        return new SubClasseInimigo("Mago Negro", Raca.ELFO(), new Mago(), nivel, 40);
    }

    public static SubClasseInimigo DRAGAO_ANCIAO(int nivel) throws Exception {
        return new SubClasseInimigo("Drakthor", Raca.DRAGAO(), new Guerreiro(), nivel, 80);
    }


    @Override
    public String toString() {
        return "\n" +
                "===== INIMIGO =====\n" +
                "Nome: " + nome + "\n" +
                "Raça: " + raca.getNome() + "\n" +
                "Classe: " + classe.getNome() + "\n" +
                "Nível: " + nivel + "\n" +
                "XP Drop: " + experienciaDrop + "\n" +
                "Vida: " + vidaAtual + "/" + vidaMaxima + "\n" +
                "Mana: " + manaAtual + "/" + manaMaxima + "\n" +
                "Stamina: " + staminaAtual + "/" + staminaMaxima + "\n" +
                "Ataque: " + ataque + "\n" +
                "Defesa: " + defesa + "\n" +
                "===================\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        SubClasseInimigo s = (SubClasseInimigo) obj;

        if (
                (this.nome.equals(s.nome)) ||
                        (this.raca.equals(s.raca)) ||
                        (this.classe.equals(s.classe)) ||
                        this.ataque != s.ataque ||
                        this.defesa != s.defesa ||
                        this.vidaMaxima != s.vidaMaxima ||
                        this.vidaAtual != s.vidaAtual ||
                        this.manaAtual != s.manaAtual ||
                        this.manaMaxima != s.manaMaxima ||
                        this.staminaAtual != s.staminaAtual ||
                        this.staminaMaxima != s.staminaMaxima ||
                        this.nivel != s.nivel ||
                        this.experienciaDrop != s.experienciaDrop ||
                        (this.inventario.equals(s.inventario))
        ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int retorno = super.hashCode();

        retorno = retorno * 7 + ((Integer)experienciaDrop).hashCode();

        if (retorno < 0) retorno = -retorno;
        return retorno;
    }
}
