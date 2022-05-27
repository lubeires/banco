package dio.com;

import java.util.Scanner;

public class Main {
    public void buscarConta() {

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Banco banco = new Banco("BANCO DIGITAL");
        System.out.println("=========== " + banco.getNome() + " ===========");

        Conta conta = null;
        int opcao, tipo;
        do {
            System.out.println("OPERACOES:\n1 - listar contas\n2 - criar conta\n3 - realizar transacao\n4 - sair");
            opcao = scan.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("\n----- CONTAS -----");
                    banco.imprimirContas();
                    break;
                case 2:
                    System.out.println("\n----- NOVA CONTA -----");
                    System.out.println("NOME DO TITULAR: ");
                    scan.nextLine();
                    Cliente cliente = new Cliente(scan.nextLine());

                    do {
                        System.out.println("TIPO DE CONTA:\n1 - corrente\t\t2 - poupanca");
                        tipo = scan.nextInt();

                        switch (tipo) {
                            case 1 -> conta = new ContaCorrente(cliente);
                            case 2 -> conta = new ContaPoupanca(cliente);
                            default -> System.out.println("DIGITE UMA OPCAO VALIDA...\n");
                        }
                    } while (tipo != 1 && tipo != 2);
                    banco.adicionarConta(conta);
                    break;
                case 3:
                    System.out.println("\n----- TRANSACAO -----");

                    int numConta;
                    conta = null;

                    while (conta == null) {
                        System.out.println("NUMERO DA CONTA:");
                        numConta = scan.nextInt();
                        conta = banco.procurarConta(numConta);
                        if (conta == null) System.out.println("DIGITE UM NUMERO DE CONTA VALIDO...\n");
                    }


                    do {
                        System.out.println("TIPO DE TRANSACAO:\n1 - deposito\t\t2 - saque\t\t3 - transferencia");
                        tipo = scan.nextInt();
                        if (tipo < 1 || tipo > 3) System.out.println("DIGITE UMA OPCAO VALIDA...\n");
                    } while (tipo < 1 || tipo > 3);

                    double valor;
                    System.out.println("VALOR (R$): ");
                    valor = scan.nextDouble();

                    switch (tipo) {
                        case 1:
                            conta.depositar(valor);
                            break;
                        case 2:
                            conta.sacar(valor);
                            break;
                        case 3:
                            int numContaDestino;
                            Conta contaDestino = null;

                            while (contaDestino == null) {
                                System.out.println("NUMERO DA CONTA DE DESTINO:");
                                numContaDestino = scan.nextInt();
                                contaDestino = banco.procurarConta(numContaDestino);
                                if (contaDestino == null)
                                    System.out.println("DIGITE UM NUMERO DE CONTA VALIDO...\n");
                            }

                            conta.transferir(valor, contaDestino);
                            break;
                    }
                    break;
                case 4:
                    System.out.println("ENCERRANDO...");
                    break;
                default:
                    System.out.println("DIGITE UMA OPCAO VALIDA...\n");
            }
        } while (opcao != 4);

    }
}
