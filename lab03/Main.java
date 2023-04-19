import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws ParseException{
        try (Scanner read = new Scanner(System.in)) {

            // INSTANCIANDO A SEGURADORA (teremos apenas uma seguradora para o programa todo)
            System.out.println("Entre com o nome da seguradora: ");
            String nome_seguradora = read.nextLine();

            System.out.println("Entre com o telefone da seguradora: ");
            String telefone_seguradora = read.nextLine();

            System.out.println("Entre com o email da seguradora: ");
            String email_seguradora = read.nextLine();

            System.out.println("Entre com o endereco da seguradora: ");
            String endereco_seguradora = read.nextLine();


            Seguradora seguradora = new Seguradora(nome_seguradora, telefone_seguradora, email_seguradora, endereco_seguradora);

            System.out.println(seguradora.toString());

            int operacao = 1;

            while(operacao!=0){
                System.out.println("ACOES QUE O USUARIO PODE SEGUIR:");
                System.out.println("0 = PARAR O PROGRAMA");
                System.out.println("1 = CADASTRAR CLIENTE PESSOA FISICA");
                System.out.println("2 = CADASTRAR CLIENTE PESSOA JURIDICA");
                System.out.println("3 = REMOVER CLIENTE (PF OU PJ)");
                System.out.println("4 = GERAR UM SINISTRO PARA UM DADO CLIENTE E SEU VEICULO");
                System.out.println("5 = LISTAR CLIENTES DE UM TIPO (PF OU PJ)");
                System.out.println("6 = VISUALIZAR SINISTRO DE ALGUM CLIENTE");
                System.out.println("7 = LISTAR SINISTROS DA SEGURADORA");
                System.out.println("Escolha a proxima operacao: ");
                operacao = read.nextInt();
                read.nextLine();

                if(operacao==1){
                    System.out.println("Entre com o nome do cliente_pf: ");
                    String nome_cliente1 = read.nextLine();
                    
                    
                    System.out.println("Entre com o endereco do cliente_pf: ");
                    String end_cliente1 = read.nextLine();
                    
                    
                    System.out.println("Entre com a data de licenca do cliente_pf: ");
                    String licenca_string1 = read.nextLine();
                    Date data_licenca_cliente1 = new SimpleDateFormat("dd/MM/yyyy").parse(licenca_string1);
                    
                    
                    System.out.println("Entre com a educacao do cliente_pf: ");
                    String educacao_cliente1 = read.nextLine();


                    System.out.println("Entre com o genero do cliente_pf: ");
                    String genero_cliente1 = read.nextLine();
                    
                    
                    System.out.println("Entre com a classe economica do cliente_pf: ");
                    String classe_ec_cliente1 = read.nextLine();
                    
                    
                    System.out.println("Entre com o cpf do cliente_pf: ");
                    System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
                    String cpf_cliente1 = read.nextLine();
                    
                    System.out.println("Entre com a data de nascimento do cliente_pf: ");
                    String data_nascimento = read.nextLine();
                    Date nascimento_cliente1 = new SimpleDateFormat("dd/MM/yyyy").parse(data_nascimento);
                    
                    
                    System.out.println("Quantos veiculos o cliente_pf possui?: ");
                    // para add os veiculos na lista
                    int qnt_veiculos_c1 = read.nextInt();
                    read.nextLine();

                    while((ClientePF.validarCPF(cpf_cliente1))==false){
                        System.out.println("CPF do cliente_pf eh invalido! Digite outro cpf: ");
                        cpf_cliente1 = read.nextLine();
                    }
                    
                    ClientePF cliente_pf  = new ClientePF(nome_cliente1, end_cliente1, data_licenca_cliente1, educacao_cliente1, genero_cliente1, classe_ec_cliente1, null, cpf_cliente1, nascimento_cliente1);
                   
                    System.out.println(cliente_pf.toString());

                    for(int i=0; i<qnt_veiculos_c1; i++){
                        System.out.println("Placa do veiculo " + i + ": ");
                        String placa1 = read.nextLine();

                        System.out.println("Modelo do veiculo " + i + ": ");
                        String modelo1 = read.nextLine();

                        System.out.println("Marca do veiculo " + i + ": ");
                        String marca1 = read.nextLine();

                        System.out.println("Ano do veiculo " + i + ": ");
                        int ano1 = read.nextInt();
                        read.nextLine();
                        
                        Veiculo veiculo_cadastrado1 = new Veiculo(placa1, marca1, modelo1, ano1);
                        System.out.println("#############");
                        System.out.println(veiculo_cadastrado1.toString());
                        System.out.println("#############");
                        cliente_pf.addVeiculoPF(veiculo_cadastrado1);
                    }

                    boolean cadastrou1 = seguradora.cadastrarCliente(cliente_pf);

                    if(cadastrou1==false){
                        System.out.println("Atencao! Ja existe um cliente cadastrado com este cpf!");
                    }

                    else{
                        System.out.println("Cliente cadastrado com sucesso!");
                    }

                }

                else if(operacao==2){
                    System.out.println("Entre com o nome do cliente_pj: ");
                    String nome_cliente2 = read.nextLine();
            
            
                    System.out.println("Entre com o endereco do cliente_pj: ");
                    String end_cliente2 = read.nextLine();
            
            
                    System.out.println("Entre com o cnpj do cliente_pj: ");
                    System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
                    String cnpj_cliente2 = read.nextLine();
            
            
                    System.out.println("Entre com a data de fundacao do cliente_pj: ");
                    String fundacao_string2 = read.nextLine();
                    Date fundacao2 = new SimpleDateFormat("dd/MM/yyyy").parse(fundacao_string2);
            
            
                    System.out.println("Quantos veiculos o cliente_pj possui?: ");
                    // para add os veiculos na lista
                    int qnt_veiculos_c2 = read.nextInt();
                    read.nextLine();


                    while((ClientePJ.validarCNPJ(cnpj_cliente2))==false){
                        System.out.println("CNPJ do cliente_pj eh invalido! Digite outro cnpj: ");
                        cnpj_cliente2 = read.nextLine();
                    }
                    
                    ClientePJ cliente_pj = new ClientePJ(nome_cliente2, end_cliente2, null,  cnpj_cliente2, fundacao2);

                    System.out.println(cliente_pj.toString());

                    for(int i=0; i<qnt_veiculos_c2; i++){
                        System.out.println("Placa do veiculo " + i + ": ");
                        String placa2 = read.nextLine();

                        System.out.println("Modelo do veiculo " + i + ": ");
                        String modelo2 = read.nextLine();

                        System.out.println("Marca do veiculo " + i + ": ");
                        String marca2 = read.nextLine();

                        System.out.println("Ano do veiculo " + i + ": ");
                        int ano2 = read.nextInt();
                        read.nextLine();
                        
                        Veiculo veiculo_cadastrado2 = new Veiculo(placa2, marca2, modelo2, ano2);

                        System.out.println("#############");
                        System.out.println(veiculo_cadastrado2.toString());
                        System.out.println("#############");

                        cliente_pj.addVeiculoPJ(veiculo_cadastrado2);
                    }


                    boolean cadastrou2 = seguradora.cadastrarCliente(cliente_pj);

                    if(cadastrou2==false){
                        System.out.println("Atencao! Ja existe um cliente cadastrado com este cnpj!");
                    }

                    else{
                        System.out.println("Cliente cadastrado com sucesso!");
                    }

                }

                else if(operacao==3){
                    System.out.println("Digite o cpf ou cnpj do cliente que sera removido: ");
                    System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
                    System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
                    String cliente_removido = read.nextLine();
                    boolean removeu = seguradora.removerCliente(cliente_removido);
                    if(removeu==true)
                        System.out.println("Cliente removido com sucesso!");
                    else
                        System.out.println("Nao foi possivel remover o cliente!");
                }

                else if(operacao==4){

                    System.out.println("Entre com o endereco do sinistro: ");
                    String end_sinistro = read.nextLine();
            
                    System.out.println("Entre com a data do sinistro: ");
                    String data_sinistro = read.nextLine();

                    System.out.println("Entre com o cnpj ou cpf do cliente do sinistro: ");
                    System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
                    System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");


                    String cliente_sinistro = read.nextLine();

                    System.out.println("Entre com a placa do veiculo deste cliente que sera registrado no sinistro: ");
                    String veiculo_sinistro = read.nextLine();
                    
                    int id_gerado = Sinistro.GeraId();
                    
                    if(id_gerado<0)
                        id_gerado = id_gerado * (-1); // garantindo que o id eh positivo
                    
                    Sinistro sinistro = new Sinistro(id_gerado, data_sinistro, end_sinistro, null, seguradora, null);

                    boolean gerou_sinistro = seguradora.gerarSinistro(sinistro, cliente_sinistro, veiculo_sinistro);

                    if(gerou_sinistro==false)
                        System.out.println("Nao foi possivel gerar o sinistro para o cliente e veiculo dados!");

                    else{
                        System.out.println("Sinistro gerado com sucesso!");
                        sinistro.toString();
                    }

                }

                else if(operacao==5){
                    System.out.println("Tipo de cliente que sera listado: ");
                    String tipo = read.nextLine();
                    seguradora.listarClientes(tipo);
                }

                else if(operacao==6){
                    System.out.println("Digite o cpf ou cnpj do cliente cujo sinistro sera mostrado: ");
                    System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
                    System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
                    String cliente_sinistro = read.nextLine();
                    boolean visualizar = seguradora.visualizarSinistro(cliente_sinistro);
                    if(visualizar==false)
                        System.out.println("Nao foi possivel visualizar o sinistro para o cliente dado!");
                }

                else if(operacao==7){
                    seguradora.listarSinistros();
                }

            }
        }

    }

}
