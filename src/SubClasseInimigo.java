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

    public static SubClasseInimigo GOBLIN() throws Exception {
        return new SubClasseInimigo("Goblin", Raca.ORC(), Classe.LADINO(), 3, 50);
    }

    public static SubClasseInimigo ESQUELETO() throws Exception {
        return new SubClasseInimigo("Esqueleto", Raca.HUMANO(), Classe.GUERREIRO(), 3, 75);
    }

    public static SubClasseInimigo MAGO_NEGRO() throws Exception {
        return new SubClasseInimigo("Mago Negro", Raca.ELFO(), Classe.MAGO(), 4, 100);
    }

    public static SubClasseInimigo ORC_BRUTO() throws Exception {
        return new SubClasseInimigo("Orc Bruto", Raca.ORC(), Classe.GUERREIRO(), 5, 150);
    }

    public static SubClasseInimigo DEMONIO_MENOR() throws Exception {
        return new SubClasseInimigo("Demônio Menor", Raca.ANAO(), Classe.PALADINO(), 6, 200);
    }
}
