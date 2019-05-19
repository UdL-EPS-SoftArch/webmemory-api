package cat.udl.eps.entsoftarch.webmemoryapi.domain;

import java.util.Collection;
import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Player extends User {

	@Length(min=2, max=256)
	public String Name;

	@Value("${Currency:0}")
	public int Currency;

	public int getCurrency(){ return this.Currency; }
	public void setCurrency(int newCurrency){ this.Currency = newCurrency; }

	//Using the escrow variable to determine the changes we'll be doing to the player's currency
	public int escrow;

	public int getEscrow() { return this.escrow; }
	public void setEscrow(int newEscrow) { this.escrow = newEscrow; }

	@Override
	public String getUsername(){return this.Name;}

	@Override
	@Transient
	/**
	 * This function returns a collection of player credentials.
	 * Returns the collection of granted authority for the user.
	 */
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_PLAYER");
	}
}
