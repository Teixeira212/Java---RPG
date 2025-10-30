public class ItemConsumivel extends Item{

    public ItemConsumivel(String nome, String descricao, String efeito) {
        super(nome, descricao, efeito);
    }

    // Usar o item e reduzir sua quantidade
    public void usar(Personagem p) {
        if (getQuantidade() > 0) {
            setQuantidade(getQuantidade() - 1);
            System.out.println("Você usou " + getNome() + "! " + getEfeito());

            if (getQuantidade() == 0) {
                System.out.println(getNome() + " acabou!");
            }
        } else {
            System.out.println("Você não tem mais " + getNome() + "!");
        }
    }
}
