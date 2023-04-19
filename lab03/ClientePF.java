import java.util.InputMismatchException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ClientePF extends Cliente {
    
    private String cpf;
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


    protected static boolean validarCPF(String cpf) {
    	
    	cpf = cpf.replaceAll("\\.","");
		cpf = cpf.replaceAll("-","");
    	
    	// se o tamanho do cpf for diferente de 11 -> invalido
        if(cpf.length() != 11)
            return(false);

        // se o cpf eh formado apenas pelo mesmo algarismo -> invalido
        if(cpf.equals("00000000000") || cpf.equals("11111111111") || cpf.equals("22222222222")
        || cpf.equals("33333333333") || cpf.equals("44444444444") || cpf.equals("55555555555")
        || cpf.equals("66666666666") || cpf.equals("77777777777") || cpf.equals("88888888888")
        || cpf.equals("99999999999"))
            return(false);
	
	        char digito_10, digito_11;
	        int soma, t, num, peso;
	
	        // usando try para evitar erros de conversao de int para char
	        try {
	        	// conta para chegar no primeiro digito verificador
	            soma = 0;
	            peso = 10;
	            for (int i=0; i<9; i++) {
	            // transformando os caracteres em inteiros
	            	num = (int)(cpf.charAt(i) - 48);
	            	soma = soma + (num * peso);
	            	peso = peso - 1;
	            }
	
	            t = 11 - (soma % 11);
	            if ((t == 10) || (t == 11))
	                digito_10 = '0';
	            else 
	            	// converte para char no caractere numerico
	            	digito_10 = (char)(t + 48); 
	
	            // conta para chegar no segundo digito verificador
	            soma = 0;
	            peso = 11;
	            for(int i=0; i<10; i++) {
	            	// transformando os caracteres em inteiros
	            	num = (int)(cpf.charAt(i) - 48);
	            	soma = soma + (num * peso);
	            	peso = peso - 1;
	            }
	
	            t = 11 - (soma % 11);
	            if ((t == 10) || (t == 11))
	                 digito_11 = '0';
	            else 
	            	// converte para char no caractere numerico
	            	digito_11 = (char)(t + 48);
	
	            // verificando se os digitos calculados conferem com os digitos do cpf
	            if ((digito_10 == cpf.charAt(9)) && (digito_11 == cpf.charAt(10)))
	                return(true);
	            else 
	                return(false);
	            } 
	                
	            catch (InputMismatchException erro) {
	                return(false);
	            }
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
