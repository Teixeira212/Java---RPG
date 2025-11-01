public class ItemConsumivel extends Item {

    public ItemConsumivel(String nome, String descricao, String efeito) {
        super(nome, descricao, efeito);
    }

    // Construtor de cópia (para Deep Copy)
    public ItemConsumivel(ItemConsumivel modelo) throws Exception {
        super(modelo);
    }

    // Poção de Cura — recupera vida
    public static ItemConsumivel POCAO_CURA() {
        ItemConsumivel pocaoCura = new ItemConsumivel(
                "Poção de Cura",
                "Uma poção mágica que restaura 100 pontos de vida.",
                "cura"
        );
        pocaoCura.setQuantidade(1);
        return pocaoCura;
    }

    // Poção de Mana — recupera mana
    public static ItemConsumivel POCAO_MANA() {
        ItemConsumivel pocaoMana = new ItemConsumivel(
                "Poção de Mana",
                "Uma poção energética que restaura 30 pontos de mana.",
                "mana"
        );
        pocaoMana.setQuantidade(1);
        return pocaoMana;
    }

    @Override
    public String toString() {
        return "[Consumível] " + getNome() + ": " + getDescricao() +
                " | Quantidade: " + getQuantidade() +
                " | Efeito: " + getEfeito();
    }

    // Equals (delega para a classe Item)
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public Object clone() {
        try {
            return new ItemConsumivel(this);
        } catch (Exception e) {
            return null;
        }
    }
}