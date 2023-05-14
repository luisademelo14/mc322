import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ClientePJ extends Cliente {

    private final String cnpj;
    private Date dataFundacao;
	private int quantidadeFunc;

	public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, Date dataFundacao, int quantidadeFunc){
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
		this.quantidadeFunc = quantidadeFunc;
    }

	// getters e setters

	public String getCnpj() {
		return cnpj;
	}


	public Date getdataFundacao() {
		return dataFundacao;
	}


	public void setdataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public int getQuantidadeFunc() {
		return quantidadeFunc;
	}

	public void setQuantidadeFunc(int quantidadeFunc) {
		this.quantidadeFunc = quantidadeFunc;
	}

	protected void addVeiculoPJ(Veiculo veiculo){
		this.getListaVeiculos().add(veiculo);
	}

	// metodo sobrescrito da classe Cliente
	@Override
	protected double calculaScore(){
		int quantidade_carros = this.getListaVeiculos().size();

		return (CalcSeguro.VALOR_BASE.getValor() * (1+(quantidadeFunc)/100) * quantidade_carros);

	}
    

    @Override
    // imprimir as informacoes do cliente PJ
    public String toString(){

		Date date = dataFundacao;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);
		
        String str = super.toString() + "CNPJ do cliente: " + cnpj + "\n"
		+ "Data fundacao do cliente: " + strDate + "\n";

        return str;
    }

    

    
}

