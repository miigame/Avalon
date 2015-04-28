package game.mii.avalon;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by mii on 2015/4/22.
 */
public class GameSetting {
    private String loyal_servant_of_Arthur = "亞瑟的忠臣";//5 :Generic Good Guy
    private String Merlin = "梅林";
    private String Percival = "派西維爾";
    private String minion_of_Mordred = "莫德雷德的爪牙";//3 :Generic Bad Guy
    private String Assassin = "刺客";
    private String Morgana = "莫甘娜";
    private String Mordred = "莫德雷德";
    private String Oberon = "奧伯倫";
    private int good_count = 3;
    private int bad_count = 2;
    private ArrayList<String> characters = new ArrayList<String>();
    public ArrayList<String> GameSetting(int count, boolean hasPercival, boolean hasMorgana, boolean hasMordred, boolean hasOberon){
        if(count < 5 || count > 10) {
            return null;
        }

        switch(count){
            case 5:
                good_count = 3;
                bad_count = 2;
                break;
            case 6:
                good_count = 4;
                bad_count = 2;
                break;
            case 7:
                good_count = 4;
                bad_count = 3;
                break;
            case 8:
                good_count = 5;
                bad_count = 3;
                break;
            case 9:
                good_count = 6;
                bad_count = 3;
                break;
            case 10:
                good_count = 6;
                bad_count = 4;
                break;
        }
        characters.add(Merlin);
        good_count--;
        characters.add(Assassin);
        bad_count--;
        if (hasPercival && good_count > 0) {
            characters.add(Percival);
            good_count--;
        }
        while(good_count > 0){
            characters.add(loyal_servant_of_Arthur);
            good_count--;
        }
        if (hasMorgana && bad_count > 0) {
            characters.add(Morgana);
            bad_count--;
        }
        if (hasMordred && bad_count > 0) {
            characters.add(Mordred);
            bad_count--;
        }
        if (hasOberon && bad_count > 0) {
            characters.add(Oberon);
            bad_count--;
        }
        while(bad_count > 0){
            characters.add(minion_of_Mordred);
            bad_count--;
        }
        Log.i("Avalon",characters.size() + " characters.");
        for(int i = 0; i < characters.size(); i++){
            Log.i("Avalon","characters "+(i+1)+" : "+characters.get(i));
        }
        return characters;

    }
}
