import java.util.InputMismatchException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ClientePJ extends Cliente {

    private final String cnpj;
    private Date dataFundacao;


	public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, Date dataFundacao){
        super(nome, endereco, listaVeiculos);
        this.cnpj = cnpj;
        this.dataFundacao = dataFundacao;
    }

	public String getCnpj() {
		return cnpj;
	}


	public Date getdataFundacao() {
		return dataFundacao;
	}


	public void setdataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	protected void addVeiculoPJ(Veiculo veiculo){
		this.getListaVeiculos().add(veiculo);
	}


    protected static boolean validarCNPJ(String cnpj) {
    	
    	cnpj = cnpj.replaceAll("\\.", "");
		cnpj = cnpj.replaceAll("-", "");
		cnpj = cnpj.replaceAll("\\/", "");
    	
    	// se o tamanho do cnpj for diferente de 11 -> invalido
        if(cnpj.length() != 14)
            return(false);

        // se o cnpj eh formado apenas pelo mesmo algarismo -> invalido
        if(cnpj.equals("00000000000000") || cnpj.equals("11111111111111") || cnpj.equals("22222222222222")
        || cnpj.equals("33333333333333") || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
        || cnpj.equals("66666666666666") || cnpj.equals("77777777777777") || cnpj.equals("88888888888888")
        || cnpj.equals("99999999999999"))
            return(false);
	
	        char digito_13, digito_14;
	        int soma, t, num, peso;
	
	        // usando try para evitar erros de conversao de int para char
	        try {
	        	// conta para chegar no primeiro digito verificador
	            soma = 0;
	            peso = 2;
	            for (int i=11; i>=0; i--) {
	            // transformando os caracteres em inteiros
	            	num = (int)(cnpj.charAt(i) - 48);
	            	soma = soma + (num * peso);
	            	peso++;

                    if(peso==10)
                        peso = 2;
	            }
	
	            t = soma % 11;
	            if ((t == 0) || (t == 1))
	                digito_13 = '0';
	            else 
	            	// converte para char no caractere numerico
	            	digito_13 = (char)((11-t) + 48); 
	
	            // conta para chegar no segundo digito verificador
	            soma = 0;
	            peso = 2;
	            for(int i=12; i>=0; i--) {
	            	// transformando os caracteres em inteiros
	            	num = (int)(cnpj.charAt(i) - 48);
	            	soma = soma + (num * peso);
	            	peso++;

                    if(peso==10)
                        peso = 2;
	            }
	
	            t = soma % 11;
	            if ((t == 0) || (t == 1))
	                 digito_14 = '0';
	            else 
	            	// converte para char no caractere numerico
	            	digito_14 = (char)((11-t) + 48);
	
	            // verificando se os digitos calculados conferem com os digitos do cnpj
	            if ((digito_13 == cnpj.charAt(12)) && (digito_14 == cnpj.charAt(13)))
	                return(true);
	            else 
	                return(false);
	            } 
	                
	            catch (InputMismatchException erro) {
	                return(false);
	            }
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


