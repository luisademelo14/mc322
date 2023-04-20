public class Main {

    public static void main(String[] args){
        if(args.length>0)
            System.out.println(args[0]);
        
        // instancia (objeto) sinistro
        Sinistro sinistro = new Sinistro(0, "04/12/2004", "Rua da Luisa");

        System.out.println(sinistro.getId());

        System.out.println(sinistro.getData());

        System.out.println(sinistro.getEndereco());

        // instancia (objeto) veiculo
        Veiculo veiculo = new Veiculo("FPM4B84", "renault", "kwid");

        System.out.println(veiculo.getPlaca());

        System.out.println(veiculo.getMarca());

        System.out.println(veiculo.getModelo());

        // instancia (objeto) cliente
        Cliente cliente = new Cliente("Luisa", "12345678910", "04/12/2000", 23, "Rua Comprida");

        System.out.println(cliente.getNome());

        System.out.println(cliente.getCpf());

        System.out.println(cliente.getDataNascimento());

        System.out.println(cliente.getIdade());

        System.out.println(cliente.getEndereco());


        // instancia (objeto) seguradora
        Seguradora seguradora = new Seguradora("Seguradora Boa", "19912134522", "segboa@gmail.com.br", "Rua da Seguradora");

        System.out.println(seguradora.getNome());

        System.out.println(seguradora.getTelefone());

        System.out.println(seguradora.getEmail());

        System.out.println(seguradora.getEndereco());

    }


    
    
}
