package cat.udl.eps.entsoftarch.webmemoryapi.steps;

import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

public class DeleteGameStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @When("^I delete a game with id \"([^\"]*)\"$")
    public void iDeleteAGameWithId(String gameName) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/games/{id}", gameName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print());
    }

    @And("^The game with id \"([^\"]*)\" does not exist$")
    public void theGameWithIdDoesNotExist(String gameName) throws Throwable {
            stepDefs.result = stepDefs.mockMvc.perform(
                    get("/games/{id}", gameName)
                            .accept(MediaType.APPLICATION_JSON)
                            .with(AuthenticationStepDefs.authenticate()))
                    .andDo(print());
    }
}


