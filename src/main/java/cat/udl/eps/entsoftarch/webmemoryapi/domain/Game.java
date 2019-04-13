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
    @NotBlank
    public int BoardSeed;
    @NotBlank
    public int Reward;

    @NonNull
    public int[][] cardNumbers = new int[6][6];
    @NonNull
    public boolean[][] cardOrientation = new boolean[6][6];

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    public ZonedDateTime CreatedAt, EndedAt;


}
