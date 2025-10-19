import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        /*
        // Introdução narrativa
        System.out.println("🔥 Introdução: O Herdeiro da Oitava Chama\n");
        pause(2000);
        System.out.println("O vento das montanhas ainda traz o cheiro de cinzas antigas.");
        pause(2000);
        System.out.println("Séculos atrás, o Reino das Sete Chamas era uma terra unida — sete tronos governando sob o juramento dos dragões.");
        pause(2000);
        System.out.println("Cada chama representava uma virtude: coragem, sabedoria, lealdade, justiça, honra, fé e poder.");
        pause(3000);
        System.out.println("Mas quando o último dragão tombou, as chamas se apagaram… e com elas veio a guerra.\n");
        pause(3000);
        System.out.println("Você nasceu nas Terras de Cinábria, o menor e mais esquecido dos sete reinos.");
        pause(2500);
        System.out.println("Filho (ou filha) de um ferreiro e uma sacerdotisa exilada, cresceu ouvindo histórias sobre os dragões e sobre a antiga união dos povos.");
        pause(3000);
        System.out.println("O que poucos sabem é que, mesmo após sua queda, alguns dragões antigos ainda permanecem ocultos nos reinos, cada um guardando um fragmento de um poder ancestral.");
        pause(3000);
        System.out.println("Reunindo todos, dizem, seria possível pôr fim à guerra que assola o mundo.\n");
        pause(3000);
        System.out.println("Enquanto os reinos se preparam para mais uma batalha pelo trono, rumores correm entre viajantes e mercadores:");
        pause(2500);
        System.out.println("dragões antigos ainda dormem em lugares esquecidos, protegendo seus fragmentos.");
        pause(2500);
        System.out.println("E aqueles que forem capazes de encontrá-los terão uma chance de mudar o destino de todos os povos.\n");
        pause(3000);
        System.out.println("Movido por coragem — ou talvez por um chamado que não consegue explicar —, você parte em jornada.");
        pause(2000);
        System.out.println("Seu objetivo é descobrir quem realmente é, enfrentar perigos inimagináveis e tentar reunir os fragmentos antes que o mundo seja consumido pelo fogo da guerra.");
        pause(3000);
        System.out.println("No coração do continente, entre cinzas, traições e antigas profecias,");
        pause(2000);
        System.out.println("você deve escolher: reunir os fragmentos e reacender as Sete Chamas… ou deixar que o mundo queime de vez.\n");
        pause(3000);*/

        // Escolha do nome
        System.out.print("Antes de partir, diga-nos seu nome, herói: ");
        String nome = reader.readLine();
        System.out.println("Muito bem, " + nome + ". Sua jornada começa agora.\n");
        pause(500);

        // Escolha de raça
        System.out.println("=== Escolha sua raça ===");
        System.out.println("1 - Humano");
        System.out.println("2 - Elfo");
        System.out.println("3 - Orc");
        System.out.println("4 - Anão");
        System.out.print("Opção: ");
        int opcaoRaca = Integer.parseInt(reader.readLine());

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

        // Escolha de classe
        System.out.println("\n=== Escolha sua classe ===");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Ladino");
        System.out.println("5 - Paladino");
        System.out.print("Opção: ");
        int opcClasse = Integer.parseInt(reader.readLine());

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

        // Criação do personagem
        Personagem p1 = new Personagem(nome, racaEscolhida, classeEscolhida);
        p1.ganharExperiencia(300);
        p1.exibirStatus();

        // Exemplo de inimigo e combate
        Inimigo goblin = Inimigo.GOBLIN();
        goblin.exibirStatus();


        SistemaCombateTurnos combate = new SistemaCombateTurnos();
        combate.batalhar(p1, goblin);
    }

    // Pausa para narrativa
    private static void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }
}
