import java.util.ArrayList;

public class Frota {
    private String code;
    private ArrayList <Veiculo> listaVeiculos;

    public Frota(String code, ArrayList<Veiculo> listaVeiculos){
        this.code = code;
        this.listaVeiculos = new ArrayList<Veiculo>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ArrayList<Veiculo> getListaVeiculos() {
        return listaVeiculos;
    }

    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
        this.listaVeiculos = listaVeiculos;
    }

    protected boolean addVeiculo(Veiculo veiculo){
        for(Veiculo v : listaVeiculos){
            if(v.getPlaca().equals(veiculo.getPlaca())){
                return false;
            }
        }
        listaVeiculos.add(veiculo);
        return true;
    }

    protected boolean removeVeiculo(String veiculo){
        for(Veiculo v : listaVeiculos){
            if(v.getPlaca().equals(veiculo)){
                listaVeiculos.remove(v);
                return true;
            }
        }
        return false;
    }

    // imprimir informacoes da frota
    @Override
    public String toString(){
        String str = "Code da frota: " + code + "\n";
        return str;
    }
}
