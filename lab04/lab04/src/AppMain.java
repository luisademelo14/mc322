import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class AppMain {

	private static ArrayList<Seguradora> listaSeguradoras;
	public static Scanner read = new Scanner(System.in);

	// exibir menu externo
	private static void exibirMenuExterno() {
		MenuOperacoes menuOpcoes[] = MenuOperacoes.values();
		System.out.println("MENU PRINCIPAL");
		for(MenuOperacoes operacao: menuOpcoes) {
			System.out.println(operacao.ordinal() + " -> " + operacao.getDescricao());
		}
	}
	

	private static void exibirSubmenu(MenuOperacoes operacao) {
		SubmenuOperacoes[] submenu = operacao.getSubmenu();
		System.out.println(operacao.getDescricao());
		for(int i=0; i<submenu.length; i++) {
			System.out.println(i +" -> " + submenu[i].getDescricao());
		}
	}
	
	// ler operacoes do menu externo
	private static MenuOperacoes lerOpcaoMenuExterno() {
		int opUsuario;
		MenuOperacoes opUsuarioConst;
		do {
			System.out.println("Digite uma operacao: ");
			opUsuario = read.nextInt();
			read.nextLine();
		}while(opUsuario < 0 || opUsuario > MenuOperacoes.values().length - 1);
		opUsuarioConst = MenuOperacoes.values()[opUsuario];
		return opUsuarioConst;
	}
	
	// ler operacao dos submenus
	private static SubmenuOperacoes lerOpcaoSubmenu(MenuOperacoes operacao) {
		int opUsuario;
		SubmenuOperacoes opUsuarioConst;
		do {
			System.out.println("Digite uma operacao: ");
			opUsuario = read.nextInt();
			read.nextLine();
		}while(opUsuario < 0 || opUsuario > operacao.getSubmenu().length - 1);
		opUsuarioConst = operacao.getSubmenu()[opUsuario];
		return opUsuarioConst;
	}
	
	// executando operacoes do menu externo
	private static void executarOpcaoMenuExterno(MenuOperacoes operacao) throws ParseException {
		switch(operacao) {
			case CADASTROS:
			case LISTAR:
			case EXCLUIR:
				executarSubmenu(operacao);
				break;

			case GERAR_SINISTRO:
				System.out.println("Executar metodo GERAR SINISTRO");
				System.out.println("Nome da seguradora em que o sinistro sera gerado:");
				String nome = read.nextLine();
				boolean existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
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

						Sinistro sinistro = new Sinistro(id_gerado, data_sinistro, end_sinistro, null, s, null);

						boolean gerou_sinistro = s.gerarSinistro(sinistro, cliente_sinistro, veiculo_sinistro);
		
							if(gerou_sinistro==false)
								System.out.println("Nao foi possivel gerar o sinistro para o cliente e veiculo dados!");
		
							else{
								System.out.println("Sinistro gerado com sucesso!");
								sinistro.toString();
							}
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;

			case TRANSFERIR_SEGURO:
				System.out.println("Executar metodo TRANSFERIR SEGURO");
				System.out.println("Nome da seguradora na qual a tranferencia sera realizada:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){

						System.out.println("Digite o cpf ou cnpj do cliente que FARA a tranferencia:");
						System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
						System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
						String dado1 = read.nextLine();

						System.out.println("Digite o cpf ou cnpj do cliente que RECEBERA a tranferencia:");
						System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
						System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
						String dado2 = read.nextLine();

						Cliente c1 = s.buscarCliente(dado1);
						Cliente c2 = s.buscarCliente(dado2);
						boolean possivel_transf = true;
						if(c1==null || c2==null){
							System.out.println("Pelo menos um dos clientes informados nao esta cadastrado!");
							possivel_transf = false;
						}
						if(possivel_transf){
							s.transferenciaVeiculos(c1, c2);
							System.out.println("Transferência realizada com sucesso!");
						}
						
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;

			case CALCULAR_RECEITA:
				System.out.println("Executar metodo CALCULAR RECEITA");
                System.out.println("Nome da seguradora cuja receita sera calculada:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						double resposta = s.calcularReceita();
						System.out.println("Receita da Seguradora = " + resposta + " reais");
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;
		}
	}
	
	public static void executarOpcaoSubMenu(SubmenuOperacoes operacaoSubmenu) throws ParseException {
			switch(operacaoSubmenu) {
			case CADASTRAR_CLIENTE:
				System.out.println("Chamar metodo CADASTRAR CLIENTE"); 
				System.out.println("Tipo de cliente que sera cadastrado (PF ou PJ):");
			    String tipo = read.nextLine();
				if(tipo.equals("PF")){
					System.out.println("Entre com o nome do cliente_pf: ");
			            String nome_cliente1 = read.nextLine();
						while((Validacao.validarNome(nome_cliente1))==false){
							System.out.println("CNPJ do cliente_pj eh invalido! Digite outro cnpj: ");
							nome_cliente1 = read.nextLine();
						}
			            
			            
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

						while((Validacao.validarCPF(cpf_cliente1))==false){
							System.out.println("CPF do cliente_pf eh invalido! Digite outro cpf: ");
							cpf_cliente1 = read.nextLine();
						}

						ClientePF cliente_pf  = new ClientePF(nome_cliente1, end_cliente1, data_licenca_cliente1, educacao_cliente1, genero_cliente1, classe_ec_cliente1, null, cpf_cliente1, nascimento_cliente1);

						for(int i=0; i<qnt_veiculos_c1; i++){
							System.out.println("Placa do veiculo " + i + ": ");
							String placa1 = read.nextLine();
							while((Validacao.validarPlaca(placa1))==false){
								System.out.println("Placa eh invalida! Digite outra placa: ");
								placa1 = read.nextLine();
							}

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

						double valor_seguro_pf = cliente_pf.calculaScore();

						cliente_pf.setValorSeguro(valor_seguro_pf);

						System.out.println(cliente_pf.toString());
					
					System.out.println("Nome da seguradora em que o cliente sera cadastrado:");
					String nome = read.nextLine();
					boolean existe = false;
					// assumindo que nao ha seguradoras com mesmo nome
					for(Seguradora s : listaSeguradoras){
						if(s.getNome().equals(nome)){
							boolean cadastrou1 = s.cadastrarCliente(cliente_pf);

							if(cadastrou1==false){
								System.out.println("Atencao! Ja existe um cliente cadastrado com este cpf!");
							}

							else{
								System.out.println("Cliente cadastrado com sucesso!");
							}
							existe = true;
						}
					}
					// nao achou seguradora com tal nome
					if(!existe)
						System.out.println("Tal seguradora nao foi cadastrada!");
				}
				else{
					System.out.println("Entre com o nome do cliente_pj: ");
                    String nome_cliente2 = read.nextLine();
					while((Validacao.validarNome(nome_cliente2))==false){
                        System.out.println("Nome invalido! Digite outro nome: ");
                        nome_cliente2 = read.nextLine();
                    }
            
            
                    System.out.println("Entre com o endereco do cliente_pj: ");
                    String end_cliente2 = read.nextLine();
            
            
                    System.out.println("Entre com o cnpj do cliente_pj: ");
                    System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
                    String cnpj_cliente2 = read.nextLine();
            
            
                    System.out.println("Entre com a data de fundacao do cliente_pj: ");
                    String fundacao_string2 = read.nextLine();
                    Date fundacao2 = new SimpleDateFormat("dd/MM/yyyy").parse(fundacao_string2);

					System.out.println("Entre com a quantidade de funcionarios que o cliente possui: ");
                    // para add os veiculos na lista
                    int qnt_func = read.nextInt();
                    read.nextLine();
            
                    System.out.println("Quantos veiculos o cliente_pj possui?: ");
                    // para add os veiculos na lista
                    int qnt_veiculos_c2 = read.nextInt();
                    read.nextLine();

					while((Validacao.validarCNPJ(cnpj_cliente2))==false){
                        System.out.println("CNPJ do cliente_pj eh invalido! Digite outro cnpj: ");
                        cnpj_cliente2 = read.nextLine();
                    }
                    
                    ClientePJ cliente_pj = new ClientePJ(nome_cliente2, end_cliente2, null,  cnpj_cliente2, fundacao2, qnt_func);

                    for(int i=0; i<qnt_veiculos_c2; i++){
                        System.out.println("Placa do veiculo " + i + ": ");
                        String placa2 = read.nextLine();
						while((Validacao.validarPlaca(placa2))==false){
							System.out.println("Placa eh invalida! Digite outra placa: ");
							placa2 = read.nextLine();
						}

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

					double valor_seguro_pj = cliente_pj.calculaScore();

					cliente_pj.setValorSeguro(valor_seguro_pj);

					System.out.println(cliente_pj.toString());

					System.out.println("Nome da seguradora em que o cliente sera cadastrado:");
					String nome = read.nextLine();
					boolean existe = false;
					// assumindo que nao ha seguradoras com mesmo nome
					for(Seguradora s : listaSeguradoras){
						if(s.getNome().equals(nome)){
							boolean cadastrou2 = s.cadastrarCliente(cliente_pj);

							if(cadastrou2==false){
								System.out.println("Atencao! Ja existe um cliente cadastrado com este cnpj!");
							}

							else{
								System.out.println("Cliente cadastrado com sucesso!");
							}
							existe = true;
						}
					}
					// nao achou seguradora com tal nome
					if(!existe)
						System.out.println("Tal seguradora nao foi cadastrada!");
				}
				break;

			case CADASTRAR_VEICULO:
				System.out.println("Chamar metodo CADASTRAR VEICULO");
				System.out.println("Nome da seguradora em que o veiculo sera cadastrado:");
					String nome = read.nextLine();
					boolean existe = false;
					// assumindo que nao ha seguradoras com mesmo nome
					for(Seguradora s : listaSeguradoras){
						if(s.getNome().equals(nome)){
							System.out.println("Placa do veiculo:");
							String placa = read.nextLine();
							while((Validacao.validarPlaca(placa))==false){
								System.out.println("Placa eh invalida! Digite outra placa: ");
								placa = read.nextLine();
							}

							System.out.println("Modelo do veiculo:");
							String modelo = read.nextLine();

							System.out.println("Marca do veiculo:");
							String marca = read.nextLine();

							System.out.println("Ano do veiculo:");
							int ano = read.nextInt();
							read.nextLine();
							System.out.println("Digite o cpf ou cnpj do cliente que tera mais um veiculo cadastrado: ");
							System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
							System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
							String cliente = read.nextLine();
							boolean cadastrou = s.cadastrarVeiculo(placa, modelo, marca, ano, cliente);
							if(cadastrou)
								System.out.println("Veiculo cadastrado com sucesso!");
							else
								System.out.println("Nao foi possivel cadastrar o veiculo");
							existe = true;
						}
					}
					// nao achou seguradora com tal nome
					if(!existe)
						System.out.println("Tal seguradora nao foi cadastrada!");

				break;

			case CADASTRAR_SEGURADORA:
				System.out.println("Chamar metodo CADASTAR SEGURADORA");
				Seguradora seg = new Seguradora(null, null, null, null);
				System.out.println("Entre com o nome da seguradora: ");
			    String nome_seguradora = read.nextLine();

			    System.out.println("Entre com o telefone da seguradora: ");
			    String telefone_seguradora = read.nextLine();

			    System.out.println("Entre com o email da seguradora: ");
			    String email_seguradora = read.nextLine();

			    System.out.println("Entre com o endereco da seguradora: ");
			    String endereco_seguradora = read.nextLine();
				seg.setNome(nome_seguradora);
				seg.setEmail(email_seguradora);
				seg.setEndereco(endereco_seguradora);
				seg.setTelefone(telefone_seguradora);
				listaSeguradoras.add(seg);
				System.out.println("Seguradora cadastrada com sucesso!");
				break;

			case LISTAR_CLIENTES:
				System.out.println("Chamar metodo LISTAR CLIENTES");
				System.out.println("Nome da seguradora cujos clientes serao listados:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						System.out.println("Tipo de cliente (PF ou PJ): ");
						tipo = read.nextLine();
						s.listarClientes(tipo);
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;
			case LISTAR_SINISTROS:
				System.out.println("Chamar metodo LISTAR SINISTROS");
				System.out.println("Nome da seguradora cujos sinistros serao listados:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						s.listarSinistros();
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;

			case LISTAR_VEICULOS:
				System.out.println("Chamar metodo LISTAR VEICULOS");
				System.out.println("Nome da seguradora cujos veiculos serao listados:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						s.listarVeiculos();
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;

			case EXCLUIR_CLIENTE:
				System.out.println("Chamar metodo EXCLUIR CLIENTE");
				System.out.println("Nome da seguradora da qual um cliente sera removido:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						// procura o cliente pelo cpf ou cnpj
						System.out.println("Digite o cpf ou cnpj do cliente que sera removido: ");
			            System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
			            System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
			            String cliente_removido = read.nextLine();
			            boolean removeu = s.removerCliente(cliente_removido);
			            if(removeu==true)
			                System.out.println("Cliente removido com sucesso!");
			            else
			                System.out.println("Nao foi possivel remover o cliente!");
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;

			case EXCLUIR_VEICULO:
				System.out.println("Chamar metodo EXCLUIR VEICULO");
				System.out.println("Nome da seguradora da qual um veiculo sera removido:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						// procura o veiculo pela placa
						System.out.println("Digite a placa do veiculo que sera removido:");
						String placa = read.nextLine();

						boolean removeu = s.removerVeiculo(placa);
			            if(removeu==true)
			                System.out.println("Veiculo removido com sucesso!");
			            else
			                System.out.println("Nao foi possivel remover o veiculo!");
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;

			case EXCLUIR_SINISTRO:
				System.out.println("Chamar metodo EXCLUIR SINISTRO");
				System.out.println("Nome da seguradora da qual um sinistro sera removido:");
				nome = read.nextLine();
				existe = false;
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						// procura o sinistro pelo id
						System.out.println("Digite o id do sinistro que sera removido: ");
						int id_remover = read.nextInt();
						read.nextLine();
						boolean removeu = s.removerSinistro(id_remover);
			            if(removeu==true)
			                System.out.println("Sinistro removido com sucesso!");
			            else
			                System.out.println("Nao foi possivel remover o sinistro!");
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;
			}
	}
	
	// executa o submenu (exibir, ler e executar)
	private static void executarSubmenu(MenuOperacoes operacao) throws ParseException {
		SubmenuOperacoes opSubmenu;
		do {
			exibirSubmenu(operacao);
			opSubmenu = lerOpcaoSubmenu(operacao);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOperacoes.VOLTAR);
	}
	
	// executa o menu externo (exibir, ler e executar)
	public static void main(String[] args) throws ParseException {
		listaSeguradoras = new ArrayList<Seguradora>();
		MenuOperacoes operacao;
		do {
			exibirMenuExterno();
			operacao = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(operacao);
		}while(operacao != MenuOperacoes.SAIR);
		System.out.println("SAIU DO PROGRAMA!");
	}

}