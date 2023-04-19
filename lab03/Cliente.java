import java.util.ArrayList;

public class Cliente {
    private String nome;
    private String endereco;
    private ArrayList <Veiculo> listaVeiculos;

    // funcao construtora da classe Cliente
    public Cliente(String nome, String endereco, ArrayList <Veiculo> listaVeiculos){
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
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


    // imprimir as informacoes do cliente
    @Override
    public String toString(){

        String str = "Nome do cliente: " + nome + "\n"
        + "Endereco do cliente: " + endereco + "\n";

        return str;
    }
    
}

