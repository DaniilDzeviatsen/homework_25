import by.teachmeskills.dzeviatsen.models.Show;
import by.teachmeskills.dzeviatsen.repository.ShowRepository;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        ShowRepository showRepository=new ShowRepository();
        try{
            List<Show>showlist=showRepository.listShows();
            for (Show show:showlist){
                System.out.println(show.toString());
            }
        }catch (Exception e){
            System.out.println("ERROR");
        }
    }
}