import java.util.Random;
import java.io.*;


public class SistemaCombateTurnos {
    private int turno = 1;
    private BufferedReader reader;

    public SistemaCombateTurnos() {
        this.reader = new BufferedReader(new InputStreamReader(System.in));

    }

    private int mostrarMenu(Personagem jogador, Inimigo inimigo) throws IOException {
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

        int escolha = 0;
        while (escolha < 1 || escolha > 4) {
            System.out.print("Digite o número da ação: ");
            try {
                 String linha = reader.readLine();
                 escolha = Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite um número de 1 a 4.");
            }
        }
        return escolha;
    }


    // Combate em turnos
    public void batalhar(Personagem jogador, Inimigo inimigo) throws InterruptedException, IOException {
        System.out.println("\n============================================");
        System.out.println("Início do combate entre " + jogador.getNome() + " e " + inimigo.getNome() + "!");
        System.out.println("============================================");

        Dado dado = new Dado();



        while (jogador.estaVivo() && inimigo.estaVivo()) {
            int escolha = mostrarMenu(jogador, inimigo);

            switch (escolha) {
                case 1: // Atacar
                    int dadoJogador = dado.rolar();
                    int dadoInimigo = dado.rolar();
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
                    //TODO
                    continue;
                case 3: // Esquivar
                    int chance = dado.rolar();
                    if (chance > 13) {
                        System.out.println(jogador.getNome() + " esquivou com sucesso e evita o ataque!");
                    } else {
                        dano = Math.max(0, inimigo.getAtaque() - jogador.getDefesa());
                        jogador.receberDano(dano);
                        System.out.println("Falha na esquiva! " + inimigo.getNome() + " atacou e causou " + dano + " de dano!");
                    }
                    break;

                case 4: // Fugir
                    System.out.println(jogador.getNome() + " fugiu da batalha!");
                    return; // Sai do combate
            }

            turno++; // Incrementa turno
            System.out.println("===============================================");
            Thread.sleep(1000);
        }



        // Resultado final (caso jogador vença, experiência já foi atribuída dentro do método do inimigo)
        if (jogador.estaVivo()) {
            System.out.println(jogador.getNome() + " venceu a batalha!");
        }
    }

}
