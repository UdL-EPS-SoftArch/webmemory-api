package cat.udl.eps.entsoftarch.webmemoryapi.steps;

import cucumber.api.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StopGameStepDefs {
    @Autowired
    private StepDefs stepDefs;

    @When("^I stop a game with the name \"([^\"]*)\"$")
    public void iStopAGameWithTheName(String gameName) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/games/{id}", gameName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print()).andExpect(status().isOk());
    }

    @When("^I stop a game with the name \"([^\"]*)\" that has already been stopped$")
    public void iStopAGameWithTheNameThatHasAlreadyBeenStopped(String gameName) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                delete("/games/{id}", gameName)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(AuthenticationStepDefs.authenticate()))
                .andDo(print()).andExpect(status().isGone());
        //I was not sure if I am supposed to add .andExpected as responses inside the step def, do consult me if you see this
    }
}
