import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Validacao {


    // funcao que valida o cpf
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



    // funcao que valida o cnpj 
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

    // funcao que valida o nome
    protected static boolean validarNome(String nome){
        CharSequence inputStr = nome;
        // verifica se eh formado apenas por letras e espacos
        Pattern pattern = Pattern.compile(new String ("^[a-zA-Z\\s]*$"));
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches())
            return true;
    
        else
            return false;
    
    }

    // funcao que valida placa do veiculo
    protected static boolean validarPlaca(String placa) {
        boolean resultado = false;

		if(placa.length()!=7)
			return false;
    
        Pattern pattern = Pattern.compile("[A-Z]{3}[0-9]{1}[A-Z]{1}[0-9]{2}|[A-Z]{3}[0-9]{4}");
        Matcher mat = pattern.matcher(placa);
        if (!mat.matches())
            resultado = false;
        else
            resultado = true;

        return resultado;
    
    }

}
