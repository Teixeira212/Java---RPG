public class ItemEquipavel extends Item implements Comparable<ItemEquipavel>, Cloneable  {
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

    public ItemEquipavel(String nome, String descricao, String efeito,
                         TipoEquipamento tipo,
                         int bonusAtaque, int bonusDefesa, int bonusVidaMaxima,
                         int bonusMana, int bonusStamina) {
        super(nome, descricao, efeito);
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
    public static final ItemEquipavel ESPADA_DE_FERRO = new ItemEquipavel(
            "Espada de Ferro",
            "Uma espada comum de ferro, afiada e resistente",
            "",
            TipoEquipamento.ARMA,
            22, 0, 0, 0, 0
    );

    public static final ItemEquipavel CAJADO_SIMPLES = new ItemEquipavel(
            "Cajado Simples",
            "Um cajado simples, usado para canalizar magia",
            "",
            TipoEquipamento.ARMA,
            10, 0, 0, 0, 0
    );

    public static final ItemEquipavel ARCO_DE_MADEIRA = new ItemEquipavel(
            "Arco de Madeira",
            "Um arco feito de madeira leve, ideal para ataques à distância",
            "",
            TipoEquipamento.ARMA,
            15, 0, 0, 0, 0
    );

    public static final ItemEquipavel ADAGA_DE_FERRO = new ItemEquipavel(
            "Adaga de Ferro",
            "Uma adaga curta e afiada, perfeita para ataques rápidos",
            "",
            TipoEquipamento.ARMA,
            12, 0, 0, 0, 0
    );

    public static final ItemEquipavel CLAVA_DE_FERRO = new ItemEquipavel(
            "Clava de Ferro",
            "Uma clava pesada de ferro, ideal para causar grande dano físico",
            "",
            TipoEquipamento.ARMA,
            18, 0, 0, 0, 0
    );


    public static final ItemEquipavel PEITORAL_DE_FERRO = new ItemEquipavel(
            "Peitoral de Ferro",
            "Um peitoral comum de ferro que aumenta sua defesa.",
            "",
            TipoEquipamento.PEITORAL,
            0, 15, 0, 0, 0
    );

    public static final ItemEquipavel CAPACETE_DE_COURO = new ItemEquipavel(
            "Capacete de Couro",
            "Um capacete leve de couro que oferece proteção básica.",
            "",
            TipoEquipamento.CAPACETE,
            0, 5, 0, 0, 0
    );

    @Override
    public String toString() {
        return "Nome: " + getNome() + "\n" +
                "Descrição: " + getDescricao() + "\n" +
                "Efeito: " + getEfeito() + "\n" +
                "Tipo: " + tipo + "\n" +
                "Bônus - Ataque: " + bonusAtaque +
                ", Defesa: " + bonusDefesa +
                ", Vida Máxima: " + bonusVidaMaxima +
                ", Mana: " + bonusMana +
                ", Stamina: " + bonusStamina + "\n" +
                "Equipado: " + (equipado ? "Sim" : "Não") + "\n" +
                "Quantidade: " + getQuantidade();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        ItemEquipavel outro = (ItemEquipavel) obj;

        if (
                !getNome().equals(outro.getNome()) ||
                        !getDescricao().equals(outro.getDescricao()) ||
                        !getEfeito().equals(outro.getEfeito()) ||
                        this.tipo != outro.tipo ||
                        this.bonusAtaque != outro.bonusAtaque ||
                        this.bonusDefesa != outro.bonusDefesa ||
                        this.bonusVidaMaxima != outro.bonusVidaMaxima ||
                        this.bonusMana != outro.bonusMana ||
                        this.bonusStamina != outro.bonusStamina ||
                        this.equipado != outro.equipado ||
                        this.getQuantidade() != outro.getQuantidade()
        ) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int retorno = super.hashCode();

        if (getNome() != null)
            retorno = retorno * 7 + getNome().hashCode();
        if (getDescricao() != null)
            retorno = retorno * 7 + getDescricao().hashCode();
        if (getEfeito() != null)
            retorno = retorno * 7 + getEfeito().hashCode();

        if (tipo != null)
            retorno = retorno * 7 + tipo.hashCode();

        retorno = retorno * 7 + ((Integer)bonusAtaque).hashCode();
        retorno = retorno * 7 + ((Integer)bonusDefesa).hashCode();
        retorno = retorno * 7 + ((Integer)bonusVidaMaxima).hashCode();
        retorno = retorno * 7 + ((Integer)bonusMana).hashCode();
        retorno = retorno * 7 + ((Integer)bonusStamina).hashCode();
        retorno = retorno * 7 + (equipado ? 1 : 0);
        retorno = retorno * 7 + ((Integer)getQuantidade()).hashCode();

        if (retorno < 0) retorno =-retorno;
        return retorno;
    }

    public ItemEquipavel(ItemEquipavel modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo ausente");

        super(modelo.getNome(), modelo.getDescricao(), modelo.getEfeito());
        this.setQuantidade(modelo.getQuantidade());

        this.tipo = modelo.tipo;
        this.bonusAtaque = modelo.bonusAtaque;
        this.bonusDefesa = modelo.bonusDefesa;
        this.bonusVidaMaxima = modelo.bonusVidaMaxima;
        this.bonusMana = modelo.bonusMana;
        this.bonusStamina = modelo.bonusStamina;
        this.equipado = modelo.equipado;
    }

    @Override
    public Object clone ()
    {
        ItemEquipavel retorno=null;
        try
        {
            retorno = new ItemEquipavel (this);
        }
        catch (Exception erro)
        {}

        return retorno;
    }

    @Override
    public int compareTo(ItemEquipavel i) {
        if (this == i) return 0;

        if (this.getNome().compareTo(i.getNome()) < 0) return -666;
        if (this.getNome().compareTo(i.getNome()) > 0) return 666;

        if (this.tipo.ordinal() < i.tipo.ordinal()) return -666;
        if (this.tipo.ordinal() > i.tipo.ordinal()) return 666;

        return 0;
    }



}
