public class Personagem {
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
    private int experienciaAtual;
    private int experienciaNecessaria;

    public Personagem(String nome, Raca raca, Classe classe) {
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

    private void subirDeNivel(){
        experienciaAtual -= experienciaNecessaria;
        nivel++;
        experienciaNecessaria = calcularExperienciaNecessaria();

        // Pode crescer atributos aqui se quiser
        vidaMaxima += 10;
        ataque += 2;
        defesa += 1;
        vidaAtual = vidaMaxima;

        System.out.println(nome + " subiu para o nível " + nivel + "!");
    }

    // Método de dano
    public void receberDano(int dano) {
        this.vidaAtual -= dano;
        if (this.vidaAtual < 0) this.vidaAtual = 0;

        if (!estaVivo()) {
            System.out.println("===============================================");
            System.out.println(nome + " foi derrotado! FIM DE JOGO!!!");
            System.out.println("===============================================");

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
