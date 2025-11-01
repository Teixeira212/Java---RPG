import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Inventario implements Cloneable, Comparable<Inventario> {

    private EnumMap<ItemEquipavel.TipoEquipamento, ItemEquipavel> slotsEquipaveis;
    private List<Item> inventario;

    public Inventario() {
        slotsEquipaveis = new EnumMap<>(ItemEquipavel.TipoEquipamento.class);
        inventario = new ArrayList<>();
    }

    // Construtor de cópia (deep copy)
    public Inventario(Inventario modelo) throws Exception {
        if (modelo == null) throw new Exception("Modelo ausente");

        // Clonando slots equipáveis
        this.slotsEquipaveis = new EnumMap<>(ItemEquipavel.TipoEquipamento.class);
        if (modelo.slotsEquipaveis != null) {
            for (ItemEquipavel.TipoEquipamento tipo : ItemEquipavel.TipoEquipamento.values()) {
                ItemEquipavel item = modelo.slotsEquipaveis.get(tipo);
                if (item != null) {
                    // Clonagem profunda (ItemEquipavel precisa implementar Cloneable)
                    this.slotsEquipaveis.put(tipo, (ItemEquipavel) item.clone());
                }
            }
        }

        // Clonando itens do inventário
        this.inventario = new ArrayList<>();
        if (modelo.inventario != null) {
            for (Item item : modelo.inventario) {
                if (item != null) {
                    this.inventario.add((Item) item.clone());
                }
            }
        }
    }

    // Deep copy usando clone()
    @Override
    public Object clone() {
        try {
            return new Inventario(this);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Métodos existentes permanecem iguais
    public void adicionarNoInventario(Item item) {
        for (Item itemInv : inventario) {
            if (itemInv.equals(item)) {
                itemInv.setQuantidade(itemInv.getQuantidade() + 1);
                return;
            }
        }
        inventario.add(item);
        System.out.println(item.getNome() + " adicionado ao inventário.");
    }

    public ItemEquipavel equiparItem(ItemEquipavel item) {
        ItemEquipavel.TipoEquipamento tipo = item.getTipo();
        if (!inventario.contains(item)) {
            System.out.println("Item não está no inventário.");
            return null;
        }
        ItemEquipavel itemAntigo = slotsEquipaveis.put(tipo, item);
        if (itemAntigo != null) {
            itemAntigo.desequipar();
            inventario.add(itemAntigo);
            System.out.println(itemAntigo.getNome() + " foi removido do slot " + tipo + ".");
        }
        inventario.remove(item);
        item.equipar();
        System.out.println(item.getNome() + " equipado no slot " + tipo + ".");
        return itemAntigo;
    }

    public ItemEquipavel desequiparItem(ItemEquipavel.TipoEquipamento tipo) {
        if (!slotsEquipaveis.containsKey(tipo)) {
            System.out.println("Nenhum item equipado no slot " + tipo + ".");
            return null;
        }
        ItemEquipavel item = slotsEquipaveis.remove(tipo);
        item.desequipar();
        inventario.add(item);
        System.out.println(item.getNome() + " desequipado e colocado no inventário.");
        return item;
    }

    public void listarItensInventario() {
        inventario.removeIf(item -> item.getQuantidade() <= 0);
        System.out.println("Itens no inventário:");
        if (inventario.isEmpty()) {
            System.out.println("  (Inventário vazio)");
            return;
        }
        int i = 1;
        for (Item item : inventario) {
            System.out.println(i + " - " + item.getNome() + " (x" + item.getQuantidade() + ")");
            i++;
        }
    }

    public Item getItemPorId(int id) {
        if (id < 1 || id > inventario.size()) {
            System.out.println("ID inválido.");
            return null;
        }
        return inventario.get(id - 1);
    }

    public void listarItensEquipados() {
        System.out.println("Itens equipados:");
        if (slotsEquipaveis.isEmpty()) {
            System.out.println("  (nenhum item equipado)");
            return;
        }
        for (ItemEquipavel.TipoEquipamento tipo : ItemEquipavel.TipoEquipamento.values()) {
            ItemEquipavel item = slotsEquipaveis.get(tipo);
            System.out.println(" - " + tipo + ": " + (item != null ? item.getNome() : "(vazio)"));
        }
    }

    public List<Item> getItens() {
        return inventario;
    }

    public void removerDoInventario(Item item) {
        inventario.remove(item);
    }

    public void organizarInventario() {
        inventario.sort((item1, item2) -> item1.getNome().compareTo(item2.getNome()));
    }

    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder();
        for (Item item : inventario) {
            texto.append(item.getNome()).append(" (x").append(item.getQuantidade()).append(")\n");
        }
        return texto.toString();
    }

    @Override
    public int hashCode() {
        int retorno = 7;
        if (this.slotsEquipaveis != null) {
            for (ItemEquipavel.TipoEquipamento tipo : this.slotsEquipaveis.keySet()) {
                ItemEquipavel item = this.slotsEquipaveis.get(tipo);
                if (item != null) retorno = retorno * 5 + item.hashCode();
            }
        }
        if (this.inventario != null) {
            for (Item item : this.inventario) {
                if (item != null) retorno = retorno * 5 + item.hashCode();
            }
        }
        return Math.abs(retorno);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;

        Inventario inv = (Inventario) obj;

        if ((this.slotsEquipaveis == null && inv.slotsEquipaveis != null) ||
                (this.slotsEquipaveis != null && !this.slotsEquipaveis.equals(inv.slotsEquipaveis)))
            return false;

        if ((this.inventario == null && inv.inventario != null) ||
                (this.inventario != null && !this.inventario.equals(inv.inventario)))
            return false;

        return true;
    }

    @Override
    public int compareTo(Inventario inv) {
        if (this == inv) return 0;
        if (inv == null) return 666;
        int tamanho = Math.min(this.inventario.size(), inv.inventario.size());
        for (int i = 0; i < tamanho; i++) {
            int cmp = this.inventario.get(i).getNome().compareTo(inv.inventario.get(i).getNome());
            if (cmp != 0) return cmp < 0 ? -666 : 666;
        }
        return 0;
    }

}
