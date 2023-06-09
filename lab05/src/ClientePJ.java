import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientePJ extends Cliente {

    private final String cnpj;
    private Date dataFundacao;
    private int quantidadeFunc;
    private ArrayList <Frota> listaFrota;

    public ClientePJ(String nome, String endereco, String telefone, String email,
    String cnpj, Date dataFundacao, int quantidadeFunc){
        super(nome, endereco, telefone, email);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
        this.quantidadeFunc = quantidadeFunc;
        this.listaFrota = new ArrayList<Frota>();
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public ArrayList<Frota> getListaFrota() {
        return listaFrota;
    }

    public void setListaFrota(ArrayList<Frota> listaFrota) {
        this.listaFrota = listaFrota;
    }

    public String getCnpj() {
        return cnpj;
    }

    public int getQuantidadeFunc() {
        return quantidadeFunc;
    }

    public void setQuantidadeFunc(int quantidadeFunc) {
        this.quantidadeFunc = quantidadeFunc;
    }

    protected boolean cadastrarFrota(Frota frota){
        for(Frota f : listaFrota){
            if(f.getCode().equals(frota.getCode())){
                return false;
            }
        }
        listaFrota.add(frota);
        return true;
    }

    protected boolean atualizarFrota(Veiculo veiculo, String code, int op){
        // caso de adicionar veiculo na frota
        if(op==1){
            for(Frota f : listaFrota){
                if(code.equals(f.getCode())){
                    f.getListaVeiculos().add(veiculo);
                    return true;
                }
            }
        }

        // caso de remover veiculo da frota
        else{
            for(Frota f : listaFrota){
                if(code.equals(f.getCode())){
                    for(Veiculo v : f.getListaVeiculos()){
                        if(v.getPlaca().equals(veiculo.getPlaca())){
                            f.getListaVeiculos().remove(veiculo);
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    // sobrecarga do metodo atualizarFrota()
    protected boolean atualizarFrota(String code){
        for(Frota f : listaFrota){
            if(code.equals(f.getCode())){
                listaFrota.remove(f);
                return true;
            }
        }
        return false;
    }

    protected boolean getVeiculosPorFrota(String frota){
        for(Frota f : listaFrota){
            if(frota.equals(f.getCode())){
                for(Veiculo v : f.getListaVeiculos()){
                    System.out.println("###################");
                    System.out.println(v.toString());
                    System.out.println("###################");
                }
                return true;
            }
        }
        return false;
    }

    // imprimir as informacoes do cliente PJ
    @Override
    public String toString(){

		Date date = dataFundacao;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);

        String str = super.toString() + "CNPJ do cliente: " + cnpj + "\n"
        + "Data de fundacao: " + strDate + "\n";

        return str;
    }

}
