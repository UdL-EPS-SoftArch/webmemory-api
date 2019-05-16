package cat.udl.eps.entsoftarch.webmemoryapi.domain;

import lombok.NonNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Game{

    @Id
    public String GameId;

    public int BoardSeed;
    public int Reward;

    public int[][] cardNumbers;
    //public int[][] cardNumbers = new int[6][6];
    public boolean[][] cardOrientation;
    //public boolean[][] cardOrientation = new boolean[6][6];

    public GameDifficulty difficulty;

    public boolean gameFinished;

    public boolean isGameFinished() { return gameFinished; }

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ZonedDateTime CreatedAt, EndedAt;


}
