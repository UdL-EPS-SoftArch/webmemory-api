package cat.udl.eps.entsoftarch.webmemoryapi.steps;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class CreateGameStepDef {

    @Autowired
    private StepDefs stepDefs;

    @When("^I create a game with the name \"([^\"]*)\"$")
    public void iCreateAGameWithTheName(String name) throws Throwable {
        JSONObject newGame = new JSONObject();
        newGame.put("name", name);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games").contentType(MediaType.APPLICATION_JSON).content(newGame.toString()).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^A game with the id and name \"([^\"]*)\" has been created$")
    public void aGameWithTheIdAndNameHasBeenCreated(String gameName) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", gameName).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

    @When("^I create a new game with the name \"([^\"]*)\" but the name already exists$")
    public void iCreateANewGameWithTheNameButTheNameAlreadyExists(String gameName) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/games/{id}", gameName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print()).andExpect(status().isConflict());
    }

    @When("^I create a new game with the name \"([^\"]*)\" and set the buy in value (\\d+) below zero$")
    public void iCreateANewGameWithTheNameAndSetTheBuyInValueBelowZero(String gameName, float newValue) throws Throwable {
        if (newValue < 0) {
            stepDefs.result = stepDefs.mockMvc.perform(get("/games/{id}", gameName).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isConflict());
        }
    }
}
