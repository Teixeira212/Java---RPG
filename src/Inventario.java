import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public class Inventario implements Cloneable {

    // Mapa que relaciona cada tipo de equipamento com o item equipado
    private EnumMap<ItemEquipavel.TipoEquipamento, ItemEquipavel> slotsEquipaveis;

    // Inventario onde ficam todos os itens (equipáveis e outros)
    private List<Item> inventario;

    public Inventario() {
        slotsEquipaveis = new EnumMap<>(ItemEquipavel.TipoEquipamento.class);
        inventario = new ArrayList<>();
    }

    public void adicionarNoInventario(Item item) {

        for (Item itemInv: inventario){
            if (itemInv.equals(item)){
                itemInv.setQuantidade(itemInv.getQuantidade() + 1);

                return;
            }
        }
        inventario.add(item);
        System.out.println(item.getNome() + " adicionado ao inventário.");
    }

    // Tenta equipar um item que está no inventário (deve ser ItemEquipavel)
    public ItemEquipavel equiparItem(ItemEquipavel item) {
        ItemEquipavel.TipoEquipamento tipo = item.getTipo();

        if (!inventario.contains(item)) {
            System.out.println("Item não está no inventário.");
            return null;
        }

        // Se já existe um item equipado nesse slot, desequipa e coloca de volta no inventário
        ItemEquipavel itemAntigo = slotsEquipaveis.put(tipo, item);
        if (itemAntigo != null) {
            itemAntigo.desequipar();
            inventario.add(itemAntigo);
            System.out.println(itemAntigo.getNome() + " foi removido do slot " + tipo + ".");
        }

        // Remove do inventário e equipa
        inventario.remove(item);
        item.equipar();

        System.out.println(item.getNome() + " equipado no slot " + tipo + ".");
        return itemAntigo; // retorna o item antigo (pode ser null)
    }

    // Desequipa item do slot e coloca no inventário
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

    @Override
    public Object clone() {
        Inventario ret = null;
        try {
            ret = new Inventario();
        } catch (Exception erro) {}
        return ret;
    }


}
