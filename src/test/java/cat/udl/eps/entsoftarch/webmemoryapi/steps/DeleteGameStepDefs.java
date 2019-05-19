package cat.udl.eps.entsoftarch.webmemoryapi.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class DeleteGameStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @When("^I delete a game with id (\\d+)$")
    public void iDeleteAGameWithId(int id) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/games/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @Then("^The game with id (\\d+) does not exist$")
    public void theGameWithIdDoesNotExist(int id) throws Throwable {
            stepDefs.result = stepDefs.mockMvc.perform(
                    get("/games/{id}", id)
                            .accept(MediaType.APPLICATION_JSON)
                            .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
    }
}


