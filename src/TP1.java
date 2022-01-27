
import java.util.Random;
import java.util.Scanner;

public class TP1 {
	static String[][] temasDoJogo = new String[50][50]; // Matriz para armazenar os temas.
	static  int i = 0, j = 1;

	public static void main(String[] args) {

		// Variaveis e constantes:
		int opcaoMenuInicial = 0, opcaoMenuTemas = 0, opcaoMenuPalavras = 0, opcaoTemas = 0, opcaoMenuPalavras1 = 0, unir = 0, b = 0, respostas= 0, contadorPalavras = 0;
		String opcaoBuscarTemas = null;
		String opcaoBuscarPalavras = null;
		Boolean temaIgual = false;
		Boolean palavraIgual = false;
		String temasTotais = "0";
		String palavrasTotais = "0";
		String valorBuscado = "0";
		String temaBusca = "0";
		String respostaJogar = "0";

		// Tela inicial:
		Scanner resposta = new Scanner(System.in); // Leitura da resposta do usu�rio.


		do {
			opcaoMenuInicial = Menu(opcaoMenuInicial, resposta); // Funcao do menu.

			// Opcoes do menu
			switch (opcaoMenuInicial) {
				case 1:
					do {
						opcaoMenuTemas = MenuTemas(opcaoMenuTemas, resposta);
						switch (opcaoMenuTemas) {
							case 1:
								String nomeDosTemas = " ";
								temasDoJogo[i][0] = GerenciarTemas(opcaoMenuTemas, resposta, temasDoJogo, nomeDosTemas,
										temasTotais, temaIgual, i);
								i++;
								break;
							case 2:
								// String valorBusca = GerenciarBusca(opcaoBuscarTemas,resposta);
								BuscarTemas(GerenciarBusca(opcaoBuscarTemas, resposta), temasDoJogo, i, j);
								break;
							case 3:
								Boolean validacao = false;
								do {
									System.out.print("|-------- Excluir tema --------|\n");
									System.out.print("| Digite um tema do jogo para  |\n");
									System.out.print("|         ser exluido          |\n");
									System.out.print("|------------------------------|\n");
									nomeDosTemas = " ";
									nomeDosTemas = resposta.next(); // Lembrar de por 0 quando por as palavras.
									validacao = ExcluirTema(nomeDosTemas, temasDoJogo, i);
								}while(!validacao);
								break;
							case 4: 
								System.out.println(" ");
								break;
							default: System.out.println("Opcao invalida. Por favor, escolha uma\n"
									+ "opcaoo de 1 a 4, somente.\n");
						}
					}while (opcaoMenuTemas != 4);

					break;

				case 2:
					do {
						opcaoMenuPalavras1 = Menu2( opcaoMenuPalavras1,resposta);
						switch(opcaoMenuPalavras1) {
							case 1:
								String palavraDosTemas = " ";
								GerenciarPalavras(opcaoMenuPalavras, valorBuscado, resposta, temasDoJogo, palavraDosTemas, palavrasTotais, palavraIgual, j, contadorPalavras);
								contadorPalavras++;
								j++;
								break;
							case 2:
								BuscarPalavra(GerenciarBuscaPalavras(opcaoBuscarPalavras, resposta), temasDoJogo, i, j);
								break;
							case 3:
								Boolean validacao2 = false;
								do {
									System.out.print("|------ Excluir palavra -------|\n");
									System.out.print("| Digite uma palavra do jogo   |\n");
									System.out.print("|       para ser exluido       |\n");
									System.out.print("|------------------------------|\n");
									palavraDosTemas = " ";
									palavraDosTemas = resposta.next(); // Lembrar de por 0 quando por as palavras.
									validacao2 = ExcluirPalavra(palavraDosTemas, temasDoJogo, j);
								}while(!validacao2);
								break;
							case 4:
								Listagem(temaBusca, unir, resposta, temasDoJogo);								
								break;
							case 5:
								System.out.println(" ");
								break;
							default: System.out.println("Opcao invalida. Por favor, escolha uma\n"
									+ "opcaoo de 1 a 5, somente.\n");
						}
					}while(opcaoMenuPalavras1 != 5);
					break;

				case 3:
					System.out.println("Jogar");
					String palavraChave = "";
					String letrasJaUsadas = "";
					String palavrasAdivinhadas = "";
					int erro = 0;
					Boolean encerrado = false;
					Random random = new Random();
					System.out.print("|-------- Vamos Jogar! --------|\n");
					System.out.print("| Digite um tema que você quer |\n");
					System.out.print("|       entrar pra jogar!      |\n");
					System.out.print("|------------------------------|\n");
					respostaJogar = resposta.next();
					Boolean check = BuscarTemas(respostaJogar, temasDoJogo,i, j);
					int c = 0;
					
					for (int a = 0; a < temasDoJogo.length; a++) {		
						if (respostaJogar.equals(temasDoJogo[a][0])) {
							c = a;
						}
					}
					if(!check) {
						break;
					}
					
					do {
						int posicaoSorteada = random.nextInt(contadorPalavras);
						if(posicaoSorteada != 0) {
							posicaoSorteada = random.nextInt(contadorPalavras);
						}
						palavraChave = temasDoJogo[c][posicaoSorteada];
						for(int k = 0; k < palavraChave.length(); k++) {
							palavrasAdivinhadas = "_";
							if(encerrado == true) {
								break;
							}
							for(int tentativas = 1; ; tentativas++) {
								System.out.print("|-----------------------------------|\n");
								System.out.print("| Tente adivinhar a palavra abaixo! |\n");
								System.out.print("|         Você tem 5 erros!         |\n");
								System.out.print("|-----------------------------------|\n");
								System.out.println("Tentativa: " + tentativas + " A palavra é: " + palavrasAdivinhadas);
								char letraTentada = new Scanner(System.in).next().charAt(0);
								if(letrasJaUsadas.indexOf(letraTentada)>= 0) {
									System.out.println("Voce ja tentou a letra: " + letraTentada);
									erro++;
								}else {
									letrasJaUsadas += letraTentada;
									if(palavraChave.indexOf(letraTentada) >= 0) {
										palavrasAdivinhadas = "";
										for(int l = 0; l <palavraChave.length(); l++) {
											palavrasAdivinhadas += letrasJaUsadas.indexOf(palavraChave.charAt(l))>=0 ? palavraChave.charAt(l) : "_";
										}
										if(palavrasAdivinhadas.contains("_")) {
											System.out.println("Acertou, essa letra existe na palavra!" + letraTentada);
										}else {
											System.out.println("Parabéns, você acertou a palavra!\n"
													+ "A palavra era: "+temasDoJogo[c][posicaoSorteada]);
											System.out.println(" ");
											encerrado = true;
											break;
										}
									}else {
										System.out.println("Poxa, essa letra não existe na palavra!\n" + letraTentada);
										erro++;
										if(erro == 5) {
											System.out.println("Voce perdeu \n");
											encerrado = true;
											break;
										}
									}
								}
								
							}
						}
					}while(!encerrado);
					break;
				case 4:
					System.out.println("Obrigada por jogar!\n"
							+ "--> Maria Abritta :) ");
					break;
				default:
					System.out.println("Opcao invalida. Por favor, escolha uma\n"
							+ "opcaoo de 1 a 4, somente.\n");

			}

		} while (opcaoMenuInicial != 4);

	}

	public static int Menu(int opcaoMenuInicial, Scanner resposta) {
		System.out.print("|------- Jogo da forca! -------|\n");
		System.out.print("|------------------------------|\n");
		System.out.print("| Opcao 1 - Gerenciar Temas    |\n");
		System.out.print("| Opcao 2 - Gerenciar Palavras |\n");
		System.out.print("| Opcao 3 - Jogar              |\n");
		System.out.print("| Opcao 4 - Sair               |\n");
		System.out.print("|------------------------------|\n");
		System.out.print("Digite uma opcao:\n");
		opcaoMenuInicial = resposta.nextInt();
		return opcaoMenuInicial;
	}

	public static int MenuTemas(int opcaoMenuTemas, Scanner resposta) {
		System.out.print("|-- Digite uma opcao --|\n");
		System.out.print("|----------------------|\n");
		System.out.print("|  1 - Cadastrar tema  |\n");
		System.out.print("|  2 - Buscar tema     |\n");
		System.out.print("|  3 - Excluir tema    |\n");
		System.out.print("|  4 - Voltar          |\n");
		System.out.print("|----------------------|\n");
		opcaoMenuTemas = resposta.nextInt();
		return opcaoMenuTemas;
	}

	public static String GerenciarTemas(int opcaoMenuTemas, Scanner resposta, String temasDoJogo[][],
		String nomeDosTemas, String temasTotais, Boolean temaIgual, int i) {
		do {
			System.out.print("|-------- Cadastrar tema ---------|\n");
			System.out.print("|---------------------------------|\n");
			System.out.print("|  Cadastre um tema para o jogo   |\n");
			System.out.print("| OBS: Nao pode ter temas iguais  |\n");
			System.out.print("|---------------------------------|\n");
			nomeDosTemas = " ";
			nomeDosTemas = resposta.next(); // Lembrar de por 0 quando por as palavras.
			temasDoJogo[i][0] = nomeDosTemas;
				if (TemasJaExiste(temasDoJogo, nomeDosTemas, i)) { 
					System.out.println(TemasJaExiste(temasDoJogo, nomeDosTemas, i));
					System.out.println("Esse tema já existe.\n");
			} else {
					if (nomeDosTemas.equals(temasTotais)) {
						temaIgual = true; // Nome repetido
						break;
					} else {
						temasDoJogo[i][0] = nomeDosTemas; // Se nao for repetido irei armazenar
						System.out.println("Na posicao " + i + " foi armazenado " + temasDoJogo[i][0]);
						System.out.println("");
						// i++; //Avancando as posicoes do vetor
						break;
					}
				}
		} while (!temaIgual);
		return temasDoJogo[i][0];
	}

	public static Boolean TemasJaExiste(String temas[][], String pesquisa, int i) {
		for (int c = 0; c < i; c++) { // Vetor que passa por todos os temas cadastrados ate o momento
			if (temas[c][0].equals(pesquisa)) { // Verifico se o tema ja foi registrado antes
				return true;
			}
		}
		return false;
	}

	public static String GerenciarBusca(String opcaoBuscarTemas, Scanner resposta) {
		System.out.print("|---------- Busca -----------|\n");
		System.out.print("|----------------------------|\n");
		System.out.print("|  Qual tema deseja buscar?  |\n");
		System.out.print("|----------------------------|\n");
		opcaoBuscarTemas = resposta.next();
		return opcaoBuscarTemas;
	}

	public static Boolean BuscarTemas(String valorBuscado, String temasDoJogo[][], int i, int j) {
		boolean existe = false;
		for (int a = 0; a < temasDoJogo.length; a++) {
			if (valorBuscado.equals(temasDoJogo[a][0])) {
				existe = true;
			}
		}
		if (existe) {
			System.out.println("Esse tema já foi cadastrado.\n");
		}else {
			System.out.println("Esse tema ainda não foi cadastrado.\n");
		}
		return existe;
	}
	
	public static Boolean ExcluirTema (String temaQueSeraExcluido, String temasDoJogo[][], int i) {
		if (TemasJaExiste(temasDoJogo, temaQueSeraExcluido, i)) { 
			System.out.println("Tema encontrado e será excluido.\n");
			int indice = BuscarPosicaoTema (temasDoJogo, temaQueSeraExcluido);
			temasDoJogo[indice][0] = " ";
			AjustarMatriz();
			return true;
		}else {
			System.out.println("Nao existe esse tema para ser excluido.\n");
			return false;
		}
	}
	
	public static int BuscarPosicaoTema (String temasDoJogo[][], String temaPesquisado) {
		int retorno = 99;
		for(int a = 0; a < temasDoJogo.length; a++) {
			if(!temasDoJogo[a][0].equals(null) && temasDoJogo[a][0].equals(temaPesquisado)) {
				retorno = a;
				break;
			}
		}
		return retorno;
	}

	public static void AjustarMatriz () {
		String[][] novosTemas = new String[50][50];
        int count = 0;
        for(int i = 0; i < temasDoJogo.length ; i++) {
            if(temasDoJogo[i][0] != null && !temasDoJogo[i][0].isEmpty() && !temasDoJogo[i][0].isBlank()) {
                novosTemas[count] = temasDoJogo[i];
                count++;
            }
        }
        i = count;
        temasDoJogo = novosTemas;
	}
	
	public static int Menu2(int opcaoMenuPalavras, Scanner resposta) {
		System.out.print("|--- Digite uma opcao ----|\n");
		System.out.print("|-------------------------|\n");
		System.out.print("|  1 - Cadastrar palavra  |\n");
		System.out.print("|  2 - Buscar palavra     |\n");
		System.out.print("|  3 - Excluir palavra    |\n");
		System.out.print("|  4 - Listagem           |\n");
		System.out.print("|  5 - Voltar             |\n");
		System.out.print("|-------------------------|\n");
		System.out.print("Digite uma opcao:\n");
		opcaoMenuPalavras = resposta.nextInt();
		return opcaoMenuPalavras;
	}
	
	public static void GerenciarPalavras(int opcaoMenuPalavras, String valorBuscado, Scanner resposta, String temasDoJogo[][], String palavraDosTemas, String palavrasTotais, Boolean palavraIgual, int j, int contadorPalavras) {
		System.out.print("|------ Cadastrar palavra --------|\n");
		System.out.print("|---------------------------------|\n");
		System.out.print("|  Pesquise um tema que já existe |\n");
		System.out.print("| para poder cadastrar uma palavra|\n");
		System.out.print("|---------------------------------|\n");
		boolean existe = false;
		valorBuscado = resposta.next();
		int b = 0;
		
		for (int a = 0; a < temasDoJogo.length; a++) {		
			if (valorBuscado.equals(temasDoJogo[a][0])) {
				existe = true;
				b = a;
			}
		}
		
		if (existe) {
			System.out.println("Esse tema existe!\n");
			do {
				System.out.print("|---- -- Cadastrar palavra --------|\n");
				System.out.print("|----------------------------------|\n");
				System.out.print("| Cadastre um palavra para um tema |\n");
				System.out.print("| OBS: Nao pode ter palvras iguais |\n");
				System.out.print("|----------------------------------|\n");
				palavraDosTemas = " ";
				palavraDosTemas = resposta.next();
					if(PalavraJaExiste(temasDoJogo, palavraDosTemas, j)) {
						System.out.println("Essa palavra já existe\n");
					}else {
						if (palavraDosTemas.equals(palavrasTotais)) {
							palavraIgual = true; //Nome repetido
							break;
						}else {
							int indice = BuscarIndiceUltimaPalavra(b);
							if(indice > 51) {
								System.out.println("Tema lotado.");
							}else {
								temasDoJogo[b][indice] = palavraDosTemas; // Se nao for repetido irei armazenar
								System.out.println("Na posicao " + indice + " foi armazenado " + temasDoJogo[b][indice]);
								contadorPalavras++;
								System.out.println("");
								break;
							}
						}
					}
			}while(!palavraIgual);
		}else {
			System.out.println("Esse tema ainda não foi cadastrado.\n");
		}
	}
	
	public static int BuscarIndiceUltimaPalavra(int i) {
		int retorno = 99;
		for(int a = 0; a < temasDoJogo[i].length; a++) {
			if(temasDoJogo[i][a] == null) {
				retorno = a;
				break;
			}
		}
		return retorno;
	}
	
	public static Boolean PalavraJaExiste(String temas[][], String pesquisa, int j) {
		for(int i = 0; i < temas.length ; i++) {
            for(int a = 0; a < temas[i].length ; a++) {
                if(temas[i][a] != null && temas[i][a].equals(pesquisa)) {
                   return true;
                }
            }
        }
		return false;
	}
	
	public static String GerenciarBuscaPalavras(String opcaoBuscarPalavras, Scanner resposta) {
		System.out.print("|---------- Busca -----------|\n");
		System.out.print("|----------------------------|\n");
		System.out.print("|  Qual tema deseja buscar?  |\n");
		System.out.print("|----------------------------|\n");
		opcaoBuscarPalavras= resposta.next();
		return opcaoBuscarPalavras;
	}
	
	public static void BuscarPalavra(String valorBuscado, String temasDoJogo[][], int i, int j) {
		boolean existe = false;
		for (int a = 0; a < temasDoJogo.length; a++) {
			if (valorBuscado.equals(temasDoJogo[0][a])) {
				existe = true;
			}
		}
		if (existe)
			System.out.println("Essa palavra já foi cadastrada.\n");
		else
			System.out.println("Essa palavra ainda não foi cadastrada.\n");
	}
	
	public static Boolean ExcluirPalavra(String palavraQueSeraExcluido, String temasDoJogo[][],  int i) {
		if (PalavraJaExiste(temasDoJogo, palavraQueSeraExcluido, j)) { 
			System.out.println("Palavra encontrada e será excluida.\n");
			for(int a = 0; a < temasDoJogo.length; a++) {
				int indice = BuscarPosicaoPalavra (temasDoJogo[a], palavraQueSeraExcluido);
				if(indice < 99) {
					temasDoJogo[a][indice] = " ";
					AjustarMatrizPalavras();
				}
			}
			return true;
		}else {
			System.out.println("Nao existe essa palavra para ser excluida.\n");
			return false;
		}
	}
	
	public static int BuscarPosicaoPalavra(String tema[], String palavraPesquisada) {
		int retorno2 = 99;
		for(int a = 0; a < tema.length; a++) {
			if(tema[a] != null && tema[a].equals(palavraPesquisada)) {
				retorno2 = a;
			}
		}
		return retorno2;
	}
	
	public static void AjustarMatrizPalavras() {
		String[] novasPalavras = new String[50];
		int count2 = 0;
        for(int j = 0; j < temasDoJogo.length ; j++) {
        	for(int b = 0; b < temasDoJogo[j].length; b++) {
        		if(temasDoJogo[j][b] != null && !temasDoJogo[j][b].isEmpty() && !temasDoJogo[j][b].isBlank()) {
                	novasPalavras[count2] = temasDoJogo[j][b];
                    count2++;
                }
        	}
            temasDoJogo[j] = novasPalavras;
            novasPalavras = new String[50];
        }
	}
	
	public static void Listagem(String temaBusca, int unir, Scanner resposta, String temasDoJogo[][]){
		System.out.print("|-------- Listagem ----------|\n");
		System.out.print("|----------------------------|\n");
		System.out.print("|  Qual tema deseja listar?  |\n");
		System.out.print("|----------------------------|\n");
        boolean temaExiste = false;
        temaBusca = resposta.next();

        for(int d = 0; d < i; d++) {
            if(temasDoJogo[d][0].equals(temaBusca)) {
                System.out.print("Esse tema foi cadastrado.\n");
                temaExiste = true;
                unir = d;
                break;
            } 
        }
        
        if(!temaExiste){
            System.out.print("Esse tema ainda não foi cadastrado.\n");
        }
        
        if(temaExiste){
            System.out.println("Tema: "+temasDoJogo[unir][0]+"\n"); //ERRO NA LISTAGEM DO PRIMEIRO TEMA!!!!!!!!!
            for(int a = 1; a < 50; a++){
                if(temasDoJogo[unir][a] != null){
                    System.out.println("palavra " + a + " - "+temasDoJogo[unir][a]);
                    System.out.println(" ");
                }
            }
        }
        
    }
	
}
