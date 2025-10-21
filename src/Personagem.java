public class Personagem {
    protected String nome;
    protected Inventario inventario;
    protected Raca raca;
    protected Classe classe;

    protected int ataque;
    protected int defesa;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int manaAtual;
    protected int staminaAtual;

    protected int nivel;
    protected int experienciaAtual;
    protected int experienciaNecessaria;


    public Personagem(String nome, Raca raca, Classe classe) throws Exception {


        this.nome = nome;
        this.raca = raca;
        this.classe = classe;
        this.inventario = new Inventario();

        //Atributos com base em raça e classe
        this.vidaMaxima = classe.getBaseVida() + raca.getBonusVida();
        this.ataque = classe.getBaseAtaque() + raca.getBonusAtaque();
        this.defesa = classe.getBaseDefesa() + raca.getBonusDefesa();
        this.manaAtual = classe.getBaseMana() + raca.getBonusMana();
        this.staminaAtual = classe.getBaseStamina() + raca.getBonusStamina();

        this.vidaAtual = vidaMaxima;

        this.nivel = 1;
        this.experienciaAtual = 0;
        this.experienciaNecessaria = calcularExperienciaNecessaria();


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

    //EXPERIENCIA
    private int calcularExperienciaNecessaria() {
        return 100 * nivel;
    }

    public void ganharExperiencia(int xpGanha) {
        experienciaAtual += xpGanha;
        System.out.println(nome + " ganhou " + xpGanha + " XP! (" + experienciaAtual + "/" + experienciaNecessaria + ")");
        while (experienciaAtual >= experienciaNecessaria) {
            subirDeNivel();
        }
    }

    private void subirDeNivel() {
        experienciaAtual -= experienciaNecessaria;
        nivel++;
        experienciaNecessaria = calcularExperienciaNecessaria();

        // Escala os atributos com base no nível
        vidaMaxima = (classe.getBaseVida() + raca.getBonusVida()) * nivel;
        ataque = (classe.getBaseAtaque() + raca.getBonusAtaque()) * nivel;
        defesa = (classe.getBaseDefesa() + raca.getBonusDefesa()) * nivel;
        manaAtual = (classe.getBaseMana() + raca.getBonusMana()) * nivel;
        staminaAtual = (classe.getBaseStamina() + raca.getBonusStamina()) * nivel;

        vidaAtual = vidaMaxima; // restaura vida ao subir de nível

        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }


    // Dano
    public void receberDano(int dano) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) this.vidaAtual = 0;

        if (!estaVivo()) {
            System.out.println("\n===============================================");
            System.out.println(nome + " foi derrotado! FIM DE JOGO!!!");
            System.out.println("===============================================\n");

            System.exit(0);

        }
    }

    public boolean estaVivo() {
        return vidaAtual > 0;
    }

    public void exibirStatus() {
        System.out.println("\n");
        System.out.println("===== STATUS DO PERSONAGEM =====");
        System.out.println("Nome: " + nome);
        System.out.println("Raça: " + raca.getNome());
        System.out.println("Classe: " + classe.getNome());
        System.out.println("--------------------------------");
        System.out.println("Nível: " + nivel);
        System.out.println("XP: " + experienciaAtual + "/" + experienciaNecessaria);
        System.out.println("--------------------------------");
        System.out.println("Vida: " + vidaAtual + "/" + vidaMaxima);
        System.out.println("Mana: " + manaAtual);
        System.out.println("Stamina: " + staminaAtual);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("================================\n");
    }

}
