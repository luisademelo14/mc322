import java.util.Random;

public class Sinistro {

    private final int id;
    private String data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;

    public Sinistro(int id, String data, String endereco, Seguradora seguradora, Veiculo veiculo, 
    Condutor condutor, Seguro seguro){
        this.id = id;
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Condutor getCondutor() {
        return condutor;
    }

    public void setCondutor(Condutor condutor) {
        this.condutor = condutor;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public int getId() {
        return id;
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
        + seguro.toString() + condutor.toString();

        return str;
    }
    
    
}
