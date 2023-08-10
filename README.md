# Comic

Projeto está usando arquitetura MVVM com clean architecture

![alt text](/doc/images.png)

foram feitos 
- teste unitarios modulo app e domain
- tratamento de erro para quando der algum erro ao carregar os itens mostrar uma tela amigavel para o usuario
- ao rotacionar o celular e caso ja tenha sido carregado os dados dos quadrinhos não é feito outra chamada para api

Modulos
- app: Modulo onde contém todas as views(fragment, activity)
- data: Modulo onde contem todos os componentes para chamar dados do mundo externo como apis
- domain: Modulo onde contém todos os useCases que contém regras de negocio para serem usada no modulo app
