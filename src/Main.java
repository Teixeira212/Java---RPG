public class Main {
    public static void main(String[] args) {
        Raca humano = new Raca("Humano", 5, 2, 2, 5, 5);
        Classe guerreiro = new Classe("Guerreiro", 100, 15, 10, 0, 20);

        Personagem p1 = new Personagem("Arthas", humano, guerreiro){};
        p1.exibirStatus();
        p1.ganharExperiencia(10000);
        p1.exibirStatus();

    }
}