package cat.udl.eps.entsoftarch.webmemoryapi.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EditGameStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @And("^I set the reward value (\\d+) of game with the id (\\d+)$")
    public void iSetTheRewardValueOfGameWithTheId(int reward, int id) throws Throwable {
        JSONObject game = new JSONObject();
        game.put("Reward", reward);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}", id).contentType(MediaType.APPLICATION_JSON).content(game.toString())
                        .accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @When("^I stop a game with the id (\\d+)$")
    public void iStopAGameWithTheName(int id) throws Throwable {
        JSONObject game = new JSONObject();
        game.put("gameFinished", true);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{id}", id).contentType(MediaType.APPLICATION_JSON).content(game.toString())
                        .accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}
