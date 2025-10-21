import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public class Inventario {

    // Mapa que relaciona cada tipo de equipamento com o item equipado
    private EnumMap<ItemEquipavel.TipoEquipamento, ItemEquipavel> slotsEquipaveis;

    // Baú onde ficam todos os itens (equipáveis e outros)
    private List<Item> bau;

    public Inventario() {
        slotsEquipaveis = new EnumMap<>(ItemEquipavel.TipoEquipamento.class);
        bau = new ArrayList<>();
    }

    // Adiciona item no baú
    public void adicionarNoBau(Item item) {
        bau.add(item);
        System.out.println(item.getNome() + " adicionado ao baú.");
    }

    // Tenta equipar um item que está no baú (deve ser ItemEquipavel)
    public boolean equiparItem(ItemEquipavel item) {
        ItemEquipavel.TipoEquipamento tipo = item.getTipo();

        if (!bau.contains(item)) {
            System.out.println("Item não está no baú.");
            return false;
        }

        // Verifica se slot já está ocupado
        if (slotsEquipaveis.containsKey(tipo)) {
            System.out.println("Slot " + tipo + " já está ocupado.");
            return false;
        }

        // Remove do baú e adiciona no slot
        bau.remove(item);
        slotsEquipaveis.put(tipo, item);
        item.equipar();

        System.out.println(item.getNome() + " equipado no slot " + tipo + ".");
        return true;
    }

    // Desequipa item do slot e coloca no baú
    public boolean desequiparItem(ItemEquipavel.TipoEquipamento tipo) {
        if (!slotsEquipaveis.containsKey(tipo)) {
            System.out.println("Nenhum item equipado no slot " + tipo + ".");
            return false;
        }

        ItemEquipavel item = slotsEquipaveis.remove(tipo);
        item.desequipar();
        bau.add(item);

        System.out.println(item.getNome() + " desequipado e colocado no baú.");
        return true;
    }

    // Lista todos os itens do baú
    public void listarItensBau() {
        System.out.println("Itens no baú:");
        if (bau.isEmpty()) {
            System.out.println("  (baú vazio)");
            return;
        }
        for (Item item : bau) {
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
