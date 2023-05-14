import java.util.ArrayList;

public abstract class Cliente {
    private String nome;
    private String endereco;
    protected double valorSeguro;
    private ArrayList <Veiculo> listaVeiculos;


    public Cliente(String nome, String endereco, ArrayList <Veiculo> listaVeiculos){
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
        this.valorSeguro = 0;
    }

    // getters e setters

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    public double getValorSeguro() {
        return valorSeguro;
    }

    public void setValorSeguro(double valorSeguro) {
        this.valorSeguro = valorSeguro;
    }

    protected double calculaScore(){
        return valorSeguro;
    }

    // imprimir as informacoes do cliente
    @Override
    public String toString(){

        String str = "Nome do cliente: " + nome + "\n"
        + "Endereco do cliente: " + endereco + "\n"
        + "Valor do seguro: " + valorSeguro + "\n";

        return str;
    }
    
}
