public class Item {
    private String nome;
    private String descricao;
    private int quantidade = 1;
    private String efeito;

    public Item(String nome, String descricao, String efeito) {
        this.nome = nome;
        this.descricao = descricao;
        this.quantidade = 1;
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

    @Override
    public boolean equals(Object obj) {
        if (obj==this) return true;
        if (obj==null) return false;
        if (obj.getClass()!=this.getClass()) return false;
        Item d=(Item)obj;
        if (this.nome != d.nome || this.descricao != d.descricao || this.efeito != d.efeito) return false;

        return true;
    }
}
