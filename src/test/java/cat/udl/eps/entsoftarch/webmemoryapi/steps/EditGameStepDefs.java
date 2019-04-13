package cat.udl.eps.entsoftarch.webmemoryapi.steps;

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

    @When("^I set the buy-in value (\\d+) of game$")
    public void ChangeGameCurrency(float newCurrency) throws Throwable {
        JSONObject game = new JSONObject();
        game.put("currency", newCurrency);
        stepDefs.result = stepDefs.mockMvc.perform(
                put("/games/{currency}", newCurrency).contentType(MediaType.APPLICATION_JSON).content(game.toString())
                        .accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
                .andDo(print()).andExpect(status().isOk());
    }

    @When("^I set the buy-in value (\\d+) of game \"([^\"]*)\" to less than zero$")
    public void iChangeTheOfGameToLessThanZero(float newValue, String gameName) throws Throwable {
        if (newValue < 0) {
            stepDefs.result = stepDefs.mockMvc.perform(get("/games/{id}", gameName).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isConflict());
        }
    }
}
