package dio.com;

import java.util.ArrayList;
import java.util.List;

public class Banco {
    private String nome;
    private List<Conta> contas = new ArrayList<Conta>();

    public Banco(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
        conta.imprimirInformacoes();
        System.out.println("CONTA ADICIONADA...\n");
    }

    public void imprimirContas() {
        for (Conta conta : contas) {
            conta.imprimirInformacoes();
        }
        System.out.println("\n");
    }

    public Conta procurarConta(int num) {
        for (Conta conta : contas) {
            if (conta.numero==num) return conta;
        }
        return null;
    }
}