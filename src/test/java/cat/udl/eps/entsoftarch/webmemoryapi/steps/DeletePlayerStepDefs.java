package cat.udl.eps.entsoftarch.webmemoryapi.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DeletePlayerStepDefs {

    @Autowired
    private StepDefs stepDefs;

    @When("^I delete a player with username \"([^\"]*)\"$")
    public void iDeleteAPlayerWithUsername(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(delete("/players/{username}", username).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
                .andExpect(status().isNoContent());
    }

    @And("^The player with the username \"([^\"]*)\" has been deleted$")
    public void thePlayerWithUsernameHasBeenDeleted(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(get("/players/{username}", username).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @And("^The player with the username \"([^\"]*)\" has not been deleted$")
    public void ThePlayerWithTheUsernameHasNotBeenDeleted(String username) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(get("/players/{username}", username).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @And("^I register a player with username \"([^\"]*)\", email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void iRegisterAPlayerWithUsernameEmailAndPassword(String username, String email, String password) throws Throwable {

        JSONObject player = new JSONObject();
        player.put("username", username);
        player.put("email", email);
        player.put("password", password);
        stepDefs.result = stepDefs.mockMvc.perform(
                post("/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(player.toString())
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }
}