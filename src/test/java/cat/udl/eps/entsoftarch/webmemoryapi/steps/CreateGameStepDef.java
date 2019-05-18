package cat.udl.eps.entsoftarch.webmemoryapi.steps;


import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.Matchers.is;


import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.slf4j.LoggerFactory;

public class CreateGameStepDef {

    @Autowired
    private StepDefs stepDefs;

    @When("^I create a game with the name \"([^\"]*)\"$")
    public void iCreateAGameWithTheID(String name) throws Throwable {
        JSONObject newGame = new JSONObject();
        newGame.put("gameName", name);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/games").contentType(MediaType.APPLICATION_JSON).content(newGame.toString()).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^A game with the id (\\d+) and name \"([^\"]*)\" has been created$")
    public void aGameWithTheIdAndNameHasBeenCreated(int id, String name) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/games/{id}", id).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate())).andDo(print());
    }

   /* @When("^I create a new game with the name \"([^\"]*)\" and set the buy in value (\\d+) below zero$")
    public void iCreateANewGameWithTheNameAndSetTheBuyInValueBelowZero(String gameName, float newValue) throws Throwable {
        if (newValue < 0) {
            stepDefs.result = stepDefs.mockMvc.perform(get("/games/{id}", gameName).accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isConflict());
        }
    }*/
}
