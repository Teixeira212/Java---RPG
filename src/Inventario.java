import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Inventario {

    // Mapa que relaciona cada tipo de equipamento com o item equipado
    private EnumMap<ItemEquipavel.TipoEquipamento, ItemEquipavel> slotsEquipaveis;

    // Inventario onde ficam todos os itens (equipáveis e outros)
    private List<Item> inventario;

    public Inventario() {
        slotsEquipaveis = new EnumMap<>(ItemEquipavel.TipoEquipamento.class);
        inventario = new ArrayList<>();
    }

    public void adicionarNoInventario(Item item) {
        inventario.add(item);
        System.out.println(item.getNome() + " adicionado ao inventário.");
    }

    // Tenta equipar um item que está no inventário (deve ser ItemEquipavel)
    public boolean equiparItem(ItemEquipavel item) {
        ItemEquipavel.TipoEquipamento tipo = item.getTipo();

        if (!inventario.contains(item)) {
            System.out.println("Item não está no inventário.");
            return false;
        }

        // Se já existe um item equipado nesse slot, desequipa e coloca no inventário
        if (slotsEquipaveis.containsKey(tipo)) {
            ItemEquipavel itemAntigo = slotsEquipaveis.get(tipo);
            itemAntigo.desequipar();
            inventario.add(itemAntigo);
            System.out.println(itemAntigo.getNome() + " foi removido do slot " + tipo + " e devolvido ao inventário.");
        }

        // Remove o novo item do inventário e equipa
        inventario.remove(item);
        slotsEquipaveis.put(tipo, item);
        item.equipar();

        System.out.println(item.getNome() + " equipado no slot " + tipo + ".");
        return true;
    }

    // Desequipa item do slot e coloca no inventário
    public boolean desequiparItem(ItemEquipavel.TipoEquipamento tipo) {
        if (!slotsEquipaveis.containsKey(tipo)) {
            System.out.println("Nenhum item equipado no slot " + tipo + ".");
            return false;
        }

        ItemEquipavel item = slotsEquipaveis.remove(tipo);
        item.desequipar();
        inventario.add(item);

        System.out.println(item.getNome() + " desequipado e colocado no inventário.");
        return true;
    }

    // Lista todos os itens do inventário
    public void listarItensInventario() {
        System.out.println("Itens no inventario:");
        if (inventario.isEmpty()) {
            System.out.println("  (Inventario vazio)");
            return;
        }
        for (Item item : inventario) {
            System.out.println(" - " + item.getNome() + " (x" + item.getQuantidade() + ")");
        }
    }

    // Mostra os itens equipados
    public void listarItensEquipados() {
        System.out.println("Itens equipados:");
        if (slotsEquipaveis.isEmpty()) {
            System.out.println("  (nenhum item equipado)");
            return;
        }
        for (ItemEquipavel.TipoEquipamento tipo : ItemEquipavel.TipoEquipamento.values()) {
            ItemEquipavel item = slotsEquipaveis.get(tipo);
            if (item != null) {
                System.out.println(" - " + tipo + ": " + item.getNome());
            } else {
                System.out.println(" - " + tipo + ": (vazio)");
            }
        }
    }
}
