package dio.com;

public abstract class Conta implements IConta {

    private static int SEQUENCIAL = 1;
    private static final int AGENCIA_PADRAO = 1;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = Conta.AGENCIA_PADRAO;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;

    }

    public int getAgencia() {
        return agencia;
    }

    public int getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    @Override
    public void sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            this.imprimirInformacoes();
            System.out.println("SAQUE EFETUADO...\n");
        } else System.out.println(String.format("VALOR INVALIDO... SEU SALDO: R$%.2f\n", this.saldo));
    }

    @Override
    public void depositar(double valor) {
        this.saldo += valor;
        this.imprimirInformacoes();
        System.out.println("DEPOSITO EFETUADO...\n");
    }

    @Override
    public void transferir(double valor, Conta contaDestino) {
        if (this.saldo >= valor) {
            this.sacar(valor);
            contaDestino.depositar(valor);
        } else System.out.println(String.format("VALOR INVALIDO... SEU SALDO: R$%.2f\n", this.saldo));
    }

    protected void imprimirInformacoes() {
        System.out.println(String.format("TITULAR: %s\t\tAGENCIA: %d\t\tNUMERO: %d\t\tSALDO: R$%.2f", this.cliente.getNome(), this.agencia, this.numero, this.saldo));
    }
}
