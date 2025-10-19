import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Escolha sua raça ===");
        System.out.println("1 - Humano");
        System.out.println("2 - Elfo");
        System.out.println("3 - Orc");
        System.out.println("4 - Anão");
        System.out.print("Opção: ");
        int opcaoRaca = sc.nextInt();

        Raca racaEscolhida;
        switch (opcaoRaca) {
            case 1 -> racaEscolhida = Raca.HUMANO();
            case 2 -> racaEscolhida = Raca.ELFO();
            case 3 -> racaEscolhida = Raca.ORC();
            case 4 -> racaEscolhida = Raca.ANAO();
            default -> {
                System.out.println("Opção inválida! Selecionado Humano por padrão.");
                racaEscolhida = Raca.HUMANO();
            }
        }


        System.out.println("\n=== Escolha sua classe ===");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Ladino");
        System.out.println("5 - Paladino");
        System.out.print("Opção: ");
        int opcClasse = sc.nextInt();

        Classe classeEscolhida;
        switch (opcClasse) {
            case 1 -> classeEscolhida = Classe.GUERREIRO();
            case 2 -> classeEscolhida = Classe.MAGO();
            case 3 -> classeEscolhida = Classe.ARQUEIRO();
            case 4 -> classeEscolhida = Classe.LADINO();
            case 5 -> classeEscolhida = Classe.PALADINO();
            default -> {
                System.out.println("Opção inválida! Selecionado Guerreiro por padrão.");
                classeEscolhida = Classe.GUERREIRO();
            }
        }


        Personagem p1 = new Personagem("Arthas", racaEscolhida, classeEscolhida);
        p1.ganharExperiencia(100);
        p1.exibirStatus();

        Inimigo goblin = Inimigo.GOBLIN();
        goblin.exibirStatus();
        goblin.receberDano(1000, p1);
        p1.receberDano(1000);
        goblin.exibirStatus();

        p1.exibirStatus();

        sc.close();
    }
}