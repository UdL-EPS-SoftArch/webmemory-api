package cat.udl.eps.entsoftarch.webmemoryapi.steps;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import cat.udl.eps.entsoftarch.webmemoryapi.domain.Player;
import cat.udl.eps.entsoftarch.webmemoryapi.repository.UserRepository;
import cucumber.api.PendingException;
import cucumber.api.java.en.And;
import cucumber.api.java.en.When;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

public class DepositCurrencyStepDefs {
    @Autowired
    private
    UserRepository playerRepository;
    @Autowired
    private StepDefs stepDefs;

    private Player player;

    @When("^I deposit (\\d+) currency into the wallet of player with username \"([^\"]*)\"$")
    public void iDepositCurrencyIntoTheWalletOfPlayerWithUsername(int currency, String username) throws Throwable {
        this.player = (Player) playerRepository.findByEmail(username);
        JSONObject playerObject = new JSONObject();
        playerObject.put("Currency", this.player.getCurrency() + currency);

        stepDefs.result = stepDefs.mockMvc.perform(patch("/players/{username}", username).contentType(MediaType.APPLICATION_JSON).content(playerObject.toString())
						.accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
							.andDo(print());
    }

    @When("^I remove (\\d+) currency from the wallet of player with username \"([^\"]*)\"$")
    public void iRemoveCurrencyIntoTheWalletOfPlayerWithUsername(float currency, String username) throws Throwable {
        this.player = (Player) playerRepository.findByEmail(username);
        JSONObject playerObject = new JSONObject();
        playerObject.put("Currency", this.player.getCurrency() - currency);

        stepDefs.result = stepDefs.mockMvc.perform(
                patch("/players/{username}", username).contentType(MediaType.APPLICATION_JSON).content(playerObject.toString())
                    .accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
						.andDo(print());
    }

    @And("^Player with username \"([^\"]*)\" has (\\d+) currency$")
    public void playerWithUsernameHasCurrency(String username, float currency) throws Throwable {
        stepDefs.result = stepDefs.mockMvc.perform(
                get("/players/{username}", username).accept(MediaType.APPLICATION_JSON).with(AuthenticationStepDefs.authenticate()))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.Currency", is(currency)));
    }
}