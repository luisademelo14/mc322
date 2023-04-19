import java.util.Random;

public class Sinistro {

    private final int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;

    // funcao construtora da classe Sinistro
    public Sinistro(int id, String data, String endereco, Cliente cliente, Seguradora seguradora, Veiculo veiculo){
        this.id = id;
        this.data = data;
        this.endereco = endereco;
        this.cliente = cliente;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
    }

    // getters e setters

    public int getId(){
        return id;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public Seguradora getSeguradora() {
        return seguradora;
    }

    public void setSeguradora(Seguradora seguradora) {
        this.seguradora = seguradora;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    
    protected static int GeraId() {
        Random random = new Random();
        
        int id_gerado = random.nextInt();
        
        return id_gerado;
        
    }
    
    // imprimir as informacoes do sinistro
    @Override
    public String toString(){

        String str = "ID do sinistro: " + id + "\n"
        + "Data do sinistro: " + data + "\n"
        + "Endereco do sinistro: " + endereco + "\n"
        + veiculo.toString() + seguradora.toString() + cliente.toString();

        return str;
    }


}
