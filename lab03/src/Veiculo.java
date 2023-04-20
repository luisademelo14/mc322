
public class Veiculo {
    private String placa;
    private String marca;
    private String modelo;
    private int anoFabricacao;

    // funcao construtora da classe Veiculo
    public Veiculo(String placa, String marca, String modelo, int anoFabricacao){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.anoFabricacao = anoFabricacao;
    }

    public String getPlaca(){
        return placa;
    }

    public void setPlaca(String placa){
        this.placa = placa;
    }

    public String getMarca(){
        return marca;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getModelo(){
        return modelo;
    }

    public void setModelo(String modelo){
        this.modelo = modelo;
    }

    public int getAnoFabricacao(){
        return anoFabricacao;
    }

    public void setANoFabricacao(int anoFabricacao){
        this.anoFabricacao = anoFabricacao;
    }

    // imprimir as informacoes do veiculo
    @Override
    public String toString(){

        String str = "Placa do veiculo: " + placa + "\n" 
        + "Marca do veiculo: " + marca + "\n"
        + "Modelo do veiculo: " + modelo + "\n"
        + "Ano de fabricacao do veiculo: " + anoFabricacao + "\n";

        return str;
    }


}

