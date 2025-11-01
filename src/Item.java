import java.io.Serializable;

public class Item implements Cloneable {
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

    // Construtor de cópia (para Deep Copy)
    public Item(Item modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo ausente");
        this.nome = modelo.nome;
        this.descricao = modelo.descricao;
        this.quantidade = modelo.quantidade;
        this.efeito = modelo.efeito;
    }

    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public String getEfeito() { return efeito; }
    public void setEfeito(String efeito) { this.efeito = efeito; }

    @Override
    public String toString() {
        return nome + ": " + descricao + " | Quantidade: " + quantidade + " | Efeito: " + efeito;
    }

    // Equals (ignora quantidade para fins de empilhamento)
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;

        Item d = (Item) obj;

        if (!this.nome.equals(d.nome)) return false;
        if (!this.descricao.equals(d.descricao)) return false;
        if (!this.efeito.equals(d.efeito)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int retorno = 17;

        if (this.nome != null)
            retorno = 31 * retorno + this.nome.hashCode();
        if (this.descricao != null)
            retorno = 31 * retorno + this.descricao.hashCode();
        if (this.efeito != null)
            retorno = 31 * retorno + this.efeito.hashCode();

        return retorno;
    }

    @Override
    public Object clone() {
        try {
            return new Item(this);
        } catch (Exception erro) {
            return null;
        }
    }

    // Item pré setados
    public static final Item FRAGMENTO_DE_DRAKTHOR = new Item(
            "Fragmento de Drakthor",
            "Um dos 7 fragmentos lendários deste mundo, carregando o poder do dragão antigo.",
            ""
    );
}