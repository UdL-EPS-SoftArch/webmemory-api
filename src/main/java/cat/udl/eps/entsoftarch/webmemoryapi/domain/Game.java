package cat.udl.eps.entsoftarch.webmemoryapi.domain;

import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Random;

@Entity
public class Game{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    public String gameName;

    public int boardSeed;
    public int reward;

    // cardNumbers, being a static array, can't have its size defined at runetime. So we'll just
    // accept the overhead and show sizes depending on difficulty. JSON can't serialize objects above
    // 256 bytes, I think, so we'll separate them into four distinct arrays.

    // Difficulties: Easy - 3x3
    //               Medium - 4x4
    //               Hard - 5x5

    public GameDifficulty difficulty;
    //public int[][] cardNumbers;
    public int[][] cardNumbersEasy = new int[3][3];
    public int[][] cardNumbersMedium = new int[4][4];
    public int[][] cardNumbersHard = new int[5][5];

    public void generateCards(int[][] cards, int size) {
        int count = 1;
        size--;
        for (int i=0; i<size/2; i++) {
            for (int j = 0; j<size/2; j++) {
                cards[i][j] = count;
                cards[size - i][size - i] = count;
                count++;
            }
        }
        //Generate middle row if cards are odd
        if ((size + 1) % 2 == 1) {
            for (int i = 0; i < size/2; i++) {
                cardNumbersHard[size/2][i] = count;
                cardNumbersHard[size/2][size - i] = count;
                count++;
            }
        }
        Random random = new Random();
        //Now shuffle the 2d array
        for (int i=size; i > 0; i--){
            for (int j=size; j > 0; j--){
                int m = random.nextInt(i+1);
                int n = random.nextInt(j+1);
                int temp = cards[i][j];
                cards[i][j] = cards[m][n];
                cards[m][n] = temp;

            }
        }
    }

    public boolean gameFinished;

    public boolean isGameFinished() { return gameFinished; }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ZonedDateTime CreatedAt, EndedAt;

}
