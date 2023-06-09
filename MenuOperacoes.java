public enum MenuOperacoes {
	CADASTRAR("Cadastros", new SubmenuOperacoes[] {
			SubmenuOperacoes.CADASTRAR_CLIENTE,
			SubmenuOperacoes.CADASTRAR_VEICULO,
			SubmenuOperacoes.CADASTRAR_SEGURADORA,
			SubmenuOperacoes.VOLTAR
	}),
	LISTAR("Listar", new SubmenuOperacoes[] {
			SubmenuOperacoes.LISTAR_SEGURADORAS,
			SubmenuOperacoes.LISTAR_CLIENTES,
			SubmenuOperacoes.LISTAR_SEGUROS,
			SubmenuOperacoes.LISTAR_SEGUROS_POR_CLIENTE,
			SubmenuOperacoes.LISTAR_SINISTROS_POR_CLIENTE,
			SubmenuOperacoes.LISTAR_SINISTROS,
			SubmenuOperacoes.LISTAR_CONDUTORES,
			SubmenuOperacoes.LISTAR_VEICULOS,
			SubmenuOperacoes.LISTAR_FROTAS,
			SubmenuOperacoes.VOLTAR
	}),
	EXCLUIR("Excluir", new SubmenuOperacoes[] {
			SubmenuOperacoes.EXCLUIR_CLIENTE,
			SubmenuOperacoes.EXCLUIR_VEICULO,
			SubmenuOperacoes.EXCLUIR_SEGURO,
			SubmenuOperacoes.VOLTAR}),
    CALCULAR_RECEITA("Calcular Receita", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	GERAR_SEGURO("Gerar Seguro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	GERAR_SINISTRO("Gerar Sinistro", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
	ATUALIZAR_FROTA("Atualizar Frota", new SubmenuOperacoes[] {
			SubmenuOperacoes.EXCLUIR_FROTA,
			SubmenuOperacoes.EXCLUIR_VEICULO_FROTA,
			SubmenuOperacoes.ADICIONAR_VEICULO_FROTA,
			SubmenuOperacoes.VOLTAR
	}),
    AUTORIZAR_CONDUTOR("Autorizar Condutor", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
    DESAUTORIZAR_CONDUTOR("Desautorizar Condutor", new SubmenuOperacoes[] {SubmenuOperacoes.VOLTAR}),
    SAIR("Sair", new SubmenuOperacoes[] {});
	
	private final String descricao;
	private final SubmenuOperacoes[] submenu;
	
	MenuOperacoes(String descricao, SubmenuOperacoes[] submenu){
		this.descricao = descricao;
		this.submenu = submenu;
	}
	
	//getters
	public String getDescricao() {
		return descricao;
	}
	
	public SubmenuOperacoes[] getSubmenu() {
		return submenu;
	}
}