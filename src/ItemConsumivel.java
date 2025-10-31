public class ItemConsumivel extends Item{

    public ItemConsumivel(String nome, String descricao, String efeito) {
        super(nome, descricao, efeito);
    }

    // Poção de Cura — recupera vida
    public static ItemConsumivel POCAO_CURA() {
        ItemConsumivel pocaoCura = new ItemConsumivel(
                "Poção de Cura",
                "Uma poção mágica que restaura 50 pontos de vida.",
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
}
