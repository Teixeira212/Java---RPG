import java.io.*;


public class SistemaCombateTurnos {
    private int turno;;

    public SistemaCombateTurnos() {
        this.turno = 1;

    }

    private int mostrarMenu(Personagem jogador, SubClasseInimigo inimigo) throws IOException {

        while (true) {
            System.out.println("\n===========================");
            System.out.println("--------- TURNO " + turno + " ---------");
            System.out.println("===========================");
            System.out.println("Vida Jogador: " + jogador.getVidaAtual() + "/" + jogador.getVidaMaxima());
            System.out.println("Vida Inimigo: " + inimigo.getVidaAtual() + "/" + inimigo.getVidaMaxima());
            System.out.println("Escolha uma ação:");
            System.out.println("1 - Atacar");
            System.out.println("2 - Inventário");
            System.out.println("3 - Esquivar");
            System.out.println("4 - Fugir");

            byte escolha = 0;
            while (escolha < 1 || escolha > 4) {
                System.out.print("Digite o número da ação: ");
                try {
                    escolha = Teclado.getUmByte();
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Digite um número de 1 a 4.");
                } catch (Exception e) {
                    escolha = -1;
                }

                try{
                    if(escolha == -1){
                        System.out.println("Opção incorreta, tente novamente!");
                        continue;
                    }
                }catch (Exception e){
                }
            }
            return escolha;
        }

    }

    // Explicar o sistema de combate
    public void explicarRegras(Personagem jogador, SubClasseInimigo inimigo) {
        System.out.println("\n============================================");
        System.out.println("Bem-vindo ao combate!");
        System.out.println("Você está prestes a enfrentar: " + inimigo.getNome());
        System.out.println("Vida do jogador: " + jogador.getVidaAtual() + "/" + jogador.getVidaMaxima());
        System.out.println("Vida do inimigo: " + inimigo.getVidaAtual() + "/" + inimigo.getVidaMaxima());
        System.out.println("\nComo funciona o combate:");
        System.out.println("- Cada turno, você e o inimigo rolam um dado para aumentar o ataque.");
        System.out.println("- Opções de ação do jogador:");
        System.out.println("  1 - Atacar: causa dano ao inimigo baseado em ataque + dado - defesa do inimigo.");
        System.out.println("  2 - Inventário: usar itens do inventário (poções de cura, mana, etc.).");
        System.out.println("  3 - Esquivar: tenta evitar o ataque do inimigo (chance depende do dado).");
        System.out.println("  4 - Fugir: tenta escapar da batalha. Se falhar, o inimigo atacará.");
        System.out.println("- A batalha termina quando a vida de um dos dois chega a 0.");
        System.out.println("Boa sorte!");
        System.out.println("============================================\n");
    }


    // Combate em turnos
    public void batalhar(Personagem jogador, SubClasseInimigo inimigo) throws Exception {
        System.out.println("\n============================================");
        System.out.println("Início do combate entre " + jogador.getNome() + " e " + inimigo.getNome() + "!");
        System.out.println("============================================");

        Dado dado = new Dado();



        while (jogador.estaVivo() && inimigo.estaVivo()) {
            int escolha = mostrarMenu(jogador, inimigo);
            int dadoJogador = dado.rolar();
            int dadoInimigo = dado.rolar();

            switch (escolha) {
                case 1: // Atacar

                    System.out.println(jogador.getNome() + " rolou: " + dadoJogador);
                    System.out.println(inimigo.getNome() + " rolou: " + dadoInimigo);


                    int dano = Math.max(0, (jogador.getAtaque() + dadoJogador) - inimigo.getDefesa());
                    System.out.println(jogador.getNome() + " atacou e causou " + dano + " de dano!");
                    inimigo.receberDano(dano, jogador);


                    if(inimigo.estaVivo()){
                        int dano2 = Math.max(0, (inimigo.getAtaque() + dadoInimigo) - jogador.getDefesa());
                        jogador.receberDano(dano2);
                        System.out.println(inimigo.getNome() + " atacou e causou " + dano2 + " de dano!");
                    }
                    break;

                case 2: // Inventário
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
                                 jogador.inventario.equiparItem((ItemEquipavel) itemEscolhido);
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


                case 3: // Esquivar
                    int chance = dado.rolar();
                    if (chance > 13) {
                        System.out.println(jogador.getNome() + " esquivou com sucesso e evita o ataque!");
                    } else {
                        dano = Math.max(0, inimigo.getAtaque() - jogador.getDefesa());
                        jogador.receberDano(dano);
                        System.out.println("Falhou em esquivar! " + inimigo.getNome() + " atacou e causou " + dano + " de dano!");
                    }
                    break;

                case 4: // Fugir

                    if(dadoJogador < 10){
                        System.out.println(jogador.getNome() + " fugiu da batalha!");
                        return; // Sai do combate
                    }
                    else{
                        dano = Math.max(0, inimigo.getAtaque() - jogador.getDefesa());
                        jogador.receberDano(dano);
                        System.out.println("Falhou em fugir! " + inimigo.getNome() + " atacou e causou " + dano + " de dano!");
                    }

            }

            turno++; // Incrementa turno
            System.out.println("===============================================");
        }



        // Resultado final (caso jogador vença, experiência já foi atribuída dentro do método do inimigo)
        if (jogador.estaVivo()) {
            System.out.println(jogador.getNome() + " venceu a batalha!");
        }
    }




}
