import java.sql.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        List moradores = new ArrayList<>();

        int choice;

        String url = "jdbc:sqlite:C:\\Users\\Julio\\Documents\\IntelliJ\\Condominio\\DB\\CondominioJava.db";

        try (Connection con = DriverManager.getConnection(url)){
            System.out.println("Voce deseja adicionar ou visualizar cadastros ? Digite 1 para adicionar / 2 Para Visualizar Pessoa especifica / 3 Para visualizar todos / ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    adicionarPessoa(scanner, con);
                    break;
                 case 2:
                     buscarResultado(scanner, con);

                     break;
                case 3:
                    List retorno = retornarTodos(con);
                    int tamanho = retorno.size();
                    for (int i = 0; i < tamanho; i++){
                        System.out.print("Número " +  (i + 1) + ": ");
                        System.out.print(retorno.get(i));
                        System.out.println();

                    }
                    System.out.println("Deseja deletar algum ? S/N ");
                    String resposta = scanner.nextLine();
                    if (resposta.toUpperCase().equals("S")) {
                        deletarPessoa(scanner, con);
                    }

                    break;
                case 4:
                    deletarPessoa(scanner, con);
                    break;
                case 5:
                    criarTabela(con);
                    break;
                }
              //  con.close();
            }
            catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println("Não Deu");
            }

        }

        public static void buscarResultado(Scanner scanner, Connection con) throws SQLException {
           String query = "SELECT * FROM MORADORES WHERE NAME = ?";

           System.out.println("Quem voce deseja buscar ? ");
           String busca = scanner.nextLine();

           PreparedStatement stmt = con.prepareStatement(query);
           stmt.setString(1, busca);
           ResultSet retorno =  stmt.executeQuery();

           while (retorno.next()) {
               System.out.println("ID: " + retorno.getString(1));
               System.out.println("Nome: " + retorno.getString(2));
               System.out.println("RG: " + retorno.getString(3));
               System.out.println("AP: " + retorno.getString(4));
               System.out.println("CPF: " + retorno.getString(5));
               System.out.println("Telefone: " + retorno.getString(6));
               System.out.println("Celular: " + retorno.getString(7));
               System.out.println("Veiculo: " + retorno.getString(8));
               System.out.println("Placa do Veiculo: " + retorno.getString(9));
               System.out.println("Conjuge: " + retorno.getString(10));
               }
        }

        public static void adicionarPessoa(Scanner scanner, Connection con)  {

            String insert = "INSERT INTO `MORADORES` VALUES(?, ?, ?, ? , ? , ? , ? , ?, ?, ?)";


            System.out.println("Insira o name: ");
            String nome = scanner.nextLine();

            System.out.println("Insira o RG: ");
            String RG = scanner.nextLine();

            System.out.println("Insira o AP");
            int apartamento = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Insira o CPF");
            String cpf = scanner.nextLine();
            System.out.println("Insira o telefone");
            String telefone = scanner.nextLine();
            System.out.println("Insira o celular");
            String celular = scanner.nextLine();
            System.out.println("Insira o veiculo");
            String veiculo = scanner.nextLine();
            System.out.println("Insira a placa do veiculo");
            String veiculoplaca = scanner.nextLine();
            System.out.println("Insira o conjuge");
            String conjuge = scanner.nextLine();
            Morador m1 =  new Morador(nome, RG, apartamento, cpf, telefone, celular, veiculo, veiculoplaca, conjuge);
            try {
                PreparedStatement ps = con.prepareStatement(insert);
                ps.setString(2, m1.getName());
                ps.setString(3, m1.getRG());
                ps.setInt(4, m1.getAP());
                ps.setString(5, m1.getCPF());
                ps.setString(6, m1.getTelefone());
                ps.setString(7, m1.getCelular());
                ps.setString(8, m1.getVeiculo());
                ps.setString(9, m1.getVeiculoPlaca());
                ps.setString(10, m1.getConjuge());
                ps.execute();
            }
            catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }

        public static List retornarTodos(Connection con) throws  SQLException{
            String query = "SELECT * FROM MORADORES";

            PreparedStatement ps = con.prepareStatement(query);
            ResultSet retorno = ps.executeQuery();

            List moradoresRetorno = new ArrayList<>();
            HashMap<String, String> map = new HashMap<>();

            String[] moradoresArray = new String[10];

            while (retorno.next()){
                moradoresArray[0] = "Id: " + retorno.getString(1);
                moradoresArray[1] = "Nome: " + retorno.getString(2);
                moradoresArray[2] = "RG: " + retorno.getString(3);
                moradoresArray[3] = "AP: " + retorno.getString(4);
                moradoresArray[4] = "CPF: " + retorno.getString(5);
                moradoresArray[5] = "Telefone: " + retorno.getString(6);
                moradoresArray[6] = "Celular: " + retorno.getString(7);
                moradoresArray[7] = "Veiculo: " + retorno.getString(8);
                moradoresArray[8] = "Placa Veiculo: " + retorno.getString(9);
                moradoresArray[9] = "Conjuge: " + retorno.getString(10);


//                map.put ("Id" , retorno.getString(1));
//                map.put ("Nome", retorno.getString(2));
//                map.put("RG", retorno.getString(3));
//                map.put("AP", retorno.getString(4));
//                map.put("CPF", retorno.getString(5));
//                map.put("Telefone", retorno.getString(6));
//                map.put("Celular", retorno.getString(7));
//                map.put("Veiculo", retorno.getString(8));
//                map.put("PlacaVeiculo", retorno.getString(9));
//                map.put("Conjuge", retorno.getString(10));

                moradoresRetorno.add(Arrays.toString(moradoresArray));
                //moradoresRetorno.add(map);

            }
            return moradoresRetorno;
        }

        public static void deletarPessoa(Scanner scanner, Connection con) throws SQLException {
            System.out.println("Quem voce deseja remover? digite o ID escolhido.");

            while(!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Por favor, Selecione ume ID válido ");
            }
            int idEscolhido = scanner.nextInt();

            String query = "DELETE FROM MORADORES WHERE ID = ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, idEscolhido);

            System.out.println("Removido.");

        }

        public static void criarTabela( Connection con) {
            try{
                List retorno = retornarTodos(con);

                String[] nomeColunas = {"Id","Nome", "RG", "AP", "CPF", "Telefone", "Celular", "Veiculo", "Veiculo Placa", "Conjuge"};

                System.out.println("Teste: ");
                System.out.println(retorno.get(0));

                JFrame frame =  new JFrame();
                frame.setTitle("Tabela");
                // Criar a tabela
                JTable table = new JTable(new DefaultTableModel(nomeColunas, 0));
                JScrollPane scrollPane = new JScrollPane(table);
                frame.add(scrollPane);
                frame.setSize(500, 500);
                frame.setVisible(true);

                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.addRow(new Object[]{"Teste", "teste 2"});


            }

            catch(SQLException ex){
                System.out.printf(ex.getMessage());
                System.exit(500);
            };
            }



}
