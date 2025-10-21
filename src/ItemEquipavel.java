public class ItemEquipavel extends Item {
    public enum TipoEquipamento {
        CAPACETE, PEITORAL, CALCA, BOTA, ARMA
    }

    private TipoEquipamento tipo;
    private int bonusAtaque;
    private int bonusDefesa;
    private int bonusVidaMaxima;
    private int bonusMana;
    private int bonusStamina;
    private boolean equipado;

    public ItemEquipavel(String nome, String descricao, int quantidade, String efeito,
                         TipoEquipamento tipo,
                         int bonusAtaque, int bonusDefesa, int bonusVidaMaxima,
                         int bonusMana, int bonusStamina) {
        super(nome, descricao, quantidade, efeito);
        this.tipo = tipo;
        this.bonusAtaque = bonusAtaque;
        this.bonusDefesa = bonusDefesa;
        this.bonusVidaMaxima = bonusVidaMaxima;
        this.bonusMana = bonusMana;
        this.bonusStamina = bonusStamina;
        this.equipado = false;
    }

    public TipoEquipamento getTipo() { return tipo; }
    public int getBonusAtaque() { return bonusAtaque; }
    public int getBonusDefesa() { return bonusDefesa; }
    public int getBonusVidaMaxima() { return bonusVidaMaxima; }
    public int getBonusMana() { return bonusMana; }
    public int getBonusStamina() { return bonusStamina; }
    public boolean isEquipado() { return equipado; }
    public void equipar() { this.equipado = true; }
    public void desequipar() { this.equipado = false; }

    public String exibirInfoEquipamento() {
        return String.format(
                "%s (%s)%nBônus - Ataque: %d, Defesa: %d, Vida Máxima: %d, Mana: %d, Stamina: %d%nEquipado: %s%nQuantidade: %d",
                getNome(), tipo, bonusAtaque, bonusDefesa, bonusVidaMaxima, bonusMana, bonusStamina,
                equipado ? "Sim" : "Não", getQuantidade()
        );
    }

    // Itens pré setados
    public static final ItemEquipavel CAPACETE_FERRO = new ItemEquipavel(
            "Capacete de Ferro", "Um capacete resistente de ferro", 1, "",
            TipoEquipamento.CAPACETE,
            0, 5, 0, 0, 0
    );

    public static final ItemEquipavel PEITORAL_AÇO = new ItemEquipavel(
            "Peitoral de Aço", "Proteção robusta para o torso", 1, "",
            TipoEquipamento.PEITORAL,
            0, 15, 50, 0, 10
    );

    public static final ItemEquipavel CALCA_COURO = new ItemEquipavel(
            "Calça de Couro", "Calça leve e confortável", 1, "",
            TipoEquipamento.CALCA,
            0, 5, 10, 0, 5
    );

    public static final ItemEquipavel BOTA_DE_VELOCIDADE = new ItemEquipavel(
            "Bota de Velocidade", "Aumenta a agilidade e stamina", 1, "",
            TipoEquipamento.BOTA,
            0, 3, 0, 0, 15
    );

    public static final ItemEquipavel ESPADA_FLAMEJANTE = new ItemEquipavel(
            "Espada Flamejante", "Uma espada com poder de fogo", 1, "",
            TipoEquipamento.ARMA,
            20, 0, 0, 0, 0
    );
}
