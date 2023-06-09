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

    private static void listarSeguradoras(){
        for(Seguradora s : listaSeguradoras){
            System.out.println("###################");
            System.out.println(s.toString());
            System.out.println("###################");
        }
    }

    private static void executarOpcaoMenuExterno(MenuOperacoes op) throws ParseException {
		switch(op) {
			case CADASTRAR:
			case LISTAR:
			case EXCLUIR:
            case ATUALIZAR_FROTA:
				executarSubmenu(op);
				break;
			case CALCULAR_RECEITA:
				System.out.println("Executar metodo calcular receita");
                System.out.println("Nome da seguradora cuja receita sera calculada:");
				String nome = read.nextLine();
				boolean existe = false;
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

			case GERAR_SEGURO:
				System.out.println("Executar metodo gerar seguro");
				System.out.println("Tipo de cliente (PF ou PJ): ");
					String tipo = read.nextLine();
					System.out.println("Nome da seguradora em que o seguro sera cadastrado:");
					nome = read.nextLine();
					existe = false;	
					// assumindo que nao ha seguradoras com mesmo nome
					for(Seguradora s : listaSeguradoras){
						if(s.getNome().equals(nome)){
							s.gerarSeguro(tipo);
							existe = true;
						}
					}
					// nao achou seguradora com tal nome
					if(!existe)
						System.out.println("Tal seguradora nao foi cadastrada!");
				break;

			case GERAR_SINISTRO:
				System.out.println("Executar metodo gerar sinistro");
				System.out.println("Digite o nome da seguradora em que sera registrado o sinistro: ");
				nome = read.nextLine();
				System.out.println("Digite o id do seguro do sinistro: ");
				int id_seguro = read.nextInt();
				read.nextLine();
				existe = false;	
				// assumindo que nao ha seguradoras com mesmo nome
					for(Seguradora s : listaSeguradoras){
						if(s.getNome().equals(nome)){
							for(Seguro seguro : s.getListaSeguros()){
								if(seguro.getId()==id_seguro){
									seguro.gerarSinistro(s, seguro);
								}
							}
							existe = true;
						}
					}
					// nao achou seguradora com tal nome
					if(!existe)
						System.out.println("Tal seguradora nao foi cadastrada!");
				break;

            case AUTORIZAR_CONDUTOR:
				System.out.println("Executar metodo autorizar condutor");
				System.out.println("Nome da seguradora em que estao os condutores:");
				nome = read.nextLine();
				existe = false;
				System.out.println("Entre com o id do seguro em que havera autorizacao de condutor: ");
				int id_seguro_codutores = read.nextInt();
				read.nextLine();
				System.out.println("Entre com o nome do condutor: ");
				String nome_cond = read.nextLine();

				System.out.println("Entre com o cpf do condutor: ");
				System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
				String cpf_cond = read.nextLine();

				while((Validacao.validarCPF(cpf_cond))==false){
					System.out.println("CPF do cliente_pf eh invalido! Digite outro cpf: ");
					cpf_cond = read.nextLine();
				}

				System.out.println("Entre com o endereco do condutor: ");
				String end_cond = read.nextLine();

				System.out.println("Entre com o email do condutor: ");
				String email_cond = read.nextLine();

				System.out.println("Entre com o telefone do condutor: ");
				String tel_cond = read.nextLine();

				System.out.println("Entre com a data de nascimento do condutor: ");
			    String data_nascimento = read.nextLine();
			    Date nascimento_cond = new SimpleDateFormat("dd/MM/yyyy").parse(data_nascimento);

				Condutor condutor = new Condutor(cpf_cond, nome_cond, tel_cond, end_cond, email_cond, nascimento_cond, null);

				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						for(Seguro seg : s.getListaSeguros()){
							if(seg.getId()==id_seguro_codutores){
								seg.autorizarCondutor(condutor);
							}
						}
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;
            case DESAUTORIZAR_CONDUTOR:
				System.out.println("Executar metodo desautorizar condutor");
				System.out.println("Nome da seguradora em que estao os condutores:");
				nome = read.nextLine();
				existe = false;
				System.out.println("Entre com o id do seguro em que havera autorizacao de condutor: ");
				int id_seguro_cond_remover = read.nextInt();
				read.nextLine();
				System.out.println("Entre com o cpf do condutor: ");
				System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
				String cpf_cond_remover = read.nextLine();
				// assumindo que nao ha seguradoras com mesmo nome
				for(Seguradora s : listaSeguradoras){
					if(s.getNome().equals(nome)){
						for(Seguro seg : s.getListaSeguros()){
							if(seg.getId()==id_seguro_cond_remover){
								seg.desautorizarCondutor(cpf_cond_remover);
							}
						}
						existe = true;
					}
				}
				// nao achou seguradora com tal nome
				if(!existe)
					System.out.println("Tal seguradora nao foi cadastrada!");
				break;
			default:
				break;
		}
	}

    public static void executarOpcaoSubMenu(SubmenuOperacoes opSubmenu) throws ParseException {
		switch(opSubmenu) {
		case CADASTRAR_CLIENTE:
			System.out.println("Chamar metodo cadastrar cliente");
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

						System.out.println("Entre com o email do cliente_pf: ");
			            String email_cliente1 = read.nextLine();

						System.out.println("Entre com o telefone do cliente_pf: ");
			            String tel_cliente1 = read.nextLine();
			            
			            System.out.println("Entre com a educacao do cliente_pf: ");
			            String educacao_cliente1 = read.nextLine();

			            System.out.println("Entre com o genero do cliente_pf: ");
			            String genero_cliente1 = read.nextLine();
			            
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

						ClientePF cliente_pf  = new ClientePF(nome_cliente1, end_cliente1, tel_cliente1, email_cliente1, educacao_cliente1, genero_cliente1, cpf_cliente1, nascimento_cliente1);

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
							cliente_pf.getListaVeiculos().add(veiculo_cadastrado1);
						}

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

					System.out.println("Entre com o email do cliente_pj: ");
					String email_cliente2 = read.nextLine();

					System.out.println("Entre com o telefone do cliente_pj: ");
					String tel_cliente2 = read.nextLine();
            
            
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
            

					while((Validacao.validarCNPJ(cnpj_cliente2))==false){
                        System.out.println("CNPJ do cliente_pj eh invalido! Digite outro cnpj: ");
                        cnpj_cliente2 = read.nextLine();
                    }
                    
                    ClientePJ cliente_pj = new ClientePJ(nome_cliente2, end_cliente2, tel_cliente2, email_cliente2, cnpj_cliente2, fundacao2, qnt_func);

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
			System.out.println("Chamar metodo cadastrar veiculo");
			System.out.println("Placa do veiculo: ");
			String placa = read.nextLine();
			while((Validacao.validarPlaca(placa))==false){
				System.out.println("Placa eh invalida! Digite outra placa: ");
				placa = read.nextLine();
			}

			System.out.println("Modelo do veiculo: ");
			String modelo = read.nextLine();

			System.out.println("Marca do veiculo: ");
			String marca = read.nextLine();

			System.out.println("Ano do veiculo: ");
			int ano = read.nextInt();
			read.nextLine();

			Veiculo veiculo_cadastrado = new Veiculo(placa, marca, modelo, ano);

			System.out.println("Nome da seguradora em que estao os sinistros:");
			String nome = read.nextLine();
			boolean existe = false;
			System.out.println("Entre com o id do seguro: ");
			int id_seguro_sinistros = read.nextInt();
			read.nextLine();
			System.out.println("Digite o cpf ou cnpj do cliente que tera mais um veiculo: ");
			System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
			System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
			String documento = read.nextLine();
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					boolean cadastrou_veiculo = s.cadastrarVeiculo(documento, veiculo_cadastrado);
					if(cadastrou_veiculo)
						System.out.println("Veiculo cadastrado com sucesso!");
					else
						System.out.println("Nao foi possivel realizar tal operacao!");
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");

			break;

		case CADASTRAR_SEGURADORA:
			System.out.println("Chamar metodo cadastrar seguradora");
			Seguradora seg = new Seguradora(null, null, null, null, null);
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

		case LISTAR_SEGURADORAS:
			System.out.println("Chamar metodo listar seguradoras");
            listarSeguradoras();
			break;

		case LISTAR_CLIENTES:
			System.out.println("Chamar metodo listar clientes");
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
			System.out.println("Chamar metodo listar sinistros");
			System.out.println("Nome da seguradora em que estao os sinistros:");
			nome = read.nextLine();
			existe = false;
			System.out.println("Entre com o id do seguro cujos sinsitros serao listados: ");
			id_seguro_sinistros = read.nextInt();
			read.nextLine();
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					s.listarSinistrosDeSeguro(id_seguro_sinistros);
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

		case LISTAR_SEGUROS:
			System.out.println("Chamar metodo listar seguros");
			System.out.println("Nome da seguradora cujos clientes serao listados:");
			nome = read.nextLine();
			existe = false;
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					s.listarSeguros();
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

		case LISTAR_CONDUTORES:
			System.out.println("Chamar metodo listar condutores");
			System.out.println("Nome da seguradora em que estao os condutores:");
			nome = read.nextLine();
			existe = false;
			System.out.println("Entre com o id do seguro cujos condutores serao listados: ");
			int id_seguro_condutores = read.nextInt();
			read.nextLine();
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					s.listarCondutoresDeSeguro(id_seguro_condutores);
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

		case LISTAR_SEGUROS_POR_CLIENTE:
			System.out.println("Chamar metodo listar seguros por cliente");
			System.out.println("Nome da seguradora onde estao os seguros:");
			nome = read.nextLine();
			existe = false;
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					System.out.println("Digite o cpf ou cnpj do cliente que sera removido: ");
					System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
					System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
					documento = read.nextLine();
					ArrayList<Seguro> segurosCliente = s.getSegurosPorCliente(documento);
					for(Seguro seguro : segurosCliente){
						System.out.println("#############");
						System.out.println(seguro.toString());
						System.out.println("#############");
					}
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

        case LISTAR_SINISTROS_POR_CLIENTE:
			System.out.println("Chamar metodo listar sinistros por cliente");
			System.out.println("Nome da seguradora onde estao os seguros:");
			nome = read.nextLine();
			existe = false;
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					System.out.println("Digite o cpf ou cnpj do cliente que sera removido: ");
					System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
					System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
					documento = read.nextLine();
					ArrayList<Sinistro> sinistrosCliente = s.getSinistrosPorCliente(documento);
					for(Sinistro sinistro : sinistrosCliente){
						System.out.println("#############");
						System.out.println(sinistro.toString());
						System.out.println("#############");
					}
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

        case LISTAR_FROTAS:
			System.out.println("Chamar metodo listar frotas");
			System.out.println("Nome da seguradora em que estao as frotas:");
			nome = read.nextLine();
			existe = false;
			System.out.println("Entre com o cnpj do cliente cujas frotas serao listadas: ");
			String cnpj_cliente = read.nextLine();
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					s.listarFrotas(cnpj_cliente);
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

        case LISTAR_VEICULOS:
			System.out.println("Chamar metodo listar veiculos");
			System.out.println("Nome da seguradora que possui os veiculos:");
			nome = read.nextLine();
			existe = false;
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					// procura o cliente pelo cpf ou cnpj
					System.out.println("Digite o cpf ou cnpj do cliente cujos veiculos serao listados: ");
					System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
					System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
					documento = read.nextLine();
					s.listarVeiculos(documento);
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

        case EXCLUIR_CLIENTE:
			System.out.println("Chamar metodo excluir cliente");
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
			System.out.println("Chamar metodo excluir veiculo");
			System.out.println("Nome da seguradora da qual um veiculo sera removido:");
			nome = read.nextLine();
			existe = false;
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					// procura o cliente pelo cpf ou cnpj
					System.out.println("Digite o cpf ou cnpj do cliente: ");
					System.out.println("->> cuidado com o formato do cnpj: ##.###.###/####-##");
					System.out.println("->> cuidado com o formato do cpf: ###.###.###-##");
					documento = read.nextLine();
					System.out.println("Digite a placa do veiculo que sera removido: ");
					String placa_removido = read.nextLine();
					boolean removeu = s.removerVeiculo(documento, placa_removido);
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
			
        case EXCLUIR_SEGURO:
			System.out.println("Chamar metodo excluir seguro");
			System.out.println("Nome da seguradora da qual um seguro sera removido:");
			nome = read.nextLine();
			existe = false;
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					int seguro_removido = read.nextInt();
					read.nextLine();
					boolean removeu = s.cancelarSeguro(seguro_removido);
					if(removeu==true)
						System.out.println("Seguro removido com sucesso!");
					else
						System.out.println("Nao foi possivel remover o seguro!");
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

        case EXCLUIR_FROTA:
			System.out.println("Chamar metodo excluir frota");
			System.out.println("Nome da seguradora em que estao as frotas:");
			nome = read.nextLine();
			existe = false;
			System.out.println("Entre com o cnpj do cliente: ");
			String cnpj_cliente_remFrota = read.nextLine();
			System.out.println("Entre com o code da frota em que sera adicionado um veiculo: ");
			String code_frota = read.nextLine();
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					boolean removeu = s.removerFrota(cnpj_cliente_remFrota, code_frota);
					if(!removeu)
						System.out.println("Nao foi possivel realizar tal operacao");
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

        case ADICIONAR_VEICULO_FROTA:
			System.out.println("Chamar metodo adicionar veiculo");
			System.out.println("Nome da seguradora em que estao as frotas:");
			nome = read.nextLine();
			existe = false;
			System.out.println("Entre com o cnpj do cliente: ");
			String cnpj_cliente_addVeiculo = read.nextLine();
			System.out.println("Entre com o code da frota em que sera adicionado um veiculo: ");
			code_frota = read.nextLine();
			System.out.println("Placa do veiculo: ");
			placa = read.nextLine();
			while((Validacao.validarPlaca(placa))==false){
				System.out.println("Placa eh invalida! Digite outra placa: ");
				placa = read.nextLine();
			}
			System.out.println("Modelo do veiculo: ");
			modelo = read.nextLine();

			System.out.println("Marca do veiculo: ");
			marca = read.nextLine();

			System.out.println("Ano do veiculo: ");
			ano = read.nextInt();
			read.nextLine();

			Veiculo veiculo_frota = new Veiculo(placa, marca, modelo, ano);
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					s.addVeiculoFrota(cnpj_cliente_addVeiculo, code_frota, veiculo_frota);
					// atualizar o valor dos seguros apos tal operacao
					for(Seguro seguro : s.getListaSeguros()){
						seguro.calcularValor();
					}
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;

		case EXCLUIR_VEICULO_FROTA:
			System.out.println("Nome da seguradora em que estao as frotas:");
			nome = read.nextLine();
			existe = false;
			System.out.println("Entre com o cnpj do cliente: ");
			String cnpj_cliente_remVeiculo = read.nextLine();
			System.out.println("Entre com o code da frota em que sera adicionado um veiculo: ");
			code_frota = read.nextLine();
			System.out.println("Placa do veiculo: ");
			placa = read.nextLine();
			while((Validacao.validarPlaca(placa))==false){
				System.out.println("Placa eh invalida! Digite outra placa: ");
				placa = read.nextLine();
			}
			// assumindo que nao ha seguradoras com mesmo nome
			for(Seguradora s : listaSeguradoras){
				if(s.getNome().equals(nome)){
					s.removerVeiculoFrota(cnpj_cliente_remVeiculo, code_frota, placa);
					// atualizar o valor dos seguros apos tal operacao
					for(Seguro seguro : s.getListaSeguros()){
						seguro.calcularValor();
					}
					existe = true;
				}
			}
			// nao achou seguradora com tal nome
			if(!existe)
				System.out.println("Tal seguradora nao foi cadastrada!");
			break;
			default:
				break;
		}
	}

    // executa o submenu (exibir, ler e executar)
	private static void executarSubmenu(MenuOperacoes op) throws ParseException {
		SubmenuOperacoes opSubmenu;
		do {
			exibirSubmenu(op);
			opSubmenu = lerOpcaoSubmenu(op);
			executarOpcaoSubMenu(opSubmenu);
		}while(opSubmenu != SubmenuOperacoes.VOLTAR);
	}

    
    public static void main(String[] args) throws ParseException {

        listaSeguradoras = new ArrayList<Seguradora>();
        
        // instanciando uma seguradora
        Seguradora seguradora = new Seguradora("46.068.425/0001-33", "Seguradora1", "1912312-0911", "Rua dos girassois", "seg@gmail.com");
		listaSeguradoras.add(seguradora);

        // instanciando uma frota
        Frota frota = new Frota("091011", null);


        // instanciando um veiculo
        Veiculo veiculo = new Veiculo("FPM4B84", "renault", "kwid", 2022);
        frota.addVeiculo(veiculo);


        // instanciando um cliente pf
        ClientePF clientePF = new ClientePF("Estela", "Rua das Moitas", "1994444-3333", "estela@gmail.com", "alta", "feminino", "524.114.568-08",null);
        String data1 = "11/12/2000";
        Date data1_date = new SimpleDateFormat("dd/MM/yyyy").parse(data1);
        clientePF.setDataNascimento(data1_date);
		seguradora.getListaClientes().add(clientePF);

        // instanciando um cliente pj
        ClientePJ clientePJ = new ClientePJ("unicamp", "Rua das Peras", "1994444-2222", "unicamp@gmail.com", "46.068.425/0001-33", null, 14);
        String data2 = "11/12/1990";
        Date data2_date = new SimpleDateFormat("dd/MM/yyyy").parse(data2);
        clientePJ.setDataFundacao(data2_date);
		seguradora.getListaClientes().add(clientePJ);

        // instanciando um condutor
        Condutor condutor = new Condutor("524.115.108-70", "Luisa", "1997141-0911",
        "Rua das rosas", "luisa@gmail.com", data2_date, null);
        String data3 = "04/12/1990";
        Date data3_date = new SimpleDateFormat("dd/MM/yyyy").parse(data3);
        clientePJ.setDataFundacao(data3_date);

        // instanciando um seguro pj
        SeguroPJ seguroPJ = new SeguroPJ(frota, clientePJ, 12345, data2_date, data3_date, seguradora);
        String data_inicio1 = "04/12/1990";
        Date data_inicio1_date = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio1);
        seguroPJ.setDataInicio(data_inicio1_date);
        String data_fim1 = "04/12/1991";
        Date data_fim1_date = new SimpleDateFormat("dd/MM/yyyy").parse(data_fim1);
        seguroPJ.setDataFim(data_fim1_date);
		seguradora.getListaSeguros().add(seguroPJ);

        // instanciando um seguro pf
        SeguroPF seguroPF = new SeguroPF(veiculo, clientePF, 679810, data2_date, data3_date, seguradora);
        String data_inicio2 = "04/12/1992";
        Date data_inicio2_date = new SimpleDateFormat("dd/MM/yyyy").parse(data_inicio2);
        seguroPF.setDataInicio(data_inicio2_date);
        String data_fim2 = "04/12/1993";
        Date data_fim2_date = new SimpleDateFormat("dd/MM/yyyy").parse(data_fim2);
        seguroPJ.setDataFim(data_fim2_date);
		seguradora.getListaSeguros().add(seguroPF);

        // gerando um sinistro
        seguroPF.gerarSinistro(141592, "11/11/2000", "Rua do morangos", seguradora, veiculo, condutor, seguroPF);


        // chamando os metodos toString() de cada objeto instanciado
        System.out.println("###################");
        System.out.println(seguradora.toString());
        System.out.println("###################");

        System.out.println("###################");
        System.out.println(frota.toString());
        System.out.println("###################");

        System.out.println("###################");
        System.out.println(veiculo.toString());
        System.out.println("###################");

        System.out.println("###################");
        System.out.println(clientePF.toString());
        System.out.println("###################");

        System.out.println("###################");
        System.out.println(clientePJ.toString());
        System.out.println("###################");

        System.out.println("###################");
        System.out.println(condutor.toString());
        System.out.println("###################");

        System.out.println("###################");
        System.out.println(seguroPF.toString());
        System.out.println("###################");

        System.out.println("###################");
        System.out.println(seguroPJ.toString());
        System.out.println("###################");

		System.out.println("Exemplo do metodo LISTAR SEGUROS da classe seguradora:");
		seguradora.listarSeguros();

		System.out.println("Exemplo do metodo LISTAR CLIENTES da classe seguradora (tipo PF):");
		seguradora.listarClientes("PF");

        MenuOperacoes operacao;
		do {
			exibirMenuExterno();
			operacao = lerOpcaoMenuExterno();
			executarOpcaoMenuExterno(operacao);
		}while(operacao != MenuOperacoes.SAIR);
		System.out.println("SAIU DO PROGRAMA!");
	}



}
