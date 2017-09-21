package br.una.aulamongo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class JavaMongo {
    public static void main(String[] args) {

        //conexão com o mongo
        MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
        //escolhendo o banco de dados
        MongoDatabase database = mongoClient.getDatabase("local");

        //buscando uma coleção
        MongoCollection<Document> filmes = database.getCollection("filmes");


        System.out.println("Nome do filme");

        //leitor ouvindo entrada pradão
        Scanner scanner = new Scanner(System.in);
        //espera a primera linha ser digitada
        String nomeFilme = scanner.nextLine();
        //cria documento com o nome do filme
        Document filme = new Document("nome", nomeFilme);
        filme.append("data_cadastro",new Date());
        //insere o filme no mongo
        filmes.insertOne(filme);

        System.out.println("meus filmes");
        System.out.println("Filme\tData");
        FindIterable<Document> filmesCadastrados = filmes.find();
        for(Document meuFilme: filmesCadastrados){
            Date data_cadastro = meuFilme.get("data_cadastro", Date.class);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            System.out.println(meuFilme.get("nome") + "\t" + sdf.format(data_cadastro));
        }
    }
}
