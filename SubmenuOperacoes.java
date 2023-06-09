public enum SubmenuOperacoes {
	CADASTRAR_CLIENTE("Cadastrar cliente"),
	CADASTRAR_VEICULO("Cadastrar veiculo"),
	CADASTRAR_SEGURADORA("Cadastrar seguradora"),
	LISTAR_SEGURADORAS("Listar seguradoras"),
	LISTAR_CLIENTES("Listar cliente"),
	LISTAR_SINISTROS("Listar sinistros"),
	LISTAR_SEGUROS("Listar seguros"),
	LISTAR_CONDUTORES("Listar condutores"),
	LISTAR_SEGUROS_POR_CLIENTE("Listar seguros por cliente"),
	LISTAR_SINISTROS_POR_CLIENTE("Listar sinistros por cliente"),
	LISTAR_FROTAS("Listar frotas"),
	LISTAR_VEICULOS("Listar veiculo"),
	EXCLUIR_CLIENTE("Excluir cliente"),
	EXCLUIR_VEICULO("Excluir veiculo"),
	EXCLUIR_SEGURO("Excluir sininstro"),
    EXCLUIR_FROTA("Excluir frota"),
    ADICIONAR_VEICULO_FROTA("Adicionar veiculo a uma frota"),
	EXCLUIR_VEICULO_FROTA("Excluir veiculo de uma frota"),
	VOLTAR("Voltar");

	
	private final String descricao;
	

	SubmenuOperacoes(String descricao){
		this.descricao = descricao;
	}
	

	public String getDescricao() {
		return descricao;
	}
}