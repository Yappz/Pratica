package praticaordenacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Douglas Torres - 119110926 /  Mateus Duarte - 119126755
 */
public class praticaordenacao {

    
    public static void main(String[] args) throws  Exception {
        
       try{
            List<Item> listaItens = new ArrayList<Item>();
            
            LerArquivoCSV(listaItens);
            
            OrdenarListaPorCategoria(listaItens);

            System.out.println("==================== LISTA ANTES DA FORMATAÇÃO ====================");
            for (int i = 0; i < listaItens.size(); i++) {
                System.out.println("Jogo:"+ listaItens.get(i).getJogo() 
                        + ", Categoria:" + listaItens.get(i).getCategoria() 
                        + ", Nota: " + listaItens.get(i).getNota());
            }

            System.out.println("==================== LISTA DEPOIS DA FORMATAÇÃO ====================");
            OrdernarListaPorNota(listaItens);

            for (Item listaIten : listaItens) {
                System.out.println("Jogo:"+ listaIten.getJogo() 
                        + ", Categoria:" + listaIten.getCategoria() 
                        + ", Nota: " + listaIten.getNota());
            }
            EscreverArquivoCSV(listaItens);
       }
       catch(Exception e){
           throw e;
       }

    }
    
    public static void LerArquivoCSV(List<Item> listaItens) throws Exception{
        try{
            String path = "D:/Projetos/ProjetoDeOrdenacao/src/projetodeordenacao/files/JogosDesordenados.csv";
            BufferedReader br = new BufferedReader(new FileReader(path));
            String linha = "";
            while((linha = br.readLine()) != null){
                String[] jogo = linha.split(",");
                listaItens.add(new Item(jogo[0],jogo[1], Double.parseDouble(jogo[2])));
            }
        }
        catch(FileNotFoundException e){
            throw e;
        }
    }

    public static void EscreverArquivoCSV(List<Item> listaItens) throws IOException {

        FileWriter writer = new FileWriter("D:/Projetos/ProjetoDeOrdenacao/src/projetodeordenacao/files/JogosOrdenados.csv");

        String collect =  listaItens.stream()
        .map(item -> item.getJogo() + ", " + item.getCategoria() + ", " + item.getNota() + "\n")
        .collect(Collectors.joining(""));

        System.out.println(collect);

        writer.write(collect);
        writer.close();
    }

    //Realiza a ordenação por categoria
    public static void OrdenarListaPorCategoria(List<Item> listaItens){
        for(int i =0; i < listaItens.size();i ++){
            for(int j = i+1; j < listaItens.size(); j++){
                String categoriaA = listaItens.get(j).getCategoria();
                String categoriaB = listaItens.get(i).getCategoria();
                if(categoriaA.compareTo(categoriaB) < 1){
                    Item temp = listaItens.get(i);
                    listaItens.set(i, listaItens.get(j));
                    listaItens.set(j, temp);
                }
            }
        }
    }

    //Realiza a ordenação por avaliação
    public static void OrdernarListaPorNota(List<Item> listaItens) throws Exception{
        
        for(int i = 0; i < listaItens.size();i++){
            for(int j = i+1; j < listaItens.size(); j++){
                Item itemA = listaItens.get(i);
                Item itemB = listaItens.get(j);

                if(itemA.getNota() < itemB.getNota() 
                    && itemB.getCategoria().equals(itemA.getCategoria())){
                    Item temp = listaItens.get(i);
                    listaItens.set(i, listaItens.get(j));
                    listaItens.set(j, temp);
                }
            }
        }
    }
}
