import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
    
    private final String cpf;
    private Date dataNascimento;
	private Date dataLicenca;
    private String educacao;
    private String genero;
    private String classeEconomica;


    public ClientePF(String nome, String endereco, Date dataLicenca, String educacao,
    String genero, String classeEconomica, ArrayList<Veiculo> listaVeiculos, String cpf, Date dataNascimento){
        super(nome, endereco, listaVeiculos);
		this.dataLicenca = dataLicenca;
        this.educacao = educacao;
        this.genero = genero;
        this.classeEconomica = classeEconomica;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;

    }

	// getters e setters

    public String getCpf() {
        return cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

	protected void addVeiculoPF(Veiculo veiculo){
		this.getListaVeiculos().add(veiculo);
	}

	public Date getDataLicenca() {
		return dataLicenca;
	}

	public void setDataLicenca(Date dataLicenca) {
		this.dataLicenca = dataLicenca;
	}

	public String getEducacao() {
		return educacao;
	}

	public void setEducacao(String educacao) {
		this.educacao = educacao;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getClasseEconomica() {
		return classeEconomica;
	}

	public void setClasseEconomica(String classeEconomica) {
		this.classeEconomica = classeEconomica;
	}

	// metodo sobrescrito da classe Cliente
	@Override
	protected double calculaScore(){
		LocalDate d1 = Instant.ofEpochMilli(dataNascimento.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate d2 = LocalDate.now();

		double idade = Period.between(d1, d2).getYears();
	
		int quantidade_carros = this.getListaVeiculos().size();

		if(idade>=18 && idade<30)
			return (CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_18_30.getValor() * quantidade_carros);

		else if(idade>=30 && idade<60)
			return (CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_30_60.getValor() * quantidade_carros);

		else
			return (CalcSeguro.VALOR_BASE.getValor() * CalcSeguro.FATOR_60_90.getValor() * quantidade_carros);

	}


    @Override
    // imprimir as informacoes do cliente PF
    public String toString(){

		Date date = dataNascimento;
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strDate = formatter.format(date);

        String str = super.toString() + "CPF do cliente: " + cpf + "\n"
        + "Data de nascimento do cliente: " + strDate + "\n";

        return str;
    }

    
}