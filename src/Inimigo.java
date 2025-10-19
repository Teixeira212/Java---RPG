public class Inimigo {

    private String nome;
    private Inventario inventario;
    private Raca raca;
    private Classe classe;

    private int ataque;
    private int defesa;
    private int vidaMaxima;
    private int vidaAtual;
    private int manaAtual;
    private int staminaAtual;

    private int nivel;
    private int experienciaDrop;

    public Inimigo(String nome, Raca raca, Classe classe, int nivel, int experienciaDrop) {
        this.nome = nome;
        this.raca = raca;
        this.classe = classe;
        this.inventario = new Inventario();
        this.nivel = nivel;
        this.experienciaDrop = experienciaDrop;

        // Atributos baseados na raça e classe
        this.vidaMaxima = classe.getBaseVida() + raca.getBonusVida();
        this.ataque = classe.getBaseAtaque() + raca.getBonusAtaque();
        this.defesa = classe.getBaseDefesa() + raca.getBonusDefesa();
        this.manaAtual = classe.getBaseMana() + raca.getBonusMana();
        this.staminaAtual = classe.getBaseStamina() + raca.getBonusStamina();

        this.vidaAtual = vidaMaxima;
    }

    // Getters
    public String getNome() { return nome; }
    public Raca getRaca() { return raca; }
    public Classe getClasse() { return classe; }
    public int getAtaque() { return ataque; }
    public int getDefesa() { return defesa; }
    public int getVidaAtual() { return vidaAtual; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getNivel() { return nivel; }
    public int getExperienciaDrop() { return experienciaDrop; }

    // Método de dano
    public void receberDano(int dano, Personagem atacante) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) this.vidaAtual = 0;

        if (!estaVivo()) {
            System.out.println("===============================================");
            System.out.println(nome + " foi derrotado e dropou " + experienciaDrop + " de experiência!");
            System.out.println("===============================================");

            atacante.ganharExperiencia(experienciaDrop);
        }
    }

    public boolean estaVivo() {
        return vidaAtual > 0;
    }

    // Exibir status
    public void exibirStatus() {
        System.out.println("\n=== Inimigo: " + nome + " ===");
        System.out.println("Raça: " + raca.getNome() + " | Classe: " + classe.getNome());
        System.out.println("Nível: " + nivel);
        System.out.println("Vida: " + vidaAtual + "/" + vidaMaxima);
        System.out.println("Ataque: " + ataque + " | Defesa: " + defesa);
        System.out.println("Experiência ao ser derrotado: " + experienciaDrop);
        System.out.println("===========================");
    }

    // Inimigos pré-definidos
    public static Inimigo GOBLIN() {
        return new Inimigo("Goblin", Raca.ORC(), Classe.LADINO(), 2, 1000);
    }

    public static Inimigo ESQUELETO() {
        return new Inimigo("Esqueleto", Raca.HUMANO(), Classe.GUERREIRO(), 3, 75);
    }

    public static Inimigo MAGO_NEGRO() {
        return new Inimigo("Mago Negro", Raca.ELFO(), Classe.MAGO(), 4, 100);
    }

    public static Inimigo ORC_BRUTO() {
        return new Inimigo("Orc Bruto", Raca.ORC(), Classe.GUERREIRO(), 5, 150);
    }

    public static Inimigo DEMONIO_MENOR() {
        return new Inimigo("Demônio Menor", Raca.ANAO(), Classe.PALADINO(), 6, 200);
    }
}
