import java.util.Random;
import java.util.random.RandomGenerator;

public class Sinistro {

    private int id;
    private String data;
    private String endereco;

    // funcao construtora da classe Sinistro
    public Sinistro(int id, String data, String endereco){
        this.id = id;
        this.data = data;
        this.endereco = endereco;
    }

    // getters e setters

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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

    // criar um metodo que gera um id

    /*
    private void GeraId() {
        int id 
        
    }
     */
    


}
