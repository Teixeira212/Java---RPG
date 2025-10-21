public class Item {
    private String nome;
    private String descricao;
    private int quantidade;
    private String efeito;

    public Item(String nome, String descricao, int quantidade, String efeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.efeito = efeito;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    @Override
    public String toString() {
        return nome + ": " + descricao + " | Quantidade: " + quantidade + " | Efeito: " + efeito;
    }
}
