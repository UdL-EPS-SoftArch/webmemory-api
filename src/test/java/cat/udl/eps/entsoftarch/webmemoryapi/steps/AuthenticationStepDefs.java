package cat.udl.eps.entsoftarch.webmemoryapi.steps;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.springframework.test.web.servlet.request.RequestPostProcessor;


public class AuthenticationStepDefs {

    private static String currentUsername;
    private static String currentPassword;

    @Before
    public void setup() {
        // Clear authentication credentials at the start of every test.
        AuthenticationStepDefs.currentPassword = "";
        AuthenticationStepDefs.currentUsername = "";
    }

    static RequestPostProcessor authenticate() {
        return currentUsername!=null ? httpBasic(currentUsername, currentPassword) : anonymous();
    }

    @Given("^I login as \"([^\"]*)\" with password \"([^\"]*)\"$")
    public void iLoginAsWithPassword(String username, String password) {
        AuthenticationStepDefs.currentUsername = username;
        AuthenticationStepDefs.currentPassword = password;
    }

    @Given("^I'm not logged in$")
    public void iMNotLoggedIn() {
        currentUsername = currentPassword = null;
    }


}
