import Classes.Classe;
import Classes.Guerreiro;
import Classes.Mago;
import Classes.Arqueiro;
import Classes.Ladino;
import Classes.Paladino;

import java.io.*;

public class Jogo {
    public static void main(String[] args) throws Exception {


        // Introdução narrativa
        /*System.out.println("🔥 Introdução: O Herdeiro da Oitava Chama\n");
        pause(2000);
        System.out.println("O vento das montanhas ainda traz o cheiro de cinzas antigas.");
        pause(2000);
        System.out.println("Séculos atrás, o Reino das Sete Chamas era uma terra unida, sete tronos governando sob o juramento dos dragões.");
        pause(2000);
        System.out.println("Cada chama representava uma virtude: coragem, sabedoria, lealdade, justiça, honra, fé e poder.");
        pause(3000);
        System.out.println("Mas quando o último dragão tombou, as chamas se apagaram… e com elas veio a guerra. \n");
        pause(3000);
        System.out.println("Você nasceu nas Terras de Cinábria, o menor e mais esquecido dos sete reinos.");
        pause(2500);
        System.out.println("Filho de um ferreiro e uma sacerdotisa exilada, cresceu ouvindo histórias sobre os dragões e sobre a antiga união dos povos.");
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
        System.out.println("Movido por coragem, ou talvez por um chamado que não consegue explicar, você parte em jornada.");
        pause(2000);
        System.out.println("Seu objetivo é descobrir quem realmente é, enfrentar perigos inimagináveis e tentar reunir os fragmentos antes que o mundo seja consumido pelo fogo da guerra.");
        pause(3000);
        System.out.println("No coração do continente, entre cinzas, traições e antigas profecias,");
        pause(2000);
        System.out.println("você deve escolher: reunir os fragmentos e reacender as Sete Chamas… ou deixar que o mundo queime de vez.\n");
        pause(3000);*/

        // Escolha do nome
        System.out.print("Antes de partir, diga-nos seu nome, herói: ");
        String nome;
        nome = Teclado.getUmString();

        System.out.println("Muito bem, " + nome + ". Sua jornada começa agora.\n");
        pause(500);

        // Escolha de raça
        System.out.println("=== Escolha sua raça ===");
        System.out.println("1 - Humano");
        System.out.println("2 - Elfo");
        System.out.println("3 - Orc");
        System.out.println("4 - Anão");
        System.out.print("Opção: ");
        byte opcaoRaca = 0;
        try {
            opcaoRaca = Teclado.getUmByte();

        } catch (NumberFormatException e) {
        } catch (Exception e) {
            System.out.println("Opção inválida! Selecionado Humano por padrão.");
            opcaoRaca = -1;

        }

        Raca racaEscolhida;
        switch (opcaoRaca) {
            case -1 -> racaEscolhida = Raca.HUMANO();
            case 1 -> racaEscolhida = Raca.HUMANO();
            case 2 -> racaEscolhida = Raca.ELFO();
            case 3 -> racaEscolhida = Raca.ORC();
            case 4 -> racaEscolhida = Raca.ANAO();
            default -> racaEscolhida = Raca.HUMANO();

        }

        // Escolha de classe
        System.out.println("\n=== Escolha sua classe ===");
        System.out.println("1 - Guerreiro");
        System.out.println("2 - Mago");
        System.out.println("3 - Arqueiro");
        System.out.println("4 - Ladino");
        System.out.println("5 - Paladino");
        System.out.print("Opção: ");
        byte opcClasse;
        try{
            opcClasse = Teclado.getUmByte();
        }catch(Exception e){
            opcClasse = -1;
            System.out.println("Opção inválida! Selecionado Guerreiro por padrão.");
        }

        Classe classeEscolhida;
        switch (opcClasse) {
            case -1 -> classeEscolhida = new Guerreiro();
            case 1 -> classeEscolhida = new Guerreiro();
            case 2 -> classeEscolhida = new Mago();
            case 3 -> classeEscolhida = new Arqueiro();
            case 4 -> classeEscolhida = new Ladino();
            case 5 -> classeEscolhida = new Paladino();
            default -> {
                classeEscolhida = new Guerreiro();
            }
        }

        // Criação do personagem
        Personagem p1 = new Personagem(nome, racaEscolhida, classeEscolhida);


        // Criação dos inimigos
        SubClasseInimigo goblin = SubClasseInimigo.GOBLIN(1);
        SubClasseInimigo esqueleto = SubClasseInimigo.ESQUELETO(2);
        SubClasseInimigo magoNegro = SubClasseInimigo.MAGO_NEGRO(2);

        //Dragão Ancião - BOSS
        SubClasseInimigo drakthor = SubClasseInimigo.DRAGAO_ANCIAO(3);

        //Fragmento

        Item fragmentoDeDrakthor = Item.FRAGMENTO_DE_DRAKTHOR;


        //Criação dos itens Equipaveis
        ItemEquipavel espadaDeFerro = ItemEquipavel.ESPADA_DE_FERRO;
        ItemEquipavel cajadoSimples = ItemEquipavel.CAJADO_SIMPLES;
        ItemEquipavel arcoDeMadeira = ItemEquipavel.ARCO_DE_MADEIRA;
        ItemEquipavel adagaDeFerro = ItemEquipavel.ADAGA_DE_FERRO;
        ItemEquipavel clavaDeFerro = ItemEquipavel.CLAVA_DE_FERRO;

        ItemEquipavel peitoralDeFerro = ItemEquipavel.PEITORAL_DE_FERRO;
        ItemEquipavel capaceteDeCouro = ItemEquipavel.CAPACETE_DE_COURO;

        //Criação de Itens Consumiveis
        ItemConsumivel PocaoDeCura = ItemConsumivel.POCAO_CURA();
        ItemConsumivel PocaoDeMana = ItemConsumivel.POCAO_MANA();

        //Iniciando o sistema de Combate
        SistemaCombateTurnos combate1 = new SistemaCombateTurnos();
        SistemaCombateTurnos combate2 = new SistemaCombateTurnos();
        SistemaCombateTurnos combate3 = new SistemaCombateTurnos();
        combate1.explicarRegras(p1, goblin);
        boolean f = combate1.batalhar(p1, goblin);

        if(f == false){
            // Drop de arma baseado na classe do jogador
            Classe classeJogador = p1.getClasse();
            ItemEquipavel drop = null;

            if (classeJogador instanceof Guerreiro) {
                drop = espadaDeFerro;
            } else if (classeJogador instanceof Mago) {
                drop = cajadoSimples;
            } else if (classeJogador instanceof Arqueiro) {
                drop = arcoDeMadeira;
            } else if (classeJogador instanceof Ladino) {
                drop = adagaDeFerro;
            } else if (classeJogador instanceof Paladino) {
                drop = clavaDeFerro;
            }

            if (drop != null) {
                System.out.println("\nO goblin dropou: " + drop.getNome() + "!");
                p1.inventario.adicionarNoInventario(drop);
            }

            System.out.println("\nO goblin dropou: " + peitoralDeFerro.getNome() + "!");
            p1.inventario.adicionarNoInventario(peitoralDeFerro);

            System.out.println("\nO goblin dropou: " + capaceteDeCouro.getNome() + "!");
            p1.inventario.adicionarNoInventario(capaceteDeCouro);

            System.out.println("\n======================================================================== ");
            System.out.println("Parabéns por derrotar o goblin, agora equipe os itens que você ganhou!!! ");
            System.out.println("======================================================================== ");
        }

        Personagem savePoint = new Personagem(p1);
        System.out.println("\n💾 Ponto de controle salvo! Se você cair em batalha, voltará a este estado.\n");

        menuPrincipal(p1);

        boolean v = false;



        System.out.println("\nVocê decide explorar os arredores...");
        Thread.sleep(1000);
        System.out.println("Após alguns minutos de caminhada, você chega a uma encruzilhada misteriosa.");
        Thread.sleep(1000);
        System.out.println("À esquerda, o caminho é estreito e coberto por névoa.");
        Thread.sleep(1000);
        System.out.println("À direita, você vê uma trilha que leva até uma colina rochosa.");
        Thread.sleep(1000);

        while (!v){
            byte escolha = 0;
            while (escolha != 1 && escolha != 2) {
                System.out.println("\nO que você deseja fazer?");
                System.out.println("1 - Seguir pela esquerda");
                System.out.println("2 - Seguir pela direita");
                System.out.print("Escolha: ");

                try{
                    escolha = Teclado.getUmByte();
                }catch(Exception e){
                    escolha = -1;
                }

                try {
                    if(escolha == -1){
                        System.out.println("Opção invalida, tente novamente!");
                        continue;
                    }

                    if (escolha == 1) {
                        System.out.println("\nVocê segue pela esquerda...");
                        Thread.sleep(1000);
                        System.out.println("De repente, o chão cede sob seus pés!");
                        Thread.sleep(1000);
                        System.out.println("Você caiu em uma armadilha! Perde alguns pontos de vida.");
                        p1.setVidaAtual(p1.getVidaAtual() - 20);
                        Thread.sleep(1000);
                        System.out.println("\nVocê se levanta com dificuldade, ainda sentindo a dor da queda...");
                        Thread.sleep(1000);
                        System.out.println("Ao olhar ao redor, percebe que caiu em uma antiga masmorra subterrânea.");
                        Thread.sleep(1000);
                        System.out.println("Do fundo do corredor, você ouve o som de ossos se movendo...");
                        Thread.sleep(1000);
                        System.out.println("Um Esqueleto!!!");
                        Thread.sleep(1000);
                        f = combate2.batalhar(p1, esqueleto);

                        if(f == false){

                            if (!p1.estaVivo()) {
                                System.out.println("\nVocê foi derrotado pelo Esqueleto...");
                                Thread.sleep(1000);
                                System.out.println("Mas uma força misteriosa o traz de volta ao último ponto de controle!");
                                Thread.sleep(1000);

                                // Restaura o personagem do save point
                                p1 = (Personagem) savePoint.clone();

                                System.out.println("\nVocê retornou ao seu estado anterior!");
                                p1.exibirStatus();
                                Thread.sleep(1500);
                                continue;
                            } else {
                                v = true;
                            }
                            System.out.println("\nO Esqueleto dropou: 4x - " + PocaoDeCura.getNome() + "!");
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            System.out.println("\nO Esqueleto dropou: " + PocaoDeMana.getNome() + "!");
                            p1.inventario.adicionarNoInventario(PocaoDeMana);
                            Thread.sleep(1000);
                            System.out.println("\nCom o esqueleto derrotado, você encontra uma escada antiga levando de volta à superfície.");
                            Thread.sleep(1000);
                            System.out.println("Ao subir, o sol volta a tocar seu rosto!");
                            Thread.sleep(1000);
                            System.out.println("Revigorado, você decide continuar sua jornada...\n");

                            System.out.println("\n======================================================================== ");
                            System.out.println("Utilize a poção de cura que você ganhou para restaurar sua vida!");
                            System.out.println("======================================================================== ");
                            break;
                        }

                    } else if (escolha == 2) {
                        System.out.println("\nVocê segue pela direita...");
                        Thread.sleep(1000);
                        System.out.println("A trilha leva até a entrada de uma caverna escura e silenciosa.");
                        Thread.sleep(1000);
                        System.out.println("Talvez haja algo valioso lá dentro...");
                        Thread.sleep(1000);
                        System.out.println("Você entra na caverna cautelosamente.");
                        Thread.sleep(1000);
                        System.out.println("De repente, uma figura sombria surge à sua frente!");
                        Thread.sleep(1000);
                        System.out.println("É um Mago Negro!!!");
                        Thread.sleep(1000);
                        f = combate2.batalhar(p1, magoNegro);

                        if(f == false){

                            if (!p1.estaVivo()) {
                                System.out.println("\nVocê foi derrotado pelo Mago Negro...");
                                Thread.sleep(1000);
                                System.out.println("Mas uma força misteriosa o traz de volta ao último ponto de controle!");
                                Thread.sleep(1000);

                                // Restaura o personagem do save point
                                p1 = (Personagem) savePoint.clone();

                                System.out.println("\nVocê retornou ao seu estado anterior!");
                                p1.exibirStatus();
                                Thread.sleep(1500);
                                continue;

                            } else {
                                v = true;
                            }
                            System.out.println("\nO Mago Negro dropou: 4x - " + PocaoDeCura.getNome() + "!");
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            p1.inventario.adicionarNoInventario(PocaoDeCura);
                            System.out.println("\nO Mago Negro dropou: " + PocaoDeMana.getNome() + "!");
                            p1.inventario.adicionarNoInventario(PocaoDeMana);
                            Thread.sleep(1000);
                            System.out.println("\nCom o Mago Negro derrotado, o silêncio retorna à caverna.");
                            Thread.sleep(1000);
                            System.out.println("Você respira fundo e decide sair dali antes que algo pior apareça.");
                            Thread.sleep(1000);
                            System.out.println("Após sua vitória, você decide continuar sua jornada...\n");

                            System.out.println("\n======================================================================== ");
                            System.out.println("Utilize a poção de cura que você ganhou para restaurar sua vida!");
                            System.out.println("======================================================================== ");
                            break;
                        }
                    }

                } catch (NumberFormatException e) {
                    System.out.println("\nEntrada inválida! Digite apenas números.");
                }

            }
        }



        Personagem savePoint2 = new Personagem(p1);
        System.out.println("\n💾 Ponto de controle salvo! Se você cair em batalha, voltará a este estado.\n");

        menuPrincipal(p1);



        System.out.println("Após muito andar");
        Thread.sleep(1000);
        System.out.println("Você sente o chão tremer...");
        Thread.sleep(1000);
        System.out.println("Das sombras surge uma criatura colossal, coberta por escamas antigas e olhos flamejantes.");
        Thread.sleep(1000);
        System.out.println("É " + drakthor.getNome() + ", um dos dragões antigos que moldaram a história deste mundo!");
        Thread.sleep(1000);
        System.out.println("Seu rugido ecoa pelas montanhas, anunciando que a batalha será lendária...");
        Thread.sleep(1000);

        boolean vitoria = false;

        while (!vitoria) {
            f = combate3.batalhar(p1, drakthor);
            if(f == true){
                Thread.sleep(1500);
                System.out.println("Você não pode fugir!!\nEnfrente seu inimigo!!");
                continue;
            }
            if (!p1.estaVivo()) {
                System.out.println("\nVocê foi derrotado pelo " + drakthor.getNome() + "...");
                Thread.sleep(1000);
                System.out.println("Mas uma força misteriosa o traz de volta ao último ponto de controle!");
                Thread.sleep(1000);

                // Restaura o personagem do save point
                p1 = (Personagem) savePoint2.clone();

                System.out.println("\nVocê retornou ao seu estado anterior!");
                p1.exibirStatus();
                Thread.sleep(1500);

                // Reinicia o dragão (para a luta recomeçar do zero)
                drakthor = SubClasseInimigo.DRAGAO_ANCIAO(3);
                System.out.println("\nO ar treme novamente... " + drakthor.getNome() + " ressurgiu das sombras!");
                Thread.sleep(1500);
            } else {
                vitoria = true;
            }
        }


        Thread.sleep(1000);
        System.out.println("\n======================================================================== ");
        System.out.println("Parabéns! Agora você ganhou um fragmento dos 7 que pertencem a este mundo!");
        System.out.println("======================================================================== ");
        Thread.sleep(1000);

        System.out.println("\nVocê encontrou: " + fragmentoDeDrakthor.getNome() + "!");
        System.out.println(fragmentoDeDrakthor.getDescricao());
        p1.inventario.adicionarNoInventario(fragmentoDeDrakthor);

        Thread.sleep(2000);
        System.out.println("\n======================================================================== ");
        System.out.println("Parabéns! Você completou o tutorial!");
        System.out.println("======================================================================== ");
        Thread.sleep(1000);
        System.out.println("\nAgora sua jornada realmente começa...");
        Thread.sleep(1000);
        System.out.println("Você deve explorar os outros reinos e enfrentar desafios lendários");
        Thread.sleep(1000);
        System.out.println("para encontrar os demais fragmentos que pertencem a este mundo!");
        Thread.sleep(1000);
        System.out.println("\nPrepare-se para novas aventuras e inimigos ainda mais poderosos!");
        Thread.sleep(1000);










    }

    // Pausa para narrativa
    private static void pause(int millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    //Menu Principal
    public static void menuPrincipal(Personagem jogador) throws IOException, InterruptedException {
        byte opcao = 0;
        byte escolha;


        while (opcao != 4) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1 - Explorar");
            System.out.println("2 - Inventário");
            System.out.println("3 - Ver status");
            System.out.println("4 - Sair do jogo");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Teclado.getUmByte();
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número de 1 a 4.");
                continue;
            } catch (Exception e) {
                opcao = -1;
            }
            try{
                if (opcao == -1) {
                    System.out.println("Opção incorreta, tente novamente!");
                    continue;
                }
            }catch (Exception e){}

            switch (opcao) {
                case 1:
                    return;
                case 2:
                    System.out.println("Abrindo inventário...");
                    jogador.inventario.listarItensEquipados();
                    jogador.inventario.listarItensInventario();
                    while (true) {
                        System.out.print("Digite o número do item que deseja usar (Digite 0 para voltar): ");

                        try{
                            escolha = Teclado.getUmByte();
                        }catch (Exception e){
                            escolha = -1;
                        }

                        try{
                            if (escolha == 0) {
                                break;
                            }

                            if(escolha == -1){
                                System.out.println("Opção invalida, digite novamente!");
                                continue;
                            }

                            Item itemEscolhido = jogador.inventario.getItemPorId(escolha);
                            if (itemEscolhido == null) {
                                System.out.println("Item não disponível! Escolha um número válido.");
                                continue;
                            }

                            if (itemEscolhido instanceof ItemEquipavel) {
                                jogador.equiparItem((ItemEquipavel) itemEscolhido);
                            }
                            else if (itemEscolhido instanceof ItemConsumivel) {
                                jogador.usarItem(itemEscolhido); // já existe essa função no jogador
                            }
                            else {
                                System.out.println("Esse item não é equipável ou consumível.");
                            }

                            break;
                        }catch (Exception e){}
                    }
                    continue;

                case 3:
                    System.out.println("Mostrando status do personagem...");
                    jogador.exibirStatus();
                    break;
                case 4:
                    System.out.println("Saindo do jogo. Até a próxima aventura!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }

    }

}
