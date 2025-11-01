import Classes.Classe;
import java.util.List;

public class Personagem implements Cloneable {
    protected String nome;
    protected Inventario inventario;
    protected Raca raca; // Assumindo que esta classe existe
    protected Classe classe; // Assumindo que esta classe existe

    protected int ataque;
    protected int defesa;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int manaAtual;
    protected int manaMaxima;
    protected int staminaAtual;
    protected int staminaMaxima;

    protected int nivel;
    protected int experienciaAtual;
    protected int experienciaNecessaria;


    public Personagem(String nome, Raca raca, Classe classe) throws Exception {

        this.nome = nome;
        this.raca = raca;
        this.classe = classe;
        this.inventario = new Inventario();

        // Atributos com base em raça e classe
        this.vidaMaxima = classe.getBaseVida() + raca.getBonusVida();
        this.ataque = classe.getBaseAtaque() + raca.getBonusAtaque();
        this.defesa = classe.getBaseDefesa() + raca.getBonusDefesa();
        this.manaMaxima = classe.getBaseMana() + raca.getBonusMana();
        this.staminaMaxima = classe.getBaseStamina() + raca.getBonusStamina();

        this.vidaAtual = vidaMaxima;
        this.manaAtual = manaMaxima;
        this.staminaAtual = staminaMaxima;

        this.nivel = 1;
        this.experienciaAtual = 0;
        this.experienciaNecessaria = calcularExperienciaNecessaria();
    }

    // Construtor de cópia (Deep Copy: ESSENCIAL para o Save Point)
    public Personagem(Personagem modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo ausente");

        this.nome = modelo.nome;
        this.raca = modelo.raca;
        this.classe = modelo.classe;

        this.ataque = modelo.ataque;
        this.defesa = modelo.defesa;
        this.vidaMaxima = modelo.vidaMaxima;
        this.vidaAtual = modelo.vidaAtual;
        this.manaAtual = modelo.manaAtual;
        this.manaMaxima = modelo.manaMaxima;
        this.staminaAtual = modelo.staminaAtual;
        this.staminaMaxima = modelo.staminaMaxima;
        this.nivel = modelo.nivel;
        this.experienciaAtual = modelo.experienciaAtual;
        this.experienciaNecessaria = modelo.experienciaNecessaria;

        // Clonagem profunda do inventário
        if (modelo.inventario != null) {
            this.inventario = (Inventario) modelo.inventario.clone();
        } else {
            this.inventario = new Inventario();
        }
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

    public void setVidaAtual(int vidaAtual) {
        this.vidaAtual = Math.max(0, Math.min(vidaAtual, this.vidaMaxima));
    }

    public void setManaAtual(int manaAtual) {
        this.manaAtual = Math.max(0, Math.min(manaAtual, this.manaMaxima));
    }

    // EXPERIENCIA
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

        // Correção da lógica de nível: Adiciona os bônus base
        vidaMaxima += (classe.getBaseVida() + raca.getBonusVida());
        ataque += (classe.getBaseAtaque() + raca.getBonusAtaque());
        defesa += (classe.getBaseDefesa() + raca.getBonusDefesa());
        manaMaxima += (classe.getBaseMana() + raca.getBonusMana());
        staminaMaxima += (classe.getBaseStamina() + raca.getBonusStamina());

        vidaAtual = vidaMaxima;
        manaAtual = manaMaxima;
        staminaAtual = staminaMaxima; // Adicionado para consistência

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
        System.out.println("Mana: " + manaAtual + "/" + manaMaxima);
        System.out.println("Stamina: " + staminaAtual);
        System.out.println("Ataque: " + ataque);
        System.out.println("Defesa: " + defesa);
        System.out.println("================================\n");
    }

    public void equiparItem(ItemEquipavel item) {
        ItemEquipavel antigo = inventario.equiparItem(item);

        // Se havia um item antigo, remover seus bônus
        if (antigo != null) {
            removerBonus(antigo);
        }

        // Aplicar os bônus do novo item
        aplicarBonus(item);
    }

    public void desequiparItem(ItemEquipavel.TipoEquipamento tipo) {
        ItemEquipavel removido = inventario.desequiparItem(tipo);
        if (removido != null) {
            removerBonus(removido);
        }
    }

    private void aplicarBonus(ItemEquipavel item) {
        this.ataque += item.getBonusAtaque();
        this.defesa += item.getBonusDefesa();
        this.vidaMaxima += item.getBonusVidaMaxima();
        this.manaMaxima += item.getBonusMana();
        this.staminaMaxima += item.getBonusStamina();

        // Opcional: curar a vida/mana/stamina ao equipar
        this.vidaAtual = Math.min(this.vidaAtual, this.vidaMaxima);
        this.manaAtual = Math.min(this.manaAtual, this.manaMaxima);
        this.staminaAtual = Math.min(this.staminaAtual, this.staminaMaxima);

        System.out.println(item.getNome() + " concedeu bônus ao personagem!");
    }

    private void removerBonus(ItemEquipavel item) {
        this.ataque -= item.getBonusAtaque();
        this.defesa -= item.getBonusDefesa();
        this.vidaMaxima -= item.getBonusVidaMaxima();
        this.manaMaxima -= item.getBonusMana();
        this.staminaMaxima -= item.getBonusStamina();

        if (vidaAtual > vidaMaxima) vidaAtual = vidaMaxima;
        if (manaAtual > manaMaxima) manaAtual = manaMaxima;
        if (staminaAtual > staminaMaxima) staminaAtual = staminaMaxima;

        System.out.println(item.getNome() + " teve seus bônus removidos.");
    }


    public void usarItem(Item item) {

        if (item.getQuantidade() <= 0) {
            System.out.println("Você não tem mais " + item.getNome() + "!");
            return;
        }

        System.out.println(nome + " usou " + item.getNome() + "!");

        switch (item.getEfeito().toLowerCase()) {
            case "cura":
            case "poção de cura":
                curar(100);
                break;

            case "mana":
            case "poção de mana":
                restaurarMana(30);
                break;

            default:
                System.out.println("O item " + item.getNome() + " não tem um efeito conhecido.");
        }

        item.setQuantidade(item.getQuantidade() - 1);

        if (item.getQuantidade() == 0) {
            System.out.println(item.getNome() + " acabou!");
            inventario.removerDoInventario(item);
        }
    }

    public void curar(int pontos) {
        if (vidaAtual == vidaMaxima) {
            System.out.println(nome + " já está com a vida cheia!");
            return;
        }
        int vidaAntes = vidaAtual;
        vidaAtual = Math.min(vidaAtual + pontos, vidaMaxima);
        int curado = vidaAtual - vidaAntes;
        System.out.println(nome + " recuperou " + curado + " pontos de vida! (" + vidaAtual + "/" + vidaMaxima + ")");
    }

    public void restaurarMana(int pontos) {
        if (manaAtual == manaMaxima) {
            System.out.println(nome + " já está com a mana cheia!");
            return;
        }
        int manaAntes = manaAtual;
        manaAtual = Math.min(manaAtual + pontos, manaMaxima);
        int recuperado = manaAtual - manaAntes;
        System.out.println(nome + " recuperou " + recuperado + " pontos de mana! (" + manaAtual + "/" + manaMaxima + ")");
    }

    @Override
    public String toString() {
        return "\n" +
                "Nome: " + nome + "\n" +
                "Raça: " + raca.getNome() + "\n" +
                "Classe: " + classe.getNome() + "\n" +
                "--------------------------------\n" +
                "Nível: " + nivel + "\n" +
                "XP: " + experienciaAtual + "/" + experienciaNecessaria + "\n" +
                "--------------------------------\n" +
                "Vida: " + vidaAtual + "/" + vidaMaxima + "\n" +
                "Mana: " + manaAtual + "/" + manaMaxima + "\n" +
                "Stamina: " + staminaAtual + "\n" +
                "Ataque: " + ataque + "\n" +
                "Defesa: " + defesa + "\n" +
                "================================\n";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Personagem p = (Personagem) obj;

        if (this.ataque != p.ataque || this.defesa != p.defesa || this.vidaMaxima != p.vidaMaxima ||
                this.vidaAtual != p.vidaAtual || this.manaAtual != p.manaAtual || this.manaMaxima != p.manaMaxima ||
                this.staminaAtual != p.staminaAtual ||
                this.staminaMaxima != p.staminaMaxima ||
                this.nivel != p.nivel || this.experienciaAtual != p.experienciaAtual ||
                this.experienciaNecessaria != p.experienciaNecessaria
        ) return false;

        if (!this.nome.equals(p.nome)) return false;
        if (!this.raca.equals(p.raca)) return false;
        if (!this.classe.equals(p.classe)) return false;
        if (!this.inventario.equals(p.inventario)) return false; // Requer .equals() em Inventario

        return true;
    }

    public int hashCode() {
        int retorno = 7;

        // Objetos simples
        if (nome != null) retorno = retorno * 31 + nome.hashCode();
        if (raca != null) retorno = retorno * 31 + raca.hashCode();
        if (classe != null) retorno = retorno * 31 + classe.hashCode();

        // Atributos primitivos
        retorno = retorno * 31 + ((Integer) ataque).hashCode();
        retorno = retorno * 31 + ((Integer) defesa).hashCode();
        retorno = retorno * 31 + ((Integer) vidaMaxima).hashCode();
        retorno = retorno * 31 + ((Integer) vidaAtual).hashCode();
        retorno = retorno * 31 + ((Integer) manaAtual).hashCode();
        retorno = retorno * 31 + ((Integer) manaMaxima).hashCode();
        retorno = retorno * 31 + ((Integer) staminaAtual).hashCode();
        retorno = retorno * 31 + ((Integer) staminaMaxima).hashCode();
        retorno = retorno * 31 + ((Integer) nivel).hashCode();
        retorno = retorno * 31 + ((Integer) experienciaAtual).hashCode();
        retorno = retorno * 31 + ((Integer) experienciaNecessaria).hashCode();

        // Inventário: percorre a lista de itens
        if (inventario != null && inventario.getItens() != null) {
            for (Item item : inventario.getItens()) {
                if (item != null) {
                    retorno = retorno * 31 + item.hashCode();
                }
            }
        }

        if (retorno<0) retorno=-retorno;
        return retorno;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    @Override
    public Object clone() {
        try {
            return new Personagem(this);
        } catch (Exception erro) {
            return null;
        }
    }
}